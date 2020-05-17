package main;

import character.Character;
import character.*;
import enums.*;
import field.*;
import item.*;
import map.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.Objects.isNull;

/**
 * a jatek kirajzolasara es logolasara hasznalt singleton osztaly
 */
public class Controller {
    /**
     * singleton controller peldany
     */
    private static Controller single_instance = null;
    /**
     * a karakterek es neveik eltarolasa
     */
    private static HashMap<Character, String> characterList = new HashMap<>();
    /**
     * a fieldek es neveik eltarolasa
     */
    private static HashMap<Field, String> fieldList = new HashMap<>();
    /**
     * a targyak es neveik eltarolasa
     */
    private static HashMap<Item, String> itemList = new HashMap<>();
    /**
     * az eppen aktiv jatekos
     */
    private static Character activePlayer;
    /**
     * a parancsok listaja
     */
    private static ArrayList<String> commandList = new ArrayList<>();
    /**
     * a blizzard parameterei
     */
    private static ArrayList<String> blizzardParameters;
    /**
     * switch ami azt mutatja, random-e a blizzard vagy sem
     */
    private static boolean randomBlizzard = false;
    /**
     * switch, ami mutatja, hogy a felhasznalo iranyitja-e a medvet
     */
    private static boolean controlPolarBear = false;
    /**
     * switch, ami mutatja, lathatoak-e a mapen a targyak akkor is, ha van rajtuk ho
     */
    private static boolean itemAlwaysVisible = true;
    /**
     * switch, ami mutatja, lathatoa-e a mapen a mezok kapacitasa
     */
    private static boolean capacityVisible = true;
    /**
     * switch, ami mutatja, hogy fut-e a jatek
     */
    private static boolean gameOn = true;
    /**
     * a jegesmedvet tarolja
     */
    private static PolarBear bear = null;
    /**
     * a mapet tarolja
     */
    private static Map map;
    /**
     * azt jelzi, epp a manualisan medve kore van-e
     */
    private static boolean polarBearManualMove = false;

    private static void rowTopBottom() {
        System.out.print("+---+");
    }

    private static void row2(Field f) {
        System.out.print("|");
        if ((f.getSnow() != 0 && !itemAlwaysVisible))
            System.out.print('?');
        else if ((itemAlwaysVisible || f.getSnow() == 0) && f.getItem() == null)
            System.out.print(' ');
        else if (itemAlwaysVisible || f.getSnow() == 0)
            System.out.print(f.getItem().draw());
        if (f.getShelter() == null)
            System.out.print(' ');
        else
            System.out.print(f.getShelter().draw());
        System.out.print(" |");
    }

    private static void row3(Field f) {
        System.out.print("|");
        int charnum = f.getCharacters().size();
        charnum = Math.min(charnum, 3);
        for (int i = 0; i < charnum; i++)
            System.out.print(characterList.get(f.getCharacters().get(i)));
        for (int i = 0; i < 3 - charnum; i++)
            System.out.print(' ');
        System.out.print("|");
    }

    private static void row4(Field f) {
        System.out.print("|");
        int charnum = f.getCharacters().size();
        charnum = Math.max(charnum, 3);
        charnum -= 3;
        for (int i = 0; i < charnum; i++)
            System.out.print(characterList.get(f.getCharacters().get(i + 3)));
        for (int i = 0; i < 3 - charnum; i++)
            System.out.print(' ');
        System.out.print("|");
    }

    private static void row5(Field f) {
        System.out.print("|");
        System.out.print(f.getSnow());
        if (capacityVisible || f.getRevealed())
            System.out.print(f.getCapacity());
        else
            System.out.print('?');
        if (bear == null)
            System.out.print(" ");
        else if (bear.getField().equals(f))
            System.out.print('P');
        else
            System.out.print(' ');
        System.out.print("|");
    }

