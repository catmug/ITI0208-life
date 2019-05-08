package ee.expensetracker.controller;

import ee.expensetracker.dao.ExpenseDao;
import ee.expensetracker.dao.UserDao;
import ee.expensetracker.dto.ExpenseDto;
import ee.expensetracker.dto.GoalDto;
import ee.expensetracker.model.Expense;
import ee.expensetracker.model.Goal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoalController {
    @Autowired
    private ExpenseDao dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/goal")
    public void saveGoal(@RequestBody GoalDto goal) {
        System.out.println(goal);
        userDao.addGoal(modelMapper.map(goal, Goal.class));
    }

    @GetMapping("/goal")
    public Double getGoal() {
        return userDao.getGoal();
    }
    @GetMapping("/goalleft")
    public Double getGoalleft() {
        List<ExpenseDto> expenseDtos = dao.findCategoriesByUserId().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        Double sum = Double.valueOf(0);

        for (ExpenseDto e :
                expenseDtos) {
            sum += e.getAmount();
        }

        return sum;
    }

    private ExpenseDto convertToDto(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        expenseDto.setInsertionDate(expense.getInsertTime());
        return expenseDto;
    }
}
