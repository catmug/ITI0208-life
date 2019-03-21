package ee.expensetracker.dto;

import ee.expensetracker.model.Category;
import ee.expensetracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpenseDto {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Long expenseId;
    private Long category;
//    private Long user;
    private String insertTime;
    private String comment;
    private double amount;

    public LocalDateTime getInsertionDateConverted() throws ParseException {
        return LocalDateTime.parse(this.insertTime, formatter);
    }

    public void setInsertionDate(LocalDateTime dateTime) {
        this.insertTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
