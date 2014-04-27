/*
 * Tämä on luokka, joka pitää sisällään kokoelman Rectangle-tyyppisiä tähtiä
 * ja niihin liittyviä metodeja
 */
package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Tahtitaivas extends AlasPainLiikkuva implements Runnable {

    int x, y, ySuunta;

    ArrayList<Rectangle> tahdet = new ArrayList();

    /*
     * Tässä konstruktorissa luodaan uusi Rectangle-tyyppisten tähtien joukko,
     * jota tämä peli kierrättää.
     */
    public Tahtitaivas() {
        ySuunta = 1;

        for (int i = 0; i < 15; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle tahti = new Rectangle(x, y, 1, 1);
            tahdet.add(tahti);
        }
    }

    public void piirra(Graphics g) {

        for (Rectangle tahti : tahdet) {
            g.setColor(Color.WHITE);
            g.fillRect(tahti.x, tahti.y, tahti.width, tahti.height);
        }
    }

    /*
     * Muuttaa tähden suuntaa.
     */
    public void liiku() {
        for (Rectangle tahti : tahdet) {
            tahti.y += ySuunta;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Rectangle tahti : tahdet) {
                    if (tahti.y > 585) {
                        tahti.y = 0;
                        tahti.x = xArvonArpominen();
                    }
                }
                liiku();
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
