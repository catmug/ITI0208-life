package ee.expensetracker.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Expense {

    private Long expense_id;
    private Long category;
    private Long user;
    private Timestamp insert_time;
    private String comment;
    private double amount;

    public Expense(Long expense_id, Long category, Long user, Timestamp insert_time, String comment, double amount) {
        this.expense_id = expense_id;
        this.category = category;
        this.user = user;
        this.insert_time = insert_time;
        this.comment = comment;
        this.amount = amount;
    }

    public Long getExpense_id() {
        return expense_id;
    }

    public Long getCategory() {
        return category;
    }

    public Long getUser() {
        return user;
    }

    public Timestamp getInsert_time() {
        return insert_time;
    }

    public String getComment() {
        return comment;
    }

    public double getAmount() {
        return amount;
    }
}
