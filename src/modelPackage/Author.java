package modelPackage;

public class Author {
    private String pseudo;
    private String firstName;
    private String lastName;

    public Author(String pseudo, String firstName, String lastName) {
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
