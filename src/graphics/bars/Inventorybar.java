package graphics.bars;

import character.Character;
import graphics.IView;
import graphics.character.CharacterView;
import graphics.item.ItemView;
import item.Item;
import team.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A játékos inventory-ját megjelenítő osztály
 */
public class Inventorybar extends JPanel implements IView {
    private HashMap<Character, ArrayList<ItemView>> inventory = new HashMap<>();
    private HashMap<Character, CharacterView> characterViews = new HashMap<>(); /// tuti h ez fos
    private Character activePlayer;
    private Team team;

    private JLabel activeViewLabel = new JLabel();
    private JLabel stamina = new JLabel();
    private JPanel items;

    /**
     * Konstruktor.
     * 
     * @param t               - korabban letrejott csapat
     * @param characterImages - a karakterek IView peldanyai
     * @param dimension       - a bar merete
     */
    public Inventorybar(Team t, ArrayList<CharacterView> characterImages, Dimension dimension) {
        team = t;

        setActivePlayer();

        // Inventory init
        for (Character c : team.getCharacters()) {
            inventory.put(c, new ArrayList<ItemView>());
        }
        // Karakterképek init
        for (CharacterView cv : characterImages) {
            this.characterViews.put(cv.getCharacter(), cv);
        }

        /// SWING ///
        setLayout(new BorderLayout());
        setPreferredSize(dimension);

        items = new JPanel();
        items.setLayout(new GridLayout(1, inventory.get(activePlayer).size()));
        this.add(items, BorderLayout.CENTER);

        // Stamina
        stamina.setText("stamina: " + team.getStamina());
        add(stamina, BorderLayout.EAST);

        // Aktív játékos képe
        activeViewLabel.setIcon(characterViews.get(activePlayer).getTexture());
        add(activeViewLabel, BorderLayout.WEST);
    }

    /**
     * Az aktiv jatekos beallitasara szolgal
     */
    private void setActivePlayer() {
        activePlayer = team.getactiveCharacter();
    }

    /**
     * Egy targyat evvel a metodussal adhatunk hozza a barhoz
     * 
     * @param c  - tulajdonos karakter
     * @param iv - Targy IView peldanya
     */
    public void addItemView(Character c, ItemView iv) {
        inventory.get(c).add(iv);
    }

    /**
     * Az allapot frissitesere szolgalo metodus
     */
    @Override
    public void update() {
        this.removeAll(); // Panel reset
        items.removeAll();

        setActivePlayer();
        items.setLayout(new GridLayout(1, inventory.get(activePlayer).size()));
        items.setSize(new Dimension(100 * inventory.get(activePlayer).size(), 100));

        for (ItemView it : inventory.get(activePlayer)) {
            it.update();
            items.add(it);
        }

        stamina.setText("stamina: " + team.getStamina());

        activeViewLabel.setIcon(characterViews.get(activePlayer).getTexture());

        add(activeViewLabel, BorderLayout.WEST);
        add(items, BorderLayout.CENTER);
        add(stamina, BorderLayout.EAST);

    }

    /**
     * Kiszedi a barbol a targyat
     * 
     * @param i
     */
    public void removeItem(Item i) {
        ArrayList<ItemView> temp = inventory.get(activePlayer);
        ItemView del = null;
        for (ItemView iv : temp) {
            if (iv.getItem() == i) {
                del = iv;
            }
        }
        inventory.get(activePlayer).remove(del);
    }

    /// METHOD DUMP ///
    @Override
    public void addTexture() {
    }
}
