package field;

import character.Character;
import enums.MovingParam;
import graphics.field.StableView;
import main.Controller;

import java.io.Serializable;

/**
 * Egy stabil tipusu mezot reprezental, ahova akarhany karakter lephet.
 */
public class Stable extends Field implements Serializable {

    /**
     * Stable konstruktor
     *
     * @param s: hómennyiség
     */
    public Stable(int s) {
        snow = s;
        capacity = 9;
        canHoldItem = true;
        StableView st = new StableView(this);
    }

    /**
     * Hozzáad egy karaktert a Stable-höz, mivel végtelen a kapacitása, nem
     * történhet semmi
     *
     * @param c: a Character, amely hozzáadásra kerül a Fieldhez
     */
    @Override
    public void addCharacter(Character c, Field oldField) {
        characters.add(c);
        c.setField(this);
        if (oldField != null)
            Controller.instance().moveOutput(c, oldField, this, MovingParam.SuccessFull, null);
    }
}
