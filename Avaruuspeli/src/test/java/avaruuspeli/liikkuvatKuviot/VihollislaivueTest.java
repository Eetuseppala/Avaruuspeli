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
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

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
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

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
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

        boolean kelvollinen = false;

        if (viholliset.x > 0 && viholliset.x < 500) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void laivueenYKoordinaattiKelvollinen() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

        boolean kelvollinen = false;

        if (viholliset.y > 0 && viholliset.y < 600) {
            kelvollinen = true;
        }

        assertEquals(kelvollinen, true);
    }

    @Test
    public void vihollisaluksetLiikkuvatAlas() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

        assertEquals(viholliset.ySuunta, 1);
    }

    @Test
    public void liikuMetodiToimii() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

        Rectangle vihollinen = new Rectangle(200, 200, 15, 10);

        viholliset.viholliset.add(vihollinen);

        int entinenY = vihollinen.y;

        viholliset.liiku();

        assertEquals(vihollinen.y, entinenY + 1);
    }
    
    @Test
    public void vihollisAluksiaOikeaMaara() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);
        
        assertEquals(viholliset.viholliset.size(), 8);
    }
    
    @Test
    public void ampuminenToimii() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);
        
        viholliset.ammu();
        
        assertEquals(viholliset.ammukset.size(), 8);
    }
    
    @Test
    public void ammuksetLiikkuu() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);
        
        viholliset.ammukset.add(new Rectangle(300, 300, 3, 5));
        
        viholliset.liikutaAmmuksia();
        
        assertEquals(viholliset.ammukset.get(0).y, 305);
    }
    
    @Test
    public void tuhoutuminenToimii() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);
        
        Rectangle vihollinen = new Rectangle(200, 200, 15, 10);

        viholliset.viholliset.add(vihollinen);
        
        viholliset.tuhoudu(vihollinen);
        
        assertEquals(vihollinen.height, 0);
        assertEquals(vihollinen.width, 0);
    }
    
    @Test
    public void vihollinenTuhoutuuKunSeOsuuAsteroidiin() {
        Asteroidikentta asteroidit = new Asteroidikentta();
        Vihollislaivue viholliset = new Vihollislaivue(asteroidit);
        
        Rectangle vihollinen = new Rectangle(250, 250, 15, 10);
        
        viholliset.viholliset.add(vihollinen);
        
        asteroidit.asteroidit.add(new Rectangle(250, 250, 30, 30));
        
        viholliset.osumaAsteroidiin();
        
        assertEquals(vihollinen.height, 0);
        assertEquals(vihollinen.width, 0);
    }
}
