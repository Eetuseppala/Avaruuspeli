package avaruuspeli.liikkuvatKuviot;

import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VihollislaivueTest {

    public VihollislaivueTest() {
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

        Vihollislaivue viholliset = new Vihollislaivue();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int xArvo = viholliset.xArvonArpominen();

            if (xArvo < 0 || xArvo > 500) {
                toimii = false;
            }
        }
        assertEquals(toimii, true);
    }

    @Test
    public void yArvotaanOikein() {

        Vihollislaivue viholliset = new Vihollislaivue();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int yArvo = viholliset.xArvonArpominen();

            if (yArvo < 0 || yArvo > 600) {
                toimii = false;
            }
        }
        assertEquals(toimii, true);
    }

    @Test
    public void laivueenXKoordinaattiKelvollinen() {
        Vihollislaivue viholliset = new Vihollislaivue();

        boolean kelvollinen = false;

        if (viholliset.x > 0 && viholliset.x < 500) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void laivueenYKoordinaattiKelvollinen() {
        Vihollislaivue viholliset = new Vihollislaivue();

        boolean kelvollinen = false;

        if (viholliset.y > 0 && viholliset.y < 600) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void vihollisaluksetLiikkuvatAlas() {
        Vihollislaivue viholliset = new Vihollislaivue();

        assertEquals(viholliset.ySuunta, 1);
    }

    @Test
    public void liikuMetodiToimii() {
        Vihollislaivue viholliset = new Vihollislaivue();

        Rectangle asteroidi = new Rectangle(200, 200, 15, 10);

        viholliset.viholliset.add(asteroidi);

        int entinenY = asteroidi.y;

        viholliset.liiku();

        assertEquals(asteroidi.y, entinenY + 1);
    }
}
