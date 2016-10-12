package items;

/**
 * @author Colt Rogness
 */
public class OneUseItem extends Item {
	
	/**
	 * the number of uses remaining for this item
	 */
	protected int usesLeft;
	
	
	/**
	 * constructs a placeholder item
	 */
	public OneUseItem() {
		name = "";
		durability = 0;
		flavorText = "";
		value = 0;
		usesLeft = 0;
	}
	
	
	/**
	 * constructs a consumable item with the given stats
	 * 
	 * @param name
	 * @param flavor
	 * @param durability
	 * @param value
	 * @param usesLeft
	 */
	public OneUseItem(String name, String flavor, int durability, int value, int usesLeft) {
		super.name = name;
		super.durability = durability;
		super.flavorText = flavor;
		super.value = value;
		this.usesLeft = usesLeft;
	}
	
	
	/**
	 * @return - the Item's remaining uses
	 */
	public int getUsesLeft() {
		return usesLeft;
	}
	
	public ItemType getType() {
		return ItemType.ONE_USE_ITEM;
	}
	
	public OneUseItem clone() {
		return new OneUseItem(name, flavorText, durability, value, usesLeft);
	}
}
