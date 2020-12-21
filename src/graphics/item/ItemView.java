package graphics.item;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import graphics.IView;
import graphics.ViewController;
import item.Item;
import character.Character;

/**
 * Az itemek nezeteit megvalosito osztalyok os absztrakt osztálya
 */
public abstract class ItemView extends JPanel implements IView {
    protected ArrayList<ImageIcon> texture;
    protected Item ownItem;
    protected JLabel label;

    public ItemView(Character character) {
    }

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

    public Item getItem() {
        return ownItem;
    }
}