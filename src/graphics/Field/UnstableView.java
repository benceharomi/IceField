package graphics.field;

import field.Unstable;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;

import javax.swing.*;
import java.awt.*;

//instabil mezot kirajzolo osztalt
public class UnstableView extends graphics.field.FieldView {

    /**
     * konstruktor ami beallitja a label-t valamint hozza adja magat a viewcollection-hez
     * @param u
     */
    public UnstableView(Unstable u) {
        ownField = u;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(160, 160));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));

        label = new JLabel();

        addTexture();
        update();
        add(label);
        ViewController.instance().addFieldPanel(ownField, this);

    }
    /**
     * frisiti a mezo kinezetet fuggoen a homenyisegetol valamint hogy van rajta item
     */
    @Override
    public void update() {
        this.removeAll();
        label.setIcon(null);
        switch (ownField.getSnow()) {
            case 0:
                if (ownField.getItem() != null) {
                    label.setIcon(texture.get(0));
                } else
                    label.setIcon(texture.get(1));
                break;
            case 1:
                label.setIcon(texture.get(2));
                break;
            case 2:
                label.setIcon(texture.get(3));
                break;
            case 3:
                label.setIcon(texture.get(4));
                break;
            case 4:
                label.setIcon(texture.get(5));
                break;
        }
        if(ownField.getRevealed()) {
            ViewController.instance().setCapacity(new JLabel("" + ownField.getCapacity()), ownField);
        }
        this.add(label);
        label.revalidate();
    }

    /**
     * a texturak lekerdezese
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.UNSTABLE);
    }
}
