package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrderDao;
import org.yearup.models.*;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao {
    @Autowired
    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Order getByUserId(int id) {
        Order order = new Order();
        String query =
                "SELECT * " +
                "FROM orders o " +
                "JOIN shopping_cart sc ON sc.user_id = o.user_id " +
                "JOIN products p ON p.product_id = sc.product_id " +
                "WHERE o.user_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product orderProduct = new Product(
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

                OrderLineItem orderItem = new OrderLineItem();
                orderItem.setProduct(orderProduct);
                orderItem.setQuantity(rs.getInt("quantity"));

                order.add(orderItem);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return order;
    }

    @Override
    public Order create(int id, Profile profile) {
        Order order = getByUserId(id);
        String query =
                "INSERT INTO orders(user_id, date, address, city, state, zip, shipping_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setString(3, profile.getAddress());
            stmt.setString(4, profile.getCity());
            stmt.setString(5, profile.getState());
            stmt.setString(6, profile.getZip());
            stmt.setBigDecimal(7, order.getTotal());

            stmt.executeUpdate();

            return order;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
