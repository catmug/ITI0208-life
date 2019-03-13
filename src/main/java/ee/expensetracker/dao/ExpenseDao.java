package ee.expensetracker.dao;

import ee.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Primary
@Repository
public class ExpenseDao implements Dao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Expense expense) {
        if (expense.getExpenseId() == null) {
            em.persist(expense);
        } else {
            em.merge(expense);
        }
    }

    public List<Expense> findAll() {
        return em.createQuery(
                "select e from Expense e",
                Expense.class).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Expense expense = em.find(Expense.class, id);

        if (expense != null) em.remove(expense);
    }

    @Transactional
    public void changeExpense(Expense expense) {
        if (expense != null) {
            save(expense);
        }
    }

    public void edit(Expense expense) {

    }
//    @Autowired
//    private JdbcTemplate template;
//
//    public Expense save(Expense expense) {
//
//        String sql = "insert into expense (expense_id, category_id, user_id, insertion_time, comment, amount) "
//                + "values (NEXT VALUE FOR seq_user, ?, ?, ? , ?, ?)";
//
//        GeneratedKeyHolder holder = new GeneratedKeyHolder();
//
//        template.update(conn -> {
//
//            PreparedStatement ps = conn.prepareStatement(
//                    sql, new String[]{"expense_id"});
//
//            System.out.println(ps);
//
//            ps.setLong(1, expense.getCategory());
//            ps.setLong(2, 1);
//            ps.setTimestamp(3, Timestamp.valueOf(expense.getInsertTime()));
//            ps.setString(4, expense.getComment());
//            ps.setDouble(5, expense.getAmount());
//
//            return ps;
//
//        }, holder);
//
//        expense.setExpenseId(holder.getKey().longValue());
//
//        return expense;
//    }
//
//    public List<Expense> findAll() {
//        String sql = "select * from expense";
//
//        return template.query(sql, (rs, rowNum) -> new Expense(
//                rs.getLong("expense_id"),
//                rs.getLong("category_id"),
//                rs.getLong("user_id"),
//                rs.getTimestamp("insertion_time").toLocalDateTime(),
//                rs.getString("comment"),
//                rs.getDouble("amount")));
//    }
//
//    public void deleteById(Long id) {
//        String sql = "delete from expense where expense_id = ?";
//
//        template.update(sql, id);
//    }
//
//    public void changeExpense(Expense expense) {
//        Long tempId = expense.getExpenseId();
//        String sql = "SELECT expense_id, category_id, user_id, insertion_time" +
//                ", comment, amount FROM expense " +
//                "WHERE expense_id=tempId";
//
//    }
//
//    public void edit(Expense expense) {
//        String sql = "UPDATE expense SET category_id = ?, comment = ?, amount = ? WHERE expense_id = ?";
//        System.out.println("i got here");
//        template.update(conn -> {
//
//            PreparedStatement ps = conn.prepareStatement(
//                    sql);
//
//            System.out.println(ps);
//
//            ps.setLong(1, expense.getCategory());
//            ps.setString(2, expense.getComment());
//            ps.setDouble(3, expense.getAmount());
//            ps.setLong(4, expense.getExpenseId());
//
//            return ps;
//
//        });
//    }
}
