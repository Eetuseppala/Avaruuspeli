
package avaruuspeli.liikkuvatKuviot;

import java.util.ArrayList;
import java.awt.Graphics;

public class Tahtikokoelma {
    
    private ArrayList<Tahti> tahtikokoelma;
    private Graphics g;

    public Tahtikokoelma(Graphics g) {
        this.tahtikokoelma = new ArrayList();
    }
    
    public void lisaaTahti(Tahti tahti) {
        this.tahtikokoelma.add(tahti);
    }
    
    public void piirra() {
        for (Tahti tahti : tahtikokoelma) {
            tahti.piirra(g);
        }
    }
}
