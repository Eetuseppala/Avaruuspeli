/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.avaruuspeli;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
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
public class MainTest {

    public MainTest() {
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
    public void pelikenttaOikeanVarinen() {
        Main paaohjelma = new Main();

        assertEquals(paaohjelma.getBackground(), Color.BLACK);
    }

    @Test
    public void ohjelmanVoiSulkeaRastista() {
        Main paaohjelma = new Main();

        assertEquals(paaohjelma.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }

    @Test
    public void ikkunaOikeanKokoinen() {
        Main paaohjelma = new Main();

        assertEquals(paaohjelma.getSize(), new Dimension(500,600));
    }
}
