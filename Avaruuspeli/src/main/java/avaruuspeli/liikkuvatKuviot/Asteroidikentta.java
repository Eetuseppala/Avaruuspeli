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

    private ArrayList<Rectangle> asteroidit = new ArrayList();

    public Asteroidikentta() {
        ySuunta = 1;

        for (int i = 0; i < 15; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle asteroidi = new Rectangle(x, y, 15, 10);
            asteroidit.add(asteroidi);
        }
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

        for (Rectangle asteroidi : asteroidit) {
            g.setColor(new Color(156, 93, 82));
            g.fillRect(asteroidi.x, asteroidi.y, asteroidi.width, asteroidi.height);
            g.fillRect(asteroidi.x+2, asteroidi.y-2, asteroidi.width-4, asteroidi.height+4);
        }
    }

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
                    if (asteroidi.y > 585) {
                        asteroidi.y = 0;
                        asteroidi.x = xArvonArpominen();
                    }
                }
                liiku();
                Thread.sleep(15);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
