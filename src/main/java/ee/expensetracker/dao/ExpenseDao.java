package ee.expensetracker.dao;

import ee.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Primary
@Repository
public class ExpenseDao implements Dao {

    @Autowired
    private JdbcTemplate template;

    public List<Expense> findAll() {
        String sql = "select * from expense";

        return template.query(sql, (rs, rowNum) -> new Expense(
                rs.getLong("expense_id"),
                rs.getLong("category_id"),
                rs.getLong("user_id"),
                rs.getTimestamp("insertion_time"),
                rs.getString("comment"),
                rs.getDouble("amount")));
    }


}
