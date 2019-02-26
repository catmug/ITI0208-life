package ee.expensetracker.dao;

import ee.expensetracker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;


@Primary
@Repository
public class CategoryDao {
    @Autowired
    private JdbcTemplate template;

    public List<Category> getAllCategories() {
        String sql = "select * from category";

        return template.query(sql, (rs, rowNum) -> new Category(
                rs.getLong("category_id"),
                rs.getString("name"),
                rs.getLong("user_id")
        ));
    }

    public Category save(Category category) {


        String sql = "insert into category (category_id, name, user_id) "
                + "values (NEXT VALUE FOR seq_category, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(
                    sql, new String[]{"category_id"});

            System.out.println(ps);

            ps.setString(1, category.getName());
            ps.setLong(2, 1);

            return ps;

        }, holder);

        category.setCategoryId(holder.getKey().longValue());

        return category;
    }

    public void editCategory(Category category) {
        String sql = "UPDATE category SET name = ?" +
                "WHERE category_id = ?";

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(ps);
            ps.setString(1, category.getName());
            ps.setLong(2, category.getCategoryId());
            return ps;
        });
    }
}