    /**
     * A palya kirajzolasahoz hasznalt fuggveny, a konzolra rajzol Az e fuggveny
     * felett megtalalhato privat fuggvenyek mind ezen fuggveny segedfuggvenyei,
     * azokhoz nem irtunk leirast, mivel nem elerhetoek kulso kontextusbol
     */
    public static void drawMap() {
        if (map == null)
            return;
        ArrayList<Field> fields = map.getFields();
        System.out.println(map.getFields().size());
        for (int mapRow = 0; mapRow < map.getRows(); mapRow++) {
            for (int row = 0; row < 7; row++) {
                for (int mapColumn = 0; mapColumn < map.getColumns(); mapColumn++) {
                    Field currentField = fields.get((mapRow) * (map.getColumns()) + mapColumn);
                    switch (row) {
                        case (0):
                            rowTopBottom();
                            break;
                        case (1):
                            row2(currentField);
                            break;
                        case (2):
                            row3(currentField);
                            break;
                        case (3):
                            row4(currentField);
                            break;
                        case (4):
                            row5(currentField);
                            break;
                        case (5):
                            rowTopBottom();
                            break;
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * map setter
     * 
     * @param map
     */
    public static void setMap(Map map) {
        Controller.map = map;
        Controller.instance().drawMap();
    }

    /**
     * bear setter
     * 
     * @param bear
     */
    public static void setBear(PolarBear bear) {
        Controller.bear = bear;
    }

    /**
     * Controller instance letrehozasa.
     *
     * @return a single instance
     */
    public static Controller instance() {
        if (single_instance == null)
            single_instance = new Controller();
        return single_instance;
    }

    /**
     * Kimenti a commandList-et egy log file-kent
     *
     * @param fileName - logFile neve
     * @throws IOException - valami rossz :)
     */
    public static void saveLog(String fileName) {

        String filePath = new File("").getAbsolutePath() + "/test/output/" + fileName;
        File log = new File(filePath);
        // if (!log.createNewFile()) {
        // throw new FileNotFoundException("Nem sikerult letrehozni a log file-t");
        // }
        try {
            FileWriter fw = new FileWriter(filePath);
            commandList.add("Game log saved to " + fileName);
            System.out.println("Game log saved to " + fileName);
            for (String s : commandList) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Controller");
        }

    }

    /**
     * Letrehoz egy karaktert a Controllerben
     *
     * @param c
     */
    public static void createCharacter(Character c) {
        int letter = 65 + characterList.size();
        characterList.put(c, "" + (char) letter);
    }

    /**
     * Letrehoz egy mezot a Controllerben
     *
     * @param f
     */
    public static void createField(Field f) {
        int number = characterList.size();
        fieldList.put(f, "t" + number);
    }

    /**
     * Letrehoz egy targyat a Controllerben
     * 
     * @param i
     */
    public static void createItem(Item i) {
        itemList.put(i, i.getClass().getName());
    }

    ////// GETTEREK, SETTEREK //////

    public static boolean isPolarBearManualMove() {
        return polarBearManualMove;
    }

    public static void setPolarBearManualMove(boolean polarBearManualMove) {
        Controller.polarBearManualMove = polarBearManualMove;
    }

    public static void setActivePlayer(Character who) {
        activePlayer = who;
    }

    public static Character getActivePlayer() {
        return activePlayer;
    }

    public static void setBlizzardParameters(ArrayList<String> parameters) {
        blizzardParameters = parameters;
    }

    public static ArrayList<String> getBlizzardParameters() {
        return blizzardParameters;
    }

    public static boolean isRandomBlizzard() {
        return randomBlizzard;
    }

    public static void setRandomBlizzard(boolean value) {
        randomBlizzard = value;
    }

    public boolean isControlPolarBear() {
        return controlPolarBear;
    }

    public static void setControlPolarBear(boolean value) {
        controlPolarBear = value;
    }

    public static boolean isItemAlwaysVisible() {
        return itemAlwaysVisible;
    }

    public static void setItemAlwaysVisible(boolean value) {
        itemAlwaysVisible = value;
    }

    public static boolean isCapacityVisible() {
        return capacityVisible;
    }

    public static void setCapacityVisible(boolean value) {
        capacityVisible = value;
    }

    public static void setGameOn(boolean value) {
        gameOn = value;
    }

    public static boolean getGameOn() {
        return gameOn;
    }

    ////// KIMENET //////

    /**
     * A hovihar output fuggvenye
     *
     * @param character - a mezon levo karakter
     * @param field     - a mezo, ahol a hovihar van
     * @param PARAM     - case valszto enum
     */
    public static void blizzardOutput(Character character, Field field, BlizzardParam PARAM) {
        String output;
        switch (PARAM) {
            case NO_EFFECT: {
                output = "Blizzard " + fieldList.get(field) + " no effect";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case SAFE: {
                output = "Blizzard " + fieldList.get(field) + " safe";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case DAMAGE: {
                output = "Blizzard " + fieldList.get(field) + " " + characterList.get(character) + " damaged";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case SNOW_AT_MAX: {
                output = "Blizzard " + fieldList.get(field) + " no effect, snow at maximum";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println("Hibas parameter");
                break;
            }
        }
    }

    /**
     * A satorepites output fuggvenye
     *
     * @param character a birtokos karakter
     * @param field     a mezo ahova epiteni akarunk
     * @param PARAM     case valaszto enum
     */
    public static void tentOutput(Character character, Field field, TentParam PARAM) {
        String output;
        switch (PARAM) {
            case BUILD: {
                output = characterList.get(character) + " built tent on " + fieldList.get(field);
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case DESTROY: {
                output = "Tent on " + fieldList.get(field) + " destroyed";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case NO_TENT: {
                output = characterList.get(character) + " has no tent";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case OCCUPIED: {
                output = characterList.get(character) + " didn't build " + fieldList.get(field) + " has shelter";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println("Hibas parameter");
                break;
            }
        }

    }

    /**
     * A targyak outputja
     *
     *
     * @param field ahol felszedheti a targyat
     * @param PARAM modosito
     */
    public static void itemOutput(Field field, ItemParam PARAM) {
        String output;
        switch (PARAM) {
            case SNOW: {
                output = characterList.get(activePlayer) + " GetItem " + fieldList.get(field) + " snow";
                System.out.println(output);
                commandList.add(output);
                break;

            }
            case NORMAL: {
                output = characterList.get(activePlayer) + " GetItem " + fieldList.get(field);
                System.out.println(output);
                commandList.add(output);
                break;

            }
            case NO_ITEM: {
                output = characterList.get(activePlayer) + " GetItem " + fieldList.get(field) + " no item";
                System.out.println(output);
                commandList.add(output);
                break;

            }
            default: {
                System.out.println("Hibas parameter");
                break;
            }
        }
    }

    /**
     * Targy torles output
     * 
     * @param character - ki torolte
     * @param item      - mit
     */
    public static void deleteItemsOutput(Character character, Item item) {
        String output = characterList.get(character) + " removed " + item.getClass().getName();
        System.out.println(output);
        commandList.add(output);
    }

    /**
     * Az asas outputja
     *
     * @param character aki as
     * @param field     ahol as
     * @param PARAM     ahogy as
     */
    public static void digOutput(Character character, Field field, ShovelParam PARAM) {
        String output;
        switch (PARAM) {
            case NO_SHOVEL: {
                output = characterList.get(character) + " Dig " + fieldList.get(field);
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case SHOVEL: {
                output = characterList.get(character) + " Dig " + fieldList.get(field) + " shovel";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case NO_SNOW: {
                output = characterList.get(character) + " Dig " + fieldList.get(field) + " no snow";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println("Hibas parameter");
                break;
            }
        }
    }

    /**
     * A jatekos aktivalas outputja
     *
     * @param character az aktiv jatekos
     */
    public static void activePlayerOutput(Character character) {
        String output = characterList.get(character) + " is active player";
        System.out.println(output);
        commandList.add(output);
    }

    /**
     * Game Over uzenet outputja
     */
    public static void GameOver() {
        String output = "GameOver";
        System.out.println(output);
        commandList.add(output);
    }
    // -------------------------------------------------------------------------------

    /**
     * mozgas uzenet outputja sikeresen gond nelkul mozgott at lyukba lepett de van
     * rajta buvarruha instabil mezore mozgott es atfordult lyukba lepett es
     * megfulladt a jatekos lyukba lepett es szomszedos mezorol megmentetek
     *
     * @param character    a karakter aki mozog
     * @param fromField    a mezo ahonnan mozgott
     * @param newField     a mezo ahova mozgott
     * @param PARAM        milyen mzgas volt
     * @param rescudeField a megmentesnel a megmentes mezoje
     */
    public static void moveOutput(Character character, Field fromField, Field newField, MovingParam PARAM,
            Field rescudeField) {
        if (isNull(fromField)) {
            return;
        }
        String output;
        switch (PARAM) {
            case SuccessFull: {
                output = characterList.get(character) + " move from " + fieldList.get(fromField) + " to "
                        + fieldList.get(newField);
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case HoleScuba: {
                output = characterList.get(character) + " move from " + fieldList.get(fromField) + " to "
                        + fieldList.get(newField) + " in scubasuit";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case UnstableFlipped: {
                output = characterList.get(character) + " move from " + fieldList.get(fromField) + " to "
                        + fieldList.get(newField) + " the field flipped";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case Drowned: {
                output = characterList.get(character) + " move from " + fieldList.get(fromField) + " to "
                        + fieldList.get(newField) + " and drowned";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case Andreascued: {
                output = characterList.get(character) + " move from " + fieldList.get(fromField) + " to "
                        + fieldList.get(newField) + " and was rescued from " + fieldList.get(rescudeField);
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println(PARAM);
                break;
            }
        }
    }

    /**
     * Medve mozgasanak outputja medve msikeresen atlep szomszedos mezore medve
     * atlep es megtamadja a rajta levo karaktereket
     * 
     *
     * @param fromField a mezo ahonnan a medve mozog
     * @param newField  a mezo ahova leptt
     * @param PARAM     a tipus milyen fajta mozgas hajtott vegre
     */
    public static void polarbearmoveoutput(Field fromField, Field newField, MovingParam PARAM) {
        String output;
        switch (PARAM) {
            case polarBearMove: {
                output = "Polarbear moved from " + fieldList.get(fromField) + " to " + fieldList.get(newField);
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case polarBearAttack: {
                output = "Polarbear moved from " + fieldList.get(fromField) + " to " + fieldList.get(newField)
                        + " and attacked";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println(PARAM);
                break;
            }
        }
    }

    /**
     * karakter specialis kepeseget hasznal eszkimo sikeresen hasznalta a kepeseget
     * es iglut rakott a mezore eszkimo nem tudott rakni iglut mert van mar rajta
     * valami explorer felfedte a szomszedos mezo kapacitasat
     *
     *
     * @param f     a mezo ahol epiteni akar/felfedni a kapacitas
     * @param PARAM a tipus hogy hogyan tud epiteni
     */
    public static void eszkimoSpecialAbility(Field f, int cap, SpecParam PARAM) {
        String output;
        switch (PARAM) {
            case build: {
                output = characterList.get(activePlayer) + " Eskimo " + fieldList.get(f) + " built igloo";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case cantBuild: {
                output = characterList.get(activePlayer) + " Eskimo " + fieldList.get(f) + " already have shelter";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case explored: {
                output = characterList.get(activePlayer) + " Explorer explored " + fieldList.get(f) + " capacity: "
                        + cap;
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println(PARAM);
                break;
            }
        }
    }

    /**
     * mezohoz hozzza rendel szomszedos mezoket
     *
     * @param f              a mezo amihez hozzarendeltek szomszedokat
     * @param neighbourField a szomszedos mezok listaja
     */
    public static void addNeighbours(Field f, ArrayList<Field> neighbourField) {
        String output = "Added neighbours to " + fieldList.get(f) + ", neighbours: ";
        for (int i = 0; i < neighbourField.size(); i++) {
            output = output + fieldList.get(neighbourField.get(i)) + " ";
        }
        System.out.println(output);
    }

    /**
     * uj mezo letrehozassa
     *
     * @param f        az uj mezo
     * @param type     a mezo tipusa
     * @param capacity a mezo capacitasa
     */
    public static void creatnewFiels(Field f, String type, int capacity) {
        int letter = fieldList.size() + 1;
        String output = "Created new Field " + " " + letter + ", " + type + ", " + capacity;
        fieldList.put(f, "" + letter);
        System.out.println(output);
    }

    /**
     * a jatekosok megnyertek a jatekot
     */
    public static void victoryout() {
        String output;
        output = "Victory";
        System.out.println(output);
        commandList.add(output);
    }

    /**
     * a jatekosok megprobaljak osszerakni a jelzopisytoly de nem sikerul mert nincs
     * meg az osszes alkatresz
     *
     * @param character a karakter aki probalkozik osszerakni
     * @param num       darabszam ahany db jelzopisztoy alkatresz mar megvan
     */
    public static void misspieces(Character character, int num) {
        String output = characterList.get(character) + " Assemble parts missing: ";
        for (int i = 0; i < 3 - num; i++) {
            output += " Flaregunpart" + (char) (i + 65);
        }
        System.out.println(output);
        commandList.add(output);
    }

    /**
     * a jatekosok osszetudjak szerelni a jelzopisztolyt de nincsenek azonos mezokon
     */
    public static void differentout() {
        String output = "AssambleParts different fields";
        System.out.println(output);
        commandList.add(output);
    }

    /**
     * A karakter megprobal enni ennek az outputja A jatekos sikeresen evett es
     * novekedett a testhoje A jatekos meg probal enni de nincs nala etel A jatekos
     * megprobal enni de nem tud mert maximum a testhoje
     *
     * @param character aki enni akkar
     * @param PARAM     milyen tipusu az amikor esszik
     */
    public static void eatoutput(Character character, EatParam PARAM) {
        String output;
        switch (PARAM) {
            case eat: {
                output = characterList.get(character) + " Eat " + (character.getHeat() - 1) + " " + character.getHeat();
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case eatNoFood: {
                output = characterList.get(character) + " Eat no food";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            case heatMax: {
                output = characterList.get(character) + " Eat max";
                System.out.println(output);
                commandList.add(output);
                break;
            }
            default: {
                System.out.println(PARAM);
                break;
            }
        }
    }

    /**
     * A jatekos befejezi a koret
     * 
     * @param character a jatekos aki befejezi a kort
     */
    public static void endTurnout(Character character) {
        String output = characterList.get(character) + " ended turn early";
        System.out.println(output);
        commandList.add(output);
    }
}
