package modelPackage;

public class SeasonRecipe {
    private String title;
    private String author;
    private String category;
    private String season;

    public SeasonRecipe(String title, String author, String category, String season) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.season = season;
    }

    public SeasonRecipe(String title, String author, String category) {
        this(title,author,category,null);
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
