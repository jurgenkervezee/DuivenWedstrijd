import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuifTest {

    @Test
    public void checkDuif() {

        EigendomsRing eigendomsRing;

        Duif duif = new Duif(2019, GeslachtDuif.DOFFER, "NL");
        eigendomsRing = duif.getEigendomsRing();
        System.out.println(eigendomsRing.getEigendomsNummer());

    }

}