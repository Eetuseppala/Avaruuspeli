/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.avaruuspeli;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Eetu
 */
public class Avaruusalus {

    private int x;
    private int y;

    public Avaruusalus() {
        this.x = 30;
        this.y = 30;
    }

    public void siirra(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void piirra(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(getX(), getY(), 20, 10);
    }
}
