package ee.expensetracker.model;

public class Category {
    private Long categoryId;
    private String name;
    private Long userId;

    public Category() {

    }

    public Category(Long categoryId, String name, Long userId) {
        this.categoryId = categoryId;
        this.name = name;
        this.userId = userId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }
}
