package character;

import enums.*;
import java.io.Serializable;
import java.util.ArrayList;

import field.Direction;
import field.Field;
import field.Hole;
import graphics.ViewController;
import item.Item;
import main.Controller;

/**
 * Felelosseg A kulonbozo karakterek absztrakt ososztalya, ami leirja a kozos
 * viselkedeseket es absztrakt fuggvenykent adja meg a karakter tipus-specifikus
 * fuggvenyt. Attributumok int heat: A karakter testhojet tarolja egy integer
 * valtozoban. int maxHeat: A karakter maximalis testhojet tarolja azert, hogy
 * ne lehessen tullepni. A ketfele karakternek mas a maximuma. ArrayList<Item>
 * items: A karakternel levo eszkozoket tarolja. field ownField: A mezo, amin a
 * karakter all. Metodusok: public int AssamblePartsC(): A karakter megprobalja
 * osszeszerelni jelzoraketat, a nala levo itemekre meghivja az Assemble
 * fuggvenyt. Megszamolja, hany targy tert vissza true-val, majd a true-k
 * szamaval visszater. public void Damage(): A karakter testhojet (heat)
 * csokkenti egyel. pl hovihar eseten public bool DeployC(): A jatekos satrat
 * probal allitani a mezon, amin all. Vegigmegy a tarolt targyakon es meghivja
 * rajuk a Deploy fuggvenyt, azzal a mezovel, amin all. Ha kap true
 * visszaterest, akkor true-val ter vissza, egyebkent false-szal ter vissza.
 * public bool DigC(): A mezon ho eltakaritasa, ha tudott asni akkor ter vissza
 * true-val egyebkent false-t add vissza. A karakternel tarolt targyakon
 * meghivja a Dig fuggvenyt, azzal a field-el, amin all. Ha van aso nala, akkor
 * ez is elvegzi az asast. public bool eatC(): A karakter enni probal egy etelt,
 * ami noveli az eletet eggyel, ha tudott enni. Meghivja a nala levo targyak eat
 * fuggvenyet sajat magaval. Ha tudott enni akkor ter vissza true-val egyebkent
 * false-t add vissza public bool GetItemC(): Megprobal felvenni egy itemet az
 * alatta levo mezo GiveItem fuggveny meghivasaval. Ha a fuggveny ad vissza
 * valamit, akkor ter vissza true-val egyebkent false-t add vissza. A kapott
 * item-et berakja az items taroloba. public bool HoleNotification(): ez, akkor
 * hivodik meg ha lyukba esett a karakter. Meghivja a nala levo targyakon a Swim
 * fuggvenyt sajat magaval. Ha barmelyik true-val ter vissza, akkor true-val ter
 * vissza egyebkent false. public bool IncrementHeat(): ez a fuggveny eggyel
 * noveli a jatekos eletet, ha az nem maximalis. Ha tudta novelni, akkor
 * true-val ter vissza egyebkent false-szal. public bool MoveC(Direction d): A
 * karakter lep d iranyba ugy, hogy az alatta levo field d iranyu szomszedjahoz
 * hozzaadja magat az AddCharacter fuggvennyel az eredeti mezorol pedig torli
 * magat. Ha tudott lepni akkor ter vissza true-val egyebkent false-t add
 * vissza. False-t abban az esetben kapunk, ha a karakter ki akar menni a
 * palyarol, ekkor ertelemszeruen nem kerul athelyezesre. public bool
 * Rescue(Character c): ez a fuggveny, akkor hivodik meg, ha egy mellette levo
 * mezon levo tarsa lyukba esett. A karakter meghivja a targyaira a Pull
 * fuggvenyt a bajba esett karakterrel es a tarsa alatt levo mezovel. Ha van
 * nala kotel, akkor kimenti a tarsat es true-val ter vissza. public void
 * SetField(field f): ezzel a fugvennyel adhatjuk hozza a karaktert egy mezohoz.
 * A jatek es a kulonbozo forgatokonyvek letrehozasahoz szukseges egyszeru
 * setter fuggveny. public void SetHeat(int i): A karakter eletet lehet
 * beallitani a megadott i ertekre ezzel a fuggvennyel, elsosorban a jatekos
 * eletenek 0-ra allitasara szolgal. public bool SpecialAbilityC(): abstract A
 * karakterhez tartozo specialis kepesseget hajtja vegre amennyiben sikerul
 * true-val ha nem akkor false-szal ter vissza.
 */

public abstract class Character implements Serializable {

	/**
	 * public int heat: A karakter testhojet tarolja egy integer valtozoban. public
	 * int maxHeat: A karakter maximalis testhojet tarolja azert, hogy ne lehessen
	 * tullepni. A ketfele karakternek mas a maximuma. public ArrayList<Item> items:
	 * A karakternel levo eszkozoket tarolja. public field ownField: A mezo, amin a
	 * karakter all.
	 */
	protected int heat;
	protected int maxHeat;
	protected ArrayList<Item> items;
	protected Field ownField;

