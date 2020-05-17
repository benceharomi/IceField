package item;

import character.Character;
import skeleton.Skeleton;

/**
 * ScubaSuit
  Felelosseg
 A buvarruha targyat reprezentalja. Ha a karakter viseli, akkor nem fullad meg a vizben.
  Ososztalyok
 Item->Shovel
  Metodusok
    bool Wet(Character c): visszatert egy true ertekkel, ezzel jelzi, hogy a jatekosnal
 van buvarruha.
 */

public class ScubaSuit extends Item{
    public ScubaSuit(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }
    public boolean swim(Character c) {
        Skeleton.instance().called(this, "swim");
        Skeleton.instance().returned();
        return true;
    }
}
