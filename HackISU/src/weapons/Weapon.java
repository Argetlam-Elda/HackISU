package weapons;

import textGame.Item;

/**
 * 
 * @author Colt Rogness
 *
 */
public abstract class Weapon extends Item {
	
	/**
	 * amount of damage the weapon does
	 */
	protected int damage;
	
	/**
	 * 
	 * @return - the weapons damage
	 */
	public int getDamage() {
		return damage;
	}
	
	public abstract WeaponType getType();
	public abstract Weapon clone();
	
}
