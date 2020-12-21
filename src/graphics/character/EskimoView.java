package graphics.character;

import character.Character;
import character.Eskimo;
import graphics.TextureCollection;
import graphics.Textures;
import graphics.ViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Eskimot kirajzolo osztaly
 */
public class EskimoView extends CharacterView {
    private Eskimo ownChar;
    private ImageIcon imageIcon;

    /**
     * konstruktor ami beallitja a kepet noveli a karakterek szamat majd hozzaadja magat a viewcollection-hez
     * @param e eszkimo amit kirajzol
     */
    public EskimoView(Eskimo e) {

        ownChar = e;

        this.setVisible(true);
        this.setPreferredSize(new Dimension(100, 100));
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 1));

        addTexture();

        this.field = ownChar.getField();

        switch (populus){
            case 0: label.setIcon(texture.get(0)); imageIcon = texture.get(0); break;
            case 1: label.setIcon(texture.get(1)); imageIcon = texture.get(1); break;
            case 2: label.setIcon(texture.get(2)); imageIcon = texture.get(2); break;
            case 3: label.setIcon(texture.get(3)); imageIcon = texture.get(3); break;
            case 4: label.setIcon(texture.get(4)); imageIcon = texture.get(4); break;
            case 5: label.setIcon(texture.get(5)); imageIcon = texture.get(5); break;
        }

        populus++;
        this.add(label);

        ViewController.instance().addCharacter(field, this);
        ViewController.instance().addCharacterView(this);
    }

    /**
     * frissito fg ami leszedi magat egy fieldrol es atrakja az ujra
     */
    @Override
    public void update() {
        ViewController.removeCharacter(field, this);
        field = ownChar.getField();
        ViewController.addCharacter(field, this);
    }

    /**
     * a textura lekerdezese
     */
    @Override
    public void addTexture() {
        texture = TextureCollection.instance().getTexture(Textures.ESKIMO);
    }

    /**
     *
     * @return vissza adja az eskimo kinezetet
     */
    @Override
    public ImageIcon getTexture() {
        return imageIcon;
    }

    /**
     *
     * @return vissza adja az eszkimot amit kirajzol
     */
    @Override
    public Character getCharacter() {
        return ownChar;
    }
}
