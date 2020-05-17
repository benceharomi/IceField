package character;

import java.io.Serializable;
import java.util.ArrayList;

import enums.SpecParam;
import field.Field;
import graphics.character.EskimoView;
import graphics.character.ExplorerView;
import item.Igloo;
import item.Item;
import main.Controller;

/**
 * Az eszkimo tipuso karakterek osztalya. Az eszkimokra jellemzo, Character-tol
 * eltero viselkedest es tulajdonsegokat tarolja.
 */
public class Eskimo extends Character implements Serializable {

	/**
	 * Az eszkimo alap konstruktora, amiben parameterkent megkpaja a mezot, amire el
	 * kell helyezni a
	 * 
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 */
	public Eskimo(Field startField) {
		super(startField);
		maxHeat = 5;
		heat = maxHeat;
		EskimoView es = new EskimoView(this);
	}

	/**
	 * Az eszkimo ososztaly konstruktora, amelyben megadjuk a karakter kezdo mezojet
	 * es kezdo inventory-jat.
	 *
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 * @param bodyHeat   : A testho amit szeretnenk az uj eszkimonak megadni.
	 * @param inventory  : Az item array list ami a karakter inventory-jat fogja
	 *                   kepezni.
	 */
	public Eskimo(Field startField, int bodyHeat, ArrayList<Item> inventory) {
		super(startField, inventory);
		maxHeat = 5;
		if (bodyHeat > maxHeat) {
			heat = maxHeat;
			System.out.println("Tul nagy testho lett megadva, maximalis erteket kapott a karakter.");
		} else {
			heat = bodyHeat;
		}
		EskimoView es = new EskimoView(this);
	}

	/**
	 * Az eszkimo specialis kepesseget hajtja vegre, ami egy iglu epitese. Az
	 * eszkimo letrehoz egy iglut, es meghivja az alatta levo mezo SetShelter
	 * fuggvenyet az uj iglu-val, mint parameterrel. Ha sikerult lerakni az iglut
	 * true-val ter vissza egyebkent false-szal.
	 * 
	 * @return : A kepesseg hasznalatanak sikeressegerol ad visszajelzest.
	 */
	public boolean specialAbilityC() {
		Igloo newIgloo = new Igloo();
		if (ownField.setShelter(newIgloo)) {
			Controller.instance().eszkimoSpecialAbility(ownField, 0, SpecParam.build);
			return true;
		}
		Controller.instance().eszkimoSpecialAbility(ownField, 0, SpecParam.cantBuild);
		return false;
	}

}
