package armor;

import textGame.Item;

public class Helm extends Armor{
	
	/**
	 * constructs a new empty helm
	 */
	public Helm() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
	}
	
	/**
	 * constructs a new helm with the given stats
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Helm(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
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
