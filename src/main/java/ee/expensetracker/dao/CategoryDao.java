package ee.expensetracker.dao;

import ee.expensetracker.model.Category;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.util.List;


@Primary
@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager em;

    public List<Category> getAllCategories() {
        return em.createQuery(
                "select c from Category c",
                Category.class).getResultList();
    }

    @Transactional
    public void save(Category category) {
        User user = em.find(User.class, 1L);
        category.setUser(user);
        if (category.getCategoryId() == null) {
            em.persist(category);
        } else {
            em.merge(category);
        }
    }

    @Transactional
    public void rename(@NotNull Category category) {
        System.out.println("minasiin");
        System.out.println(category);
        if (category.getCategoryId() != null && category.getName() != null) {
            Category c = em.find(Category.class, category.getCategoryId());
            c.setName(category.getName());
            System.out.println("hoopissiin");
        }
    }
}
