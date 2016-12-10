package items;

/**
 * @author Colt Rogness
 */
public abstract class Weapon extends Item {
	
	/**
	 * amount of damage the weapon does
	 */
	protected int damage;
	
	
	/**
	 * @return - the weapons damage
	 */
	public int getDamage() {
		return damage;
	}
	
	
	public abstract Weapon clone();
	
	
	@Override
	public String toString() {
		return name + ": Dmg = " + damage;
	}
}
