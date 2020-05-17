package graphics.item;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import field.Field;
import graphics.IView;
import graphics.ViewController;

/**
 * A Shelterhez tartozo nezeteket megvalosito osztalyok os absztrakt osztalya
 */
public abstract class ShelterView extends JPanel implements IView {
    protected ArrayList<ImageIcon> texture;
    protected JLabel label;

    /**
     * Rajzolofuggveny, beallitja a megfelelo texturat a labelre
     */
    @Override
    public abstract void update();

    /**
     * Segedfuggveny, a texturecollectiontol lekeri a sajat texturait
     */
    @Override
    public abstract void addTexture();
}