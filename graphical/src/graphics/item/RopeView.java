package graphics.item;

import javax.swing.JLabel;
import java.awt.*;

import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.Rope;

/**
 * A Ropehoz tartozo nezetet megvalosito osztaly
 */
public class RopeView extends ItemView {

    /**
     * A RopeView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * Ropehoz tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param c a birtoklo Character
     * @param r a Rope objektum, akihez a nezet tartozik
     */
    public RopeView(character.Character c, Rope r) {
        super(c);
        ownItem = r;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();

        label = new JLabel();
        add(label);
        ViewController.instance().addItemView(c, this);
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
        texture = TextureCollection.instance().getTexture(Textures.ROPE);
    }
}