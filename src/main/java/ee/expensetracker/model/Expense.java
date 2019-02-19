package ee.expensetracker.model;

import java.sql.Timestamp;

public class Expense {

    private Long expenseId;
    private Long category;
    private Long user;
    private Timestamp insertTime;
    private String comment;
    private double amount;

    Expense() {

    }

    public Expense(Long expenseId, Long category, Long user, Timestamp insertTime, String comment, double amount) {
        this.expenseId = expenseId;
        this.category = category;
        this.user = user;
        this.insertTime = insertTime;
        this.comment = comment;
        this.amount = amount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public Long getCategory() {
        return category;
    }

    public Long getUser() {
        return user;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public String getComment() {
        return comment;
    }

    public double getAmount() {
        return amount;
    }
}
