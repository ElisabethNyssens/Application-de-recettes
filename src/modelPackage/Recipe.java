package modelPackage;

import java.util.GregorianCalendar;

public class Recipe {
    private String title;
    private GregorianCalendar creationDate;
    private boolean isHot;
    private boolean isSweet;
    private boolean isSalty;
    private String budget;
    private String difficulty;
    private String preparationTime;
    private int nbPersons;
  /*  private String season; */

    public Recipe(String title, GregorianCalendar creationDate, boolean isHot, boolean isSweet,
                  boolean isSalty, String budget, String difficulty, String preparationTime,
                  int nbPersons) {
        this.title = title;
        this.creationDate = creationDate;
        this.isHot = isHot;
        this.isSweet = isSweet;
        this.isSalty = isSalty;
        this.budget = budget;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.nbPersons = nbPersons;
        /*this.season = season; */
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

    public String getDifficulty() {
        return difficulty;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

 /*   public String getSeason() {
        return season;
    }*/
}
