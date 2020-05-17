package item;

import character.Character;
import field.*;

/**
 * A targyak absztrakt ososztalya, benne az osszes fuggveny megvan, ami a
 * targyakban, azonban mindegyik torzse csak egy false-szal valo visszaterest
 * tartalmaz. Csak abban az osztalyban van megvalositva az osszes fuggveny, ahol
 * relevans. A metodusok mukodeset nem reszletezzuk egyesevel, mivel torzsuk
 * csak egy hamis visszaterest tartalmaz.
 */
public abstract class Item {
    public boolean assemble() {
        return false;
    }

    public boolean dig(Field f, Character character) {
        return false;
    }

    public boolean eat(Character c) {
        return false;
    }

    public boolean pull(Character c, Hole h, Field newF, Field fromField) {
        return false;
    }

    public boolean swim(Character c) {
        return false;
    }

    public boolean deploy(Field f) {
        return false;
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    public abstract char draw();
}
