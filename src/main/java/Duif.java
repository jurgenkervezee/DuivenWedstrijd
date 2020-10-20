import java.time.LocalDateTime;
import java.util.Random;

public class Duif {

    private EigendomsRing eigendomsRing;
    private ChipRing chipRing;
    private int geboorteJaar;
    private GeslachtDuif geslachtDuif;
    private String landAfkorting;
    private int behaaldePunten;
    private int chipRingNummer;

    public Duif(int geboorteJaar, GeslachtDuif geslachtDuif, String landAfkorting) {
        this.geboorteJaar = geboorteJaar;
        this.geslachtDuif = geslachtDuif;
        this.landAfkorting = landAfkorting;
        eigendomsRing = new EigendomsRing(landAfkorting, geboorteJaar);
    }

    public EigendomsRing getEigendomsRing() {
        return eigendomsRing;
    }

    public int getGeboorteJaar() {
        return geboorteJaar;
    }

    public void addChipRing(ChipRing chipRing) {
        this.chipRing = chipRing;

    }

    public void setLossenTijd(LocalDateTime time) {
        chipRing.setLossenTijd(time);
    }

    public void setAankomstTijdEnLocatie(LocalDateTime time, double lengtegraad, double breedteGraad) {
    chipRing.setAankomsTijdEnLocatie(time, lengtegraad, breedteGraad);
    }

    public int getChipRingNummer(){
        this.chipRingNummer  = chipRing.getRingNummer();
        return chipRingNummer;
    }

    public ChipRing getChipRing() {
        return chipRing;
    }

    public int getBehaaldePunten() {
        return behaaldePunten;
    }

    @Override
    public String toString() {
        return eigendomsRing.getEigendomsNummer();

    }

    public void addBehaaldePunten(int punten) {
        this.behaaldePunten = behaaldePunten + punten;
    }
}
