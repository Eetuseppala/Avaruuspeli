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
    Avaruusalus alus;
    Asteroidikentta asteroidit;

    public Vihollislaivue(Asteroidikentta asteroidit) {

        for (int i = 0; i < 8; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle vihollinen = new Rectangle(x, y, 10, 20);
            viholliset.add(vihollinen);
            eiPaallekkaisiaVihollisia();
        }
        this.alus = alus;
        this.asteroidit = asteroidit;
    }

    public int xArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(500);
        return arpa;
    }

    public int yArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(600);
        return arpa;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.MAGENTA);

        for (Rectangle vihollinen : viholliset) {
            g.fillRect(vihollinen.x, vihollinen.y, vihollinen.width, vihollinen.height);
        }

        for (int i = 0; i < ammukset.size(); i++) {
            g.setColor(Color.RED);
            g.fillRect(ammukset.get(i).x, ammukset.get(i).y, ammukset.get(i).width, ammukset.get(i).height);
        }
    }

    public void liiku() {
        for (Rectangle vihollisalus : viholliset) {
            vihollisalus.y += ySuunta;
        }
    }

    public void ammu() {

        if (viivemittari <= 0) {
            for (Rectangle vihollinen : viholliset) {

                if (vihollinen.height == 20) {   //Testataan käytännössä onko alus tuhoutunut vai ei: tuhoutuneella 0
                    ammusX = vihollinen.x + 4;
                    ammusY = vihollinen.y + 16;
                    
                    ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
                }
            }
            viivemittari = 100;
        }
    }

    public void liikutaAmmuksia() {
        for (Rectangle ammus : ammukset) {
            ammus.y += 5;
        }
    }

    public Iterable<Rectangle> getViholliset() {
        return this.viholliset;
    }

    public void tuhoudu(Rectangle vihollinen) {
        vihollinen.height = 0;
        vihollinen.height = 0;
    }

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

    private void eiPaallekkaisiaVihollisia() {
        for (int i = 0; i < viholliset.size(); i++) {

            Rectangle verrattava = viholliset.get(i);

            if (viholliset.size() > 1) {

                for (Rectangle vihollinen : viholliset) {
                    if (verrattava.intersects(vihollinen))  {
                        vihollinen.x -= 7;
                        vihollinen.y -= 7;
                    }
                }
            }
        }
    }
    

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
