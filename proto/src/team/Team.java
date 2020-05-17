package team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import character.Character;
import character.PolarBear;
import field.Direction;
import map.Map;
import main.Controller;

/**
 * A team osztaly fogja ossze a jatekosok karaktereit. Ez az osztaly tartalmazza a
 karaktereket es tudja roluk, hogy melyik az eppen soron levo karakter es hogy a
 karakter hany lepest tehet meg a koreben. Ez az osztaly hajtatja vegre a jatekosokkal a
 kapott muveleteket.
 */
public class Team implements Serializable {
    /**
     * Az eppen aktiv karakter indexe.
     */
    private int active;
    /**
     * A jatekteret osszefogo Map osztaly.
     */
    private Map map;
    /**
     * A palyan maszkalo jegesmedve
     */
    private PolarBear polarBear;
    /**
     * Az tarolja, hogy a jatekos meg hany lepest tehet meg a koreben.
     * Minden kor elejen a kezdo erteke 4.
     */
    private int stamina;
    /**
     * A jatekosokat tarolo lista.
     */
    private ArrayList<Character> team;
    /**
     * Randomszam generalasara
     */
    private Random rand = new Random();

    /**
     * Team konstruktor
     * @param map a terkep, amelyen mozognak a karakterek
     */
    public Team(Map map) {
        active = 0;
        this.map = map;
        stamina = 4;
        team = new ArrayList<Character>();
    }

    /**
     * Ellenorzi, van-e halott karakter
     * @return true, ha van halott karakter, false, ha nincs
     */
    private boolean deadCharacter() {
        for (Character character : team) {
            if (character.getHeat() == 0) {
                Controller.instance().GameOver();
                return true;
            }
        }
        return false;
    }

    /**
     * A csapathoz hozzadja a parameterkent kapott karaktert.
     *
     * @param character a hozzaadando karakter
     */
    public void addCharacter(Character character) {
        if (team.size() == 0) {
            Controller.setActivePlayer(character);
            Controller.activePlayerOutput(character);
        }
        team.add(character);
    }

    /**
     * A jelzoraketa alkatreszek osszeszereleset kezdemenyezo metodus. Ellenorzi,
     * hogy minden karakter egy mezon all-e es, hogy naluk van-e mind a 3 akatresz,
     * amennyiben ezek teljesulnek, meghivja a decreaseStamina() metodust.
     *
     * @return boolean, amennyiben sikeresen lezajlott true ertekkel, ellenben
     *         false-al
     */
    public boolean assembleParts() {
        int sumParts = 0;
        for (Character character : team) {
            if (team.get(active).getField().equals(character.getField())) {
                sumParts += character.assamblePartsC();
            } else {
                Controller.instance().differentout();
                return false;
            }
        }
        if (sumParts == 3) {
            Controller.instance().victoryout();
            decreaseStamina();
            return true;
        }
        Controller.instance().misspieces(team.get(active), sumParts);
        return false;

    }

    /**
     * Staminat csokkento segedfuggveny.
     */
    private void decreaseStamina() {
        stamina--;
    }

    /**
     * Sator epiteset kezdemenyezo metodus. Meghivja az aktiv karakter deployC()
     * metodusat. Amennyiben sikeres volt, meghivja a decreaseStamina() metodust.
     */
    public void deploy() {
        if (team.get(active).deployC()) {
            decreaseStamina();
        }
    }

    /**
     * Asast kezdemenyezo metodus. Meghivja az aktiv karakter digC() metodusat.
     * Amennyiben sikeres volt, meghivja a decreaseStamina() metodust.
     */
    public void dig() {
        if (team.get(active).digC()) {
            decreaseStamina();
        }
    }

    /**
     * Evest kezdemenyezo metodus. Meghivja az aktiv karakter eatC() metodusat.
     * Amennyiben sikeres volt, meghivja a decreaseStamina() metodust.
     */
    public void eat() {
        if (team.get(active).eatC()) {
            decreaseStamina();
        }
    }

    /**
     * Fuggveny a medve kozvetlen mozgatasara
     * @param d irany
     */
    public void moveBear(Direction d) {
        polarBear.moveP(d);
        if (!deadCharacter())
            Controller.instance().activePlayerOutput(team.get(active));
        Controller.setPolarBearManualMove(false);
    }

    /**
     * Akcio befejezesekor hivodik a map kirajzolasara
     * @return
     */
    public boolean endAction() {
        if (deadCharacter())
            return true;
        boolean ret = false;
        if (stamina == 0)
            ret = endTurn();
        Controller.drawMap();
        return ret;
    }

    /**
     * A jatekos korenek befejezo fuggvenye. A karakterek testhojet ellenorzi, ha az
     * utolso jatekoson hivodott meg, akkor a jegesmedve mozgasat is elinditja.
     *
     * @return boolean, melynek erteke true amennyiben valamely jatekos testhoje
     *         nullara csokkent, egyebkent false
     */
    public boolean endTurn() {
        if (stamina > 0)
            Controller.instance().endTurnout(team.get(active));
        if (active == team.size() - 1) {
            active = 0;
            if (Controller.instance().isRandomBlizzard()) {
                map.blizzardM();
            }
            if (polarBear != null) {
                boolean validMove = false;
                Direction moveDirection = Direction.NORTH;
                if (Controller.instance().isControlPolarBear()) {
                    Controller.instance().setPolarBearManualMove(true);
                    System.out.print("Adj meg egy iranyt a medvenek");
                } else {
                    int randInt = rand.nextInt(4);
                    switch (randInt) {
                        case 0:
                            polarBear.moveP(Direction.EAST);
                            break;
                        case 1:
                            polarBear.moveP(Direction.NORTH);
                            break;
                        case 2:
                            polarBear.moveP(Direction.SOUTH);
                            break;
                        case 3:
                            polarBear.moveP(Direction.WEST);
                            break;
                        default:
                            break;
                    }
                }
                if (deadCharacter()) {
                    return true;
                }

            }
        } else

        {
            active++;
        }

        map.destroyShelterM();
        Controller.instance().setActivePlayer(team.get(active));
        if (!Controller.instance().isControlPolarBear())
            Controller.instance().activePlayerOutput(team.get(active));
        stamina = 4;
        return false;
    }


    /**
     * Targy felvetelet kezdemenyezo metodus. Meghivja az aktiv karakter getItemC()
     * metodusat. Amennyiben sikeres volt, meghivja a decreaseStamina() metodust.
     */
    public void getItem() {
        if (team.get(active).getItemC()) {
            decreaseStamina();
        }
    }

    /**
     * Karakter mozgatasat kezdemenyezo metodus. Meghivja az aktiv karakter moveC()
     * metodusat. Amennyiben sikeres volt, meghivja a decreaseStamina() metodust.
     *
     * @param direction
     */
    public void move(Direction direction) {
        if (team.get(active).moveC(direction)) {
            decreaseStamina();
        }
    }

    /**
     * Jegesmedvet allit be a jatekhoz
     *
     * @param polarBear
     */
    public void setPolarBear(PolarBear polarBear) {
        this.polarBear = polarBear;
    }

    /**
     * Karakter specialis kepessegenek hasznalatat kezdemenyezo metodus. Meghivja az
     * aktiv karakter specialAbilityC() metodusat. Amennyiben sikeres volt, meghivja
     * a decreaseStamina() metodust.
     */
    public void specialAbility() {
        if (team.get(active).specialAbilityC()) {
            decreaseStamina();
        }
    }
}