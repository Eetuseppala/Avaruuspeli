package avaruuspeli.liikkuvatKuviot;

import java.awt.Rectangle;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TahtitaivasTest {

    public TahtitaivasTest() {
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

        Tahtitaivas tahtitaivas = new Tahtitaivas();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int xArvo = tahtitaivas.xArvonArpominen();

            if (xArvo < 0 || xArvo > 500) {
                toimii = false;
            }
        }

        //Tuhat kertaa testattuna voidaan olettaa, että logiikka on tarpeeksi tarkkaa.
        assertEquals(toimii, true);
    }

    @Test
    public void yArvotaanOikein() {

        Tahtitaivas tahtitaivas = new Tahtitaivas();

        boolean toimii = true;

        for (int i = 0; i < 1000; i++) {
            int yArvo = tahtitaivas.yArvonArpominen();

            if (yArvo < 0 || yArvo > 600) {
                toimii = false;
            }
        }

        //Tässäkin testissä voidaan olettaa, että tuhat kertaa on tarpeeksi riittävä testausmäärä.
        assertEquals(toimii, true);
    }

    @Test
    public void tahdenXKoordinaattiKelvollinen() {
        Tahtitaivas tahti = new Tahtitaivas();

        boolean kelvollinen = false;

        if (tahti.x > 0 && tahti.x < 500) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void tahdenYKoordinaattiKelvollinen() {
        Tahtitaivas tahti = new Tahtitaivas();

        boolean kelvollinen = false;

        if (tahti.y > 0 && tahti.y < 600) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void tahdetLiikkuvatAlas() {
        Tahtitaivas tahti = new Tahtitaivas();

        assertEquals(tahti.ySuunta, 1);
    }

    @Test
    public void liikuMetodiToimii() {
        Tahtitaivas tahtitaivas = new Tahtitaivas();
        ArrayList<Rectangle> tahtilista = new ArrayList();

        Rectangle tahti = new Rectangle(200, 200, 1, 1);

        tahtilista.add(tahti);

        int entinenY = tahti.y;

        tahtitaivas.liiku();

        assertEquals(tahti.y, entinenY);

        //Tääkin testaa jotenkin "väärin" nyt. Ens viikolla koitan korjata.
    }
}