	/**
	 * A karakter ososztaly konstruktora. Megadjuk benne, hogy hova legyen
	 * elhelyezve a karakter. A karakter inventorijat is megadjuk egy ures
	 * inventory-kent.
	 * 
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 */
	public Character(Field startField) {
		// Controller.instance().createCharacter(this);
		ownField = startField;
		items = new ArrayList<Item>();
	}

	/**
	 * A karakter ososztaly konstruktora, amelyben megadjuk a karakter kezdo mezojet
	 * es kezdo inventory-jat.
	 * 
	 * @param startField : A mezo amelyre a karaktert elhelyezzuk.
	 * @param inventory  : Az item array list ami a karakter inventory-jat fogja
	 *                   kepezni.
	 */
	public Character(Field startField, ArrayList<Item> inventory) {
		// Controller.instance().createCharacter(this);
		ownField = startField;
		items = inventory;
	}

	/**
	 * A karakter megprobalja osszeszerelni jelzoraketat, a nala levo itemekre
	 * meghivja az Assemble fuggvenyt. Megszamolja, hany targy tert vissza true-val,
	 * majd a true-k szamaval visszater.
	 * 
	 * @return
	 */
	public int assamblePartsC() {
		int counter = 0;
		if (items != null) {
			for (Item i : items) {
				if (i.assemble()) {
					counter++;
				}
			}
		}
		return counter;
	}

	/**
	 * A karakter testhojet (heat) csokkenti egyel. pl hovihar eseten.
	 */
	public void damage() {
		// Controller.instance().blizzardOutput(this, ownField, BlizzardParam.DAMAGE);
		heat--;
		return;
	}

	/**
	 * A jatekos satrat probal allitani a mezon, amin all. Vegigmegy a tarolt
	 * targyakon es meghivja rajuk a Deploy fuggvenyt, azzal a mezovel, amin all. Ha
	 * kap true visszaterest, akkor true-val ter vissza, egyebkent false-szal ter
	 * vissza.
	 * 
	 * @return : A visszateresben jelezzuk a targyhasznalat sikeresseget.
	 */
	public boolean deployC() {
		boolean ret = false;
		if (items != null) {
			Item del = null;
			for (Item i : items) {
				if (i.deploy(ownField)) {
					// Controller.instance().tentOutput(this, ownField, TentParam.BUILD);
					del = i;
					ret = true;
				}
			}
			removeItem(del);
			// Controller.instance().deleteItemsOutput(this, del);
		}
		// if (!ret)
		// Controller.instance().tentOutput(this, ownField, TentParam.NO_TENT);
		return ret;
	}

	/**
	 * A mezon ho eltakaritasa, ha tudott asni akkor ter vissza true-val egyebkent
	 * false-t add vissza. A karakternel tarolt targyakon meghivja a Dig fuggvenyt,
	 * azzal a field-el, amin all. Ha van aso nala, akkor ez is elvegzi az asast.
	 * 
	 * @return : A visszateresben jelezzuk a targyhasznalat sikeresseget.
	 */
	public boolean digC() {
		if (ownField.removeSnow()) {
			// Controller.instance().digOutput(this, ownField, ShovelParam.NO_SHOVEL);
			if (items != null) {
				for (Item i : items) {
					if (i.dig(ownField, this)) {
						// Controller.instance().digOutput(this, ownField, ShovelParam.SHOVEL);
						return true;
					}
				}
			}
			return true;
		}
		// Controller.instance().digOutput(this, ownField, ShovelParam.NO_SNOW);
		return false;
	}

	/**
	 * A karakter enni probal egy etelt, ami noveli az eletet eggyel, ha tudott
	 * enni. Meghivja a nala levo targyak Eat fuggvenyet sajat magaval. Ha tudott
	 * enni akkor ter vissza true-val egyebkent false-t add vissza
	 * 
	 * @return : A visszateresben jelezzuk a targyhasznalat sikeresseget.
	 */
	public boolean eatC() {
		int beforeHeat = heat;
		if (items != null) {
			for (Item i : items) {
				if (i.eat(this)) {
					if (beforeHeat < heat) {
						removeItem(i);
						// Controller.instance().deleteItemsOutput(this, i);
						// Controller.instance().eatoutput(this, EatParam.eat);
						return true;
					}
					// Controller.instance().eatoutput(this, EatParam.heatMax);
					return false;
				}
			}
		}
		// if (beforeHeat == heat) {
		// Controller.instance().eatoutput(this, EatParam.eatNoFood);
		// }
		return false;
	}

