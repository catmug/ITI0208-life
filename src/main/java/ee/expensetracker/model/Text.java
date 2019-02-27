package ee.expensetracker.model;

public class Text {
    private Long id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    private Double amount;

    public Text(Long id, String text, Double amount) {

        this.id = id;
        this.text = text;
        this.amount = amount;
    }

    public Text() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
