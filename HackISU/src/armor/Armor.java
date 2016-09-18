package armor;

import textGame.Item;

public abstract class Armor extends Item{
	protected int defence;
	
	public int getDefence() {
		return defence;
	}
	
	public abstract ArmorType getType();
	public abstract Item clone();
	
	
}
