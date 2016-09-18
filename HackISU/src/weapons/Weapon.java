package weapons;

import textGame.Item;

public class Weapon extends Item{

	protected int damage;
	
	public Weapon() {
		name = "unequipped";
		damage = 0;
		durability = -1;
		flavorText = null;
	}
	
	public Weapon(String name, int damage, int durability, String flavorText) {
		super.name = name;
		this.damage = damage;
		super.durability = durability;
		super.flavorText = flavorText;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public WeaponType getType() {
		return WeaponType.MELEE;
	}
	
}
