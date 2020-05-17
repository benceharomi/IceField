package item;

import character.Character;
import field.*;
import skeleton.Skeleton;

/**
 Rope
   Felelosseg
 A kotel targyat reprezentalja. A jatekos ennek segitsegevel ki tudja huzni egy mellette levo
 jatekost a lyukbol.
   ososztalyok
 Item -> Rope
   Metodusok
    void bool Pull(Character c, Hole h): fuggveny, amellyel a parameterkent
 kapott karater kihuzasat valositja meg a parameterkent kapott lyukbol. A logikai
 visszateres erteke attol fugg, hogy a muvelet sikeres volt-e vagy sem.
 */

public class Rope extends Item{
    public Rope(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    public boolean pull(Character c, Hole h, Field newF) {
        Skeleton.instance().called(this, "pull");
        h.RemoveCharacter(c);
        c.addField(newF);
        newF.AddCharacter(c);
        Skeleton.instance().returned();
        return true;
    }
}
