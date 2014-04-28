
package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Tässä luokassa on pelaajan ohjaama avaruusalus ja siihen liittyvät metodit
 */
public class Pelaaja implements Runnable {

    int x, y, ySuunta, xSuunta;
    int ammusX, ammusY;
    Rectangle alus;
    Asteroidikentta asteroidit;
    Vihollislaivue viholliset;
    public int pisteet = 0;
    public int parasTulos = 0;
    public boolean pelaajaKuollut = false;
    public int ammustenAikarajoitin = 0;
    ArrayList<Rectangle> ammukset = new ArrayList();

    /*
     * Avaruusalus-luokan konstruktorissa luodaan alusta kuvaava Rectangle-tyyppinen objekti
     * ja annetaan avaruusalukselle tieto asteroideista ja vihollisista, joita se tarvitsee
     *
     * @param x avaruusaluksen sijainnin x-arvo
     *
     * @param y avaruusaluksen sijainnin y-arvo
     *
     * @param asteroidikentta avaruusalus tarvitsee tiedon pelin asteroideista
     *
     * @param vihollislaivue avaruusalus tarvitsee tiedon pelin vihollisista
     */
    public Pelaaja(int x, int y, Asteroidikentta asteroidikentta, Vihollislaivue vihollislaivue) {
        this.x = x;
        this.y = y;
        alus = new Rectangle(x, y, 10, 20);
        this.asteroidit = asteroidikentta;
        this.viholliset = vihollislaivue;
    }


