package ee.expensetracker.controller;

import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseDao dao;

    @PostMapping("expense")
    public void save(@RequestBody Expense expense) {
        dao.save(expense);
    }

    @GetMapping("expense")
    public List<Expense> getExpenses() {
        return dao.findAll();
    }

    @PostMapping("expense/edit")
    public void edit(@RequestBody Expense expense) {
        dao.edit(expense);
    }

    @DeleteMapping("expense/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        dao.deleteById(id);
    }
}