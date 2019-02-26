package ee.expensetracker.dao;

import ee.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Primary
@Repository
public class ExpenseDao implements Dao {

    @Autowired
    private JdbcTemplate template;

    public Expense save(Expense expense) {

        LocalDateTime ldt = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(ldt);


        String sql = "insert into expense (expense_id, category_id, user_id, insertion_time, comment, amount) "
                + "values (NEXT VALUE FOR seq_user, ?, ?, ? , ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(
                    sql, new String[]{"expense_id"});

            System.out.println(ps);

            ps.setLong(1, expense.getCategory());
            ps.setLong(2, 1);
            ps.setTimestamp(3, now);
            ps.setString(4, expense.getComment());
            ps.setDouble(5, expense.getAmount());

            return ps;

        }, holder);

        expense.setExpenseId(holder.getKey().longValue());

        return expense;
    }

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

    public void deleteById(Long id) {
        String sql = "delete from expense where expense_id = ?";

        template.update(sql, id);
    }

    public void changeExpense(Expense expense) {
        Long tempId = expense.getExpenseId();
        String sql = "SELECT expense_id, category_id, user_id, insertion_time" +
                ", comment, amount FROM expense " +
                "WHERE expense_id=tempId";

    }
}
