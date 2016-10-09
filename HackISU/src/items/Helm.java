package items;

public class Helm extends Armor{
	
	/**
	 * constructs an empty helm
	 */
	public Helm() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
		super.challengeRating = defense;
	}
	
	/**
	 * constructs a helm with the given stats
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Helm(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
		super.challengeRating = defense;
	}

	@Override
	public Item clone() {
		return new Helm(name, durability, defense);
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ArmorType getType() {
		return ArmorType.HELM;
	}
	
}
