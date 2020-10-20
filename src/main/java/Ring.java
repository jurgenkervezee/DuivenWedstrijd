import java.util.Random;

public abstract class Ring {
    private int ringNummer;

     public Ring() {
        Random random = new Random();
        this.ringNummer = random.nextInt(1000);
    }
}
