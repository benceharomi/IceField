package graphics.item;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.Shovel;
import character.Character;

/**
 * A Shovelhez tartozo nezetet megvalosito osztaly
 */
public class ShovelView extends ItemView {

    /**
     * A ShovelView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * Shovelhez tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param c a birtoklo Character
     * @param s a Shovel objektum, akihez a nezet tartozik
     */
    public ShovelView(Character character, Shovel s) {
        super(character);
        ownItem = s;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();
        label = new JLabel();
        update();
        add(label);
        ViewController.instance().addItemView(character, this);
    }

    /**
     * Rajzolofuggveny, beallitja a megfelelo texturat a labelre
     */
    @Override
    public void update() {
        label.setIcon(texture.get(0));
        label.revalidate();
    }

    /**
     * Segedfuggveny, a texturecollectiontol lekeri a sajat texturait
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.SHOVEL);
    }
}