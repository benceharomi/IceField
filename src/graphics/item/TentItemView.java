package graphics.item;

import character.Character;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.Tent;

import javax.swing.*;
import java.awt.*;

/**
 * A Tenthez tartozo item nezetet megvalosito osztaly
 */
public class TentItemView extends ItemView {

    /**
     * A TentItemView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * Tenthez tartozo item texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param c a birtoklo Character
     * @param t a Tent objektum, akihez a nezet tartozik
     */
    public TentItemView(Character character, Tent t) {
        super(character);
        ownItem = t;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();
        label = new JLabel();
        update();
        add(label);
        ViewController.instance().addItemView(character, (ItemView) this);
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
        texture = TextureCollection.instance().getTexture(Textures.TENT);
    }
}
