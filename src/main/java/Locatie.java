public class Locatie {

    private String naam;
    private double lengteGraad;
    private double breedteGraad;

    public Locatie(String naam, double lengteGraad, double breedteGraad) {
        this.naam = naam;
        this.lengteGraad = lengteGraad;
        this.breedteGraad = breedteGraad;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getLengteGraad() {
        return lengteGraad;
    }

    public void setLengteGraad(double lengteGraad) {
        this.lengteGraad = lengteGraad;
    }

    public double getBreedteGraad() {
        return breedteGraad;
    }

    public void setBreedteGraad(double breedteGraad) {
        this.breedteGraad = breedteGraad;
    }
}
