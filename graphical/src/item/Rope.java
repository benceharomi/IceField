package item;

import enums.MovingParam;
import field.Field;
import field.Hole;
import character.Character;
import graphics.item.RopeView;
import main.Controller;

import java.io.Serializable;

/**
 * A kotel targyat reprezentalja. A jatekos ennek segitsegevel ki tud huzni egy
 * mellette levo jatekost egy lyukbol.
 */

public class Rope extends Item implements Serializable {
    /**
     * A huzast megvalostio fuggveny
     * 
     * @param character - A karakter akit ki akarunk huzni
     * @param hole      - A lyuk ahonnan kihuzzuk
     * @param field     - A mezo ahova huzzuk a karaktert.
     * @return Ha sikerul a muvelet true, ha nem false.
     */
    public boolean pull(Character character, Hole hole, Field field, Field fromField) {
        hole.removeCharacter(character);
        Controller.instance().moveOutput(character, fromField, hole, MovingParam.Andreascued, field);
        field.addCharacter(character, hole);
        return true;
    }

    @Override
    public void createItemView(Character c) {
        RopeView r = new RopeView(c, this);
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return 'R';
    }
}
