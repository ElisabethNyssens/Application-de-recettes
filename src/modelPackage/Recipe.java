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
        this.title = title;
        this.creationDate = creationDate;
        this.isHot = isHot;
        this.isSweet = isSweet;
        this.isSalty = isSalty;
        this.budget = budget;
        this.preparationTime = preparationTime;
        this.nbPersons = nbPersons;
        this.season = season;
        this.author = author;
        this.regime = regime;
        this.category = category;
    }

    public Recipe(String title, GregorianCalendar creationDate, boolean isHot, boolean isSweet,
                  boolean isSalty, String budget, String preparationTime,
                  int nbPersons, String author, String category) {
        this(title,creationDate,isHot,isSweet,isSalty,budget,preparationTime,nbPersons,null,author,null,category);
    }

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

    public void setSeason(String season) {
        this.season = season;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }
}
