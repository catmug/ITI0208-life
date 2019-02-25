package ee.expensetracker.controller;

import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseDao dao;

    @PostMapping("expense")
    public String save(@RequestBody Expense expense) {
        dao.save(expense);
        return "woop woop";
    }

    @GetMapping("expense")
    public List<Expense> getExpenses() {
        return dao.findAll();
    }

}