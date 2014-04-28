package avaruuspeli.liikkuvatKuviot;

import avaruuspeli.liikkuvatKuviot.Asteroidikentta;
import avaruuspeli.liikkuvatKuviot.Vihollislaivue;
import avaruuspeli.liikkuvatKuviot.Pelaaja;
import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {

    private Asteroidikentta ak;

    public PelaajaTest() {
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
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.setYSuunta(-1);

        assertEquals(alus.ySuunta, -1);
    }

    @Test
    public void suunnanVaihtaminenOikealleOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.setYSuunta(1);

        assertEquals(alus.ySuunta, 1);
    }

    @Test
    public void ylosMeneminenOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.setXSuunta(-1);

        assertEquals(alus.xSuunta, -1);
    }

    @Test
    public void alasMeneminenOnnistuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.setXSuunta(1);

        assertEquals(alus.xSuunta, 1);
    }

    @Test
    public void alusAloittaaOikeastaKohdasta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);
        assertEquals(alus.x, 250);
        assertEquals(alus.y, 300);
    }

    @Test
    public void alusPysyyRajojenSisapuolella() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.y = 25;

        alus.ySuunta = -1;

        alus.liiku();

        assertEquals(alus.y, 25);
    }

    @Test
    public void ammuksetLiikkuu() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.ammukset.add(new Rectangle(300, 300, 3, 5));

        alus.liikutaAmmuksia();

        assertEquals(alus.ammukset.get(0).y, 298);
    }

    @Test
    public void nakyvistaHavinneitaAmmuksiaEiEnaaPiirreta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        alus.ammukset.add(new Rectangle(300, -201, 3, 5));

        alus.poistaNakyvistaHavinneetAmmukset();

        assertEquals(alus.ammukset.get(0).height, 0);
        assertEquals(alus.ammukset.get(0).width, 0);
    }

    @Test
    public void kunTuhotaanAsteroidiNiinSaadaan10pistetta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        asteroidikentta.asteroidit.add(new Rectangle(250, 250, 30, 30));

        alus.ammukset.add(new Rectangle(250, 250, 3, 5));

        alus.osumaAsteroidiin();

        assertEquals(alus.getPisteet(), 10);
    }

    @Test
    public void kunAmmusOsuuAsteroidiinNiinSeHaviaa() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 300, asteroidikentta, vihollislaivue);

        asteroidikentta.asteroidit.add(new Rectangle(250, 250, 30, 30));

        alus.ammukset.add(new Rectangle(250, 250, 3, 5));

        alus.osumaAsteroidiin();

        assertEquals(alus.ammukset.get(0).height, 0);
        assertEquals(alus.ammukset.get(0).width, 0);
    }

    @Test
    public void pelaajaKuoleeAsteroidiinTormayksesta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);

        asteroidikentta.asteroidit.add(new Rectangle(250, 250, 30, 30));

        alus.osumaAsteroidiin();

        assertEquals(alus.pelaajaKuollut, true);
        assertEquals(alus.alus.height, 0);
        assertEquals(alus.alus.width, 0);
    }

    @Test
    public void kunTuhotaanVihollinenNiinSaadaan30pistetta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);

        vihollislaivue.viholliset.add(new Rectangle(250, 300, 30, 30));

        alus.ammukset.add(new Rectangle(250, 300, 3, 5));

        alus.osumaViholliseen();

        assertEquals(alus.getPisteet(), 30);
    }

    @Test
    public void kunAmmusOsuuViholliseenNiinSeHaviaa() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);

        vihollislaivue.viholliset.add(new Rectangle(250, 300, 30, 30));

        alus.ammukset.add(new Rectangle(250, 300, 3, 5));

        alus.osumaViholliseen();

        assertEquals(alus.ammukset.get(0).height, 0);
        assertEquals(alus.ammukset.get(0).width, 0);
    }

    @Test
    public void pelaajaKuoleeViholliseenTormayksesta() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);

        vihollislaivue.viholliset.add(new Rectangle(250, 250, 30, 30));

        alus.osumaViholliseen();

        assertEquals(alus.pelaajaKuollut, true);
        assertEquals(alus.alus.height, 0);
        assertEquals(alus.alus.width, 0);
    }
    
    @Test
    public void pelaajaKuoleeVihollisenAmmukseen() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);

        Rectangle vihollinen = new Rectangle(250, 247, 10, 20);
        vihollislaivue.viholliset.add(vihollinen);
        
        vihollislaivue.ammu();
        
        alus.osumaVihollisenAmmukseen();
        
        assertEquals(alus.pelaajaKuollut, true);
        assertEquals(alus.alus.height, 0);
        assertEquals(alus.alus.width, 0);
    }
    
    @Test
    public void alusHeraaHenkiin() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);
        
        alus.kuole();
        alus.heraaHenkiin();

        assertEquals(alus.alus.height, 20);
        assertEquals(alus.alus.width, 10);
    }
    
    @Test
    public void kuolemismetodiToimii() {
        Asteroidikentta asteroidikentta = new Asteroidikentta();
        Vihollislaivue vihollislaivue = new Vihollislaivue(asteroidikentta);
        Pelaaja alus = new Pelaaja(250, 250, asteroidikentta, vihollislaivue);
        
        alus.kuole();
        
        assertEquals(alus.pelaajaKuollut, true);
    }
}
