package items;

/**
 * 
 * @author Colt Rogness
 *
 */
public class WearableItem extends Armor{
	
	/**
	 * constructs an empty wearable item
	 */
	public WearableItem() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	/**
	 * constructs a wearable item with the given stats
	 * @param name - name of the item
	 * @param durability - items durability
	 * @param defense - the items defense
	 */
	public WearableItem(String name, int durability, int defense) {
		super.name = name;
		super.durability = durability;
		super.defense = defense;
		value = durability/10 + defense*10;
		super.challengeRating = defense;
	}

	@Override
	public Item clone() {
		return new WearableItem(name, durability, defense);
	}
	
	public ArmorType getType() {
		return ArmorType.WEARABLEITEM;
	}
}
