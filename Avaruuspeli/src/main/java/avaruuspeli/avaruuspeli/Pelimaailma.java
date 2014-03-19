/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaruuspeli.avaruuspeli;

/**
 *
 * @author Eetu
 */
public class Pelimaailma {

    //private ArrayList<Asteroidi> asteroidit;
    private Avaruusalus alus;

    public Pelimaailma() {
        this.alus = new Avaruusalus();
    }
    
    public Avaruusalus getAlus() {
        return this.alus;
    }

}
