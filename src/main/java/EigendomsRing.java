import java.util.Random;

public class EigendomsRing extends Ring{

    private String eigendomsNummer;

    public EigendomsRing(String landAfkorting, int geboorteJaar) {

        Random random = new Random();
        int number = random.nextInt(1000);

        StringBuilder str = new StringBuilder();
        str.append(geboorteJaar).append("-").append(landAfkorting).append("-").append(number);
        eigendomsNummer = str.toString();
    }

    public String getEigendomsNummer() {
        return eigendomsNummer;
    }
}
