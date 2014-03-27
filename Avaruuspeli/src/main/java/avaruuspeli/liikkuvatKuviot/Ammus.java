
package avaruuspeli.liikkuvatKuviot;

import java.awt.Graphics;
import java.awt.Rectangle;
import kayttoliittyma.Kayttoliittyma;

public class Ammus {
    
    int x, y;
    Rectangle ammus;
    private Kayttoliittyma kl;
    
    public Ammus(int x, int y) {
        this.x = x;
        this.y = y;
        
        ammus = ammus = new Rectangle(kl.getAlus().x, kl.getAlus().y, 3, 5);
    }
    
    public void liiku() {
        y -= 10;
    }
    
    public void piirra(Graphics g) {
        g.drawRect(x, y, 3, 5);
    }
}
