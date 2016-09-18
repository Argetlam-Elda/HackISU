package armor;

import textGame.Item;

public class UnequippedA extends Armor{

	public UnequippedA() {
		name = "";
		value = -1;
		durability = -1;
		defence = 0;
	}

	@Override
	public ArmorType getType() {
		return ArmorType.ANY;
	}
	
	@Override
	public Item clone() {
		return new UnequippedA();
	}
	
	
}
