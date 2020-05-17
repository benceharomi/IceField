package character;

import direction.Direction;
import item.*;
import field.*;
import main.Main;
import skeleton.Skeleton;

import java.util.*;

/**
 Character
   Felelosseg
 A karakter kepessegeit mozgasat kezeli, valamint a targyakkal levo viselkedest.
   Attributumok
     -field: Field: Azt tarolja, melyik Fielden all a karakter
     -items: Item: A karakternel levo eszkozoket tarolja
   Metodusok
     bool assamblePartsC(): A karakter megprobalja osszeszerelni jelzoraketat ha sikerul
 true-val ter vissza ha nem akkor false-al.
     void Damage(): A karakter eletet (heat) csokkenti pl hovihar eseten
     bool DigC(): A mezon ho eltakaritasa (1 vagy 2 lapattal) ha tudott asni akkor ter
 vissza true-val egyebkent false-t add vissza
     bool EatC(): A karakter enni fog egy etelt es noveli az eletet eggyel ha tudott enni
 akkor ter vissza true-val egyebkent false-t add vissza
     bool GetItemC(): Felvesz egy itemet a mezorol ha fel tudta venni az eszkozt akkor
 ter vissza true-val egyebkent false-t add vissza
     void IncrementFood(): A karakternel levo elelem szamat noveli 1-gyel
     bool MoveC(d: Direction): A karakter lep d iranyba ha tudott lepni akkor ter vissza
 true-val egyebkent false-t add vissza
     bool Rescue(): A mellett levo mezon lyukba esett tarsat ha van nala kotel kimenti
 magahoz huzza ha sikerul akkor true-val ter vissza
     void SetHeat(i: int): A karakter eletet lehet beallitani a megadott i ertekre
     void SetRope(b: bool): Beallitja hogy a karakternel van-e kotel
     bool SpecialAbilityC(): abstract A karakterhez tartozo specialis kepesseget hajtja
 vegre amennyiben sikerul true-val ha nem akkor false-al ter vissza

 **/

public abstract class Character {
    protected List<Item> items = new ArrayList<>();
    protected Field field;

    public void setHeat(){
        Skeleton.instance().called(this, "setHeat");
        Skeleton.instance().returned();
    }

    public int AssemblePartsC() {
        Skeleton.instance().called(this, "AssemblePartsC");
        int partnum = 0;
        for (int i = 0; i < items.size(); i++) {
            boolean t = items.get(i).assemble();
            if (t) {
                partnum++;
            }
        }
        Skeleton.instance().returned();
        return partnum;
    }

    public void Damage() {
        Skeleton.instance().called(this, "Damage");
        Skeleton.instance().returned();
    }

    public Field getField(){
        Skeleton.instance().called(this, "getField");
        Skeleton.instance().returned();
        return field;
    }

    public boolean DigC() {
        Skeleton.instance().called(this, "DigC");
        if (field.RemoveSnow(1)) {
            for (int i = 0; i < items.size(); i++) {
                boolean t = items.get(i).dig(field);
                if (t) {
                    Skeleton.instance().returned();
                    return true;
                }
            }
            Skeleton.instance().returned();
            return true;
        }
        Skeleton.instance().returned();
        return false;
    }

    public boolean EatC() {
        Skeleton.instance().called(this, "EatC");
        for (int i = 0; i < items.size(); i++) {
            boolean t = items.get(i).eat(this);
            if (t) {
                Skeleton.instance().returned();
                return true;
            }
        }
        Skeleton.instance().returned();
        return false;
    }

    public boolean GetItemC() {
        Skeleton.instance().called(this, "GetItemC");
        Item nItem = field.GiveItem();
        if (nItem != null) {
            items.add(nItem);
            Skeleton.instance().returned();
            return true;
        } else {
            Skeleton.instance().returned();
            return false;
        }
    }

    public abstract boolean IncrementHeat();

    public boolean MoveC(Direction d) {
        Skeleton.instance().called(this, "MoveC");
        Field nfield = field.GetNeighbourField(d);
        if (nfield != null) {
            field.RemoveCharacter(this);
            nfield.AddCharacter(this);
            Skeleton.instance().returned();
            return true;
        } else {
            Skeleton.instance().returned();
            return false;
        }
    }

    public boolean Rescue(Character c, Hole h, Field newF) {
        Skeleton.instance().called(this, "Rescue");
        for (int i = 0; i < items.size(); i++) {
            boolean t = items.get(i).pull(c, h, newF);
            if (t) {
                Skeleton.instance().returned();
                return true;
            }
        }
        Skeleton.instance().returned();
        return false;
    }

    public abstract boolean SpecialAbilityC();

    public void addField(Field newF) {
        Skeleton.instance().called(this, "addField");
        field = newF;
        Skeleton.instance().returned();
    }


    public boolean HoleNotification() {
        Skeleton.instance().called(this, "HoleNotification");
        for (int i = 0; i < items.size(); i++) {
            boolean t = items.get(i).swim(this);
            if (t) {
                Skeleton.instance().returned();
                return true;
            }
        }
        Skeleton.instance().returned();
        return false;
    }

    public void SetItems(ArrayList<Item> i) {
        Skeleton.instance().called(this, "setItems");
        items = i;
        Skeleton.instance().returned();
    }
}
