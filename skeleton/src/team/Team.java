package team;

import field.Field;
import map.Map;
import direction.Direction;
import character.Character;
import skeleton.Skeleton;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Team
 *  Felelosseg
 * Ez az osztaly tarolja az osszes jatekost
 * es az eppen aktiv jatekost is.
 * Ezen az osztalyon keresztul erkezik az osszes jatekoshoz
 * az osszes parancs.
 *  Attributumok
 *  -team: a karaktereket tarolja
 *  -map: eltarolja a mapet
 */

public class Team {
    private ArrayList<Character> team = new ArrayList<>();
    private Map m;

    public Team(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    /**
     * Adds the given character to the team.
     *
     * @param character the desired character to add to the team.
     */
    public void AddCharacter(Character character) {
        Skeleton.instance().called(this, "AddCharacter");

        team.add(character);

        Skeleton.instance().returned();
    }

    /**
     * Tries to assemble the flare gun parts if the stamina is more than 0.
     *
     * @return true if the assemble was successful and false if not.
     */
    public boolean AssembleParts() {
        Skeleton.instance().called(this, "AssembleParts");
        if (team.get(0).AssemblePartsC() == 1) {
            Skeleton.instance().returned();
            return true;
        }
        Skeleton.instance().returned();
        return false;
    }

    /**
     * Tries to dig with the active character if the stamina is more than 0
     * If the dig was successful the stamina is decreased with 1 unit.
     */
    public void Dig() {
        Skeleton.instance().called(this, "Dig");
        if (team.get(0).DigC()) {}


            Skeleton.instance().returned();
        }

        /**
         * Tries to eat with the active character if the stamina is more than 0
         * If the eat was successful the stamina is decreased with 1 unit.
         */
        public void Eat() {
            Skeleton.instance().called(this, "Eat");
            if (team.get(0).EatC()) {

            } else {
                System.out.println("Nincs eleg stamina");
            }
            Skeleton.instance().returned();
        }

        /**
         * This function is called at hte end of any turns
         * Starts the Blizzard on the map and then checks if any character's heat is
         * decreased to 0 or not.
         *
         * @return false if any character's heat is equal to 0, true if not
         */
        public boolean EndTurn () {
            Skeleton.instance().called(this, "EndTurn");

            m.BlizzardM();

            System.out.println("Halt meg karakter? (y/n)");
            Scanner scn = new Scanner(System.in);
            String answer = scn.nextLine();

            if (answer.toLowerCase().equals("y")) {
                Skeleton.instance().returned();
                return false;
            } else if (answer.toLowerCase().equals("n")) {
                Skeleton.instance().returned();
                return true;
            }
            Skeleton.instance().returned();
            return true;

        }

    /**
     * Tries to get an item from the field with the active character if
     * the stamina is more than 0. If the item was successfully picked up
     * the stamina is decreased with 1 unit.
     */
    public void GetItem(){
        Skeleton.instance().called(this, "GetItem");

        team.get(0).GetItemC();

        Skeleton.instance().returned();
    }

    /**
     * Tries to move with the active character to the given direction if the
     * stamina is more than 0. If the movement happened it decreases the
     * stamina with 1 unit.
     *
     * @param direction the wanted direction of the movement
     */
    public void Move(Direction direction){
        Skeleton.instance().called(this, "Move");

        team.get(0).MoveC(direction);

            Skeleton.instance().returned();
        }

    /**
     * Tries to use the special ability with the active character if the
     * stamina is more than 0. If the special ability was used successfully
     * the stamina is decreased with 1 unit.
     */
    public void SpecialAbility() {
        Skeleton.instance().called(this, "SpecialAbility");

        team.get(0).SpecialAbilityC();

        Skeleton.instance().returned();
    }

    public void setTeam(ArrayList<Character> t){
        team = t;
    }
}
