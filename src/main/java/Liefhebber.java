import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Liefhebber {

    private String naam;
    private String woonplaats;
    private String eMailAdres;
    private String landAfkorting;
    private boolean contributieBetaald;
    private int behaaldePunten;
    private LocalDateTime now;
    private List<Duif> duifList = new ArrayList<>();
    private List<Rol> rolList = new ArrayList<>();
    private Locatie locatie;

    public Liefhebber(String naam, String woonplaats, String eMailAdres, String landAfkorting, double lengteGraad, double breedteGraad, Rol rol) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        this.eMailAdres = eMailAdres;
        this.landAfkorting = landAfkorting;
        rolList.add(rol);
        locatie = new Locatie(woonplaats,lengteGraad, breedteGraad);
    }

    public void inschrijvenDuif(int geboorteJaar, GeslachtDuif geslachtDuif, String landAfkorting, Wedstrijd wedstrijd) {

        Duif duif = new Duif(geboorteJaar, geslachtDuif, landAfkorting);
        duifList.add(duif);
        wedstrijd.inkorven(duif);
    }

    public void aankomstRegistreren(){
        Random random = new Random();

        for (int i = 0; i < duifList.size(); i++) {
            if(duifList.get(i).getChipRingNummer()> 0){
                int addRandomHours = random.nextInt(24);
                int addRandomMinutes = random.nextInt(60);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                this.now = LocalDateTime.now().plusHours(addRandomHours).plusMinutes(addRandomMinutes);
                duifList.get(i).setAankomstTijdEnLocatie(now, locatie.getLengteGraad(), locatie.getBreedteGraad());
            }
        }
    }

    public void addBehaaldePunten(int punten){
        this.behaaldePunten = behaaldePunten + punten;

    }

    public int getChipNummer() {

        for(Duif p: duifList){
            if(p.getChipRingNummer()>0){
                return p.getChipRingNummer();
            }
        }
        return 0;
    }
}
