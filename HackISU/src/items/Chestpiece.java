package items;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Chestpiece extends Armor{
	
	/**
	 * constructs an empty Chestpiece
	 */
	public Chestpiece() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	/**
	 * constructs a new Chestpiece with the given stats
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Chestpiece(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
		super.challengeRating = defense;
	}

	@Override
	public Chestpiece clone() {
		return new Chestpiece(super.name, super.durability, super.defense);
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ItemType getType() {
		return ItemType.CHESTPIECE;
	}
}
