package item;

import skeleton.Skeleton;

/**
 * FlareGunParts
    Felelosseg
 A jelzoraketa alkatreszeit reprezentalja. osszesen harom szerepel beloluk a jatekban. Hogyha
 mindharmat osszegyujtottek a jatekosok, az Assamble fuggvennyel lehet befejezni a jatekot.
 Megjegyzes: azert nincs kulon osztaly a kulonbozo alkatreszeknek, mivel funkciojukban
 egyaltalan nem kulonboznek, a grafikus megjeleniteshez kesobb az osszes Item rendelkezni
 fog egy texture attributummal, amely alapjan a kulonbozo alkatreszeket meg lehet majd
 kulonboztetni, de ezt nem foglalja magaba az analizis modell.
    ososztalyok
 Item − FlareGunParts
    Metodusok
     Assamble(Character c): bool: A jatekosnak egy true ertekkel ter vissza es ezzel
 jelzi, hogy van FlareGunPartja a jatekosnak. Ezeket a true visszatereseket szamolja
 meg a rendszer.
 */

public class FlareGunPart extends Item{
    public FlareGunPart(String name, String type){
        Skeleton.instance().created(this, name, type);        
        Skeleton.instance().returned();
    }

    public boolean assemble() {
        Skeleton.instance().called(this, "assemble");
        Skeleton.instance().returned();
        return true;
    }
}
