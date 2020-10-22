import java.time.LocalDateTime;
import java.util.Random;

public class ChipRing extends Ring{

    private int ringNummer;
    private LocalDateTime lossenTijd;
    private LocalDateTime aankomsTijd;
    private Locatie locatieLosplaats;
    private Locatie locatieAankomst;
    private double afstandGevlogen;
    private double snelheid;

    public ChipRing() {
        Random random = new Random();
        ringNummer = random.nextInt(1000);
    }

    public void addLosplaats(double lengteGraad, double breedteGraad) {
        locatieLosplaats = new Locatie("Losplaats", lengteGraad, breedteGraad);

    }

    public void setAankomsTijdEnLocatie(LocalDateTime aankomsTijd, double lengteGraadAankomst, double breedteGraadAankomst) {
        this.aankomsTijd = aankomsTijd;
        locatieAankomst = new Locatie("AankomstLocatie", lengteGraadAankomst, breedteGraadAankomst);

    }
    public void setLossenTijd(LocalDateTime lossenTijd) {
        this.lossenTijd = lossenTijd;
    }

    public int getRingNummer() {
        return ringNummer;
    }

    public double getLengteGraadLosPlaats() {
        return locatieLosplaats.getLengteGraad();
    }

    public double getBreedteGraadLosPlaats() {
        return locatieLosplaats.getBreedteGraad();
    }

    public double getLengteGraadAankomst() {
        return locatieAankomst.getLengteGraad();
    }

    public double getBreedteGraadAankomst() {
        return locatieAankomst.getBreedteGraad();
    }

    public LocalDateTime getLossenTijd() {
        return lossenTijd;
    }

    public LocalDateTime getAankomsTijd() {
        return aankomsTijd;
    }

    public Locatie getLocatieLosplaats() {
        return locatieLosplaats;
    }

    public Locatie getLocatieAankomst() {
        return locatieAankomst;
    }

    public void setAfstand(double afstandGevlogen) {
        this.afstandGevlogen = afstandGevlogen;
    }

    public double getAfstandGevlogen() {
        return afstandGevlogen;
    }

    public double getSnelheid() {
        return snelheid;
    }

    public void setSnelheid(double snelheid) {
        this.snelheid = snelheid;
    }

    @Override
    public String toString() {
        return "ChipRing{" +
                "snelheid=" + snelheid +
                '}';
    }
}
