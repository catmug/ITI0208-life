package ee.expensetracker.dao;

import ee.expensetracker.model.Expense;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Primary
@Repository
public class ExpenseDao implements Dao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(Expense expense) {
        User user = em.find(User.class, userDao.getLoggedInUserId());
        expense.setUser(user);
        LocalDateTime ldt = LocalDateTime.now();
        expense.setInsertTime(ldt);

        if (expense.getExpenseId() == null) {
            em.persist(expense);
        } else {
            em.merge(expense);
        }
    }

    public List<Expense> findCategoriesByUserId() {
        long id = userDao.getLoggedInUserId();
        return em.createQuery(
                "select e from Expense e where e.user.userId = :id",
                Expense.class).setParameter("id", id).getResultList();
    }

    public List<Expense> findAllByCategory(Long id) {
        Query query = em.createQuery("select e from Expense e where e.category.categoryId=:id", Expense.class);
        query.setParameter("id", id);
        return query.getResultList();

    }

    @Transactional
    public void deleteById(Long id) {
        Expense expense = em.find(Expense.class, id);

        if (expense != null) em.remove(expense);
    }

    @Transactional
    public void edit(Expense expense) {
        save(expense);
    }

    public List<Expense> findAllByPeriod() {

        return Collections.emptyList();
    }

    public List<Expense> findLastWeekExpenses() {
        long id = userDao.getLoggedInUserId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minusDays(7L);
        return em.createQuery(
                "select e from Expense e where e.user.userId = :id and e.insertTime < :start and e.insertTime > :end",
                Expense.class)
                .setParameter("id", id)
                .setParameter("start", now)
                .setParameter("end", weekAgo)
                .getResultList();
    }

    public List<Expense> findLastMonthExpenses() {
        long id = userDao.getLoggedInUserId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minusMonths(1L);
        return em.createQuery(
                "select e from Expense e where e.user.userId = :id and e.insertTime < :start and e.insertTime > :end",
                Expense.class)
                .setParameter("id", id)
                .setParameter("start", now)
                .setParameter("end", weekAgo)
                .getResultList();
    }

    public List<Expense> findCustomTimeExpenses(List<String> dates) {
        long id = userDao.getLoggedInUserId();
        System.out.println(dates);
        LocalDateTime start = LocalDateTime.parse(dates.get(0));
        LocalDateTime end = LocalDateTime.parse(dates.get(1));
        List<Expense> foo =  em.createQuery(
                "select e from Expense e where e.user.userId = :id and e.insertTime < :start and e.insertTime > :end",
                Expense.class)
                .setParameter("id", id)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        return foo;
    }
}
