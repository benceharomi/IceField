package graphics.field;

import field.Field;
import graphics.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Mezoket kirajzolo abstrakt osztaly
public abstract class FieldView extends JPanel implements IView {
    //A mezohoz tartozo kep tomb
    protected ArrayList<ImageIcon> texture;
    //A label amire bealltja a kepet
    protected JLabel label;
    //A mezo ammit reprezental
    protected Field ownField;

    @Override
    public abstract void update();

    @Override
    public abstract void addTexture();
}
