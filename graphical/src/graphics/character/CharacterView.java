package graphics.character;

import character.Character;
import field.Field;
import graphics.IView;

import javax.swing.*;
import java.util.ArrayList;


/**
 * karakterekhez tartozo kinezetett kiralyzolo abstrakt osztaly
 */
public abstract class CharacterView extends JPanel implements IView {
    //a kepeket tartalmazo tomb
    protected ArrayList<ImageIcon> texture;
    //label ami tartalmazza a kepek
    protected JLabel label = new JLabel();;
    //a field amin all a karakter amit kirajzol
    protected Field field;
    //a karakterek szama
    protected static int populus = 0;

    /**
     * kinezet frisitese
     */
    @Override
    public abstract void update();

    /**
     * a kepek lekerese
     */
    @Override
    public abstract void addTexture();

    /**
     * a kep lekerdezese
     * @return a kep ami a karakterhez tertozik
     */
    public abstract ImageIcon getTexture();

    /**
     * vissza adja a karaktert amit kirajzol
     * @return
     */
    public abstract Character getCharacter();


}
