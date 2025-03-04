package item;

import field.Field;
import graphics.item.IglooView;

/**
 * Az eszkimo a specialis kepessegevel tudja epiteni ezt a menedeket megvlosito
 * iglut.
 */
public class Igloo implements Shelter {
    /**
     * Mivel megvedi a bent allo karaktereket igy true-val ter vissza
     */
    @Override
    public boolean attack() {
        return true;
    }

    /**
     * Mivel az iglut nem lehet elpusztitani igy nem csinal semmit
     */
    @Override
    public void destroy(Field f) {
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    public char draw() {
        return 'I';
    }

    @Override
    public void createItemView(Field f) {
        IglooView iv = new IglooView(f, this);
    }
}
