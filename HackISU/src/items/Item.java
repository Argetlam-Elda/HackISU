package items;

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
	 * the difficulty of finding this item, matches the CR of monsters that can have it
	 */
	protected int challengeRating;
	
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
	 * @return - the Item's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return - the items challenge rating
	 */
	public int getChallengeRating() {
		return challengeRating;
	}
	
	/**
	 * 
	 * @return - whether the item is broken or not
	 */
	public boolean isBroken() {
		return durability <= 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
