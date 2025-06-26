package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = mapRow(rs);
                categories.add(category);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String query = "SELECT * FROM categories WHERE category_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, categoryId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String query =
                "INSERT INTO categories(category_id, name, description) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, category.getCategoryId());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();

                if (keys.next()) {
                    int categoryId = keys.getInt(1);

                    return getById(categoryId);
                }
            }
            return category;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String query =
                "UPDATE categories SET name = ?, description = ? " +
                "WHERE category_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, categoryId);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String query = "DELETE FROM categories WHERE category_id = ?";

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, categoryId);

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
