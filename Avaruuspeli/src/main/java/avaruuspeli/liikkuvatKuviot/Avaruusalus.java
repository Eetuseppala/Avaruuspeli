package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Avaruusalus implements Runnable {

    int x, y, ySuunta, xSuunta;
    int ammusX, ammusY;
    Rectangle alus;
    Rectangle ammus;
    boolean valmisAmpumaan;

    private ArrayList<Rectangle> ammukset = new ArrayList();

    public Avaruusalus(int x, int y) {
        this.x = x;
        this.y = y;
        alus = new Rectangle(x, y, 10, 20);
    }

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
            
            valmisAmpumaan = true;

            if (valmisAmpumaan) {
                ammusX = alus.x;
                ammusY = alus.y;

                ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
            }
        }
    }

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

        if (e.getKeyCode() == e.VK_SPACE) {
            valmisAmpumaan = true;

            for (Rectangle ammus : ammukset) {
                if (ammus.y <= -5) {
                    ammus = new Rectangle(0, 0, 0, 0); //ei voi laittaa null
                    valmisAmpumaan = true;
                }
            }
        }
    }

    public void setYSuunta(int uusiSuunta) {
        ySuunta = uusiSuunta;
    }

    public void setXSuunta(int uusiSuunta) {
        xSuunta = uusiSuunta;
    }

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

    public void ammu() {
        for (Rectangle ammus : ammukset) {
            ammus.y--;
        }
    }

    public void piirra(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(alus.x, alus.y, alus.width, alus.height);

        for (Rectangle ammus: ammukset) {
            g.setColor(Color.GREEN);
            g.fillRect(ammus.x, ammus.y, ammus.width, ammus.height);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                ammu();
                liiku();
                Thread.sleep(4);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
