package avaruuspeli.liikkuvatKuviot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Avaruusalus implements Runnable {

    int x, y, ySuunta, xSuunta;
    Rectangle alus;

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
    }

    public void setYSuunta(int uusiSuunta) {
        ySuunta = uusiSuunta;
    }

    public void setXSuunta(int uusiSuunta) {
        xSuunta = uusiSuunta;
    }

    public void liiku() {
        alus.y += ySuunta;
        
        if(alus.y <= 25)
            alus.y = 25;
        else if(alus.y >= 575)
            alus.y = 575;
        
        alus.x += xSuunta;
        
        if(alus.x <= 5)
            alus.x = 5;
        else if(alus.x >= 485)
            alus.x = 485;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(alus.x, alus.y, alus.width, alus.height);
    }

    @Override
    public void run() {
        try {
            while (true) {
                liiku();
                Thread.sleep(6);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
