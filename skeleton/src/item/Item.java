package item;

import field.*;
import character.Character;
import skeleton.Skeleton;

/**
 Item
    Felelosseg
 A targyak ososzalya, benne az osszes fuggveny megvan, ami a targyakban, azonban
 mindegyik torzse ures. Csak abban az osztalyban van megvalositva az osszes fuggveny, ahol
 relevans.
    Metodusok
     bool Assmeble(): a targyak azon fuggvenye, amely a harom pisztolyalkatresz
 osszeszereleset idezi elo. A visszateres logikai tartalma attol fugg, hogy az adott
 akcio sikeres volt-e vagy sem. Ez a fuggveny csak a FlareGunParts osztalyban van
 megvalositva.
     bool Dig(Field: f): a targyak azon fuggvenye, amely segitsegevel a parameterkent
 kapott mezon ashat az adott targgyal (2 egysegnyi havat). A visszateres logikai tartalma
 attol fugg, hogy az adott akcio sikeres volt-e vagy sem. Ez a fuggveny csak
 a Shovel osztalyban van megvalositva.
     bool Eat(Character c): a targyak azon fuggvenye, amely segitsegevel a parameterkent
 kapott karakter megeheti az adott targyat. A visszateres logikai tartalma attol fugg,
 hogy az adott akcio sikeres volt-e vagy sem. Ez a fuggveny csak a Food osztalyban van
 megvalositva.
     bool Pull(Character c, Hole h): a targyak azon fuggvenye, amely segitsegevel
 a parameterkent atadott karaktert kihuzzak a parameterkent kapott lyukbol. A
 visszateres logikai tartalma attol fugg, hogy az adott akcio sikeres volt-e vagy sem. Ez
 a fuggveny csak a Rope osztalyban van megvalositva.
     bool Wet(Character c): a targyak azon fuggvenye, amely segitsegevel a
 parameterkent kapott karakter amikor vizbe esik megmenekulhet. A visszateres logikai
 tartalma attol fugg, hogy az adott akcio sikeres volt-e vagy sem. Ez a fuggveny csak
 a ScubaSuit osztalyban van megvalositva.
 */


public abstract class Item {
    public boolean assemble(){
        Skeleton.instance().called(this, "assemble");
        Skeleton.instance().returned();
        return false;
    }

    public boolean dig(Field f){
        Skeleton.instance().called(this, "dig");
        Skeleton.instance().returned();
        return false;
    }

    public boolean eat(Character c){
        Skeleton.instance().called(this, "eat");
        Skeleton.instance().returned();
        return false;
    }

    public boolean pull (Character c, Hole h, Field newF){
        Skeleton.instance().called(this, "pull");
        Skeleton.instance().returned();
        return false;
    }

    public boolean swim (Character c){
        Skeleton.instance().called(this, "swim");
        Skeleton.instance().returned();
        return false;
    }
}
