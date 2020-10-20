import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Wedstrijd {

    private Vlucht vlucht;
    private List<Duif> korf = new ArrayList<>();
    private HashMap<Double, Duif> wedstrijdUitslag = new HashMap<Double, Duif>();
    private LocalDateTime now;
    private Losplaatsen losplaatsen;
    private Locatie losLocatie;

    public Wedstrijd(Vlucht vlucht, Losplaatsen losPlaatsen) {
        this.vlucht = vlucht;
        this.losplaatsen = losPlaatsen;

        setCoordinates(losPlaatsen);
    }

    private void setCoordinates(Losplaatsen losPlaatsen) {

        switch (losPlaatsen){

            case PARIJS:    losLocatie = new Locatie(Losplaatsen.PARIJS.toString(),
                    2.3522219,  48.856614);

            case BARCELONA: losLocatie = new Locatie( Losplaatsen.BARCELONA.toString(),
                    2.1734035, 41.3850639);

            case GENT:      losLocatie = new Locatie(Losplaatsen.GENT.toString(),
                    3.7174243, 51.0543422);
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.now = LocalDateTime.now();

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
            System.out.println("Snelheid is: " + (Math.round(snelheid * 100.0) / 100.0));
            wedstrijdUitslag.put((Math.round(snelheid * 100.0) / 100.0), duif);

        }



        //sort hashmap
        TreeMap<Double, Duif> sorted = new TreeMap<>();
        sorted.putAll(wedstrijdUitslag);

        // moet desc zijn
//        MyComparator comp = new MyComparator(wedstrijdUitslag);
//
        Map<Double, Duif> newMap = new TreeMap(sorted);
        newMap.putAll(wedstrijdUitslag);

        //print hashmap
        for (Double name : newMap.keySet()) {
            String key = name.toString();
            String value = newMap.get(name).toString();
            System.out.println(key + " " + value);
        }
        //change key in hash to position
        // iterator gebruiken om een i te kunnen gebruiken?
//        for (Duif duif : sorted.values()) {
//            ChipRing chipring;
//            chipring = duif.getChipRing();
//            int behaaldePunten = chipring.getAfstandGevlogen() + sorted.size() + 1 - (i + 1);
//        }

    }



    public double berekenAfstand(Locatie losLocatie, Locatie aankomstLocatie){

        double lat1 = losLocatie.getBreedteGraad();
        double lat2 = aankomstLocatie.getBreedteGraad();
        double lon1 = losLocatie.getLengteGraad();
        double lon2 = aankomstLocatie.getLengteGraad();
        double el1 = 1;
        double el2 = 1;

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

