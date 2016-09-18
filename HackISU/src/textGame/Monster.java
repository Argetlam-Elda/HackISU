package textGame;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Monster extends Characters{

	/**
	 * how much damage the monster can deal
	 */
	protected int damage;

	/**
	 * constructs a weak-ass (dead) monster
	 */
	public Monster() {
		damage =0;
		strength = 0;
		agility = 0;
		defense = 0;
		speed = 0;
		money = 0;
		maxHitPoints = 0;
	}
	
	/**
	 * constructs a new monster with the given stats
	 * @param name - the monsters name
	 * @param damage - the monsters base damage
	 * @param strength - the monsters strength
	 * @param agility - the monsters agility
	 * @param defense - the monsters defence
	 * @param speed - the monsters speed
	 * @param maxHP - the monsters max health
	 */
	public Monster(String name, int damage, int strength, int agility, int defense, int speed, int maxHP) {
		title = name;
		this.damage = damage;
		super.strength = strength;
		super.agility = agility;
		super.defense = defense;
		super.speed = speed;
		super.money = damage + defense;
		super.maxHitPoints = 5;
		currentHitPoints = maxHitPoints;
	}
	
	/**
	 * 
	 * @return - the monster's damage
	 */
	public int getDamage() {
		return damage;
	}

}
