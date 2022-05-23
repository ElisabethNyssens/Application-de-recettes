package modelPackage;

import java.util.GregorianCalendar;

public class Recipe {
    private String title;
    private GregorianCalendar creationDate;
    private boolean isHot;
    private boolean isSweet;
    private boolean isSalty;
    private String budget;
    private String preparationTime;
    private int nbPersons;
    private String season;
    private String author;
    private String regime;
    private String category;

    public Recipe(String title, GregorianCalendar creationDate, boolean isHot, boolean isSweet,
                  boolean isSalty, String budget, String preparationTime,
                  int nbPersons, String season, String author, String regime, String category) {
        setTitle(title);
        this.creationDate = creationDate;
        this.isHot = isHot;
        this.isSweet = isSweet;
        this.isSalty = isSalty;
        setBudget(budget);
        setPreparationTime(preparationTime);
        setNbPersons(nbPersons);
        this.season = season;
        setAuthor(author);
        this.regime = regime;
        setCategory(category);
    }

    public Recipe(String title, GregorianCalendar creationDate, boolean isHot, boolean isSweet,
                  boolean isSalty, String budget, String preparationTime,
                  int nbPersons, String author, String category) {
        this(title,creationDate,isHot,isSweet,isSalty,budget,preparationTime,nbPersons,null,author,null,category);
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public boolean isHot() {
        return isHot;
    }

    public boolean isSalty() {
        return isSalty;
    }

    public boolean isSweet() {
        return isSweet;
    }

    public int getNbPersons() {
        return nbPersons;
    }

    public String getBudget() {
        return budget;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public String getSeason() {
        return season;
    }

    public String getAuthor() {
        return author;
    }

    public String getRegime() {
        return regime;
    }

    public String getCategory() {
        return category;
    }

    // Setters

    public void setTitle(String title) {
        if (title.length() <= 100 && title.length() >= 3) this.title = title;
    }

    public void setBudget(String budget) {
        if (budget.length() <= 15) {
            this.budget = budget;
        }
    }

    public void setSeason(String season) {
        if (season.length() <= 15) {
            this.season = season;
        }
    }

    public void setNbPersons(int nbPersons) {
        if (nbPersons > 0 && nbPersons <= 50) this.nbPersons = nbPersons;
    }

    public void setPreparationTime(String preparationTime) {
        if (preparationTime.length() <= 15) {
            this.preparationTime = preparationTime;
        }
    }

    public void setRegime(String regime) {
        if (regime.length() <= 20) {
            this.regime = regime;
        }
    }

    public void setCategory(String category) {
        if (category.length() <= 20) {
            this.category = category;
        }
    }

    public void setAuthor(String author) {
        if (author.length() <= 15) this.author = author;
    }
}
