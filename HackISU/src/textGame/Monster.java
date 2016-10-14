package textGame;

import java.util.ArrayList;
import java.util.Random;

import items.*;

/**
 * @author Colt Rogness
 */
public class Monster extends Characters {
	
	/**
	 * how much damage the monster can deal
	 */
	protected int damage;
	
	
	/**
	 * the world this cell lives in, gives access to armor and weapons in the world
	 */
	private World w;
	
	
	/**
	 * the maonster's challenge rating
	 */
	protected int challengeRating;
	
	
	/**
	 * constructs a weak-ass (dead) monster
	 */
	public Monster() {
		damage = 0;
		strength = 0;
		agility = 0;
		defense = 0;
		speed = 0;
		money = 0;
		maxHitPoints = 0;
		challengeRating = 0;
	}
	
	
	/**
	 * constructs a new monster with the given stats
	 * 
	 * @param name
	 *            - the monsters name
	 * @param damage
	 *            - the monsters base damage
	 * @param strength
	 *            - the monsters strength
	 * @param agility
	 *            - the monsters agility
	 * @param defense
	 *            - the monsters defence
	 * @param speed
	 *            - the monsters speed
	 * @param maxHP
	 *            - the monsters max health
	 */
	public Monster(World w, String name, int damage, int strength, int agility, int defense, int speed, int maxHP) {
		this.w = w;
		title = name;
		this.damage = damage;
		super.strength = strength;
		super.agility = agility;
		super.defense = defense;
		super.speed = speed;
		super.maxHitPoints = 5;
		challengeRating = (damage + defense + (maxHP / 5)) / 3;
		super.money = challengeRating * 3;
		currentHitPoints = maxHitPoints;
		fillWeaponsWithUnequipped();
		fillArmorWithUnequipped();
		pouch = new ArrayList<Item>();
		randomItem();
	}
	
	
	/**
	 * Adds a random item to the monster's inventory from the armor, melee, or ranged ArrayLists
	 */
	private void randomItem() {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			int temp = rand.nextInt(7);
			if (temp < 5) {
				pouch.add(w.getMeleeWeapon(challengeRating));
			}
			else {
				pouch.add(w.getRangedWeapon(challengeRating));
			}
		}
		else {
			int temp = rand.nextInt(5);
			if (temp == 0) {
				pouch.add(w.getArmor(challengeRating + 1, ItemType.BOOTS));
			}
			if (temp == 1) {
				pouch.add(w.getArmor(challengeRating + 1, ItemType.LEGGINGS));
			}
			if (temp == 2) {
				pouch.add(w.getArmor(challengeRating + 1, ItemType.GLOVES));
			}
			if (temp == 3) {
				pouch.add(w.getArmor(challengeRating + 1, ItemType.CHESTPIECE));
			}
			if (temp == 4) {
				pouch.add(w.getArmor(challengeRating + 1, ItemType.HELM));
			}
		}
	}
	
	
	/**
	 * @return - the monster's damage
	 */
	public int getDamage() {
		return damage;
	}
	
	
	/**
	 * @return - the monsters challenge rating
	 */
	public int getChallangeRating() {
		return challengeRating;
	}
	
	
	public Monster clone() {
		return new Monster(w, title, damage, strength, agility, defense, speed, maxHitPoints);
	}
	
}
