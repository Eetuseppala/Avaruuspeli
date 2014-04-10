/* 
 * Tämä on luokka, joka pitää sisällään kokoelman Rectangle-tyyppisiä asteroideja
 * ja niihin liittyviä metodeja
 */
package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Asteroidikentta implements Runnable {

    int x, y, ySuunta;

    ArrayList<Rectangle> asteroidit = new ArrayList();

    /* 
     * Tässä konstruktorissa luodaan uusi Rectangle-tyyppisten asteroidien joukko,
     * jota tämä peli kierrättää.
     */
    public Asteroidikentta() {
        ySuunta = 1;

        for (int i = 0; i < 25; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle asteroidi = new Rectangle(x, y, 15, 10);
            asteroidit.add(asteroidi);
        }
    }
    
    /* 
     * Yksittäiselle Rectangle-tyyppiselle asteroidille määritellään satunnainen x-koordinaatti
     * peli-ikkunan puitteissa.
     */
    public int xArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(500);
        return arpa;
    }

    /* 
     * Yksittäiselle Rectangle-tyyppiselle asteroidille määritellään satunnainen y-koordinaatti
     * peli-ikkunan puitteissa.
     */
    public int yArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(600);
        return arpa;
    }

    public void piirra(Graphics g) {

        for (Rectangle asteroidi : asteroidit) {
            g.setColor(new Color(156, 93, 82));
            g.fillRect(asteroidi.x, asteroidi.y, asteroidi.width, asteroidi.height);
            g.fillRect(asteroidi.x + 2, asteroidi.y - 2, asteroidi.width - 4, asteroidi.height + 4);
        }
    }

    /* 
     * Muuttaa asteroidin suuntaa.
     */
    public void liiku() {
        for (Rectangle asteroidi : asteroidit) {
            asteroidi.y += ySuunta;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Rectangle asteroidi : asteroidit) {
                    if (asteroidi.y > 595) {
                        asteroidi.y = 0;
                        asteroidi.x = xArvonArpominen();
                        asteroidi.height = 10;
                        asteroidi.width = 15;
                    }
                }
                liiku();
                Thread.sleep(15);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Iterable<Rectangle> getAsteroidit() {
        return this.asteroidit;
    }

    public void tuhoudu(Rectangle asteroidi) {
        asteroidi.width = 0;
        asteroidi.height = 0;
    }

}
