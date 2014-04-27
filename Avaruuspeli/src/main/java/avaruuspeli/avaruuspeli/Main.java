/* 
 * Ohjelman pääluokka, joka käynnistää käyttöliittymän, luo threadit ja käynnistää ne.
 */
package avaruuspeli.avaruuspeli;

import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;

public class Main extends JFrame {

    public static void main(String[] args) {
        Kayttoliittyma ikkuna = new Kayttoliittyma();

        Thread liikkuvatTahdet = new Thread(ikkuna.tahdet);
        
        liikkuvatTahdet.start();
    }

}
