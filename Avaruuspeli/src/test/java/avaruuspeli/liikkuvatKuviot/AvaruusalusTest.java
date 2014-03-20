/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.liikkuvatKuviot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eetu
 */
public class AvaruusalusTest {

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
        Avaruusalus alus = new Avaruusalus(250, 300);

        alus.setYSuunta(-1);

        assertEquals(alus.ySuunta, -1);
    }

    @Test
    public void suunnanVaihtaminenOikealleOnnistuu() {
        Avaruusalus alus = new Avaruusalus(250, 300);

        alus.setYSuunta(1);

        assertEquals(alus.ySuunta, 1);
    }

    @Test
    public void ylosMeneminenOnnistuu() {
        Avaruusalus alus = new Avaruusalus(250, 300);

        alus.setXSuunta(-1);

        assertEquals(alus.xSuunta, -1);
    }

    @Test
    public void alasMeneminenOnnistuu() {
        Avaruusalus alus = new Avaruusalus(250, 300);

        alus.setXSuunta(1);

        assertEquals(alus.xSuunta, 1);
    }

    @Test
    public void alusAloittaaOikeastaKohdasta() {
        Avaruusalus alus = new Avaruusalus(250, 300);
        assertEquals(alus.x, 250);
        assertEquals(alus.y, 300);
    }
}
