
package avaruuspeli.liikkuvatKuviot;

import java.awt.Rectangle;
import java.util.Random;

/*
 * Abstrakti luokka, joka sisältää yleisimmät metodit alas päin liikkuville objekteille
 * (asteroideille, tähdille ja vihollisille)
 */

public abstract class AlasPainLiikkuva {

    /* 
     * Yksittäiselle Rectangle-tyyppiselle alas päin liikkuvalle objektille määritellään 
     * satunnainen x-koordinaatti peli-ikkunan puitteissa.
     *
     * @return  arpa    uusi x-koordinaatin arvo
     */
    
    public int xArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(490);
        return arpa;
    }

    /** 
     * Yksittäiselle Rectangle-tyyppiselle alas päin liikkuvalle objektille määritellään 
     * satunnainen y-koordinaatti peli-ikkunan puitteissa.
     *
     * @return  arpa    uusi y-koordinaatin arvo
     */
    
    public int yArvonArpominen() {
        Random r = new Random();
        int arpa = r.nextInt(600);
        return arpa;
    }

    /* 
     * Tätä metodia kutsutaan, kun tietyn asian täytyy tuhoutua.
     *
     * @param   tuhottava  asia, joka tuhotaan
     */
    
    public void tuhoudu(Rectangle tuhottava) {
        tuhottava.height = 0;
        tuhottava.width = 0;
    }
}
