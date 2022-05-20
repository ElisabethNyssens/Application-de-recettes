package modelPackage;

public class RecipeWithIngred {
    private String title;
    private String author;
    private String category;
    private String budget;
    private String preparationTime;

    public RecipeWithIngred(String title, String author, String category, String budget, String preparationTime) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.budget = budget;
        this.preparationTime = preparationTime;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getBudget() {
        return budget;
    }

    public String getPreparationTime() {
        return preparationTime;
    }
}
