/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package avaruuspeli.avaruuspeli;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Eetu
 */
public class Piirtoalusta extends JPanel {
     
    private Avaruusalus alus;
 
    public Piirtoalusta(Avaruusalus alus) {
        super.setBackground(Color.BLACK);
        this.alus = alus;
    }
 
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        alus.piirra(graphics);
    }
     
}
