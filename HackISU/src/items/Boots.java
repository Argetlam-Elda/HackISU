package items;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Boots extends Armor{
	
	/**
	 * constructs an empty boot
	 */
	public Boots() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	/**
	 * constructs a boot with the given stats
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Boots(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
		super.challengeRating = defense;
	}

	@Override
	public Boots clone() {
		return new Boots(name, durability, defense);
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ItemType getType(){
		return ItemType.BOOTS;
	}
}
