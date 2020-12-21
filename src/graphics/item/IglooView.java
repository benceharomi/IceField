package graphics.item;

import java.text.Format.Field;
import java.awt.*;
import javax.swing.*;

import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;
import item.Igloo;

/**
 * Az Igloohoz tartozo nezetet megvalosito osztaly
 */
public class IglooView extends ShelterView {
    private Igloo ownShelter;

    /**
     * Az IglooView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja az
     * Igloohoz tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param field a Field amin az igloo áll
     * @param i     az Igloo objektum, melyhez a nezet tartozik
     */
    public IglooView(field.Field field, Igloo i) {
        ViewController.instance().addShelter(field, this);

        ownShelter = i;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();

        label = new JLabel();
        add(label);
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
        texture = TextureCollection.instance().getTexture(Textures.IGLOO);
    }

}