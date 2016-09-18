package textGame;

public abstract class Item {

	protected int value; // how much the item costs
	protected int durability; // how much durability the item has
	
	protected String flavorText; // flavor text for the item
	protected String name; // name of the Item
	
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
	
	
}
