package graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.text.View;

import character.Character;
import field.Direction;
import field.Field;
import graphics.bars.Inventorybar;
import graphics.bars.Statusbar;
import graphics.character.BearView;
import graphics.character.CharacterView;
import graphics.field.FieldView;
import graphics.item.ItemView;
import graphics.item.ShelterView;
import graphics.listeners.ArrowListener;
import graphics.menu.PlayerSettings;
import item.Item;
import main.Controller;
import team.Team;

public class ViewCollection extends JPanel {

	// Attributumok
	private LinkedHashMap<FieldView, Field> fields = new LinkedHashMap<>();
	private HashMap<Field, ContainerView> containers = new HashMap<>();
	private Team team;

	private int DEFAULT_WIDTH = 1000;
	private int DEFAULT_HEIGHT = 1000;

	private Statusbar statusBar; // Jatekosok allapotanak kijelzesere
	private Inventorybar inventoryBar; // Aktiv jatekos, targyai
	private JPanel map = new JPanel(); // Kozepso resz ahol a jatek megy
	private JPanel containerPanel = new JPanel(); // idk yet

	/**
	 * Kezdeti panelbeállítás
	 */
	public void createPanel() {

		this.setVisible(true);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setBackground(new Color(138, 147, 234));
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener(new ArrowListener(team)); // na ez jobb nem?

		this.setLayout(new BorderLayout());
		/// pack?
	}

	public void setTeam(Team t) {
		System.out.println("Karakterek szama: " + t.getCharacters().size()); // valszeg debug?
		team = t;
	}

	public void setupMap(int rows, int collumns, JFrame owner) {
		requestFocus();
		map.setLayout(new GridLayout(rows, collumns));
		map.setPreferredSize(new Dimension(160 * collumns, 160 * rows));
		map.setBackground(new Color(138, 147, 234));

		containerPanel.setLayout(new GridLayout(rows, collumns));
		containerPanel.setPreferredSize(new Dimension(160 * collumns, 160 * rows)); // talán a mezők mérete
		containerPanel.setBackground(new Color(0, 0, 0, 0));
		containerPanel.setOpaque(false);

		Collection<FieldView> field = fields.keySet();

		for (FieldView fv : field) {
			map.add(fv);
			containerPanel.add(containers.get(fields.get(fv)));
		}

		System.out.println(owner.getSize().getHeight());
		map.setBounds((DEFAULT_WIDTH - 160 * collumns) / 2, (DEFAULT_HEIGHT - 160 * rows - 150) / 2, 160 * collumns,
				160 * rows);
		containerPanel.setBounds((DEFAULT_WIDTH - 160 * collumns) / 2, (DEFAULT_HEIGHT - 160 * rows - 150) / 2,
				160 * collumns, 160 * rows);

		Integer a = 0; // ???????????????????????????????????????
		JLayeredPane LayerdPane = new JLayeredPane(); // ???????????????????????????????
		LayerdPane.add(map, a, 0); // ???????????????????????????????
		a = 1; // ???????????????????????????????
		LayerdPane.add(containerPanel, a, 0); // ???????????????????????????????
		this.add(LayerdPane, BorderLayout.CENTER); // ???????????????????????????????

		ViewController.instance().addBars();

		this.setVisible(true);
		System.out.println(owner.getSize().getHeight());
		ViewController.instance().updateAll();
	}

	/**
	 * Adds the Inventorybar to the collection
	 * 
	 * @param ib
	 */
	public void addInventorybar(Inventorybar ib) {
		inventoryBar = ib;
		this.add(inventoryBar, BorderLayout.SOUTH);
	}

	/**
	 * Adds the Statusbar to the collection
	 * 
	 * @param sb
	 */
	public void addStatusbar(Statusbar sb) {
		statusBar = sb;
		this.add(statusBar, BorderLayout.NORTH);
	}

	/**
	 * This method updates the GUI objects of the game each turn
	 */
	public void updateAll() {
		Dimension size = ViewController.instance().getSize();

		/// menj integrálj legközelebb
		map.setBounds((DEFAULT_WIDTH - 160 * team.getMap().getColumns()) / 2,
				(DEFAULT_HEIGHT - 160 * team.getMap().getRows() - 150) / 2, 160 * team.getMap().getColumns(),
				160 * team.getMap().getRows()); // mibe
		containerPanel.setBounds((DEFAULT_WIDTH - 160 * team.getMap().getColumns()) / 2,
				(DEFAULT_HEIGHT - 160 * team.getMap().getRows() - 150) / 2, 160 * team.getMap().getColumns(),
				160 * team.getMap().getRows());

		Collection<ContainerView> moveableObjects = containers.values();
		Collection<FieldView> field = fields.keySet();

		/// Updating containers above fields
		for (ContainerView cv : moveableObjects) {
			cv.update();
		}
		/// Updating fields
		for (FieldView fv : field) {
			fv.update();
		}

		/// Updating bars
		statusBar.update();
		inventoryBar.update();
	}

	public void addFieldPanel(Field field, FieldView fieldView) {
		containers.put(field, new ContainerView(new Dimension(160, 160)));
		fields.put(fieldView, field);
	}

	public void addCharacter(Field key, CharacterView cv) {
		containers.get(key).addCharacter(cv);
	}

	public void removeCharacter(Field key, CharacterView cv) {
		containers.get(key).removeCharacter(cv);
	}

	public void addShelter(Field key, ShelterView sv) {
		containers.get(key).addShelter(sv);
	}

	public void removeShelter(Field key) {
		containers.get(key).removeShelter();
	};

	public void addBear(Field key, BearView bv) {
		containers.get(key).addBear(bv);
	}

	public void removeBear(Field key) {
		containers.get(key).removeBear();
	};

	public void addItem(Character own, ItemView iv) {
		inventoryBar.addItemView(own, iv);
	}

	public void setCapacity(JLabel cap, Field f){
		containers.get(f).setCapacity(cap);
	}


	public void reveal(Field field) {
		// TODO ez itt nagyon fos. Csináld meg!!!!
		JLabel capac = new JLabel("[" + field.getCapacity() + "]");
		for (FieldView cv : fields.keySet()) {
			if (fields.get(cv) == field) { // ok szóval ide nem lép be soha
				// TODO: this needs to be implemented
			}
		}
	}

	public void removeItem(Item i) {
		inventoryBar.removeItem(i);
	}
}
