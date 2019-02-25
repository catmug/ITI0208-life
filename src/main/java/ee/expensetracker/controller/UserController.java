package ee.expensetracker.controller;

import ee.expensetracker.dao.UserDao;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDao dao;

    @GetMapping("user")
    public User getExpenses() {
        return dao.getUsers().get(0);
    }

}
