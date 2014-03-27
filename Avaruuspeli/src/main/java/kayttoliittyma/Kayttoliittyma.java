package kayttoliittyma;

import avaruuspeli.liikkuvatKuviot.Avaruusalus;
import avaruuspeli.liikkuvatKuviot.Tahtitaivas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Kayttoliittyma extends JFrame {

    Image kuva;
    Graphics grafiikat;

    public static Avaruusalus alus = new Avaruusalus(250, 300); //Pelaajan liikuteltava alus
    
    public static Tahtitaivas tahdet = new Tahtitaivas();

    int leveys = 500,
            korkeus = 600;
    Dimension ruudunKoko = new Dimension(leveys, korkeus);

    private Kuuntelija kuuntelija = new Kuuntelija();

    public Kayttoliittyma() {
        this.setTitle("Avaruuspeli");
        this.setSize(ruudunKoko);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new Kuuntelija());
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

        tahdet.piirra(g);
        
        repaint();
    }

    public Avaruusalus getAlus() {
        return alus;
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
