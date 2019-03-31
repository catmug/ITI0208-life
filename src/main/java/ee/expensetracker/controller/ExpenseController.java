package ee.expensetracker.controller;

import ee.expensetracker.dao.CategoryDao;
import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.dao.UserDao;
import ee.expensetracker.dto.ExpenseDto;
import ee.expensetracker.model.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Some methods and guidelines taken from:
 * https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 * 06.03.2019 10:32
 */
@RestController
public class ExpenseController {

    @Autowired
    private ExpenseDao dao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserDao userDao;

    @PostMapping("expense")
    public void save(@RequestBody ExpenseDto expense) throws ParseException {
        dao.save(convertToModel(expense));
    }

    @GetMapping("expense")
    public List<ExpenseDto> getExpenses() {
        return dao.findCategoriesByUserId().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("expense/{id}")
    public List<ExpenseDto> getExpenses(@PathVariable("id") Long id) {
        return dao.findAllByCategory(id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("expense/edit")
    public void edit(@RequestBody ExpenseDto expense) throws ParseException {
        dao.edit(convertToModel(expense));
    }

    @DeleteMapping("expense/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        dao.deleteById(id);
    }

    //todo setting date should be in a service
    private Expense convertToModel(ExpenseDto expenseDto) throws ParseException {
        LocalDateTime ldt = LocalDateTime.now();
        expenseDto.setInsertionDate(ldt);

        Expense expense = modelMapper.map(expenseDto, Expense.class);
        expense.setInsertTime(expenseDto.getInsertionDateConverted());
        expense.setCategory(categoryDao.getCategoryById(expenseDto.getCategory()));
        return expense;
    }

    private ExpenseDto convertToDto(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        expenseDto.setInsertionDate(expense.getInsertTime());
        return expenseDto;
    }
}