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
        setSeason(season);
        setAuthor(author);
        setRegime(regime);
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
        if (budget.equals("Bon marché") || budget.equals("Coût moyen") || budget.equals("Assez cher")) {
            this.budget = budget;
        }
    }

    public void setSeason(String season) {
        if (season.equals("Toute saison") || season.equals("Ete") || season.equals("Automne")
                || season.equals("Printemps") || season.equals("Hiver")) {
            this.season = season;
        }
    }

    public void setNbPersons(int nbPersons) {
        if (nbPersons > 0 && nbPersons <= 50) this.nbPersons = nbPersons;
    }

    public void setPreparationTime(String preparationTime) {
        if (preparationTime.equals("< 30min") || preparationTime.equals("30min >< 1h") || preparationTime.equals("1h >< 2h")
                || preparationTime.equals("2h >< 1j") || preparationTime.equals("> 1j")) {
            this.preparationTime = preparationTime;
        }
    }

    public void setRegime(String regime) {
        if (regime.equals("Pescetarien") || regime.equals("Vegetarien")
                || regime.equals("Vegan") || regime.equals("Sans gluten")) {
            this.regime = regime;
        }
    }

    public void setCategory(String category) {
        if (category.equals("Accompagnement") || category.equals("Amuse-gueule") || category.equals("Boisson")
                || category.equals("Dessert") || category.equals("Entree") || category.equals("Plat principal")
                || category.equals("Sauce") || category.equals("Snack") || category.equals("Soupe")) {
            this.category = category;
        }
    }

    public void setAuthor(String author) {
        if (author.length() <= 15) this.author = author;
    }
}
