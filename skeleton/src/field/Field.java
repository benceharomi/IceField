package field;

import item.*;
import direction.Direction;
import character.Character;
import main.Main;
import skeleton.Skeleton;

import java.util.*;

import static main.Main.SnowAmount;

/**
 * Absztrakt osztaly, amely azon mezoket kezeli, amelyek a terkepet kepezik, ahol a karakterek
 * mozoghatnak, illetve ahol a targyak elhelyezkedhetnek.
 */
public abstract class Field {

    private static final Readable InputStream = null;
	/// Attributes
    protected HashMap<Direction, Field> neighbours = new HashMap<>();
    protected Item item;
    protected ArrayList<Character> characters = new ArrayList<>();
    public void SetCharacters(ArrayList<Character> c) {
    	characters = c;
    }

    /**
     * Visszater a mezon allo karakterekkel.
     * @return
     */
    public ArrayList<Character> GetCharacter() {
        Skeleton.instance().called(this, "GetCharacter");
        Skeleton.instance().returned();
        return characters;
    }

    /**
     * Beallitja a mezo szomszedait minden iranyban.
     * @param d - az irany
     * @return - a mezo szomszedja a d iranyban.
     */
    public Field GetNeighbourField(Direction d) {
        Skeleton.instance().called(this, "GetNeighbourField");
        Skeleton.instance().returned();
        return neighbours.get(d);
    }

    /**
     *  Visszater egy targgyal a mezorol
     * @return - atargy a mezon, nullptr, ha nincs a mezon targy
     */
    public Item GiveItem() {
        Skeleton.instance().called(this, "GiveItem");
        int snow = Main.SnowAmount();
        Skeleton.instance().returned();
        if (snow != 0) {
            return null; // Ha va rajta ho, akkor nem kapunk targyat
        }
        else if (snow == 0 && item != null) {
            return item; // a targy
        }
        else {
            return null;
        }
    }

    /**
     *  Noveli a homennyiseget
     * @throws ArrayIndexOutOfBoundsException
     */
    public void IncrementSnow() throws ArrayIndexOutOfBoundsException {
        Skeleton.instance().called(this, "IncrementSnow");
        Skeleton.instance().returned();
    }

    /**
     * Iglut ad a mezohoz.
     */
    public boolean SetIgloo() {
        Skeleton.instance().called(this, "SetIgloo");
        
        System.out.println("Van iglo a mezon? (y/n)");
        Scanner scn = new Scanner(System.in);
        String answer = scn.nextLine();

        if (answer.toLowerCase().equals("y")) {
            Skeleton.instance().returned();
            return false;
        } else if (answer.toLowerCase().equals("n")) {
            Skeleton.instance().returned();
            return true;
        }
        Skeleton.instance().returned();
        return false;
    }

    /**
     * Hozzaad egy uj targyat a mezohoz
     * @param newItem - az uj targy
     * @throws IllegalArgumentException - ha a mezon van mar targy
     */

    public void SetItem(Item newItem) throws IllegalArgumentException {
        Skeleton.instance().called(this, "SetItem");
        if (item != null) throw new IllegalArgumentException("Ezen a mezon mar van targy!");
        else item = newItem;
        Skeleton.instance().returned();
    }

    /**
     * Beallitja a mezo szomszedait az adott iranyban
     */
    public void SetNeighbour(Direction d, Field f) throws  IllegalArgumentException {
        Skeleton.instance().called(this, "SetNeighbour()");
        neighbours.put(d, f);
        Skeleton.instance().returned();
    }

    /**
     * Felfedi a mezo fajtajat, azaz atallitja a revealed attributum erteket
     */
    public void SetRevealed() {
        Skeleton.instance().called(this, "SetRevealed");
        Skeleton.instance().returned();
    }

    /**
     * Hozzáad egy karaktert a mezőhöz
     * @param character
     */
    public abstract void AddCharacter(Character character);

    /**
     *A mezot a hovihar evvel a fuggvennyel sujtja, kulonbozo modositasokat
     * vegez a mezovel, illetve az esetlegesen rajta allo jatekos(ok)on.
     */
    public void BlizzardF() {
        Skeleton.instance().called(this, "BlizzardF");
        System.out.println("van iglu a mezon? (y/n): ");

        @SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
        String answer = scn.nextLine();
        answer = answer.toLowerCase();
        switch (answer) {
            case "y": break;
            case "n": {
                if (characters.size() > 0) {
                    for (Character c : characters)
                        c.Damage();
                }
                break;
            }
            default: break;
        }
        Skeleton.instance().returned();
    }


    public void RemoveCharacter(Character character) {
        Skeleton.instance().called(this, "RemoveCharacter");
        characters.remove(character);
        Skeleton.instance().returned();
    }

    /**
     * Havat takarit el a megadott mennyiseget(1 vagy 2).
     * A muvelet sikeressegetol fuggoen ter vissza.
     */
    public boolean RemoveSnow(int num) {
        Skeleton.instance().called(this, "RemoveSnow");
        Skeleton.instance().returned();
        int snow = SnowAmount();
        if(snow == 0)
            return false;
        else
            return true;

    }
}
