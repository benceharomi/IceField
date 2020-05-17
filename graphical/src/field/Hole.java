package field;

import character.Character;
import enums.MovingParam;
import main.Controller;
import graphics.ViewController;
import graphics.field.*;

import java.io.Serializable;

/**
 * Egy lyuk tipusu mezot reprezental.
 */
public class Hole extends Field implements Serializable {

    /**
     * Hole konstruktor
     *
     * @param s: hómennyiség
     */
    public Hole(int s) {
        snow = s;
        canHoldItem = false;
        HoleView a = new HoleView(this);
    }

    /**
     * Hozzáadja a karaktert a Fieldhez. Meghívja a karakter holenotification
     * függvényét, hogyha az igazzal tér vissza (a karakternél van ScubaSuit), akkor
     * nem csinál semmit a karakterrel. Hogyha nincs, meghívj a a környező mezők
     * pullNotification függvényét. Hogyha a karaktert kimentették, akkor nem csinál
     * semmit, egyébként beállítja a karakter testhőjét 0-ra.
     *
     * @param c: a Character, amely hozzáadásra kerül a Fieldhez
     */
    @Override
    public void addCharacter(Character c, Field oldField) {
        characters.add(c);
        c.setField(this);
        ViewController.instance().updateAll();
        if (c.holeNotification()) {
            Controller.instance().moveOutput(c, oldField, this, MovingParam.HoleScuba, null);
            return;
        }
        boolean hasBeenPulled = false;
        for (Direction d : neighbours.keySet()) {
            if (!hasBeenPulled)
                hasBeenPulled = neighbours.get(d).pullNotification(c, this, oldField);
        }
        if (!hasBeenPulled) {
            c.setHeat(0);
            Controller.instance().moveOutput(c, oldField, this, MovingParam.Drowned, null);
        }
    }
}
