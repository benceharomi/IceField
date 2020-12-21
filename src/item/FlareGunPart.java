package item;

import character.Character;
import graphics.item.FlareGunPartView;

import java.io.Serializable;

/**
 * A jelzoraketa alkatreszeit reprezentalja. osszesen harom szerepel beloluk a
 * jatekban. Amennyiben mindharmat osszegyujtottek a jatekosok, hogyha egy
 * mezore gyulnek, befejezhetik a jatekot. Megjegyzes: azert nincs kulon osztaly
 * a kulonbozo alkatreszeknek, mivel funkciojukban egyaltalan nem kulonboznek, a
 * grafikus megjeleniteshez kesobb az osszes Item rendelkezni fog egy texture
 * attributummal, amely alapjan a kulonbozo alkatreszeket meg lehet majd
 * kulonboztetni, de ezt nem foglalja magaba a modell.
 */
public class FlareGunPart extends Item implements Serializable {
    private int num;

    public FlareGunPart(int n){num = n;}
    /**
     * Az osszeszerelest elvegzo fuggveny.
     * 
     * @return - Ha van a jatekosnak alkatresze, akkor true-val ter vissza,
     *         egyebkent false-szal
     */
    public boolean assemble() {
        return true;
    }

    @Override
    public void createItemView(Character c) {
        FlareGunPartView gp = new FlareGunPartView(c, this, num);
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return 'f';
    }
}
