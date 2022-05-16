package modelPackage;

public class Menu {
    private String title;
    private String comment;

    public Menu(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }
}
