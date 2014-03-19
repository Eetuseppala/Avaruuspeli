/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.avaruuspeli;

/**
 *
 * @author Eetu
 */
public class Pelimoottori extends Thread {

    private Kayttoliittyma kayttoliittyma;
    private Pelimaailma maailma;
    private boolean kaynnissa;

    public Pelimoottori(Kayttoliittyma kayttoliittyma, Pelimaailma maailma) {
        this.kayttoliittyma = kayttoliittyma;
        this.maailma = maailma;
        this.kaynnissa = true;
    }

    @Override
    public void run() {
        while (kaynnissa) {
            kayttoliittyma.piirra();
            odota();
        }
    }

    // muut metodit

    public void odota() {
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
