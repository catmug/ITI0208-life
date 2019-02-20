package ee.expensetracker;

import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao dao;


    @GetMapping("category")
    public List<Category> getCategories() {
        return dao.getAllCategories();
    }
}
