package items;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Gloves extends Armor{
	
	/**
	 * constructs an empty glove
	 */
	public Gloves() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	/**
	 * constructs a glove with the given stats
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Gloves(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
		super.challengeRating = defense;
	}

	@Override
	public Item clone() {
		return new Gloves(name, durability, defense);
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ArmorType getType(){
		return ArmorType.GLOVES;
	}
}
