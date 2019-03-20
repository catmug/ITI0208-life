package ee.expensetracker.controller;

import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao dao;


    @GetMapping("category")
    public List<Category> getCategories() {
        return dao.getAllCategories();
    }

    @PostMapping("category")
    public void save(@RequestBody Category category) {
        dao.save(category);
    }

    @PostMapping("category/rename")
    public void rename(@RequestBody Category category) {
        dao.rename(category);
    }

}
