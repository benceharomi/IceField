package map;

//import java.rmi.server.Skeleton;
import java.util.ArrayList;
import field.*;
import skeleton.Skeleton;

/**
 * Map
   Felelosseg
 Ez az osztaly reprezentalja a palyat. Tarolja a palya mezoit es felelos a hoviharok
 vegrehajtasaert.
   Attributumok
   -fields : Field: A jatek mezoit tarolo dinamikus tomb.
   Metodusok
   void BlizzardM(): ez a metodus donti el, hogy tortenik-e hovihar valamint
 vegrehajtja azt. Ha van hovihar, akkor a veletlenszeruen kivalaszt az altala tarolt
 field-ek kozul nehanyat es meghivja azoknak a BlizzardF() fuggvenyet.
 */

public class Map {
    public Map(String name, String type) {
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }
    ArrayList<Field> fields;

    /**
     * Starts the Blizzard on the fields.
     */
    public void BlizzardM() {
        Skeleton.instance().called(this, "BlizzardM");

        for (Field field: fields) {
            field.BlizzardF();
        }

        Skeleton.instance().returned();
    }

    public void SetFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
}
