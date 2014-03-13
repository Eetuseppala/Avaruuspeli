package avaruuspeli.avaruuspeli;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        
        Avaruusalus alus = new Avaruusalus() {};

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(alus);
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
