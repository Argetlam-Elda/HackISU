package textGame;

public class Monster extends Characters{
	
	protected int damage; // how much damage the monster can deal
	
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
