package field;

import character.Character;
import enums.MovingParam;
import main.Controller;
import graphics.field.*;

import java.io.Serializable;

/**
 * Instabil mezot reprezental, ami azt tudja, hogy ha tul sok karakter van
 * rajta, felborul, es a rajta levo karakterek meghalnak.
 */
public class Unstable extends Field implements Serializable {

    /**
     * Unstable konstruktor
     *
     * @param s: hómennyiség
     * @param c: kapacitás
     */
    public Unstable(int s, int c) {
        snow = s;
        capacity = c;
        canHoldItem = true;
        UnstableView un = new UnstableView(this);
    }

    /**
     * Hozzáad egy charactert az Unstable-höz, hogyha azzal együtt túlterhelődik a
     * field kapacitása, az összes karakter testhőjét 0-ra állítja.
     *
     * @param c: a Character, amely hozzáadásra kerül a Fieldhez
     */
    @Override
    public void addCharacter(Character c, Field oldField) {
        characters.add(c);
        c.setField(this);
        if (characters.size() > capacity) {
            for (Character c1 : characters)
                c1.setHeat(0);
            // Controller.instance().moveOutput(c, oldField, this,
            // MovingParam.UnstableFlipped, null);
            return;
        }
        // Controller.instance().moveOutput(c, oldField, this, MovingParam.SuccessFull,
        // null);
    }
}
