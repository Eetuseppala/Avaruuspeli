package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Avaruusalus implements Runnable {

    int x, y, ySuunta, xSuunta;
    int ammusX, ammusY;
    Rectangle alus;
    Asteroidikentta asteroidit;
    int pisteet = 0;
    public boolean pelaajaKuollut = false;
    public int aikaaEdellisestaAmmuksesta = 0;

    private ArrayList<Rectangle> ammukset = new ArrayList();

    public Avaruusalus(int x, int y, Asteroidikentta asteroidikentta) {
        this.x = x;
        this.y = y;
        alus = new Rectangle(x, y, 10, 20);
        this.asteroidit = asteroidikentta;
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

            if (!pelaajaKuollut && aikaaEdellisestaAmmuksesta <= 0) {
                ammusX = alus.x + 4;
                ammusY = alus.y - 4;

                ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
                aikaaEdellisestaAmmuksesta = 100;
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
            Rectangle poistettavaAmmus = null;
            boolean poistetaan = false;

            for (Rectangle ammus : ammukset) {
                if (ammus.y <= -20) {
                    poistettavaAmmus = ammus;
                    poistetaan = true;
                }
            }
            if (poistetaan) {
                ammukset.remove(poistettavaAmmus);
            }
        }
    }

    public void setYSuunta(int uusiSuunta) {
        ySuunta = uusiSuunta;
    }

    public void setXSuunta(int uusiSuunta) {
        xSuunta = uusiSuunta;
    }

    public int getYSuunta() {
        return this.ySuunta;
    }

    public int getXSuunta() {
        return this.xSuunta;
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
            ammus.y -= 2;
        }
    }

    public void osuma() {
        
        for (int i = 0; i < ammukset.size() ; i++) {

            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

                if (ammukset.get(i).intersects(asteroidi)) {
                    asteroidit.tuhoudu(asteroidi);
                    pisteet += 10;
                    ammukset.get(i).height = 0;
                    ammukset.get(i).width = 0;
                }
            }
        }

        for (Rectangle asteroidi : asteroidit.getAsteroidit()) {

            if (asteroidi.intersects(alus)) {
                alus.height = 0;
                alus.width = 0;
                pelaajaKuollut = true;
            }
        }
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(alus.x, alus.y, alus.width, alus.height);
        g.setColor(Color.CYAN);
        g.fillRect(alus.x + 2, alus.y + 2, alus.width - 3, alus.height - 15);

        for (Rectangle ammus : ammukset) {
            g.setColor(Color.GREEN);
            g.fillRect(ammus.x, ammus.y, ammus.width, ammus.height);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                aikaaEdellisestaAmmuksesta--;
                liiku();
                ammu();
                osuma();
                Thread.sleep(4);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Logger.getLogger(Avaruusalus.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
