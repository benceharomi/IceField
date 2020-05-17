package graphics.item;

import character.Character;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.FlareGunPart;

import javax.swing.*;
import java.awt.*;

/**
 * A flaregunparthoz tartozo nezetet megvalosito osztaly
 */
public class FlareGunPartView extends ItemView {
    private int num;

    /**
     * A FlareGunPartView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja
     * a flaregunparthoz tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param character a Character aki birtokolja a targyat
     * @param f         a FlareGunPart objektum akihez a nezet tartozik
     * @param n         a flaregunpart szama
     */
    public FlareGunPartView(Character character, FlareGunPart f, int n) {
        super(character);
        num = n;
        ownItem = f;
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
        label.setIcon(texture.get(num - 1));
        label.revalidate();
    }

    /**
     * Segedfuggveny, a texturecollectiontol lekeri a sajat texturait
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.GUNPART);
    }
}
