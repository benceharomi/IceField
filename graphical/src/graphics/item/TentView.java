package graphics.item;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import character.Character;
import field.Field;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewCollection;
import graphics.ViewController;
import item.Tent;

/**
 * A Tenthez tartozo nezetet megvalosito osztaly
 */
public class TentView extends ShelterView {
    private Tent ownShelter;
    private Field field;

    /**
     * A TentView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * Tenthez tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param _field a field amin a tent áll
     * @param t      a Tent objektum, akihez a nezet tartozik
     */
    public TentView(Field _field, Tent t) {
        field = _field;
        ViewController.instance().addShelter(field, this);
        ownShelter = t;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));
        addTexture();
        label = new JLabel();
        update();
        add(label);
    }

    /**
     * Rajzolofuggveny, beallitja a megfelelo texturat a labelre, ha elhasznalodott
     * a tent, akkor letorli
     */
    @Override
    public void update() {
        if (ownShelter == null) {
            ViewController.instance().removeShelter(field);
        } else {
            label.setIcon(texture.get(0));
        }
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