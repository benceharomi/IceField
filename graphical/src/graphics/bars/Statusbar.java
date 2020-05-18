package graphics.bars;

import graphics.character.CharacterView;
import graphics.IView;
import team.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A jatekban szereplo karakterek allapotat jeleniti meg
 */
public class Statusbar extends JPanel implements IView {
    /// ATTRIBUTES ///

    private ArrayList<CharacterView> characterViews;
    private Team team;

    /// SWING ///
    ArrayList<JLabel> hp = new ArrayList<JLabel>();;
    ArrayList<ImageIcon> playerImages = new ArrayList<ImageIcon>();

    /// METHODS ///

    /**
     * Konstruktor
     * 
     * @param t - team
     * @param d - az ablak merete
     */
    public Statusbar(Team t, ArrayList<CharacterView> cvs, Dimension d) {

        team = t;
        characterViews = cvs;

        for (int i = 0; i < team.getSize(); i++) {
            hp.add(new JLabel(Integer.toString(team.getCharacters().get(i).getHeat())));
        }

        // Beállítjuk a képeket
        for (int i = 0; i < characterViews.size(); i++) {
            playerImages.add(characterViews.get(i).getTexture());
        }

        // Beállítjuk a labeleket
        for (int i = 0; i < hp.size(); i++) {
            hp.get(i).setText("Heat: " + team.getCharacters().get(i).getHeat());
        }

        /// SWING ///

        setPreferredSize(d); // panel méret
        setLayout(new GridLayout(2, team.getSize())); // layout méret

        // Berakjuk a képeket
        for (int i = 0; i < team.getSize(); i++) {
            JLabel l = new JLabel();
            l.setIcon(playerImages.get(i));
            add(l);
        }

        // Belerakjuk a labeleket a panelba
        for (int i = 0; i < hp.size(); i++) {
            add(hp.get(i));
        }
    }

    @Override
    /**
     * Frissitjuk a statusbart
     */
    public void update() {
        for (int i = 0; i < hp.size(); i++) {
            hp.get(i).setText("Heat: " + team.getCharacters().get(i).getHeat());
        }
    }

    /// METHOD DUMP ///
    @Override
    public void addTexture() {
    }
}
