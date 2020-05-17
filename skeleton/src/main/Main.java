package main;

import character.*;
import character.Character;
import direction.Direction;
import field.*;
import map.*;
import team.*;
import item.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Jegmezo szkeleton -- nullpointer csapat");
        System.out.println();
        System.out.println("Milyen forgatokonyvet szeretne kiprobalni?\n");
        System.out.println("1. Specialis kepesseg hasznalata");
        System.out.println("2. A jatekos befejezi a koret");
        System.out.println("3. A jatekos enni akar");
        System.out.println("4. A jatekos asni akar");
        System.out.println("5. A jatekos ossze akarja szerelni a jelzopisztolyt");
        System.out.println("6. A jatekos targyat akar felvenni");
        System.out.println("7. A jatekos mozogni akar");
        System.out.println("8. Kilepes");
        System.out.println();


        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);


        switch(choice){
            case 1: SpecialAbilityTest(); break;
            case 2: PlayerEndsTurnTest(); break;
            case 3: PlayerEatsTest(); break;
            case 4: PlayerDiggingTest(); break;
            case 5: PlayerAssembleTest(); break;
            case 6: PlayerItemPickUpTest(); break;
            case 7: PlayerMovementTest(); break;
            case 8: break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }

    }

    private static Direction MovementDirection() {
        System.out.println("Merre szeretne mozogni?");
        System.out.println("1. Eszak");
        System.out.println("2. Kelet");
        System.out.println("3. Del");
        System.out.println("4. Nyugat");

        Direction d = Direction.NORTH;

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: d = Direction.NORTH; break;
            case 2: d = Direction.EAST; break;
            case 3: d = Direction.SOUTH; break;
            case 4: d = Direction.WEST; break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
        return d;
    }

    private static int CharacterType() {
        System.out.println("Milyen tipusu legyen a karakter?");
        System.out.println("1. Eszkimo");
        System.out.println("2. Kutato");
        System.out.println();

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        int type = 0;
        switch(choice){
            case 1: break;
            case 2: type = 1; break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
        return type;
    }

    private static int StaminaAmmount() {
        System.out.println();
        System.out.println("Mennyi staminaja legyen a karakternek?(0-4)");

        String answer = scn.nextLine();
        int stamina = Integer.parseInt(answer);

        if(0 > stamina || stamina > 4) {
            System.out.println("Hibas stamina ertek!!");
            main(null);
        }
        return stamina;
    }

    public static int SnowAmount() {
        System.out.println();
        System.out.println("Mennyi ho legyen a mezon?(0-4)");

        String answer = scn.nextLine();
        int snow = Integer.parseInt(answer);

        if(0 > snow || snow > 4) {
            System.out.println("Hibas ho ertek!!");
            main(null);
        }
        return snow;
    }

    public static int HeatAmount(int type) {
        System.out.println();
        System.out.println("Mennyi hoenergiaja legyen a karakternek?");

        String answer = scn.nextLine();
        int heat = Integer.parseInt(answer);

        if((0 > heat || heat > 4) && type == 1) {
            System.out.println("Hibas hoenergia ertek!!");
            main(null);
        }
        if((0 > heat || heat > 5) && type == 0) {
            System.out.println("Hibas hoenergia ertek!!");
            main(null);
        }
        return heat;
    }

    /**
     * ====================================
     *  SCENARIO 1
     * ====================================
     * */


    /**
     * Fuggveny amely az egyes karakterek spec. kepesseget teszteli stamina fuggvenyeben.
     */
    private static void SpecialAbilityTest() {
        System.out.println("1. Specialis kepesseg hasznalata");
        System.out.println();
        int type = CharacterType();
        int stamina = StaminaAmmount();

        switch(type){
            case 0: EskimoSpecialTest(stamina); break;
            case 1: ExplorerSpecialTest(stamina); break;
            default: System.out.println("Nem megfelelo input"); break;
        }
    }

    /**
     * Leteszteli az eszkimok spec. kepessegenek hasznalatat
     * @param stamina -- az atadott stamina mennyiseg
     */
    private static void EskimoSpecialTest(int stamina) {
        System.out.println("EskimoSpecialTest");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        Eskimo es = new Eskimo("eskimo", "Eskimo");
        es.addField(s);

        System.out.println();
        System.out.println("EskimoSpecialTest");


        ArrayList<Character> players = new ArrayList<Character>();
        players.add(es);
        t.setTeam(players);

        t.SpecialAbility();
        main(null);
    }

    /**
     * Leteszteli az felfedezo spec. kepessegenek hasznalatat
     * @param stamina -- az atadott stamin mennyiseg
     */
    private static void ExplorerSpecialTest(int stamina) {
        System.out.println("ExplorerSpecialTest");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        Explorer ex = new Explorer("explorer", "Explorer");
        ex.addField(s);

        NeighboursSetUp(s);

        ArrayList<Character> players = new ArrayList<Character>();
        players.add(ex);
        t.setTeam(players);

        t.SpecialAbility();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 2
     * ====================================
     * */

    /**
     * Leteszteli a játékosok körének befejezését
     */
    private static void PlayerEndsTurnTest(){
        System.out.println("2. A jatekos befejezi a koret");
        System.out.println();
        System.out.println("Legyen hovihar?");
        System.out.println("1. Igen");
        System.out.println("2. Nem");
        System.out.println();

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: BlizzardTest(); break;
            case 2: main(null); break;
            default: System.out.println("Nem megfelelo input"); break;
        }
    }

    /**
     * Leteszteli a hovihart hivo fuggvenyeket.
     */
    private static void BlizzardTest() {
        System.out.println("BlizzardTest");

        int type = CharacterType();
        int stamina = StaminaAmmount();

        Stable s = new Stable("viharos_mezo", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        Explorer teammate = new Explorer ("teammate","Explorer");
        teammate.addField(s);
        players.add(teammate);

        t.setTeam(players);
        s.AddCharacter(players.get(0));
        s.AddCharacter(players.get(1));

        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(s);
        Map m = new Map("palya", "Map");
        m.SetFields(fields);

        m.BlizzardM();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 3
     * ====================================
     * */

    /**
     * Leteszteli a jatekosok etelkezelo fuggvenyeit, illetve valasztast ad a scenarioban
     */
    private static void PlayerEatsTest(){
        System.out.println("3. A jatekos enni akar");
        System.out.println();

        int type = CharacterType();
        int stamina = StaminaAmmount();
        int heat = HeatAmount(type);

        System.out.println("Legyen nala elelmiszer?");
        System.out.println("1. Igen");
        System.out.println("2. Nem");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: PlayerEat(type,stamina,heat,1); break;
            case 2: PlayerEat(type,stamina,heat,0); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
    }

    /**
     * Az etelkezelo teszt belso fuggvenye.
     * @param type -- a karakter tipusa
     * @param stamina -- staminamennyiség
     * @param heat -- a karakter hoje
     * @param food -- etel a karakternel
     */
    private static void PlayerEat(int type, int stamina, int heat, int food) {
        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        ArrayList<Item> itemlist = new ArrayList<Item>();
        switch(food) {
            case 0: Shovel shovel = new Shovel("shovel", "Shovel"); itemlist.add(shovel); players.get(0).SetItems(itemlist); break;
            case 1: Food fd = new Food("food", "Food"); itemlist.add(fd); players.get(0).SetItems(itemlist); break;
        }
        ScubaSuit sc = new ScubaSuit("scubasuit","ScubaSuit");
        Rope rope = new Rope("rope","Rope");
        itemlist.add(sc);
        itemlist.add(rope);

        players.get(0).SetItems(itemlist);

        t.Eat();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 4
     * ====================================
     * */

    /**
     * A jatekos asni probal tesztesete
     */
    private static void PlayerDiggingTest(){
        System.out.println("4. A jatekos asni akar");
        int type = CharacterType();
        int stamina = StaminaAmmount();

        System.out.println("Legyen a jatekosnak asoja?");
        System.out.println("1. Igen");
        System.out.println("2. Nem");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: PlayerDigging(type,stamina,1); break;
            case 2: PlayerDigging(type,stamina,0); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }

    }

    /**
     * A jatekos asni probal fuggvenye
     *
     * @param type A karakter típusa
     * @param stamina Az átadott staminamennyiség
     * @param shovel Ha van ásó akkor 1, ha nincs akkor 0
     */
    private static void PlayerDigging(int type, int stamina, int shovel) {
        System.out.println("PlayerDigging");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        ArrayList<Item> itemlist = new ArrayList<Item>();
        switch(shovel) {
            case 1: Shovel sh = new Shovel("shovel", "Shovel"); itemlist.add(sh); players.get(0).SetItems(itemlist); break;
            case 0: Food fd = new Food("food", "Food"); itemlist.add(fd); players.get(0).SetItems(itemlist); break;
        }

        ScubaSuit sc = new ScubaSuit("scubasuit","ScubaSuit");
        Food food = new Food("food","Food");
        itemlist.add(sc);
        itemlist.add(food);

        players.get(0).SetItems(itemlist);

        t.Dig();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 5
     * ====================================
     * */

    /**
     * A jelzopisztoly osszeszereles tesztesete
     */
    private static void PlayerAssembleTest(){
        System.out.println("5. A jatekos ossze akarja szerelni a jelzopisztolyt");
        int type = CharacterType();
        int stamina = StaminaAmmount();

        System.out.println("Melyik forgatokonyvet szeretne kiprobalni?");
        System.out.println("1. A ket karakter mas-mas mezon van de nincs meg az osszes alkatresz");
        System.out.println("2. A ket karakter mas-mas mezon van de megvannak az alkatreszek");
        System.out.println("3. A ket karakter egy mezon van de nincs meg az osszes alkatresz");
        System.out.println("4. A ket karakter egy mezon van es megvan az osszes alkatresz");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: DifferentFieldsAssemble(type,stamina,0); break;
            case 2: DifferentFieldsAssemble(type,stamina,1); break;
            case 3: SameFieldAssemble(type,stamina,0); break;
            case 4: SameFieldAssemble(type,stamina,1); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
    }

    /**
     * Osszeszereles abban az esetben, amikor kulonbozo tablakon allnak a jatekosok
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     * @param parts ha megvan az osszes alkatresz 1, ha nincs 0
     */
    private static void DifferentFieldsAssemble(int type, int stamina, int parts) {
        System.out.println("DifferentFieldsAssemble");

        Stable s1 = new Stable("field1", "Stable");
        Stable s2 = new Stable("field2", "Stable");

        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        ArrayList<Item> itemlist = new ArrayList<Item>();
        itemlist.add(new Shovel("shovel", "Shovel"));
        itemlist.add(new Food("food", "Food"));

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s1); es.SetItems(itemlist);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s1); ex.SetItems(itemlist);
                players.add(ex);
                break;
        }

        ArrayList<Item> itemlist2 = new ArrayList<Item>();
        itemlist2.add(new Shovel("shovel", "Shovel"));
        itemlist2.add(new Food("food", "Food"));

        Explorer teamMate = new Explorer("teammate", "Explorer");
        teamMate.SetItems(itemlist2);
        teamMate.addField(s2);
        players.add(teamMate);

        t.setTeam(players);

        switch(parts) {
            case 0: {FlareGunPart part = new FlareGunPart("gunpart", "FlareGunPart"); itemlist.add(part); teamMate.SetItems(itemlist); break;}
            case 1: {FlareGunPart part1 = new FlareGunPart("gunpart1", "FlareGunPart"); itemlist.add(part1);
                FlareGunPart part2 = new FlareGunPart("gunpart2", "FlareGunPart"); itemlist.add(part2);
                FlareGunPart part3 = new FlareGunPart("gunpart3", "FlareGunPart"); itemlist.add(part3);
                break;}
        }

        t.AssembleParts();
        main(null);
    }

    /**
     * Osszeszereles abban az esetben, amikor ugyanazon a tablan allnak a jatekosok
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     * @param parts ha megvan az osszes alkatresz 1, ha nincs 0
     */
    private static void SameFieldAssemble(int type, int stamina, int parts) {
        System.out.println("DifferentFieldsAssemble");

        Stable s1 = new Stable("field", "Stable");

        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        ArrayList<Item> itemlist = new ArrayList<Item>();
        ArrayList<Item> itemlist2 = new ArrayList<Item>();
        itemlist.add(new Shovel("shovel", "Shovel"));
        itemlist.add(new Food("food", "Food"));

        itemlist2.add(new Shovel("shovel", "Shovel"));
        itemlist2.add(new Food("food", "Food"));

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s1); es.SetItems(itemlist);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s1); ex.SetItems(itemlist);
                players.add(ex);
                break;
        }

        Explorer teamMate = new Explorer("teammate", "Explorer");
        teamMate.SetItems(itemlist2);
        teamMate.addField(s1);
        players.add(teamMate);

        t.setTeam(players);



        switch(parts) {
            case 0: {FlareGunPart part = new FlareGunPart("gunpart", "FlareGunPart"); itemlist.add(part); teamMate.SetItems(itemlist); break;}
            case 1: {FlareGunPart part1 = new FlareGunPart("gunpart1", "FlareGunPart"); itemlist2.add(part1);
                FlareGunPart part2 = new FlareGunPart("gunpart2", "FlareGunPart"); itemlist2.add(part2);
                FlareGunPart part3 = new FlareGunPart("gunpart3", "FlareGunPart"); itemlist2.add(part3);
                break;}
        }

        t.AssembleParts();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 6
     * ====================================
     * */

    /**
     * A jatekos targyat probal felvenni tesztesete
     */
    private static void PlayerItemPickUpTest(){
        System.out.println("6. A jatekos targyat akar felvenni");
        int type = CharacterType();
        int stamina = StaminaAmmount();

        System.out.println("Legyen a mezon targy?");
        System.out.println("1. Igen");
        System.out.println("2. Nem");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: PlayerPicksUpItem(type, stamina, 1); break;
            case 2: PlayerPicksUpItem(type, stamina, 0); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
    }

    /**
     * A jatekos targyat vesz fel
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     * @param item 1 ha van a mezon targy, 0 ha nincs
     */
    private static void PlayerPicksUpItem(int type, int stamina, int item) {
        System.out.println("PlayerPicksUpItem");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        switch(item) {
            case 0: Shovel sh = new Shovel("shovel", "Shovel"); s.SetItem(sh); break;
            case 1: ; break;
        }

        t.GetItem();
        main(null);
    }

    /**
     * ====================================
     *  SCENARIO 7
     * ====================================
     * */

    /**
     * A jatekos mozogni akar tesztesete
     */
    private static void PlayerMovementTest(){
        System.out.println("7. A jatekos mozogni akar");
        int type = CharacterType();
        int stamina = StaminaAmmount();

        System.out.println("Merre szeretne mozogni?");
        System.out.println("1. Ki akar menni a palyarol");
        System.out.println("2. Stabil mezore akar mozogni");
        System.out.println("3. Instabil mezore akar mozogni");
        System.out.println("4. Lyukra akar mozogni");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: PlayerMoveOutOfPlayArea(type,stamina); break;
            case 2: PlayerMovesOnStableField(type,stamina); break;
            case 3: PlayerMovesOnUnstableField(type,stamina); break;
            case 4: PlayerMovesOnHole(type,stamina); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
    }

    /**
     * A jatekos ki probal mozogni a palyarol
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     */
    private static void PlayerMoveOutOfPlayArea(int type, int stamina){
        System.out.println("PlayerMoveOutOfPlayArea");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        t.Move(MovementDirection());
        main(null);
    }

    /**
     * A jatekos stabil tablara mozog
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     */
    private static void PlayerMovesOnStableField(int type, int stamina){
        System.out.println("PlayerMovesOnStableField");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);
        NeighboursSetUp(s);

        t.Move(MovementDirection());
        main(null);

    }

    /**
     * A jatekos instabil tablara mozog
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     */
    private static void PlayerMovesOnUnstableField(int type, int stamina){
        System.out.println("PlayerMovesOnUnstableField");

        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); ex.addField(s);
                players.add(ex);
                break;
        }

        System.out.println("Hany karakter legyen a mezon?");
        String answer = scn.nextLine();
        int numberofplayers = Integer.parseInt(answer);


        UnStable un = new UnStable("unstable", "UnStable");
        s.SetNeighbour(Direction.NORTH, un);
        un.SetNeighbour(Direction.SOUTH, s);

        ArrayList<Character> temp = new ArrayList<Character>();
        for(int i = 0; i < numberofplayers; i++) {
            Eskimo e = new Eskimo("eskimo", "Eskimo");
            e.addField(un);
            temp.add(e);
        }
        un.SetCharacters(temp);

        t.setTeam(players);

        t.Move(Direction.NORTH);
        main(null);
    }

    /**
     * a jatekos lyukra mozog
     *
     * @param type a karakter tipusa
     * @param stamina a staminamennyiseg
     */
    private static void PlayerMovesOnHole(int type, int stamina){
        System.out.println("PlayerMovesOnHole");

        System.out.println("Melyik forgatokonyvet szeretne kiprobalni?");
        System.out.println("1. A karakteren van buvarruha");
        System.out.println("2. A karakteren nincs buvarrua es nem mentik ki");
        System.out.println("3. A karakteren nincs buvarrua es kimentik");

        String answer = scn.nextLine();
        int choice = Integer.parseInt(answer);

        switch(choice){
            case 1: ScubaSuitDive(type,stamina); break;
            case 2: ColdDive(type,stamina,0); break;
            case 3: ColdDive(type,stamina,1); break;
            default: System.out.println("Nem megfelelo input"); main(null); break;
        }
    }

    /**
     * Buvarruhaval valo merules
     *
     * @param type karakter tipusa
     * @param stamina staminamennyiseg
     */
    private static void ScubaSuitDive(int type, int stamina) {
        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        Hole h = new Hole("hole","Hole");
        s.SetNeighbour(Direction.NORTH, h);
        h.SetNeighbour(Direction.SOUTH, s);

        Shovel shovel = new Shovel("shovel","Shovel");
        ScubaSuit sc = new ScubaSuit("scubasuit","ScubaSuit");
        Food food = new Food("food","Food");

        ArrayList<Item> itemlist = new ArrayList<Item>();
        itemlist.add(shovel);
        itemlist.add(sc);
        itemlist.add(food);

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); s.AddCharacter(es); es.addField(s); es.SetItems(itemlist);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); s.AddCharacter(ex); ex.addField(s); ex.SetItems(itemlist);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        t.Move(Direction.NORTH);
        main(null);
    }

    /**
     * Buvarruha nelkuli merules
     *
     * @param type karakter tipusa
     * @param stamina staminamennyiseg
     * @param rescue 0 ha nem mentik meg, 1 ha megmentik
     */
    private static void ColdDive(int type, int stamina, int rescue) {
        Stable s = new Stable("stableField", "Stable");
        Team t = new Team("team", "Team");
        ArrayList<Character> players = new ArrayList<Character>();

        Hole h = new Hole("hole","Hole");
        NeighboursSetUp(h);
        s.SetNeighbour(Direction.NORTH, h);
        h.SetNeighbour(Direction.SOUTH, s);

        Shovel shovel = new Shovel("shovel","Shovel");
        Food food = new Food("food","Food");

        ArrayList<Item> itemlist = new ArrayList<Item>();
        itemlist.add(shovel);
        itemlist.add(food);

        switch(rescue) {
            case 0: break;
            case 1: Rope rope = new Rope("rope","Rope"); itemlist.add(rope); break;
        }

        Explorer teamMate = new Explorer("teammate", "Explorer");
        s.AddCharacter(teamMate);
        teamMate.SetItems(itemlist);
        teamMate.addField(s);
        t.AddCharacter(teamMate);

        switch(type) {
            case 0: Eskimo es = new Eskimo("eskimo", "Eskimo"); s.AddCharacter(es); es.addField(s);
                players.add(es);
                break;
            case 1: Explorer ex = new Explorer("explorer", "Explorer"); s.AddCharacter(ex); ex.addField(s);
                players.add(ex);
                break;
        }

        t.setTeam(players);

        t.Move(Direction.NORTH);
        main(null);
    }

    /**
     * A szomszedokat letrehozo fuggveny
     *
     * @param f
     */
    private static void NeighboursSetUp(Field f) {
        Stable s1 = new Stable("stableField1", "Stable");
        Stable s2 = new Stable("stableField2", "Stable");
        Stable s3 = new Stable("stableField3", "Stable");
        Stable s4 = new Stable("stableField4", "Stable");

        f.SetNeighbour(Direction.NORTH, s1);
        f.SetNeighbour(Direction.EAST, s2);
        f.SetNeighbour(Direction.SOUTH, s3);
        f.SetNeighbour(Direction.WEST, s4);

        return;
    }
}