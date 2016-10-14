package items;

/**
 * @author Colt Rogness
 */
public abstract class Armor extends Item {
	
	/**
	 * amount of defense this armor adds to its wearer
	 */
	protected int defense;
	
	
	/**
	 * @return - item's defense
	 */
	public int getDefense() {
		return defense;
	}
	
	
	/**
	 * @return - the type of armor this is
	 */
	public abstract ItemType getType();
	
	
	public abstract Item clone();
	
	
	@Override
	public String toString() {
		return name + ": Def = " + defense;
	}
	
}
