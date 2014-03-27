package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Tahtitaivas implements Runnable {

    int x, y, ySuunta;

    private ArrayList<Rectangle> tahdet = new ArrayList();

    public Tahtitaivas() {
        ySuunta = 1;

        for (int i = 0; i < 15; i++) {
            this.x = xArvonArpominen();
            this.y = yArvonArpominen();
            Rectangle tahti = new Rectangle(x, y, 1, 1);
            tahdet.add(tahti);
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

        for (Rectangle tahti : tahdet) {
            g.setColor(Color.WHITE);
            g.fillRect(tahti.x, tahti.y, tahti.width, tahti.height);
        }
    }

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
