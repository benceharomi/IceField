package field;

import character.Character;
import direction.Direction;
import skeleton.Skeleton;

//import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 Hole
      Felelosseg
 Lyukakat reprezentalo osztaly. Ezen mezok stabilitasa 0.
      ososztalyok
 Field->Hole
 */

public class Hole extends Field {

    public Hole(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    /**
     * Absztakt fuggveny, amely hozzaadja a
     * parameterkent megkapott karaktert a Field-hez, illetve egyeb valtozasokat tesz, attol
     * fuggoen, hogy mely leszarmazottakban valosul meg.
     *
     * @param character - a hozzaadott karakter
     */
    @Override
    public void AddCharacter(Character character) {
        Skeleton.instance().called(this, "AddCharacter()");
        characters.add(character); // hozzaaadjuk az uj karaktert
        ArrayList<Character> nCharacters = new ArrayList<>();
        ArrayList<Field> nFields = new ArrayList<>();
        boolean hasSuit = character.HoleNotification();
        if (hasSuit) {
            Skeleton.instance().returned();
            return;
        } else {

            for (Map.Entry entry : neighbours.entrySet()) {
                nFields.add((Field) entry.getValue()); // Elvileg a cast nem baj, mert nem erdekes,
            }                                          // hogy milyen a field csak aki rajta van.
            for (Field f : nFields) {
                for (int i = 0; i < f.characters.size(); i++)
                    nCharacters.add(f.characters.get(i));
            }

            boolean gotHelp = false;

            /// Rescue
            /// Ha boolean a visszateres, akkor minden egyes karakterre mas erteket kap, meg akkor is ha kimentettek?
            /// Maybe: ha mar van egy true is akkor megmenekult. Nem OOP?
            /// es ha valaki kihuzta, de meg van akinel van kotel?
            for (Character ch : nCharacters) {
                gotHelp = ch.Rescue(character, this, ch.getField());
                if (gotHelp) break;
            }
            if (!gotHelp) {
                character.setHeat();
            }
        }

    }
}
