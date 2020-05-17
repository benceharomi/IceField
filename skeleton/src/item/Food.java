package item;

import character.Character;
import skeleton.Skeleton;

/**Food
   Felelosseg
 Az etel targyat reprezentalja. A jatekos testhojenek novelesere tudja hasznalni.
   ososztalyok
 Item -> Food
   Metodusok
    bool Eat(Character c): meghivja a parameterkent kapott Character IncrementHeat()
 fuggvenyet, ami megnoveli eggyel a hoenergia attributumanak erteket, true-
 val ter vissza, ha sikerul hoenergiat novelni ha nem akkor false-szal.
 */

public class Food extends Item {
    public Food(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    public boolean eat(Character c) {
        Skeleton.instance().called(this, "eat");
        Skeleton.instance().returned();
        return c.IncrementHeat();
    }
}
