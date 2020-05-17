package graphics;

import character.Character;
import field.Field;
import graphics.bars.Inventorybar;
import graphics.bars.Statusbar;
import graphics.character.BearView;
import graphics.character.CharacterView;
import graphics.field.FieldView;

import graphics.item.ItemView;
import graphics.item.ShelterView;
import graphics.menu.PlayerSettings;
import item.Item;
import main.Game;
import team.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Static class for managing GUI elements
 */
public class ViewController {

	private static ViewController single_instance = null;
	private static ViewCollection viewcollection;
	private static Team team;
	private static JFrame frame;
	private static ArrayList<String> params;
	private static ArrayList<CharacterView> characterImages = new ArrayList<>();

	/**
	 * Required for singleton usage
	 * 
	 * @return - static ViewController instance
	 */
	public static ViewController instance() {
		if (single_instance == null)
			single_instance = new ViewController();
		return single_instance;
	}

	/**
	 * You can set the parameters of the GameStart in this pop-up window.
	 * 
	 * @param g -- WindowAdapter
	 */
	public static void startGame(Game g) {
		PlayerSettings f = new PlayerSettings(g);
	}

	/**
	 * Initializes the Team and the JPanel of the game window.
	 * 
	 * @param tem
	 */
	public static void createTeam(Team tem) {
		team = tem; // csapat
		viewcollection.setTeam(team); // atadjuk a ViewCollectionnek
		int rows = team.getMap().getRows(); // meret?
		int collumns = team.getMap().getColumns(); // meret?
		viewcollection.createPanel(); // TODO: CHECK THIS
		viewcollection.setupMap(rows, collumns, frame); // Map beállítás --> középső panel?

		frame.pack();
	}

	/**
	 * Method which initializes the ViewCollection of the GUI objects.
	 */
	public static void addViewCollection() {
		viewcollection = new ViewCollection(); // Collection INIT
		frame = new JFrame("Jegmezo"); // ez milyen frame?
		frame.add(viewcollection); // yay szal a collection egy panel?
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Puts the given Field - FieldView key and value pair to the collections
	 * HashMap for later acces.
	 * 
	 * @param key - the Field which the FieldView belongs to
	 * @param fv  - the FieldView responsible for visualizing the given Field Key
	 */
	public static void addFieldPanel(Field key, FieldView fv) {
		viewcollection.addFieldPanel(key, fv);
	}

	/**
	 * Puts the given Character - CharacterView key and value pair to the
	 * collections HashMap for later acces.
	 * 
	 * @param key - the Character which the CharacterView belongs to
	 * @param vc- the CharacterView responsible for visualizing the given Character
	 *            Key
	 */
	public static void addCharacter(Field key, CharacterView vc) {
		viewcollection.addCharacter(key, vc);
	}

	/**
	 * Adds a CharacterView to the collection
	 * 
	 * @param cv
	 */
	public void addCharacterView(CharacterView cv) {
		characterImages.add(cv);
	}

	/**
	 * Removes a live character from the given field. Used for moving
	 * implementations.
	 * 
	 * @param key - the field you want to remove the character from
	 * @param cv  - the character
	 */
	public static void removeCharacter(Field key, CharacterView cv) {
		viewcollection.removeCharacter(key, cv);
	} // a karaktert am kiszedjük?

	/**
	 * Visualizes a Shelter on the map on the given Field
	 * 
	 * @param key - the field u want to put a shelter to
	 * @param sv  - the sehelter GUI object
	 */
	public static void addShelter(Field key, ShelterView sv) {
		viewcollection.addShelter(key, sv);
	}

	/**
	 * Removes a shelter from the given Field key
	 * 
	 * @param key - the field you want to remove the shelter from
	 */
	public static void removeShelter(Field key) {
		viewcollection.removeShelter(key);
	}

	/**
	 * Removes an item from the collection.
	 * 
	 * @param i
	 */
	public static void removeItem(Item i) {
		viewcollection.removeItem(i);
	}

	/**
	 * Adds a Polar Bear to the ViewCollection for later acces.
	 * 
	 * @param key - the field you want to initialize the bear on.
	 * @param bv  - the bear
	 */
	public static void addBear(Field key, BearView bv) {
		viewcollection.addBear(key, bv);
	}

	/**
	 * Removes the bear from the given field. Used for moving implementations
	 * 
	 * @param key
	 */
	public static void removeBear(Field key) {
		viewcollection.removeBear(key);
	}

	/**
	 * When a character picks up an Item this method is invoked.
	 * 
	 * @param own - the owner of the item.
	 * @param iv  - the GUI object of an item.
	 */
	public static void addItemView(Character own, ItemView iv) {
		viewcollection.addItem(own, iv);
	}

	/**
	 * Initializes the two bars of the game.
	 */
	public static void addBars() {
		setupStatusbar();
		setupInventorybar();
	}

	/**
	 * @return gets the main windows frame size
	 */
	public static Dimension getSize() {
		return frame.getSize();
	}

	/**
	 * Init of the Inventorybar
	 */
	private static void setupInventorybar() {
		Inventorybar ib = new Inventorybar(team, characterImages, new Dimension((team.getSize() + 1) * 50, 50));
		viewcollection.addInventorybar(ib);
	}

	/**
	 * Init of the Statusbar
	 */
	private static void setupStatusbar() {
		Statusbar sb = new Statusbar(team, characterImages, new Dimension((team.getSize() + 1) * 50, 100));
		viewcollection.addStatusbar(sb);
	}

	/**
	 * @return - Returns a string of parameters. Used for init.
	 */
	public ArrayList<String> getParams() {
		return params;
	}

	/**
	 * Sets the parameters
	 * 
	 * @param params
	 */
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}

	/**
	 * Updates the GUI objects of the game
	 */
	public void updateAll() {
		SwingUtilities.updateComponentTreeUI(frame);
		viewcollection.updateAll();
	}

	/**
	 * When the game ends the main window closes.
	 */
	public static void endGame() {
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Reveals the capacity of a given field
	 * 
	 * @param field
	 */
	public static void reveal(Field field) { // TODO: THIS SHIT DOESN'T WORK
		viewcollection.reveal(field);
	}

	public static void setCapacity(JLabel cap, Field f){
		viewcollection.setCapacity(cap, f);
	}

}
