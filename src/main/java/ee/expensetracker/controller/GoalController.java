package ee.expensetracker.controller;

import ee.expensetracker.dao.UserDao;
import ee.expensetracker.dto.GoalDto;
import ee.expensetracker.model.Goal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoalController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/goal")
    public void saveGoal(@RequestBody GoalDto goal) {
        System.out.println(goal);
        userDao.addGoal(modelMapper.map(goal, Goal.class));
    }


}
