package ee.expensetracker;

import ee.expensetracker.dao.TextHsqlDao;
import ee.expensetracker.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private TextHsqlDao dao;

    @PostMapping("expense")
    public void save(@RequestBody Text text) {
        dao.save(text);
    }

    @GetMapping("expense")
    public List<Text> getTexts() {
        return dao.findAll();
    }

}