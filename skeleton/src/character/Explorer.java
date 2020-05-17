package character;

import direction.Direction;
import main.Main;
import skeleton.Skeleton;

/**
 Explorer
 Felelosseg
 A sarkkutatora jellemzo dolgokat kezeli
 ososztalyok
 Character -> Explorer
 Metodusok
 bool SpecialAbilityC(): A kutato specialis kepesseget hajtja vegre amiaz hogy a
 mellette levo mezok terhelhetoseget megtekinti.
 */

public class Explorer extends Character{

    public Explorer(String name, String type){
        Skeleton.instance().created(this, name, type);
        Skeleton.instance().returned();
    }

    public boolean IncrementHeat() {
        Skeleton.instance().called(this, "IncrementHeat");
        int heat = Main.HeatAmount(1);
        if(heat == 4){
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
    	
    	this.field.GetNeighbourField(Direction.NORTH).SetRevealed();
    	this.field.GetNeighbourField(Direction.EAST).SetRevealed();
    	this.field.GetNeighbourField(Direction.SOUTH).SetRevealed();
    	this.field.GetNeighbourField(Direction.WEST).SetRevealed();
    	
    	Skeleton.instance().returned();
    	return true;
    }
}
