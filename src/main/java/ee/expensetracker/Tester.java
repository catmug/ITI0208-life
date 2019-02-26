package ee.expensetracker;

import ee.expensetracker.config.DbConfig;
import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.model.Expense;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
        ExpenseDao bean = ctx.getBean(ExpenseDao.class);

        System.out.println(bean.findAll());
        Expense foo = new Expense();
        foo.setAmount(59D);
        foo.setComment("testing");
        foo.setCategory(2L);
        bean.save(foo);
        System.out.println(bean.findAll());
//        bean.deleteById(3L);
        System.out.println(bean.findAll());
    }
}
