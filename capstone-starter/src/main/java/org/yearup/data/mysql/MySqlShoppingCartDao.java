package org.yearup.data.mysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String query =
                "SELECT * " +
                "FROM shopping_cart sc " +
                "JOIN products p ON p.product_id = sc.product_id " +
                "WHERE user_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product cartProduct = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("category_id"),
                        rs.getString("description"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getBoolean("featured"),
                        rs.getString("image_url")
                );

                ShoppingCartItem cartItem = new ShoppingCartItem();
                cartItem.setProduct(cartProduct);
                cartItem.setQuantity(rs.getInt("quantity"));

                shoppingCart.add(cartItem);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return shoppingCart;
    }

    @Override
    public void add(int userId, int productId) {
        ShoppingCart shoppingCart = getByUserId(userId);
        boolean itemInCart = shoppingCart.contains(productId);
        String query;
        int itemCount;

        if (itemInCart) {
            itemCount = shoppingCart.get(productId).getQuantity() + 1;
            query = "UPDATE shopping_cart " +
                    "SET quantity = ? " +
                    "WHERE user_id = ? AND product_id = ?";
        }
        else {
            itemCount = 1;
            query = "INSERT INTO shopping_cart(quantity, user_id, product_id) " +
                    "VALUES (?, ?, ?)";
        }

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, itemCount);
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(int userId, int productId, ShoppingCartItem item) {
        ShoppingCart shoppingCart = getByUserId(userId);
        boolean itemInCart = shoppingCart.contains(productId);
        String query =
                "UPDATE shopping_cart " +
                "SET quantity = ? " +
                "WHERE user_id = ? AND product_id = ?";

        if (!itemInCart) {
            return;
        }

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, item.getQuantity());
            stmt.setInt(2, userId);
            stmt.setInt(3, productId);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void clear(int userId) {
        String query = "DELETE FROM shopping_cart WHERE user_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, userId);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
