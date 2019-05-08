package ee.expensetracker.dao;

import ee.expensetracker.model.Category;
import ee.expensetracker.model.User;
import ee.expensetracker.service.MyUserPrincipal;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Category> getCategoriesByUserId() {
        long id = getLoggedInUserId();
        return em.createQuery(
                "select c from Category c where c.user.userId = :userId",
                Category.class).setParameter("userId", id).getResultList();
    }

    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    @Transactional
    public String save(Category category) {
        User user = em.find(User.class, getLoggedInUserId());
        category.setUser(user);
        if (findByName(category.getName()).size() == 0) {
            if (category.getCategoryId() == null) {
                em.persist(category);
            } else {
                em.merge(category);
            }
            return "Category " + category.getName() + " added!";
        } else {
            return "A category with this name already exists!";
        }
    }

    @Transactional
    public String rename(@NotNull Category category) {
        if (category.getCategoryId() != null && category.getName() != null) {
            Category c = em.find(Category.class, category.getCategoryId());
            System.out.println(category);
            System.out.println(c);
            if (findByName(category.getName()).size() != 0) {
                return "A category with this name already exists!";
            }
            c.setName(category.getName());
            return "Category name has been updated to " + category.getName();
        }
        return "You either left the name blank or forgot to choose a category to rename!";
    }

    public List<Category> findByName(String name) {
        return em.createQuery(
                "select c from Category c where c.name = :categoryName and c.user.userId = :user",
                Category.class)
                .setParameter("categoryName", name)
                .setParameter("user", getLoggedInUserId())
                .getResultList();
    }

    public Long getLoggedInUserId() {
        Long id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            id = ((MyUserPrincipal) principal).getUserId();
        }
        return id;
    }

    public void createDefaultCategories(User user) {
        List<String> categoryNames = Arrays.asList("Riided", "Toidukaubad", "Meelelahutus", "Kodutarbed", "Elektroonika");
        for (String category : categoryNames) {
            em.persist(new Category(null, category, user));
        }
    }
}
