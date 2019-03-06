package ee.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Expense {

    private Long expenseId;
    private Long category;
    private Long user;
    private LocalDateTime insertTime;
    private String comment;
    private double amount;
}
