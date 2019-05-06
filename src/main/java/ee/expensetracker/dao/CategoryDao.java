package ee.expensetracker.dao;

import ee.expensetracker.model.Category;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


@Primary
@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserDao userDao;

    public List<Category> getAllCategories() {
        return em.createQuery(
                "select c from Category c",
                Category.class).getResultList();
    }

    public List<Category> getCategoriesByUserId() {
        long id = userDao.getLoggedInUserId();
        return em.createQuery(
                "select c from Category c where c.user.userId = :userId",
                Category.class).setParameter("userId", id).getResultList();
    }

    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    @Transactional
    public String save(Category category) {
        User user = em.find(User.class, userDao.getLoggedInUserId());
        category.setUser(user);
        if (findByName(category.getName()).size() == 0) {
            if (category.getCategoryId() == null) {
                em.persist(category);
            } else {
                em.merge(category);
            }
            return "Category " + category.getName() + " added!";
        } else {
            return "This category name already exists!";
        }

    }

    @Transactional
    public void rename(@NotNull Category category) {
        if (category.getCategoryId() != null && category.getName() != null) {
            Category c = em.find(Category.class, category.getCategoryId());
            c.setName(category.getName());
        }
    }

    public List<Category> findByName(String name) {
        return em.createQuery(
                "select c from Category c where c.name = :categoryName",
                Category.class)
                .setParameter("categoryName", name).getResultList();
    }
}
