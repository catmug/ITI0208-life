package ee.expensetracker.dao;


import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class UserDao implements Dao {
    @Autowired
    private JdbcTemplate template;

    public List<User> getUsers() {
        String sql = "select * from user";

         return template.query(sql, (rs, rowNum) -> new User(
                rs.getLong("user_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        ));
    }
}
