package graphics.listeners;

import field.Direction;
import graphics.ViewController;
import team.Team;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Spec. Listener a karakterek mozgatására.
 */
public class ArrowListener extends KeyAdapter {
    private Team team;

    public ArrowListener(Team team){
        this.team = team;
    }

    /**
     * MOzgatás
     * @param e - lenyomott billentyu eventje
     */
    @Override
    public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    team.move(Direction.WEST);
                    System.out.println("BALRA");
                    break;
                case KeyEvent.VK_RIGHT:
                    team.move(Direction.EAST);
                    System.out.println("JOBBRA");
                    break;
                case KeyEvent.VK_UP:
                    team.move(Direction.NORTH);
                    System.out.println("FEL");
                    break;
                case KeyEvent.VK_DOWN:
                    team.move(Direction.SOUTH);
                    System.out.println("LE");
                    break;
                case KeyEvent.VK_A:
                    boolean victory = team.assembleParts();
                    if (victory) {
                        JOptionPane.showMessageDialog(null, "Gratulálok! Nyertél!");
                        ViewController.instance().endGame();
                    }
                    System.out.println("AssambleParts");
                    break;
                case KeyEvent.VK_D:
                    team.dig();
                    System.out.println("Dig");
                    break;
                case KeyEvent.VK_T:
                    team.deploy();
                    System.out.println("Tent deployed");
                    break;
                case KeyEvent.VK_E:
                    team.eat();
                    System.out.println("Eat");
                    break;
                case KeyEvent.VK_SPACE:
                    team.endTurn();
                    System.out.println("EndTurn was called early");
                    break;
                case KeyEvent.VK_G:
                    team.getItem();
                    System.out.println("Item has been picked up");
                    break;
                case KeyEvent.VK_S:
                    team.specialAbility();
                    System.out.println("Special ability");
                    break;
                default:
                    System.out.println("Helytelen utasitas!");
            }
            if(team.endAction()){
                JOptionPane.showMessageDialog(null, "Meghaltál! A játéknak vége!");
                ViewController.instance().endGame();
            }
    }
}
