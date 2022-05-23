package modelPackage;

public class Menu {
    private String title;
    private String comment;

    public Menu(String title, String comment) {
        setTitle(title);
        setComment(comment);
    }

    public Menu(String title) {
        this(title, null);
    }

    // getters

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    // setters

    public void setComment(String comment) {
        if (comment.length() <= 200) this.comment = comment;
    }

    public void setTitle(String title) {
        if (title.length() <= 100) this.title = title;
    }
}
