package field;

import character.Character;
import skeleton.Skeleton;

import java.util.Scanner;

/**
 UnStable
  Felelosseg
 Az instabil jegmezot reprezentalja. Ezen mezok stabilitasa korlatozott, amit, ha meglepne
 ososztalyok
 Field->UnStable
  Attributumok:
    Az ososztaly attributumait orokli.
  Metodusok
    void AddCharacter(Character c): Hozzaadja a parameterkent kapott karaktert a
 mezohoz, illetve ellenorzi, hogy ezzel a mezo atlepte-e a kapacitasanak hatarat. Ha
 igen, akkor a mezo a fejere fordul, ezzel minden rajta allo jatekos hoenergiajat 0-ra
 allitja.
 */

public class UnStable extends Field {

    public UnStable(String name, String type){
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    @Override
    public void AddCharacter(Character c) {
        Skeleton.instance().called(this, "AddCharacter()");
        characters.add(c);
        System.out.println("Mekkora a mezo kapacitasa?");
        int capacity = new Scanner(System.in).nextInt();
        if (capacity < characters.size()) {
            for (Character character : characters) {
                character.setHeat();
            }
        }
        Skeleton.instance().returned();
    }
}
