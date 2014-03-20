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
public class TahtiTest {
    
    public TahtiTest() {
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
        
        //Tämä testi on ehkä vähän huono? En keksinyt kattavaa ja nopeaa testiä
        
        Tahti tahti = new Tahti();
        
        int xArvo = tahti.x;
        boolean toimii = false;
        
        if(xArvo >=0 && xArvo <= 500) {
            toimii = true;
        }
        
        assertEquals(toimii, true);
    }
    
    @Test
    public void tahtiEiLiikuVaakasuunnassa() {
        Tahti tahti = new Tahti();
        
        assertEquals(tahti.y, 0);
    }
    
    @Test
    public void tahtiLiikkuuAlas() {
        Tahti tahti = new Tahti();
        
        assertEquals(tahti.ySuunta, 1);
    }    
}
