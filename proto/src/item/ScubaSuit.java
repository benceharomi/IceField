package item;

import character.Character;

import java.io.Serializable;

/**
 * A buvarruha targyat reprezentalo osztaly. Ha a karakter viseli, akkor nem
 * fullad meg a vizben.
 */
public class ScubaSuit extends Item implements Serializable {

    /**
     * Visszatert egy true ertekkel, ezzel jelzi, hogy a jatekosnal van buvarruha.
     * 
     * @param c - A jatekos
     * @return true, ha van a jatekosnal ruha, false ha nincs.
     */
    public boolean swim(Character c) {
        return true;
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return '$';
    }
}
