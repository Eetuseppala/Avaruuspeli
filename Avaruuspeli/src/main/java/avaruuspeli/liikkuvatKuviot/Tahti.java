package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tahti implements Runnable {

    int x, y, ySuunta;
    Rectangle tahti;

    public Tahti() {
        this.x = xArvonArpominen();
        this.y = 0;
        ySuunta = 1;
        tahti = new Rectangle(x, y, 2, 2);
    }

    public int xArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(500);
        return arpa;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(tahti.x, tahti.y, tahti.width, tahti.height);
    }
    
    public void liiku() {
        tahti.y += ySuunta;
    }

    @Override
    public void run() {
        try {
            while (true) {
                liiku();
                Thread.sleep(7);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
