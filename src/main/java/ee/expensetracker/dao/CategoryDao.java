package ee.expensetracker.dao;

import ee.expensetracker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Primary
@Repository
public class CategoryDao implements Dao{
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
}
