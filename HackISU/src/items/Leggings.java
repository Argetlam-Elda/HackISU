package items;

/**
 * @author Colt Rogness
 */
public class Leggings extends Armor {
	
	/**
	 * constructs empty leggings
	 */
	public Leggings() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	
	/**
	 * constructs leggings with the given stats
	 * 
	 * @param durability
	 *            - how many hits it can take
	 * @param defense
	 *            - how much damage it removes
	 */
	public Leggings(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability / 10 + defense * 10;
		super.challengeRating = defense;
	}
	
	
	@Override
	public Leggings clone() {
		return new Leggings(name, durability, defense);
	}
	
	
	/**
	 * @return - the type of armor this is
	 */
	public ItemType getType() {
		return ItemType.LEGGINGS;
	}
}
