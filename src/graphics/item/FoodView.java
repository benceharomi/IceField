package graphics.item;

import java.awt.*;

import javax.swing.JLabel;

import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.Food;
import character.Character;

/**
 * A Foodhoz tartozo nezetet megvalosito osztaly
 */
public class FoodView extends ItemView {

    /**
     * A FoodView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * Foodhoz tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param character a birtoklo Character
     * @param f         a Food objektum, akihez a nezet tartozik
     */
    public FoodView(Character character, Food f) {
        super(character);

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
        label.setIcon(texture.get(0));
        label.revalidate();
    }

    /**
     * Segedfuggveny, a texturecollectiontol lekeri a sajat texturait
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.FOOD);
    }
}