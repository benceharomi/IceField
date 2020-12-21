package item;

import character.Character;
import field.Field;
import graphics.item.ShovelView;

import java.io.Serializable;

/**
 * Az aso targyat reprezentalja. Ha a birtokosnak van asoja egyel tobb havat tud
 * eltavolitani az adott mezorol
 */
public class Shovel extends Item implements Serializable {
    /**
     * Az asast megvalosito fuggveny.
     * 
     * @param field - Az a mezo ahol asunk
     * @return - Sikeres muvelet eseten true, egyebkent false.
     */
    public boolean dig(Field field, Character character) {
        return field.removeSnow();
    }

    @Override
    public void createItemView(Character c) {
        ShovelView sh = new ShovelView(c, this);
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return 'S';
    }

    public int getLife(){return 1;}
}
