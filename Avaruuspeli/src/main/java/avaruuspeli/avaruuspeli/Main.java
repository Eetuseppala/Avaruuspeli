package avaruuspeli.avaruuspeli;

import avaruuspeli.liikkuvatKuviot.Avaruusalus;
import avaruuspeli.liikkuvatKuviot.Tahti;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Main extends JFrame {

    Image kuva;
    Graphics grafiikat;

    static Avaruusalus alus = new Avaruusalus(250, 300);
    static Tahti tahti = new Tahti();

    int leveys = 500,
            korkeus = 600;
    Dimension ruudunKoko = new Dimension(leveys, korkeus);

    public Main() {
        this.setTitle("Avaruuspeli");
        this.setSize(ruudunKoko);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new Kuuntelija());
    }

    public static void main(String[] args) {
        Main ikkuna = new Main();
        Thread avaruusalus = new Thread(alus);
        avaruusalus.start();

        Thread liikkuvatahti = new Thread(tahti);
        liikkuvatahti.start();

    }

    @Override
    public void paint(Graphics g) {
        kuva = createImage(getWidth(), getHeight());
        grafiikat = kuva.getGraphics();
        piirra(grafiikat);
        g.drawImage(kuva, 0, 0, this);
    }

    public void piirra(Graphics g) {
        alus.piirra(g);
        tahti.piirra(g);

        repaint();
    }

    public class Kuuntelija extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            alus.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            alus.keyReleased(e);
        }
    }
}
