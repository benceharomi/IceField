package graphics.menu;

import graphics.ViewController;
import main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerSettings extends JFrame {
    private JPanel panel = new JPanel();
    private JComboBox<Integer> eskimoBox = new JComboBox<Integer>();
    private JComboBox<Integer> explorerBox = new JComboBox<Integer>();
    private JButton start = new JButton();

    public PlayerSettings(Game g) {
        /// Logic
        setComboBox(eskimoBox);
        setComboBox(explorerBox);

        /// SWING
        setTitle("Adja meg a játékosok számát!");
        setLayout(new FlowLayout());

        add(new JLabel("Eszkimók: "));
        add(eskimoBox);
        add(new JLabel("Felfedezők: "));
        add(explorerBox);
        addWindowListener(g);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> params = new ArrayList<String>();
                params.add(Integer.toString(getEskimoNumber()));
                params.add(Integer.toString(getExplorerNumber()));
                int eskimos = Integer.parseInt(params.get(0));
                int explorers = Integer.parseInt(params.get(1));
                if (eskimos + explorers < 7 && eskimos + explorers > 1) {
                    ViewController.instance().setParams(params);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong number characters");
                }
            }
        });
        start.setText("Start!");

        add(start);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setComboBox(JComboBox<Integer> box) {
        int[] values = { 0, 1, 2, 3, 4, 5, 6 };
        for (int i = 0; i < values.length; i++) {
            box.addItem(values[i]);
        }
    }

    /**
     * Visszaadja az eszkimok szamat
     * 
     * @return
     */
    public int getEskimoNumber() {
        return (int) eskimoBox.getSelectedItem();
    }

    /**
     * Visszaadja a felfedezok szamat
     * 
     * @return
     */
    public int getExplorerNumber() {
        return (int) explorerBox.getSelectedItem();
    }

}
