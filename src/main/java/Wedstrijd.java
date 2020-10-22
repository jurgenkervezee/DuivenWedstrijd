import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Wedstrijd {

    private Vlucht vlucht;
    private List<Duif> korf = new ArrayList<>();
    private HashMap<Double, Duif> wedstrijdUitslag = new HashMap<Double, Duif>();
    private Locatie losLocatie;

    public Wedstrijd(Vlucht vlucht, Losplaatsen losPlaatsen) {
        this.vlucht = vlucht;
        setCoordinates(losPlaatsen);
    }

    private void setCoordinates(Losplaatsen losPlaatsen) {

        switch (losPlaatsen){

            case PARIJS:    losLocatie = new Locatie(Losplaatsen.PARIJS.toString(),
                    2.3522219,  48.856614);
                break;

            case BARCELONA: losLocatie = new Locatie( Losplaatsen.BARCELONA.toString(),
                    2.1734035, 41.3850639);
                break;

            case GENT:      losLocatie = new Locatie(Losplaatsen.GENT.toString(),
                    3.7174243, 51.0543422);
                break;
        }
    }

    public void inkorven(Duif duif) {

        if (duif.getGeboorteJaar() == 2020 && (vlucht != Vlucht.JONGEDUIVEN)) {
            System.out.println("De duif is te jong");

        } else if (duif.getGeboorteJaar() < 2020 && vlucht == Vlucht.JONGEDUIVEN) {
            System.out.println("Duif is te oud voor de jonge duiven fond");

        } else {
            ChipRing chipRing = new ChipRing();
            chipRing.addLosplaats(losLocatie.getLengteGraad(), losLocatie.getBreedteGraad());
            duif.addChipRing(chipRing);
            korf.add(duif);
        }
    }

    public void lossingVoorWedstrijd(){
        LocalDateTime now = LocalDateTime.now();

        for (Duif p: korf){
            p.setLossenTijd(now);
        }
    }

    public void berekenWedstrijdResultaat(){

        for(Duif duif: korf){

            ChipRing chipRing;
            chipRing = duif.getChipRing();

            //bereken de afstand
            double afstand = berekenAfstand(chipRing.getLocatieLosplaats(), chipRing.getLocatieAankomst());
            chipRing.setAfstand(afstand);

            //bereken de tijd
            LocalDateTime localDateTime;
            LocalDateTime localDateTime1;
            localDateTime = chipRing.getLossenTijd();
            localDateTime1 = chipRing.getAankomsTijd();
            long minutes = ChronoUnit.MINUTES.between(localDateTime, localDateTime1);

            //bereken de snelheid
            double snelheid = afstand / (minutes / 60);
             chipRing.setSnelheid((Math.round(snelheid * 100.0) / 100.0));

        }

        System.out.println(korf);

        korf.sort(new Comparator<Duif>() {
            @Override
            public int compare(Duif o1, Duif o2) {
                return Double.compare(o2.getChipRing().getSnelheid(), o1.getChipRing().getSnelheid());
            }
        });

        System.out.println("\n" + korf);

        //maak een loop waarmee je door de korf loopt en de punten toekent
        // Aantal-kilometers + Aantal-Duiven-die-meededen + 1 – positie

    }



    public double berekenAfstand(Locatie losLocatie, Locatie aankomstLocatie){

        double lat1 = losLocatie.getBreedteGraad();
        double lat2 = aankomstLocatie.getBreedteGraad();
        double lon1 = losLocatie.getLengteGraad();
        double lon2 = aankomstLocatie.getLengteGraad();
        double el1 = 0.0;
        double el2 = 0.0;

            final int R = 6371; // Radius of the earth

            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double afstand = R * c;

            double height = el1 - el2;

            afstand = Math.pow(afstand, 2) + Math.pow(height, 2);

            return Math.round(Math.sqrt(afstand) * 100.0) / 100.0;

    }
}

