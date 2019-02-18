package ee.expensetracker;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ExpenseController {

    @CrossOrigin
    @RequestMapping("/api")
    public String index() {
        return "Asi on pysti ja t;;tab";
    }

}