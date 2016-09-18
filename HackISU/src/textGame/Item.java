package textGame;

public abstract class Item {

	/**
	 * how much the item costs
	 */
	protected int value;
	
	/**
	 * how much durability the item has
	 */
	protected int durability;

	/**
	 * flavor text for the item
	 */
	protected String flavorText;
	
	/**
	 * name of the Item
	 */
	protected String name;
	
	/**
	 * 
	 * @return - the Item's durability
	 */
	public int getDurability() {
		return durability;
	}
	
	/**
	 * 
	 * @return - the Item's monetary value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return - the Item's flavor text
	 */
	public String getFlavorText() {
		return flavorText;
	}
	
	/**
	 * 
	 * @return - return the Item's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return - whether the item is broken or not
	 */
	public boolean isBroken() {
		return durability <= 0;
	}
	
}
