package character;

import java.io.Serializable;
import java.util.ArrayList;

import field.Direction;
import field.Field;
import item.Item;
import graphics.character.*;

/**
 * Az kutato tipuso karakterek osztalya. Az kutatora jellemzo, Character-tol
 * eltero viselkedest es tulajdonsegokat tarolja.
 */
public class Explorer extends Character implements Serializable {
	/**
	 * A felfedezo alap konstruktora, amiben parameterkent megkpaja a mezot, amire
	 * el kell helyezni a
	 * 
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 */
	public Explorer(Field startField) {
		super(startField);
		maxHeat = 4;
		heat = maxHeat;
		ExplorerView exp = new ExplorerView(this);
	}

	/**
	 * A felfedezo ososztaly konstruktora, amelyben megadjuk a karakter kezdo
	 * mezojet es kezdo inventory-jat.
	 * 
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 * @param bodyHeat   : A testho amit szeretnenk az uj eszkimonak megadni.
	 * @param inventory  : Az item array list ami a karakter inventory-jat fogja
	 *                   kepezni.
	 */
	public Explorer(Field startField, int bodyHeat, ArrayList<Item> inventory) {
		super(startField, inventory);
		maxHeat = 4;
		if (bodyHeat > maxHeat) {
			heat = maxHeat;
			System.out.println("Tul nagy testho lett megadva, maximalis erteket kapott a karakter.");
		} else {
			heat = bodyHeat;
		}
		ExplorerView exp = new ExplorerView(this);
	}

	/**
	 * A kutato specialis kepesseget hajtja vegre. A kutato meghivja az alatta levo
	 * mezo osszes szomszedjanak SetRevealed fuggvenyet, es vegul true-val ter
	 * vissza.
	 */
	public boolean specialAbilityC() {
		for (Direction d : Direction.values()) {
			Field f = ownField.getNeighbourField(d);
			if (f != null)
				f.setRevealed();
		}
		return true;
	}

}
