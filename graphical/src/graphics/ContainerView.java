package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import graphics.character.BearView;
import graphics.character.CharacterView;
import graphics.item.ShelterView;

/**
 * This class visualizes the movable objects above a field's surface
 */
public class ContainerView extends JPanel implements IView {
	private int SIZE;

	private BearView bear = null;
	private ShelterView shelter = null;
	private ArrayList<CharacterView> characters = new ArrayList<CharacterView>();

	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	private JLabel capacity;
	private boolean setRev = false;

	public ContainerView(Dimension dimension) {
		this.setVisible(true);
		this.setPreferredSize(dimension);
		this.setFocusable(false);
		this.setBackground(new Color(0, 0, 0, 1));
		this.setLayout(new GridLayout(3, 1));

		SIZE = dimension.height;

		// Swing setup
		setUpLeftPanel();
		setUpMiddlePanel();
		setUpRightPanel();
	}

	public void addCharacter(CharacterView cv) {
		this.characters.add(cv);
		add(cv);
	}

	public void removeCharacter(CharacterView cv) {
		characters.remove(cv);
	}

	public void addShelter(ShelterView sv) {
		this.shelter = sv;
		add(sv);
	}

	public void removeShelter() {
		this.remove(shelter);
		this.shelter = null;
	}

	public void addBear(BearView bv) {
		this.bear = bv;
		add(bv);
	}

	public void removeBear() {
		this.bear = null;
	}

	public int getContainerSize() {
		return SIZE;
	}

	/// *** ***///

	/// SWING SETUP ///
	private void setUpLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setVisible(true);
		leftPanel.setBackground(new Color(0, 0, 0, 1));
		leftPanel.setPreferredSize(new Dimension(30, 100));

		this.add(leftPanel);
	}

	private void setUpMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setVisible(true);
		middlePanel.setBackground(new Color(0, 0, 0, 1));
		middlePanel.setPreferredSize(new Dimension(40, 100));

		middlePanel.setLayout(new BorderLayout());

		if (shelter != null) {
			middlePanel.add(shelter, BorderLayout.CENTER);
		}

		if (bear != null) {
			middlePanel.add(bear, BorderLayout.WEST);
		}
		this.add(middlePanel);
	}

	private void setUpRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(0, 0, 0, 1));
		rightPanel.setPreferredSize(new Dimension(30, 100));
		rightPanel.setLayout(new GridLayout(1, 6));

		for (CharacterView cv : characters) {
			rightPanel.add(cv);
		}

		this.add(rightPanel);
	}

	/// *** END OF SWING SETUP ***///

	/**
	 * For revealing the fields capacity we need this.
	 * 
	 * @param label
	 */
	public void addLabel(JLabel label) {
		leftPanel.add(label, BorderLayout.NORTH);
	}

	//// OVERRIDEN METHODS ////

	/**
	 * Updates the moveable GUI objects of the game
	 */
	@Override
	public void update() {

		if (shelter != null) {
			shelter.update();
		}

		if (bear != null) {
			bear.update();
		}

		ArrayList<CharacterView> cv1 = new ArrayList<CharacterView>(characters);

		for (CharacterView cv : cv1) {
			cv.update();
		}
	}

	/// DUMP
	@Override
	public void addTexture() {
	}

	public void setCapacity(JLabel cap) {
		if (!setRev) {
			capacity = cap;
			leftPanel.add(capacity);
			setRev = true;
		}
	}
}
