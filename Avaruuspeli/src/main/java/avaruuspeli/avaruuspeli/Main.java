
package avaruuspeli.avaruuspeli;

import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;

/* 
 * Ohjelman pääluokka, joka luo käyttöliittymän, luo tähtien threadin
 * ja käynnistää sen.
 */

public class Main extends JFrame {

    public static void main(String[] args) {
        Kayttoliittyma ikkuna = new Kayttoliittyma();

        Thread liikkuvatTahdet = new Thread(ikkuna.tahdet);
        
        liikkuvatTahdet.start();
    }

}
