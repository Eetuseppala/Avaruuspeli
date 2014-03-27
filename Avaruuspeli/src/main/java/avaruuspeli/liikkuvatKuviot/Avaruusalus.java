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
    Asteroidikentta asteroidit;

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
            
                ammusX = alus.x +4;
                ammusY = alus.y -4;

                ammukset.add(new Rectangle(ammusX, ammusY, 3, 5));
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

            for (Rectangle ammus : ammukset) {
                if (ammus.y <= -5) {
                    poistettavaAmmus = ammus;
                }
            }      
            ammukset.remove(poistettavaAmmus);
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
    
    //Koitan tätä ensi viikolla, en saanut toimimaan vielä
    
//    public void osuma() {
//        for (Rectangle ammus : ammukset) {
//            
//            for (Rectangle asteroidi : asteroidit.getAsteroidit()) {
//                
//                if(ammus.intersects(asteroidi)) {
//                    asteroidit.tuhoudu(asteroidi);
//                }
//            }
//        }
//    }
    
    

    public void piirra(Graphics g) {
        g.setColor(Color.GRAY);
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
