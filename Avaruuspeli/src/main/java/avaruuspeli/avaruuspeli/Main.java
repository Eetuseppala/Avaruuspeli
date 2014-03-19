package avaruuspeli.avaruuspeli;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Pelimaailma maailma = new Pelimaailma();

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(maailma);
        SwingUtilities.invokeLater(kayttoliittyma);

        Pelimoottori moottori = new Pelimoottori(kayttoliittyma, maailma);

        moottori.start();
    }
}
