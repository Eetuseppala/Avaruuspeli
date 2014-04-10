/* 
 * Tämä luokka pitää sisällään joukon Rectangle-tyyppisiä vihollisia
 * ja metodeja niihin liittyen 
 */
package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Vihollislaivue implements Runnable {

    int x, y;
    int ammusX, ammusY;
    ArrayList<Rectangle> viholliset = new ArrayList();
    ArrayList<Rectangle> ammukset = new ArrayList();
    int ySuunta = 1;
    int pisteet = 0;
    public int viivemittari = 0;
    public boolean vihollisenAmmusKasittelyssa = false; //Estämään concurrentModificationExceptionit.
    Asteroidikentta asteroidit;

    /* 
     * Vihollislaivue-luokan konstruktorissa määritellään uusi Rectangle-tyyppisten
     * vihollisten joukko. Näitä vihollisia peli "kierrättää".
     *
     * @param   asteroidit  Vihollislaivue tarvitsee tiedon asteroideista, jotta voisi
     *                      tuhota niitä ja tuhoutua niihin.
     */
    public Vihollislaivue(Asteroidikentta asteroidit) {

        for (int i = 0; i < 8; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle vihollinen = new Rectangle(x, y, 10, 20);
            viholliset.add(vihollinen);
//            eiPaallekkaisiaVihollisia();
        }
        this.asteroidit = asteroidit;
    }

    /* 
     * Yksittäiselle Rectangle-tyyppiselle viholliselle määritellään satunnainen x-koordinaatti
     * peli-ikkunan puitteissa.
     */
    public int xArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(500);
        return arpa;
    }

    /* 
     * Yksittäiselle Rectangle-tyyppiselle viholliselle määritellään satunnainen y-koordinaatti
     * peli-ikkunan puitteissa.
     */
    public int yArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(600);
        return arpa;
    }

    /* 
     * Tämä metodi määrää, minkä näköisiksi viholliset ja niiden
     * ammukset piirretään.
     */
    public void piirra(Graphics g) {
        g.setColor(Color.GREEN);

        for (Rectangle vihollinen : viholliset) {
            g.fillRect(vihollinen.x, vihollinen.y, vihollinen.width, vihollinen.height);
        }

        for (int i = 0; i < ammukset.size(); i++) {
            g.setColor(Color.RED);
            g.fillRect(ammukset.get(i).x, ammukset.get(i).y, ammukset.get(i).width, ammukset.get(i).height);
        }
    }

    /* 
     * Tämä metodi liikuttaa vihollisia ySuunta-nimisen oliomuuttujan mukaan.
     * Tässä pelissä sen arvo on aina 1 (alaspäin).
     */
    public void liiku() {
        for (Rectangle vihollisalus : viholliset) {
            vihollisalus.y += ySuunta;
        }
    }

    /* 
     * Tämä metodi saa viholliset ampumaan yhtä aikaa (toteutuksellinen valintani).
     * Viivemittari määrittää ampumistiheyden.
     */
    public void ammu() {

        vihollisenAmmusKasittelyssa = true; // Tämä pieni temppu estää ConcurrentModificationExceptionin

        if (viivemittari <= 0) {
            for (Rectangle vihollinen : viholliset) {

                if (vihollinen.height == 20) {   //onko vihollinen elossa, jos 0 niin ei ole eikä silloin ammuta
                    ammusX = vihollinen.x + 4;
                    ammusY = vihollinen.y + 16;

                    ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
                }
            }
            viivemittari = 100;
        }
        vihollisenAmmusKasittelyssa = false;
    }

    /* 
     * Tämä metodi liikuttaa vihollisten ammuksia.
     * ammus.y:n muokkaaminen vaikuttaa vihollisten ammusten nopeuteen.
     */
    public void liikutaAmmuksia() {
        for (Rectangle ammus : ammukset) {
            ammus.y += 5;
        }
    }

    public Iterable<Rectangle> getViholliset() {
        return this.viholliset;
    }

    /* 
     * Tätä metodia kutsutaan, kun tietyn vihollisen täytyy tuhoutua.
     */
    public void tuhoudu(Rectangle vihollinen) {
        vihollinen.height = 0;
        vihollinen.width = 0;
    }

    /* 
     * Tässä metodissa tarkistetaan osuuko mikään vihollisten ammuksista mihinkään asteroidiin.
     * Jos näin on niin sellaiset asteroidit tuhoutuvat. Sen jälkeen katsotaan toisaalta osuuko
     * mikään asteroideista yhteenkään viholliseen. Tämän sattuessa ko. vihollinen tuhoutuu.
     */
    public void osumaAsteroidiin() {

        for (int i = 0; i < ammukset.size(); i++) {

            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

                if (ammukset.get(i).intersects(asteroidi)) {
                    asteroidit.tuhoudu(asteroidi);
                    pisteet += 10;
                    ammukset.get(i).height = 0;
                    ammukset.get(i).width = 0;
                }
            }
        }

        for (Rectangle vihollinen : viholliset) {
            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

                if (asteroidi.intersects(vihollinen)) {
                    vihollinen.height = 0;
                    vihollinen.width = 0;
                }
            }
        }
    }

    public int getPisteet() {
        return pisteet;
    }

//    private void eiPaallekkaisiaVihollisia() {
//        for (int i = 0; i < viholliset.size(); i++) {
//
//            Rectangle verrattava = viholliset.get(i);
//
//            if (viholliset.size() > 1) {
//
//                for (Rectangle vihollinen : viholliset) {
//                    if (verrattava.intersects(vihollinen)) {
//                        vihollinen.x -= 7;
//                        vihollinen.y -= 7;
//                    }
//                }
//            }
//        }
//    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Rectangle vihollinen : viholliset) {
                    if (vihollinen.y > 595) {
                        vihollinen.y = 0;
                        vihollinen.x = xArvonArpominen();
                        vihollinen.width = 10;
                        vihollinen.height = 20;
                    }
                }
                liiku();
                ammu();
                liikutaAmmuksia();
                osumaAsteroidiin();
                viivemittari--;
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
