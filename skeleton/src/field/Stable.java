package field;

import character.Character;
import skeleton.Skeleton;

/**
 Stable
 Felelosseg
 A stabil jegmezot reprezentalja. Ezen mezok stabilitasa -1, ami a vegtelent jelenti.
 ososztalyok
 Field - > Stable
 */

public class Stable extends Field{

    public Stable(String name, String type){
        skeleton.Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    /**
     * Absztakt fuggveny, amely hozzaadja a
     * parameterkent megkapott karaktert a Field-hez, illetve egyeb valtozasokat tesz, attol
     * fuggoen, hogy mely leszarmazottakban valosul meg.
     * param character - a hozzaadott karakter
     */
    @Override
    public void AddCharacter(Character c) {
        Skeleton.instance().called(this, "AddCharacter()");
        characters.add(c);
        Skeleton.instance().returned();
    }
}
