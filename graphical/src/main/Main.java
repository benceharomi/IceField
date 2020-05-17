package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import character.Eskimo;
import character.Explorer;
import character.PolarBear;
import field.Direction;
import field.Field;
import graphics.ViewController;
import item.BrittleShovel;
import item.Food;
import item.Item;
import item.Rope;
import item.ScubaSuit;
import item.Shovel;
import item.Tent;
import map.Map;
import team.Team;

public class Main {

	/**
	 * a scanner, amivel a beolvasas tortenik
	 */
	private static Scanner scn = new Scanner(System.in);
	/**
	 * a csapat
	 */
	private static Team team = null;
	/**
	 * a terkep, amelyen a jatek jatszodik
	 */
	private static Map map = null;

	/**
	 * Main fuggveny. Az input olvasasasaval es feldolgozasaval foglalkozik. Addig
	 * olvassa az inputot, amig nem kap ures sort. A kapott inputot a ' '
	 * karaktereknel feldarabolja. Az elso stringet tekinti a fo utasitasnak a
	 * tobbit pedig parameternek. A kapott stingeket tovabb adja vegrehajtasra.
	 * 
	 * @param args
	 */

	static JFrame f;

	public static void main(String[] args) {
		Game g = new Game();
		ViewController.instance().addViewCollection();
		ViewController.instance().startGame(g);
	}

	/**
	 * fuggveny a medve mozgatasara, ha a mozgatas manualis
	 * 
	 * @param input irany
	 */
	private static void bearMove(String input) {
		Direction direction;
		switch (input) {
			case "NORTH":
				direction = Direction.NORTH;
				break;
			case "EAST":
				direction = Direction.EAST;
				break;
			case "SOUTH":
				direction = Direction.SOUTH;
				break;
			case "WEST":
				direction = Direction.WEST;
				break;
			default:
				System.out.print("Adj meg egy iranyt a medvenek");
				return;
		}
		team.moveBear(direction);
	}

	/**
	 * Az execute task eldonti, hogy az inputon kapott utasitas micsoda, majd
	 * meghivja a szukseges fuggvenyt a szukseges parameterekkel.
	 * 
	 * @param command    : A fo parancs, amit az inputon kapott. Ha nem felel meg
	 *                   egyik simert parancsnak sem visszater.
	 * @param parameters : A fo parancs mellett kapott egyeb parameterek
	 */
	private static void executeTask(String command, ArrayList<String> parameters) {

		switch (command) {
			case "AssembleParts":
				commandAssambleParts();
				break;
			case "Blizzard":
				commandBlizzard(parameters);
				break;
			case "Dig":
				commandDig();
				break;
			case "Deploy":
				commandDeploy();
				break;
			case "Eat":
				commandEat();
				break;
			case "EndTurn":
				commandEndTurn();
				break;
			case "Eskimo":
				commandEskimo(parameters);
				break;
			case "Explorer":
				commandExplorer(parameters);
				break;
			case "GameStart":
				commandGameStart(parameters);
				break;
			case "GetItem":
				commandGetItem();
				break;
			case "Itemcreate":
				commandItemcreate(parameters);
				break;
			case "Loadstate":
				commandLoadState(parameters);
				break;
			case "Mapcreate":
				commandMapCreate(parameters);
				break;
			case "Move":
				commandMove(parameters);
				break;
			case "Polarbear":
				commandPolarbear(parameters);
				break;
			case "Save":
				commandSave(parameters);
				break;
			case "SpecialAbility":
				commandSpecialAbility();
				break;
			case "Setting":
				commandSettings(parameters);
				break;
			case "Teszt":
				commandTeszt(parameters);
				break;
			default:
				System.out.println("Hibas utasitas: " + command);
				break;
		}

	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandAssambleParts() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		if (!team.assembleParts()) {
			team.endAction();
		}
	}

