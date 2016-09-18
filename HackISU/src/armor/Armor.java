package armor;

import textGame.Item;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Armor extends Item{
	/**
	 * amount of defense this armor adds to its wearer
	 */
	protected int defence;

	/**
	 * constructs a new armor with zero defense and no name
	 */
	public Armor() {
		name = "";
		defence = 0;
		value = 0;
		durability = -1;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDefence() {
		return defence;
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ArmorType getType(){
		return ArmorType.ANY;
	}
	
	public Item clone() {
		return new Armor();
	}
	
	
}
