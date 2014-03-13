/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package avaruuspeli.avaruuspeli;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Eetu
 */
public class NappaimistonKuuntelija implements KeyListener {
 
    private Component component;
    private Avaruusalus alus;
 
    public NappaimistonKuuntelija(Component component, Avaruusalus alus) {
        this.alus = alus;
        this.component = component;
    }
     
    @Override
    public void keyTyped(KeyEvent e) {
         
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            alus.siirra(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            alus.siirra(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            alus.siirra(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            alus.siirra(0, 1);
        }
        component.repaint();
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
         
    }
     
}