	/**
	 * A hovihar vegrehajtasara szolgalo fuggveny. Megnezi eloszor, hogy a
	 * beallitasok szerint a jatekos kezeli-e a vihart vagy sem. Ha nem, akkor nem
	 * csinal semmit es visszater. Ha a jatekos kezeli a vihart megnezi, hogy van-e
	 * palya amin vegre hajthato a vihar. Ha van palya, akkor a Controllerben
	 * beallitja a viharnak szukseges parametereket es meghivja a palya hovihar
	 * fuggvenyet.
	 * 
	 * @param parameters : Helyes esetben azon mezok sorszamat tarolja, amelyekre
	 *                   meg kell majd hivni a hovihart.
	 */
	private static void commandBlizzard(ArrayList<String> parameters) {
		if (Controller.isRandomBlizzard()) {
			System.out.println("A vihar veletlenszerure van allitva, ezert nem rianyithato");
			System.out.println("A Setting RandomBlizzard false paranccsal allithato");
			return;
		}
		if (map == null) {
			System.out.println("Nincs palya letrehozva");
			return;
		}

		Controller.setBlizzardParameters(parameters);
		map.blizzardM();
		team.endAction();

	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandDig() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.dig();
		team.endAction();

	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandDeploy() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.deploy();
		team.endAction();

	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandEat() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.eat();
		team.endAction();

	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandEndTurn() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.endTurn();
	}

	/**
	 * Az eszkimo letrehozasare szolgalo parancs. Ha nincs team, akkor nem hajtodik
	 * vegre az utasitas. A team ellenorzese utan ellenorzi a kapott parametereket.
	 * Ha nem kapott tobb parametert, akkor az utasitas hibas mivel szukseges kezo
	 * mezot megadni a jatekos elhelyezesehez. Ha egy parametert kapott, akkor annak
	 * a mezonek kell lennie. Megszerzi a mezot es letrehozza es beallitja a
	 * karaktert, majd hozzaadja a csapathoz. Ha ket parametert kapott, akkor annak
	 * a karakter testhojenek kell lennie. Ebben az esetben azt is beallitjuk
	 * pluszban a konstruktorban. Ha kettonel tobb parameterunk van az azt jelenti,
	 * hogy megadtuk a karakter inventorijat is. Ekkor ezekkel a parameterekkel
	 * meghivjuk a inventoryCreate fuggveny, amely vissza adja a mar valos targyak
	 * tombjet. A targyak sikeres megszerzese utan azt is atadjuk a karakter
	 * konstruktoraban.
	 * 
	 * @param parameters : Helyes esetben : kezdo mezo - testho - targyak : ezek
	 *                   kozul a kezdo mezo megadasa kotelezo a tobbi opcionalis
	 */
	private static void commandEskimo(ArrayList<String> parameters) {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		if (parameters.isEmpty()) {
			System.out.println("Kotelezo mezot megadni, nem jott letre karakter");
			return;
		}

		int place = Integer.parseInt(parameters.get(0));
		ArrayList<Field> fields = map.getFields();

		Field startField = fields.get(place - 1);

		int numOfParameters = parameters.size();

		Eskimo newCharacter = null;

		if (numOfParameters == 1) {
			newCharacter = new Eskimo(startField);
		}
		if (numOfParameters == 2) {
			int bodyHeat = Integer.parseInt(parameters.get(1));
			ArrayList<Item> noItems = null;
			newCharacter = new Eskimo(startField, bodyHeat, noItems);
		}
		if (numOfParameters > 2) {
			int bodyHeat = Integer.parseInt(parameters.get(1));
			parameters.remove(0);
			parameters.remove(0);
			ArrayList<Item> items = inventoryCreate(parameters);
			newCharacter = new Eskimo(startField, bodyHeat, items);
		}
		startField.addCharacter(newCharacter, null);
		team.addCharacter(newCharacter);
		Controller.instance().drawMap();
		return;
	}

	/**
	 * A kutato karakter letrehozasare szolgalo parancs. Ha nincs team, akkor nem
	 * hajtodik vegre az utasitas. A team ellenorzese utan ellenorzi a kapott
	 * parametereket. Ha nem kapott tobb parametert, akkor az utasitas hibas mivel
	 * szukseges kezo mezot megadni a jatekos elhelyezesehez. Ha egy parametert
	 * kapott, akkor annak a mezonek kell lennie. Megszerzi a mezot es letrehozza es
	 * beallitja a karaktert, majd hozzaadja a csapathoz. Ha ket parametert kapott,
	 * akkor annak a karakter testhojenek kell lennie. Ebben az esetben azt is
	 * beallitjuk pluszban a konstruktorban. Ha kettonel tobb parameterunk van az
	 * azt jelenti, hogy megadtuk a karakter inventorijat is. Ekkor ezekkel a
	 * parameterekkel meghivjuk a inventoryCreate fuggveny, amely vissza adja a mar
	 * valos targyak tombjet. A targyak sikeres megszerzese utan azt is atadjuk a
	 * karakter konstruktoraban.
	 * 
	 * @param parameters : Helyes esetben : kezdo mezo - testho - targyak : ezek
	 *                   kozul a kezdo mezo megadasa kotelezo a tobbi opcionalis
	 */
	private static void commandExplorer(ArrayList<String> parameters) {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}

