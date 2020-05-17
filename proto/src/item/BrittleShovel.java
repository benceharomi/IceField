package item;

import character.Character;
import field.Field;

import java.util.ArrayList;

/**
 * A BrittleShovel osztaly a torekeny asot reprezentalja a programban, amely 3
 * hasznalat utan osszetorik. Hogyha asas kozben van egy karakternel
 * BrittleShovel, akkor 1 helyett 2 egyseg havat kepes eltakaritani egy mezorol.
 */
public class BrittleShovel extends Shovel {
    private int durability = 3; // az aso elettartalma

    /**
     * Megproba asni ha sikerul csokken a durability ha ez eleri a 0-at akkor
     * elpusztul es kiszedi magat a listabol
     * 
     * @param f         a mezo ahol asni kell
     * @param character a karakter
     * @return ha sikerult asni true-val ha nem akkor false-al ter vissza
     */
    public boolean dig(Field f, Character character) {
        if (f.removeSnow()) {
            durability--;
            if (durability == 0) {
                character.removeItem(this);
            }
            return true;
        }
        return false;
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    public char draw() {
        return 'B';
    }
}
