package character;

import java.io.Serializable;

import enums.MovingParam;
import field.Direction;
import field.Field;
import main.Controller;
import graphics.character.*;

/**
 * A jegesmedvet kezelo osztaly, amely kezeli annak mozgasat es interakcioit a
 * jatekosokkal.
 */
public class PolarBear implements Serializable {
    private Field field;

    public PolarBear(Field field) {
        this.field = field;
        BearView pl = new BearView(this);
    }

    /**
     * A jegesmedvet elmozditja a parameterkent megadott iranyba, visszater a mozgas
     * sikeressegevel
     * 
     * @param direction Direction, amerre a medve mozogni fog
     * @return boolean: ha a mozgas sikeres volt true a visszaterese, ha nem, akkor
     *         false
     */
    public boolean moveP(Direction direction) {
        Field newField = field.getNeighbourField(direction);
        if (newField == null) {
            return false;
        } else {
            Field oldField = field;
            field = newField;
            // if (field.polarBearAttack()) {
            // Controller.instance().polarbearmoveoutput(oldField, newField,
            // MovingParam.polarBearAttack);
            // } else {
            // Controller.instance().polarbearmoveoutput(oldField, newField,
            // MovingParam.polarBearMove);
            // }
            return true;
        }
    }

    /**
     * a medve Fieldjének gettere, csak a Map kirajzolásához szükséged
     */
    public Field getField() {
        return field;
    }
}
