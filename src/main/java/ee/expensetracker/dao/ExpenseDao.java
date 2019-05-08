package ee.expensetracker.dao;

import ee.expensetracker.model.Expense;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
        User user = em.find(User.class, userDao.getLoggedInUserId());
        expense.setUser(user);
        Expense temp = findById(expense.getExpenseId());
        LocalDateTime time = temp.getInsertTime();
        expense.setInsertTime(time);
        if (expense.getExpenseId() == null) {
            em.persist(expense);
        } else {
            em.merge(expense);
        }
    }

    public List<Expense> findCustomTimeExpenses(String start, String end) {
        long id = userDao.getLoggedInUserId();
        LocalDateTime startLdt = LocalDateTime.ofInstant(Instant.parse(start), ZoneOffset.UTC);
        LocalDateTime endLdt = LocalDateTime.ofInstant(Instant.parse(end), ZoneOffset.UTC);
        return em.createQuery(
                "select e from Expense e where e.user.userId = :id and e.insertTime > :start and e.insertTime < :end",
                Expense.class)
                .setParameter("id", id)
                .setParameter("start", startLdt)
                .setParameter("end", endLdt)
                .getResultList();
    }

    public Expense findById(Long id) {
        return em.find(Expense.class, id);
    }
}
