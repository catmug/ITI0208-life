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

    public Long getLoggedInUserId() {
        Long id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            id = ((MyUserPrincipal) principal).getUserId();
        }
        return id;
    }

    public void createDefaultCategories(User user) {
        System.out.println("Gonna start creating default categories!");
    }

//    public void createDefaultCategories(User user) {
//        System.out.println("teen default kategooriaid kasutajale " + user);
//        Category default1 = new Category();
//        default1.setName("Riided");
//        default1.setUser(user);
//
//        Category default2 = new Category();
//        default2.setName("Toidukaubad");
//        default2.setUser(user);
//
//        Category default3 = new Category();
//        default3.setName("Meelelahutus");
//        default3.setUser(user);
//
//        Category default4 = new Category();
//        default4.setName("Kodutarbed");
//        default4.setUser(user);
//
//        Category default5 = new Category();
//        default5.setName("Elektroonika");
//        default5.setUser(user);
//
//        System.out.println("tegin default category objektid ära");
//        System.out.println(this.getAllCategories());
//
//        em.persist(default1);
//        em.persist(default2);
//        em.persist(default3);
//        em.persist(default4);
//        em.persist(default5);
//    }
}
