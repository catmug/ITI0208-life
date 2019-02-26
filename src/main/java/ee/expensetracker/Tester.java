package ee.expensetracker;

import ee.expensetracker.config.DbConfig;
import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.model.Category;
import ee.expensetracker.model.Expense;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
        ExpenseDao bean = ctx.getBean(ExpenseDao.class);
        CategoryDao dao = ctx.getBean(CategoryDao.class);

        System.out.println(dao.getAllCategories());
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Tubakas");
        category.setUserId(1L);
        dao.editCategory(category);
        System.out.println(dao.getAllCategories());

//        System.out.println(bean.findAll());
//        Expense foo = new Expense();
//        foo.setAmount(59D);
//        foo.setComment("testing");
//        foo.setCategory(2L);
//        bean.save(foo);
//        System.out.println(bean.findAll());
//        bean.deleteById(3L);
//        System.out.println(bean.findAll());

    }
}
