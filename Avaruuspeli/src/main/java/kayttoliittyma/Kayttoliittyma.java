
package kayttoliittyma;

import avaruuspeli.liikkuvatKuviot.Asteroidikentta;
import avaruuspeli.liikkuvatKuviot.Pelaaja;
import avaruuspeli.liikkuvatKuviot.Tahtitaivas;
import avaruuspeli.liikkuvatKuviot.Vihollislaivue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/* 
 * Tämä on Main-metodin käynnistämä käyttöliittymä pelille.
 */
public class Kayttoliittyma extends JFrame {

    Image kuva;
    Graphics grafiikat;

    Rectangle startNappi = new Rectangle(180, 300, 150, 50);

    public static Tahtitaivas tahdet = new Tahtitaivas();

    public static Asteroidikentta asteroidit = new Asteroidikentta();

    public static Vihollislaivue viholliset = new Vihollislaivue(asteroidit);

    public static Pelaaja alus = new Pelaaja(250, 300, asteroidit, viholliset); //Pelaajan liikuteltava alus

    int leveys = 500,
            korkeus = 600;
    Dimension ruudunKoko = new Dimension(leveys, korkeus);

    Thread avaruusalus = new Thread(alus);

    Thread liikkuvatTahdet = new Thread(tahdet);

    Thread liikkuvatAsteroidit = new Thread(asteroidit);

    Thread vihut = new Thread(viholliset);

    boolean peliKaynnissa = false;

    public Kayttoliittyma() {
        this.setTitle("Avaruusraivaaja");
        this.setSize(ruudunKoko);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new Kuuntelija());
        this.addMouseListener(new Hiirikuuntelija());
    }

    @Override
    public void paint(Graphics g) {
        kuva = createImage(getWidth(), getHeight());
        grafiikat = kuva.getGraphics();
        piirra(grafiikat);
        g.drawImage(kuva, 0, 0, this);
    }

     /* 
     * Tässä metodissa piirretään pelimaailman/ikkunan sisältö tilanteen mukaan.
     */
    public void piirra(Graphics g) {

        tahdet.piirra(g);

        if (peliKaynnissa) {

            asteroidit.piirra(g);

            viholliset.piirra(g);

            alus.piirra(g);

            g.setColor(Color.GREEN);
            g.drawString("Pisteet: " + alus.getPisteet(), 10, 50);
            g.drawString("Pelikerran paras tulos: " + alus.parasTulos, 10, 580);

            if (alus.pelaajaKuollut) {
                g.setColor(Color.RED);
                g.drawString("KUOLIT!", 230, 290);

                g.setColor(Color.GREEN);
                g.fillRect(startNappi.x, startNappi.y, startNappi.width, startNappi.height);
                g.setColor(Color.BLACK);
                g.drawString("RESTART", 225, 325);
            }
        } else {
            g.setColor(Color.GREEN);
            g.drawString("Onnea matkaan!", 210, 200);
            g.fillRect(startNappi.x, startNappi.y, startNappi.width, startNappi.height);
            g.setColor(Color.BLACK);
            g.drawString("START", 240, 325);
        }

        repaint();
    }

    public Pelaaja getAlus() {
        return alus;
    }

     /* 
     * Nimensä mukaisesti käynnistetään peli ja siihen liittyvät Threadit (paitsi tähdet,
     * jotka jo ovat käynnissä).
     */
    public void kaynnistaPeli() {
        peliKaynnissa = true;
        avaruusalus.start();
        liikkuvatAsteroidit.start();
        vihut.start();
    }

     /* 
     * Tämä seuraa näppäimistön painalluksia.
     */
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

    /* 
     * Tämä seuraa hiiren painalluksia.
     */
    public class Hiirikuuntelija extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            int hiiriX = e.getX();
            int hiiriY = e.getY();

            if (hiiriX > startNappi.x && hiiriX < startNappi.x + startNappi.width
                    && hiiriY > startNappi.y && hiiriY < startNappi.y + startNappi.height) {
                if (!peliKaynnissa) {
                    kaynnistaPeli();
                } else if (alus.pelaajaKuollut) {
                    alus.pelaajaKuollut = false;
                    if (alus.pisteet > alus.parasTulos) {
                        alus.parasTulos = alus.pisteet;
                    }
                    alus.nollaaPisteet();
                    alus.heraaHenkiin();
                }
            }
        }
    }
}
