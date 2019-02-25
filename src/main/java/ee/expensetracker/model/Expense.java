package ee.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    private Long expenseId;
    private Long category;
    private Long user;
    private Timestamp insertTime;
    private String comment;
    private double amount;
}
