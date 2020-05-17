package field;

import enums.BlizzardParam;
import enums.ItemParam;
import enums.SpecParam;
import item.Item;
import item.Shelter;
import character.Character;
import main.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Egy absztrakt osztaly, mely a palyat alkoto mezoket testesiti meg.
 */
public abstract class Field {
    protected static final int MAXSNOW = 4;
    protected int capacity;
    protected boolean revealed = false;
    protected int snow;
    protected ArrayList<Character> characters = new ArrayList<Character>();
    protected Item item;
    protected HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>();
    protected Shelter shelter;

    /**
     * Hozzáadja a megadott karaktert a Fieldhez, az alosztályokban van
     * megvalósítva.
     *
     * @param c: a Character, amely hozzáadásra kerül a Fieldhez
     */
    public abstract void addCharacter(Character c, Field oldfield);

    /**
     * A függvény megnöveli a mezőn található hómennyiséget, hogyha az még nem
     * maximális, és sebzi a karaktereket, hogyha nincs shelter a mezőn.
     */
    public void blizzardF() {
        if (shelter == null)
            for (Character c : characters)
                c.damage();
        else
            Controller.instance().blizzardOutput(null, this, BlizzardParam.SAFE);
        if (!Controller.instance().isRandomBlizzard()
                || (Controller.instance().isRandomBlizzard() && new Random().nextInt(100) < 30))
            if (snow < MAXSNOW) {
                snow++;
                if (characters.size() == 0)
                    Controller.instance().blizzardOutput(null, this, BlizzardParam.NO_EFFECT);
            } else
                Controller.instance().blizzardOutput(null, this, BlizzardParam.SNOW_AT_MAX);
    }

    /**
     * Meghívja a mezőn lévő Shelter Destroy függvényét.
     */
    public void destroyShelter() {
        if (shelter != null)
            shelter.destroy(this);
    }

    /**
     * Visszaadja a megadott irányban lévő szomszédos Fieldet.
     *
     * @param d: az irány, amelyből szükség van a szomszédos mezőre.
     * @return visszaadja a szomszédos Fieldet vagy nullt, ha nincs szomszéd a
     *         megadott irányban.
     */
    public Field getNeighbourField(Direction d) {
        if (neighbours.containsKey(d))
            return neighbours.get(d);
        return null;
    }

    /**
     * Visszaadja a mezőn tárolt Itemet
     *
     * @return a mezőn tárolt Itemmel, vagy ha a mezőn nincs Item, nullal tér
     *         vissza.
     */
    public Item giveItem() {
        if (snow > 0) {
            Controller.instance().itemOutput(this, ItemParam.SNOW);
            return null;
        }
        if (item == null) {
            Controller.instance().itemOutput(this, ItemParam.NO_ITEM);
            return null;
        }
        Controller.instance().itemOutput(this, ItemParam.NORMAL);
        Item temp = item;
        item = null;
        return temp;
    }

    /**
     * Ellenőrzi, hogy a Fielden van-e shelter, hogyha nincs, vagy ha a Shelter
     * sátor, akkor az összes Fielden lévő karakter testhőjét lenullázza.
     */
    public boolean polarBearAttack() {
        boolean wasProtected = false;
        if (shelter != null)
            wasProtected = shelter.attack();
        if (!wasProtected) {
            for (Character c : characters) {
                c.setHeat(0);
            }
        }
        if (characters.size() == 0)
            wasProtected = true;
        return !wasProtected;
    }

    /**
     * Meghívja a mezőn álló karakterek rescue függvényét
     *
     * @param cToRescue a karakter, akit ki kell menteni
     * @param h         a lyuk, amelyből ki kell menteni a karaktert
     * @return hogyha történt mentés, akkor true-val, egyébként false-szal tér
     *         vissza.
     */
    public boolean pullNotification(Character cToRescue, Hole h, Field fromField) {
        boolean hasBeenPulled = false;
        for (Character c : characters) {
            if (!hasBeenPulled) {
                hasBeenPulled = c.rescue(cToRescue, h, fromField);
                return hasBeenPulled;
            }
        }
        return hasBeenPulled;
    }

    /**
     * Eltávolítja a megadott karaktert a mezőről, ha az a mezőn áll.
     *
     * @param c: Az eltávolítanivaló karakter
     */
    public void removeCharacter(Character c) {
        // if (characters.contains(c))
        characters.remove(c);
    }

    /**
     * Megpróbál eltávolítani egy egység havat a mezőről (sikeres, ha a mezőn van
     * hó)
     *
     * @return Az eltávolítás sikerességével tér vissza
     */
    public boolean removeSnow() {
        if (snow != 0) {
            snow--;
            return true;
        }
        return false;
    }

    /**
     * Beaállítja a mező által tárolt tárgyat a paraméterként átadottra.
     *
     * @param i a beállítani kívánt tárgy.
     * @return A beállítás sikerességével tér vissza
     */
    public boolean setItem(Item i) {
        if (item == null) {
            item = i;
            return true;
        }
        return false;
    }

    /**
     * Beállítja a megadott irányba szomszédnak a megadott Fieldet
     *
     * @param d: az irány
     * @param f: a szomszédos Field
     * @return true, hogyha sikeres volt a beállítás, false, hogyha már van abban az
     *         irányban szomszédja a mezőnek
     */
    public boolean setNeighbour(Direction d, Field f) {
        if (neighbours.containsKey(d))
            return false;
        neighbours.put(d, f);
        return true;
    }

    /**
     * Láthatóra állítja a mező kapacitását.
     */
    public void setRevealed() {
        revealed = true;
        Controller.instance().eszkimoSpecialAbility(this, capacity, SpecParam.explored);
    }

    /**
     * Beállítja a mezőn tárolt menedéket a paraméterben átadottra
     *
     * @param s: az új menedék a Fielden
     * @return false, hogyha már volt menedék a Fielden, true, hogyha még nem volt
     */
    public boolean setShelter(Shelter s) {
        if (shelter != null) {
            return false;
        }
        shelter = s;
        return true;
    }

    public void removeShelter() {
        shelter = null;
    }

    /**
     * az eltárolt Charactertömb gettere
     */
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * a fielden megtalálható hómennyiség gettere
     */
    public int getSnow() {
        return snow;
    }

    /**
     * a fielden tárolt Item gettere
     */
    public Item getItem() {
        return item;
    }

    /**
     * a Fielden tárolt Shelter gettere
     */
    public Shelter getShelter() {
        return shelter;
    }

    /**
     * a Field kapacitásának láthatóságának gettere
     */
    public boolean getRevealed() {
        return revealed;
    }

    /**
     * a Field kapacitásának gettere
     */
    public int getCapacity() {
        return capacity;
    }
}
