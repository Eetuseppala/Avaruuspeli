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
    public int viivemittari = 0;
    Avaruusalus alus;

    public Vihollislaivue() {

        for (int i = 0; i < 4; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle vihollinen = new Rectangle(x, y, 10, 20);
            viholliset.add(vihollinen);
        }
        this.alus = alus;
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
            g.fillRect(vihollinen.x, vihollinen.y, 10, 20);
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
                ammusX = vihollinen.x + 4;
                ammusY = vihollinen.y + 16;

                ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
            }
            viivemittari = 200;
        }
    }

    public void liikutaAmmuksia() {
        for (Rectangle ammus : ammukset) {
            ammus.y += 5;
        }
    }

    //Seuraava jää ensi viikon viimeistelyksi, tällä viikolla ei onnannut
    
//    public void osuma() {
//
//        for (int i = 0; i < ammukset.size(); i++) {
//            
//            if(ammukset.get(i).intersects(null))
//
//            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {
//
//                if (ammukset.get(i).intersects(asteroidi)) {
//                    asteroidit.tuhoudu(asteroidi);
//                    pisteet += 10;
//                    ammukset.get(i).height = 0;
//                    ammukset.get(i).width = 0;
//                }
//            }
//        }
//
//        for (Rectangle asteroidi : asteroidit.getAsteroidit()) {
//
//            if (asteroidi.intersects(alus)) {
//                alus.height = 0;
//                alus.width = 0;
//                pelaajaKuollut = true;
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
                    }
                }
                liiku();
                ammu();
                liikutaAmmuksia();
                viivemittari--;
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}