		int place = Integer.parseInt(parameters.get(0));
		Field startField = map.getFields().get(place - 1);

		int numOfParameters = parameters.size();
		if (numOfParameters == 0) {
			System.out.println("Kotelezo mezot megadni, nem jott letre karakter");
			return;
		}
		Explorer newCharacter = null;

		if (numOfParameters == 1) {
			newCharacter = new Explorer(startField);
		}
		if (numOfParameters == 2) {
			int bodyHeat = Integer.parseInt(parameters.get(1));
			ArrayList<Item> noItems = null;
			newCharacter = new Explorer(startField, bodyHeat, noItems);
		}
		if (numOfParameters > 2) {
			int bodyHeat = Integer.parseInt(parameters.get(1));
			parameters.remove(0);
			parameters.remove(0);
			ArrayList<Item> items = inventoryCreate(parameters);
			newCharacter = new Explorer(startField, bodyHeat, items);
		}
		startField.addCharacter(newCharacter, null);
		team.addCharacter(newCharacter);
		Controller.instance().drawMap();
		return;
	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandGetItem() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.getItem();
	}

	private static void commandGameStart(ArrayList<String> parameters) {
		if (parameters.isEmpty()) {
			System.out.println("Hibas parameterek");
			return;
		}

		team = new Team(parameters);
	}

	/**
	 * Ez a parancs szolgal arra, hogy manualisan elhelyezzunk targyakat a palyan.
	 * Ha nincs mezo visszater. Amennyiben van elkeri a parameterben kapott helyen
	 * talalhato mezot. Letrehozza a parameter alapjan kapott targyat is. Ha megvan
	 * a mezo es a targy, akkor beallitja a amezo targyanak az uj targyat.
	 * 
	 * @param parameters : Helyes esetben a mezo azonositojat es a letrehozando
	 *                   targy tipusat tarolja.
	 */
	private static void commandItemcreate(ArrayList<String> parameters) {
		if (map == null) {
			System.out.println("Nincs palya, letre kell hozni egyet");
			return;
		}

		int place = Integer.parseInt(parameters.get(0));
		parameters.remove(0);
		ArrayList<Item> toPlace = inventoryCreate(parameters);
		Item item = toPlace.get(0);

		Field target = map.getFields().get(place - 1);
		target.setItem(item);
		return;

	}

	/**
	 * A load state parancs egy jatek allas betoltesere szolgalo utasitas.
	 * Parameterben megkapja honnet kell betolteni az allapotot Ezzel a parameterrel
	 * megprobal fajlt megnyitni, majd abbol betolteni a jatekot.
	 * 
	 * @param parameters : Helyes esetben egy jatek allast tartalmazo fajl neve.
	 */
	private static void commandLoadState(ArrayList<String> parameters) {
		Team loadedTeam = null;

		String fileName = parameters.get(0);
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);

			loadedTeam = (Team) in.readObject();

			in.close();
			file.close();

			team = loadedTeam;
		} catch (FileNotFoundException f) {
			System.out.println("Hibas fajl nev");
		} catch (IOException ex) {
			System.out.println("IOException tortent");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException tortent");
		}
		return;
	}

	/**
	 * Palya letrehozasara szolgalo fuggveny. Parameterkent megkapjuk az oszlopok es
	 * sorok szamat, valamint felsorolva a mezok tipusat es ho mennyiseget. A sor,
	 * oszlop szam megszerzese utan a szamokat es a maradek parametereket atadjuk a
	 * palya konstruktoranak.
	 * 
	 * @param parameters : Helyes esetben : sorok szama - oszlopok szama - mezo
	 *                   tipus + homennyiseg parosok
	 */
	private static void commandMapCreate(ArrayList<String> parameters) {
		if (parameters.isEmpty() || parameters.size() <= 1) {
			System.out.println("Nem megfelelo parameterek");
			return;
		}
		int numberOfRows = Integer.parseInt(parameters.get(0));
		int numberOfCollumns = Integer.parseInt(parameters.get(1));

		parameters.remove(0);
		parameters.remove(0);

		if (parameters.isEmpty()) {
			map = new Map(numberOfRows, numberOfCollumns);
			team = new Team(map);
			Controller.instance().setMap(map);
			return;
		}
		map = new Map(numberOfRows, numberOfCollumns, parameters);
		team = new Team(map);
		Controller.instance().setMap(map);
		return;
	}

	/**
	 * A pozgatasra szolgalo utasitas. Megnezi van-e csapat, amely hasznalhato a
	 * mozgas parancs feldolgozasara. Feldolgozza es atalakitja a parametrkent
	 * kapott irany a Direction enum valamely elemere, majd ezzel meghivja a team
	 * mozgas fuggvenyet.
	 * 
	 * @param parameters : Helyes esetben a mozgas iranyat tartalmazza.
	 */
	private static void commandMove(ArrayList<String> parameters) {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}

		Direction direction;
		switch (parameters.get(0)) {
			case "NORTH":
				direction = Direction.NORTH;
				break;
			case "EAST":
				direction = Direction.EAST;
				break;
			case "SOUTH":
				direction = Direction.SOUTH;
				break;
			case "WEST":
				direction = Direction.WEST;
				break;
			default:
				System.out.println("Hibas parameter: " + parameters.get(0));
				return;
		}

		team.move(direction);
		team.endAction();

	}

	/**
	 * Jeges medve letrehozasare szolgalo utasitas. Megnezi, hogy van-e csapat es
	 * mezo. Ha mindent rendben talal megszerzi a parameterkent kapott mezot,
	 * amelyet beallit a jegesmedve mezojenek, majd a csapathoz is hozzaadja a
	 * medvet.
	 * 
	 * @param parameters : A mezo sorszam, amelyre a jegesmedvet szeretnenk
	 *                   elhelyezni.
	 */
	private static void commandPolarbear(ArrayList<String> parameters) {
		if (map == null) {
			System.out.println("Nincs palya, letre kell hozni egyet");
			return;
		}
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}

		int position = Integer.parseInt(parameters.get(0));

		Field startField = map.getFields().get(position - 1);

		PolarBear newBear = new PolarBear(startField);
		team.setPolarBear(newBear);
		Controller.instance().drawMap();
		return;
	}

	/**
	 * Megnezi, hogy van-e csapat, akinek tovabb tudja adni az utasitast. Ha van
	 * akkor meghivja a csapat megfelelo fuggvenyet.
	 */
	private static void commandSpecialAbility() {
		if (team == null) {
			System.out.println("Meg nincs csapat, a parancshoz letre kell hoznia egyet");
			return;
		}
		team.specialAbility();
		team.endAction();

	}

	/**
	 * A beallitasok kezelesere szolgalo fuggveny. Az masodik parameterben megnezi,
	 * hogy helyes utasitas ertek-et kapunk-e. Helyes parameter ertek esetan
	 * feldolgozza magat az utasitast. Ha megfelelo erteket akarunk atallitani,
	 * akkor a controllerben tarol globalis beallitast beallitja.
	 * 
	 * @param parameters : Helyes esetben a valtoztatando utasitast es annak az uj
	 *                   erteket adjuk at.
	 */
	private static void commandSettings(ArrayList<String> parameters) {
		if (parameters.isEmpty() || parameters.size() <= 1) {
			System.out.println("Hibas parameter");
			return;
		}

		boolean value;
		switch (parameters.get(1)) {
			case "true":
				value = true;
				break;
			case "false":
				value = false;
				break;
			default:
				System.out.println("Hibas parameter az erteknel: " + parameters.get(1));
				return;
		}

		switch (parameters.get(0)) {
			case "RandomBlizzard":
				Controller.setRandomBlizzard(value);
				break;
			case "ControlPolarbear":
				Controller.setControlPolarBear(value);
				break;
			case "ItemAlwaysVisible":
				Controller.setItemAlwaysVisible(value);
				break;
			case "CapacityVisible":
				Controller.setCapacityVisible(value);
				break;
			default:
				System.out.println("Hibas setting ertek: " + parameters.get(0));
				return;
		}
	}

	/**
	 * Teszt futtatasara szolgalo parancs. A parameterkent kapott ertekkel
	 * feldolgozza soronkent a fajlt es a fajl minden sorat megprobalja
	 * vegrehajtani.
	 * 
	 * @param parameters : Helyes esetben a tesztet tartalmazo fajl cimet
	 *                   tartalmazza.
	 */
	private static void commandTeszt(ArrayList<String> parameters) {
		if (parameters.size() != 1) {
			System.out.println("Hibas parameter a teszt futtatasahoz");
		}
		String filePath = new File("").getAbsolutePath() + "/test/input/" + parameters.get(0);
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			for (String line; (line = br.readLine()) != null;) {
				if (Controller.instance().isPolarBearManualMove())
					bearMove(line);
				else {

					String[] splittedInput = line.split(" ");
					String command = splittedInput[0];

					int numberOfParameters = splittedInput.length;
					ArrayList<String> lineParameters = new ArrayList<String>();
					for (int i = 1; i < numberOfParameters; i++) {
						lineParameters.add(splittedInput[i]);
					}
					executeTask(command, lineParameters);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Hibas fajl cim");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException occured");
			e.printStackTrace();
		}
		return;
	}

	/**
	 * A mentesre szolgalo parancs. Az elso parameterbol eldonti, milyen tipusu
	 * mentest szeretnenk vegezni. A parameterkent kapott ertekkel meghivja a
	 * controller megfelelo fuggvenyet.
	 * 
	 * @param parameters : Helyes esetben a mentes cel fajl-janak a cimet
	 *                   tartalmazza.
	 */
	private static void commandSave(ArrayList<String> parameters) {

		String type = parameters.get(0);

		if (type.equals("log")) {
			Controller.saveLog(parameters.get(1));
		}
		if (type.equals("state")) {
			try {
				FileOutputStream file = new FileOutputStream(parameters.get(1));
				ObjectOutputStream out = new ObjectOutputStream(file);

				out.writeObject(team);

				out.close();
				file.close();
			} catch (FileNotFoundException fe) {
				System.out.println("Hibas fajl cim");
			} catch (IOException ex) {
				System.out.println("IOException is caught");
			}
		}
		return;
	}

	/**
	 * A string parameterekbol targy lista keszitesere szolgalo fuggveny. Minden
	 * parameterre megnezi, hogy melyik targy megnevezesnek felel meg. Az
	 * elnevezesnek megfelelo targyat hoz letre es hozzaadja az ideiglenes
	 * listankhoz.
	 * 
	 * @param parameters : Helyes estben targy tipusok felsorolasa
	 * @return : A parameterek alapjan letrehozott targy lista
	 */
	private static ArrayList<Item> inventoryCreate(ArrayList<String> parameters) {
		ArrayList<Item> ret = new ArrayList<Item>();

		int numOfItems = parameters.size();
		for (int i = 0; i < numOfItems; i++) {
			switch (parameters.get(i)) {
				case "tent":
					Tent tent = new Tent();
					ret.add(tent);
					break;
				case "scubasuit":
					ScubaSuit scubasuit = new ScubaSuit();
					ret.add(scubasuit);
					break;
				case "rope":
					Rope rope = new Rope();
					ret.add(rope);
					break;
				case "food":
					Food food = new Food();
					ret.add(food);
					break;
				case "flaregunpart":
			//		FlareGunPart flareGunPart = new FlareGunPart();
				//	ret.add(flareGunPart);
					break;
				case "shovel":
					Shovel shovel = new Shovel();
					ret.add(shovel);
					break;
				case "brittleshovel":
					BrittleShovel brittleShovel = new BrittleShovel();
					ret.add(brittleShovel);
					break;
				default:
					System.out.println("Hibas parameter: " + parameters.get(i));
					return null;
			}
		}
		return ret;
	}

	/**
	 * Csapat letrehozasara szolgalo fuggvveny. Ez lenyegeben a csapat tagjait hozza
	 * letre es adja, hozza a csapathoz. A parameterekalapjan eldonti, hogy eszkimo
	 * vagy kutato tipusu karaktert kell-e hozzaadnia a csapathoz. Minden string
	 * parameterre elvegzi ezt az ellenorzest.
	 * 
	 * @param parameters : Helyes esetben karakter tipusokbol allo felsorolas, ilyen
	 *                   karaktereket hoz majd letre a fuggveny.
	 * @param startField : Az a kezdo mezo amelyet az uj karaktereknek megadunk,
	 *                   mivel ennek a megadasa kotelezo.
	 * @param team       : Az a csapat, amelyhez hozza kell adnunk az ujonnan
	 *                   letrehozott karaktereket.
	 */
	private static void teamCreate(ArrayList<String> parameters, Field startField, Team team) {
		ArrayList<Character> ret = new ArrayList<Character>();

		int numOfItems = parameters.size();
		for (int i = 0; i < numOfItems; i++) {
			switch (parameters.get(i)) {
				case "Eszkimo":
					Eskimo eszkimo = new Eskimo(startField);
					team.addCharacter(eszkimo);
					break;
				case "Explorer":
					Explorer explorer = new Explorer(startField);
					team.addCharacter(explorer);
					break;
				default:
					System.out.println("Hibas parameter: " + parameters.get(i));
					return;
			}
		}
	}
}
