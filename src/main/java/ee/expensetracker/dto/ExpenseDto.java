package ee.expensetracker.dto;

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
    private Long categoryId;
    private String insertTime;
    private String comment;
    private double amount;
    private String categoryName;

    public LocalDateTime getInsertionDateConverted() throws ParseException {
        return LocalDateTime.parse(this.insertTime, formatter);
    }

    public void setInsertionDate(LocalDateTime dateTime) {
        this.insertTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
