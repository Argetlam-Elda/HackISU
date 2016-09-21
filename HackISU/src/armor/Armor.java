package armor;

import textGame.Item;

/**
 * 
 * @author Colt Rogness
 *
 */
public abstract class Armor extends Item{
	/**
	 * amount of defense this armor adds to its wearer
	 */
	protected int defense;
	
	/**
	 * 
	 * @return
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public abstract ArmorType getType();
	
	public abstract Item clone();
	
	
}
