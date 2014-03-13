/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.avaruuspeli;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Eetu
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Avaruusalus alus;
    private Piirtoalusta pa;

    public Kayttoliittyma(Avaruusalus alus) {
        this.alus = alus;
    }

    @Override
    public void run() {
        frame = new JFrame("Avaruusmiäs!");
        frame.setPreferredSize(new Dimension(400, 400));
        
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        lisaaKuuntelijat();

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        pa = new Piirtoalusta(alus);
        container.add(pa);
    }

    private void lisaaKuuntelijat() {

        frame.addKeyListener(new NappaimistonKuuntelija(pa, alus));
    }

    public JFrame getFrame() {
        return frame;
    }
}
