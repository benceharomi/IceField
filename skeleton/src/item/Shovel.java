package item;

import field.*;
import skeleton.Skeleton;

/**
 * Shovel
   Felelosseg
 Az aso targyat reprezentalja. Ha birtokosnak van asoja, akkor eggyel tobb egyseg havat tud
 eltakaritani.
   ososztalyok
 Item -> Shovel
   Metodusok
    bool Dig(Field: f): az aso osztaly azon fuggvenye, amely segitsegevel a
 parameterkent kapott mezon ashat 2 egysegnyi havat. A visszateres logikai tartalma
 attol fugg, hogy az adott akcio sikeres volt-e vagy sem.
 */

public class Shovel extends Item{
    public Shovel(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }
    public boolean dig(Field f) {
        Skeleton.instance().called(this, "dig");
        f.RemoveSnow(1);
        Skeleton.instance().returned();
        return true;
    }
}
