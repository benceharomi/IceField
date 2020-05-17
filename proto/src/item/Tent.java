package item;

import java.io.Serializable;

import enums.TentParam;
import field.Field;
import main.Controller;

public class Tent extends Item implements Shelter, Serializable {

    /**
     * A medve hivja meg ha a mezore lep.
     * 
     * @return boolean falseal ter vissza mert nem tud megvedeni a jegesmacitol
     */
    public boolean attack() {
        return false;
    }

    /**
     * A parameterkent kapott fieldnek meghivja a setShelter fuggvenyet
     * 
     * @param field ide rakja a satrat
     * @return boolean, amennyiben van mar menedek a fielden false az erteke,
     *         egyebkent true
     */
    public boolean deploy(Field field) {
        if (field.setShelter(this)) {
            return true;
        }
        return false;
    }

    /**
     * A mapen valo kirajzolashoz.
     */
    @Override
    public char draw() {
        return 'T';
    }

    /**
     * Epusztitja a parameterkent megadott tablan allo satrat, a field removeShelter
     * metodusanak segitsegevel
     * 
     * @param field a field, amelyen a torlendo sator all
     */
    public void destroy(Field field) {
        Controller.instance().tentOutput(null, field, TentParam.DESTROY);
        field.removeShelter();
    }

}
