package avaruuspeli.avaruuspeli;

import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;

public class Main extends JFrame {
    
        public static void main(String[] args) {
        Kayttoliittyma ikkuna = new Kayttoliittyma();
        
        Thread avaruusalus = new Thread(ikkuna.alus);
        avaruusalus.start();

        Thread liikkuvatahti1 = new Thread(ikkuna.tahti1);
        Thread liikkuvatahti2 = new Thread(ikkuna.tahti2);
        Thread liikkuvatahti3 = new Thread(ikkuna.tahti3);
        Thread liikkuvatahti4 = new Thread(ikkuna.tahti4);
        liikkuvatahti1.start();
        liikkuvatahti2.start();
        liikkuvatahti3.start();
        liikkuvatahti4.start();
    }
}
