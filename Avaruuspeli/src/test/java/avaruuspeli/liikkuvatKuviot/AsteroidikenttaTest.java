package avaruuspeli.liikkuvatKuviot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsteroidikenttaTest {

    public AsteroidikenttaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void xArvotaanOikein() {

        Asteroidikentta asteroidit = new Asteroidikentta();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int xArvo = asteroidit.xArvonArpominen();

            if (xArvo < 0 || xArvo > 500) {
                toimii = false;
            }
        }
        assertEquals(toimii, true);
    }

    @Test
    public void yArvotaanOikein() {

        Asteroidikentta asteroidit = new Asteroidikentta();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int yArvo = asteroidit.xArvonArpominen();

            if (yArvo < 0 || yArvo > 600) {
                toimii = false;
            }
        }
        assertEquals(toimii, true);
    }

    @Test
    public void asteroidinXKoordinaattiKelvollinen() {
        Asteroidikentta asteroidi = new Asteroidikentta();

        boolean kelvollinen = false;

        if (asteroidi.x > 0 && asteroidi.x < 500) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void asteroidinYKoordinaattiKelvollinen() {
        Asteroidikentta asteroidi = new Asteroidikentta();

        boolean kelvollinen = false;

        if (asteroidi.y > 0 && asteroidi.y < 600) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void asteroiditLiikkuvatAlas() {
        Asteroidikentta asteroidi = new Asteroidikentta();

        assertEquals(asteroidi.ySuunta, 1);
    }

}
