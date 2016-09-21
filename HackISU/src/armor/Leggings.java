package armor;

import textGame.Item;

public class Leggings extends Armor{
	
	public Leggings() {
		name = "";
		defense = 0;
		value = 0;
		durability = -1;
	}
	
	/**
	 * 
	 * @param durability - how many hits it can take
	 * @param defense - how much damage it removes
	 */
	public Leggings(String name, int durability, int defense) {
		super.durability = durability;
		super.defense = defense;
		super.name = name;
		value = durability/10 + defense*10;
	}

	@Override
	public Item clone() {
		return new Leggings(name, durability, defense);
	}
	
	/**
	 * 
	 * @return - the type of armor this is
	 */
	public ArmorType getType(){
		return ArmorType.LEGGINGS;
	}
}
