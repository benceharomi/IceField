package item;

import field.Field;

/**
 * Az epitheto dolgok interfesze.
 */
public interface Shelter {
    /**
     * A medve hivja meg ha a mezore lep.
     */
    public boolean attack();

    /**
     * Torolni tudja magat a parameterkent kapott fieldrol.
     * 
     * @param f
     */
    public void destroy(Field f);

    /**
     * A mapen valo kirajzolashoz.
     */
    public char draw();

    public void createItemView(Field f);
}
