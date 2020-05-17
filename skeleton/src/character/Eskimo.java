package character;

import main.Main;
import skeleton.Skeleton;

/**
 Eskimo
 Felelosseg
 Az eszkimora jellemzo dolgokat kezeli
 ososztalyok
 Character -> Eskimo
 Metodusok
 bool SpecialAbilityC(): Az eszkimo specialis kepeseget hajtja vegre, ami egy iglu
 epitese.
 */

public class Eskimo extends Character{
    public Eskimo(String name, String type){
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    public boolean IncrementHeat() {
        Skeleton.instance().called(this, "IncrementHeat");
        int heat = Main.HeatAmount(0);
        if(heat == 5){
            Skeleton.instance().returned();
            return false;
        } else {
            Skeleton.instance().returned();
            return true;
        }
    }

    @Override
    public boolean SpecialAbilityC() {
        Skeleton.instance().called(this, "SpecialAbilityC");
        
        if(this.field.SetIgloo()) {
            Skeleton.instance().returned();
            return false;
        }
        Skeleton.instance().returned();
        return false;
    }
}
