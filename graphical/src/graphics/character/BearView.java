package graphics.character;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import character.PolarBear;
import field.Field;
import graphics.IView;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewCollection;
import graphics.ViewController;

/**
 * A polarbearhez tartozo nezetet megvalosito osztaly
 */
public class BearView extends JPanel implements IView {
    private ArrayList<ImageIcon> texture;
    private static Field field;
    private PolarBear ownBear;
    private JLabel label;

    /**
     * A BearView osztaly konstruktora, letrehoz egy jlabelt, amire rarakja a
     * polarbearhez tartozo texturat, majd hozzaadja magat a viewcollectionhoz
     * 
     * @param bear a nezethez tartozo PolarBear
     */
    public BearView(PolarBear bear) {
        super();
        ownBear = bear;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);

        this.setBackground(new Color(0, 0, 0, 1));

        addTexture();
        label = new JLabel();
        this.add(label);

        field = ownBear.getField();
        ViewController.instance().addBear(field, this);
        update();
    }

    /**
     * Rajzolofuggveny, letorli magat az elozo helyerol es ujrarajzolja magat az uj
     * helyere
     */
    @Override
    public void update() {
        ViewController.instance().removeBear(field);

        field = ownBear.getField();
        label.setIcon(texture.get(0));
        ViewController.instance().addBear(field, this);
        this.revalidate();
    }

    /**
     * Segedfuggveny, a texturecollectiontol lekeri a sajat texturait
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.BEAR);
    }
}