package ee.expensetracker.controller;

import ee.expensetracker.dao.UserDao;
import ee.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userdao;

    @Autowired
    private PasswordEncoder pw;

    @PostMapping("register")
    public String register(@RequestBody User u){
        u.setPassword(pw.encode(u.getPassword()));
        return userdao.save(u);
    }

    @GetMapping("user")
    public List<User> allUsers() {
        return userdao.findAll();
    }

    @GetMapping("user/id/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userdao.getUserById(id);
    }

    @GetMapping("user/exists/{username}")
    public boolean doesUserExist(@PathVariable("username") String username) {
        return userdao.doesUsernameExist(username);
    }

    @GetMapping("user/{username}")
    public User getById(@PathVariable("username") String username) {
        return userdao.findUserByUsername(username);
    }

}
