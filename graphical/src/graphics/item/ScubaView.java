package graphics.item;

import javax.swing.JLabel;
import java.awt.*;

import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.ScubaSuit;
import character.Character;

/**
 * A ScubaSuithoz tartozo nezetet megvalosito osztaly
 */
public class ScubaView extends ItemView {

    /**
     * A ScubaView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * ScubaSuithoz tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param character a birtoklo Character
     * @param ss        a ScubaSuit objektum, akihez a nezet tartozik
     */
    public ScubaView(Character character, ScubaSuit ss) {
        super(character);
        ownItem = ss;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();
        label = new JLabel();
        add(label);
        ViewController.instance().addItemView(character, this);
        update();
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
        texture = TextureCollection.instance().getTexture(Textures.SCUBA);
    }
}