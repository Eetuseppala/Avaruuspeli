package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AvaruusalusTest {

    private Asteroidikentta ak;

    public AvaruusalusTest() {
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
    public void suunnanVaihtaminenVasemmalleOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);

        alus.setYSuunta(-1);

        assertEquals(alus.ySuunta, -1);
    }

    @Test
    public void suunnanVaihtaminenOikealleOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);

        alus.setYSuunta(1);

        assertEquals(alus.ySuunta, 1);
    }

    @Test
    public void ylosMeneminenOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);

        alus.setXSuunta(-1);

        assertEquals(alus.xSuunta, -1);
    }

    @Test
    public void alasMeneminenOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);

        alus.setXSuunta(1);

        assertEquals(alus.xSuunta, 1);
    }

    @Test
    public void alusAloittaaOikeastaKohdasta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);
        assertEquals(alus.x, 250);
        assertEquals(alus.y, 300);
    }

    @Test
    public void alusPysyyRajojenSisapuolella() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);

        alus.y = 25;

        alus.ySuunta = -1;

        alus.liiku();

        assertEquals(alus.y, 25);
    }

    @Test
    public void ammuksetLiikkuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue();
        Avaruusalus alus = new Avaruusalus(250, 300, asteroidikentta, vihollislaivue);
        
        alus.ammukset.add(new Rectangle(300, 300, 3, 5));
        
        alus.liikutaAmmuksia();
        
        assertEquals(alus.getAmmuksenY(0), 298);
    }
}
