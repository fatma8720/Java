public class Country {
    private String code;
    private String name;
    private String continent;
    private int population;
    private double surfaceArea;
    private double gnp;
    private int capital;

    // Constructor
    public Country(String code, String name, String continent, int population, double surfaceArea, double gnp, int capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.surfaceArea = surfaceArea;
        this.gnp = gnp;
        this.capital = capital;
    }

    // Getter methods
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public double getGnp() {
        return gnp;
    }

    public int getCapital() {
        return capital;
    }

    // Setter methods
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}