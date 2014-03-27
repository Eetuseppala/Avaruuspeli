package avaruuspeli.avaruuspeli;

import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;

public class Main extends JFrame {

    public static void main(String[] args) {
        Kayttoliittyma ikkuna = new Kayttoliittyma();

        Thread avaruusalus = new Thread(ikkuna.alus);
        avaruusalus.start();
        
        Thread liikkuvatTahdet = new Thread(ikkuna.tahdet);
        liikkuvatTahdet.start();
        
        Thread liikkuvatAsteroidit = new Thread(ikkuna.asteroidit);
        liikkuvatAsteroidit.start();
    }
}