	/**
	 * Vissza adja a karakter aktualis testhojet.
	 * 
	 * @return : Az aktualis testho
	 */
	public int getHeat() {
		return heat;
	}

	/**
	 * Megprobal felvenni egy itemet az alatta levo mezo GiveItem fuggveny
	 * meghivasaval. Ha a fuggveny ad vissza valamit, akkor ter vissza true-val
	 * egyebkent false-t add vissza. A kapott item-et berakja az items taroloba.
	 * 
	 * @return
	 */
	public boolean getItemC() {
		Item newItem;
		if (items == null)
			items = new ArrayList<>();
		if ((newItem = ownField.giveItem()) != null) {
			items.add(newItem);
			newItem.createItemView(this);
			return true;
		}
		return false;
	}

	/**
	 * Ez, akkor hivodik meg ha lyukba esett a karakter. Meghivja a nala levo
	 * targyakon a Swim fuggvenyt sajat magaval. Ha barmelyik true-val ter vissza,
	 * akkor true-val ter vissza egyebkent false.
	 * 
	 * @return : A visszateresben jelezzuk a targyhasznalat sikeresseget.
	 */
	public boolean holeNotification() {
		if (items != null) {
			for (Item i : items) {
				if (i.swim(this)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ez a fuggveny eggyel noveli a jatekos eletet, ha az nem maximalis. Ha tudta
	 * novelni, akkor true-val ter vissza egyebkent false-szal.
	 * 
	 * @return : A testho noveleserol ad visszajelzest. Ha maximalis a testho nem
	 *         tudjuk novelni a testhot.
	 */
	public boolean incrementHeat() {
		if (heat == maxHeat) {
			return true;
		} else {
			heat++;
			return true;
		}
	}

	/**
	 * * A karakter lep d iranyba ugy, hogy az alatta levo field d iranyu
	 * szomszedjahoz hozzaadja magat az AddCharacter fuggvennyel az eredeti mezorol
	 * pedig torli magat. Ha tudott lepni akkor ter vissza true-val egyebkent
	 * false-t add vissza. False-t abban az esetben kapunk, ha a karakter ki akar
	 * menni a palyarol, ekkor ertelemszeruen nem kerul athelyezesre.
	 * 
	 * @param d : Az irany amibe a karaktert szeretnenk mozgatni.
	 * @return : A mozgas sikeresegerol ad visszajelzest.
	 */
	public boolean moveC(Direction d) {
		Field newField = ownField.getNeighbourField(d);
		if (newField == null) {
			return false;
		}
		ownField.removeCharacter(this);
		newField.addCharacter(this, ownField);
		return true;
	}

	/**
	 * eltavolitja a karaktertol a a parameterul kapott itemet
	 * 
	 * @param remov
	 */
	public void removeItem(Item remov) {
		items.remove(remov);
		ViewController.instance().removeItem(remov);
		// Controller.instance().deleteItemsOutput(this, remov);
	}

	/**
	 * Ez a fuggveny, akkor hivodik meg, ha egy mellette levo mezon levo tarsa
	 * lyukba esett. A karakter meghivja a targyaira a Pull fuggvenyt a bajba esett
	 * karakterrel es a tarsa alatt levo mezovel. Ha van nala kotel, akkor kimenti a
	 * tarsat es true-val ter vissza.
	 * 
	 * @param c    : A bajban levo karakter.
	 * @param hole : A lyuk amibe a bajba esett karakter esett.
	 * @return : A mentes sikeressegerol ad visszajelzest
	 */
	public boolean rescue(Character c, Hole hole, Field fromField) {
		if (items != null) {
			for (Item i : items) {
				if (i.pull(c, hole, ownField, fromField)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ezzel a fugvennyel adhatjuk hozza a karaktert egy mezohoz. A jatek es a
	 * kulonbozo forgatokonyvek letrehozasahoz szukseges egyszeru setter fuggveny.
	 * 
	 * @param f : A mezo amire a karakter mezojet allitjuk.
	 */
	public void setField(Field f) {
		ownField = f;
		return;
	}

	/**
	 * A karakter eletet lehet beallitani a megadott i ertekre ezzel a fuggvennyel,
	 * elsosorban a jatekos eletenek 0-ra allitasara szolgal.
	 * 
	 * @param i : A szam, amire a jatekos testhoje lesz allitva.
	 */
	public void setHeat(int i) {
		heat = i;
		return;
	}

	/**
	 * Visszaadja a mezot amin a jatekos eppen all.
	 * 
	 * @return A mezo amin a karakter all.
	 */
	public Field getField() {
		return ownField;
	}

	/**
	 * A karakterhez tartozo specialis kepesseget hajtja vegre amennyiben sikerul
	 * true-val ha nem akkor false-szal ter vissza.
	 * 
	 * @return
	 */
	public abstract boolean specialAbilityC();

}