    /*
     * Avaruusaluksen kontrollointiin (käyttäjän ohjaamana) osallistuva metodi
     *
     */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == e.VK_UP) {
            setYSuunta(-1);
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            setYSuunta(1);
        }

        if (e.getKeyCode() == e.VK_LEFT) {
            setXSuunta(-1);
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            setXSuunta(1);
        }

        if (e.getKeyCode() == e.VK_SPACE) {

            if (!pelaajaKuollut && ammustenAikarajoitin <= 0) {
                ammusX = alus.x + 4;
                ammusY = alus.y - 4;

                ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
                ammustenAikarajoitin = 150;
            }
        }
    }

    /*
     * Avaruusaluksen kontrollointiin (käyttäjän ohjaamana) osallistuva metodi
     *
     */
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == e.VK_UP) {
            setYSuunta(0);
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            setYSuunta(0);
        }

        if (e.getKeyCode() == e.VK_LEFT) {
            setXSuunta(0);
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            setXSuunta(0);
        }
    }

    /*
     * Muuttaa avaruusaluksen suuntaa y-akselilla
     *
     * @param uusiSuunta haluttu uusi suunta y-akselilla
     */
    public void setYSuunta(int uusiSuunta) {
        ySuunta = uusiSuunta;
    }

    /*
     * Muuttaa avaruusaluksen suuntaa x-akselilla
     *
     * @param uusiSuunta haluttu uusi suunta x-akselilla
     */
    public void setXSuunta(int uusiSuunta) {
        xSuunta = uusiSuunta;
    }

    public int getYSuunta() {
        return this.ySuunta;
    }

    public int getXSuunta() {
        return this.xSuunta;
    }

    /*
     * Muuttaa aluksen suunnan
     */
    public void liiku() {
        alus.y += ySuunta;

        if (alus.y <= 25) {
            alus.y = 25;
        } else if (alus.y >= 575) {
            alus.y = 575;
        }

        alus.x += xSuunta;

        if (alus.x <= 5) {
            alus.x = 5;
        } else if (alus.x >= 485) {
            alus.x = 485;
        }
    }

    /*
     * Nimensä mukaisesti liikuttaa pelaajan ampumia ammuksia eteenpäin
     */
    public void liikutaAmmuksia() {
        for (Rectangle ammus : ammukset) {
            ammus.y -= 2;
        }
    }

    /*
     * Nimensä mukaisesti poistaa näkyvistä hävinneet ammukset
     */
    public void poistaNakyvistaHavinneetAmmukset() {

        for (Rectangle ammus : ammukset) {
            if (ammus.y <= -200) {
                ammus.height = 0;
                ammus.width = 0;
            }
        }
    }

    /*
     * Testaa, osuuko mikään pelaajan ammuksista mihinkään asteroidiin.
     * Jos näin on niin ko. asteroidi tuhoutuu. Sen jälkeen katsotaan osuuko toisaalta
     * pelaaja yhteenkään asteroidiin. Jos näin on niin pelaaja kuolee.
     */
    public void osumaAsteroidiin() {

        for (int i = 0; i < ammukset.size(); i++) {

            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

                if (asteroidi.intersects(ammukset.get(i))) {
                    asteroidit.tuhoudu(asteroidi);
                    pisteet += 10;
                    ammukset.get(i).height = 0;
                    ammukset.get(i).width = 0;
                }
            }
        }

        for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

            if (asteroidi.intersects(alus)) {
                kuole();
            }
        }
    }

    /*
     * Testaa, osuuko mikään pelaajan ammuksista mihinkään viholliseen.
     * Jos näin on niin ko. vihollinen tuhoutuu. Sen jälkeen katsotaan osuuko toisaalta
     * pelaaja yhteenkään viholliseen. Jos näin on niin pelaaja kuolee.
     */
    public void osumaViholliseen() {

        for (int i = 0; i < ammukset.size(); i++) {

            for (Rectangle vihollinen : viholliset.getViholliset()) {

                if (vihollinen.intersects(ammukset.get(i))) {
                    viholliset.tuhoudu(vihollinen);
                    pisteet += 30;
                    ammukset.get(i).height = 0;
                    ammukset.get(i).width = 0;
                }
            }
        }

        for (Rectangle vihollinen : viholliset.getViholliset()) {

            if (vihollinen.intersects(alus)) {
                kuole();
            }
        }
    }

    /*
     * Testaa, osuuko pelaaja yhteenkään vihollisten ammuksista.
     * Jos näin on niin pelaaja kuolee.
     */
    public void osumaVihollisenAmmukseen() {

        if (!viholliset.vihollisenAmmusKasittelyssa) { //estetään concurrentModificationException

            Iterator<Rectangle> iteraattori = viholliset.ammukset.iterator();

            while (iteraattori.hasNext()) {
                if (iteraattori.next().intersects(alus)) {
                    kuole();
                }
            }
        }
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public void nollaaPisteet() {
        this.pisteet = 0;
    }

    /*
     * Kun pelaaja kuolee ja painaa sen jälkeen RESTART-nappia, niin tätä
     * metodia kutsutaan. Silloin pelaaja herää henkiin ja näkyy jälleen.
     */
    public void heraaHenkiin() {
        alus.height = 20;
        alus.width = 10;
        alus.x = 250;
        alus.y = 300;
    }

    /*
     * Tätä metodia kutsutaan kun pelaaja kuolee.
     */
    public void kuole() {
        alus.height = 0;
        alus.width = 0;
        pelaajaKuollut = true;
    }

    /*
     * Säännöt pelaajan ja pelaajan ammusten piirtämiseen. Määrittää miltä pelaaja näyttää ja miltä
     * pelaajan ammukset näyttävät.
     */
    public void piirra(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(alus.x, alus.y, alus.width, alus.height);

        for (Rectangle ammus : ammukset) {
            g.setColor(Color.CYAN);
            g.fillRect(ammus.x, ammus.y, ammus.width, ammus.height);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                ammustenAikarajoitin--;
                liiku();
                liikutaAmmuksia();
                poistaNakyvistaHavinneetAmmukset();
                osumaAsteroidiin();
                osumaViholliseen();
                osumaVihollisenAmmukseen();
                Thread.sleep(4);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Logger.getLogger(Pelaaja.class.getName()).log(Level.SEVERE, null, e);
            this.run();
        }
    }

}
