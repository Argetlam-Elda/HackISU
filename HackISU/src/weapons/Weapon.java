package weapons;

import textGame.Item;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Weapon extends Item{

	/**
	 * amount of damage the weapon does
	 */
	protected int damage;
	
	/**
	 * constructs a new weapon
	 */
	public Weapon() {
		name = "unequipped";
		damage = 0;
		durability = -1;
		flavorText = null;
	}
	
	/**
	 * constructs a weapon with given values
	 * @param name - name of the weapon
	 * @param damage - amount of damage it does
	 * @param durability - number of hits it can take
	 * @param flavorText - pretty self explanatory
	 */
	public Weapon(String name, int damage, int durability, String flavorText) {
		super.name = name;
		this.damage = damage;
		super.durability = durability;
		super.flavorText = flavorText;
	}
	
	/**
	 * 
	 * @return - the weapons damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * 
	 * @return - the weapons type. currently always returns MELEE
	 */
	public WeaponType getType() {
		return WeaponType.MELEE;
	}
	
}
