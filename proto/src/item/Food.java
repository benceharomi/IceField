package item;

import character.Character;

import java.io.Serializable;

/**
 * Az etelt megvalosito osztaly. A jatekos a testhojenek novelesere tudja
 * hasznalni.
 */
public class Food extends Item implements Serializable {
    /**
     * Az evest megvalosito fuggveny.
     * 
     * @param c - A karakter aki eszik.
     * @return - true, ha a muvelet sikeres (ilyenkor a jatekos testhoje novekszik),
     *         false, ha a muvelet sikertelen (nincs etel a jatekosnal vagy a testho
     *         maximalis).
     */
    public boolean eat(Character c) {
        return c.incrementHeat();
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return 'F';
    }
}
