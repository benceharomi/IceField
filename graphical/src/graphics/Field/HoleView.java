package graphics.field;

import field.Hole;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

/**
 * hole mezot kirajzolo osztaly
 */
public class HoleView extends graphics.field.FieldView {
    private boolean rev = false;

    /**
     * konstruktor ami beallitja a label-t valamint hozzaadja magat a
     * viewcollection-hez
     * 
     * @param h a hole amit kirajzol
     */
    public HoleView(Hole h) {
        this.ownField = h;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(160, 160));
        this.setFocusable(false);
        this.setBackground(new Color(100, 120, 120, 1));

        addTexture();
        label = new JLabel();
        update();
        this.add(label);
        ViewController.instance().addFieldPanel(ownField, this);
    }

    /**
     * frissiti a kinezetett fuggoen van-e mennyi ho van rajta es hogy van-e rajta
     * karakter vslsmint ha felfedik a kapacitit azt is kiirja
     */
    @Override
    public void update() {
        this.removeAll();
        label.setIcon(null);
        if (ownField.getCharacters().size() == 0 && rev == false) {
            switch (ownField.getSnow()) {
                case 0:
                    label.setIcon(texture.get(0));
                    label.setBackground(new Color(138, 147, 234));
                    break;
                case 1:
                    label.setIcon(texture.get(1));
                    break;
                case 2:
                    label.setIcon(texture.get(2));
                    break;
                case 3:
                    label.setIcon(texture.get(3));
                    break;
                case 4:
                    label.setIcon(texture.get(4));
                    break;
            }
        } else {
            rev = true;
        }
        if (ownField.getRevealed()) {
            ViewController.instance().setCapacity(new JLabel("" + ownField.getCapacity()), ownField);
        }
        this.add(label);
        label.revalidate();
    }

    /**
     * a textura lekerdezese
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.HOLE);
    }
}
