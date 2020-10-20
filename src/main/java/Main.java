public class Main {
    public static void main(String[] args){

        //wedstrijd starten
        Wedstrijd wedstrijd = new Wedstrijd(Vlucht.DAGFOND, Losplaatsen.PARIJS);

        //deelnemers Henk Intantieren
        Liefhebber henk = new Liefhebber("Henk", "Bussum",
                "email@email.com", "NL",
                5.1663871, 52.2737893, Rol.DUIVENMELKER);

        //Duiven van Henk registreren
        henk.inschrijvenDuif(2019, GeslachtDuif.DUIVIN, "NL", wedstrijd);
        henk.inschrijvenDuif(2018, GeslachtDuif.DUIVIN, "DE", wedstrijd);
        henk.inschrijvenDuif(2018, GeslachtDuif.DOFFER, "NL", wedstrijd);


        //Deelnemer Truusinstantieren
        Liefhebber truus = new Liefhebber("Truus", "almere",
                "Email@Email.nl", "NL",
                5.2647016, 52.3507849, Rol.WEDSTRIJDBEREKENAAR);

        //Duiven van Truus registreren
        truus.inschrijvenDuif(2018, GeslachtDuif.DOFFER, "NL", wedstrijd);
        truus.inschrijvenDuif(2019, GeslachtDuif.DUIVIN, "BE", wedstrijd);

        wedstrijd.lossingVoorWedstrijd();
        henk.aankomstRegistreren();
        truus.aankomstRegistreren();
        wedstrijd.berekenWedstrijdResultaat();

    }
}
