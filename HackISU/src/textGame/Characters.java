package textGame;

import java.util.ArrayList;
import armor.*;
import weapons.*;
// import oneUseItems.*;

/**
 * 
 * @author Colt Rogness
 *
 */
public abstract class Characters {
	
	protected final int proficiencyBonus = 2; // not used as of yet
	
	//protected int height;
	//protected int weight;
	
	protected int strength;
	protected int agility;
	protected int defense;
	protected int speed;
	
	protected int maxHitPoints;
	protected int tempHitPoints;
	protected int currentHitPoints;
	
	protected Armor helm;
	protected Armor chestPiece;
	protected Armor boots;
	protected Armor leggings;
	protected Armor gloves;
	
	protected ArrayList<Item> pouch;
	
	protected Weapon meleeWeapon;
	protected Weapon rangedWeapon;
	
	protected int money;
	
	protected String title;
	
	/**
	 * 
	 * @return - strength
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * 
	 * @return - agility
	 */
	public int getAgility() {
		return agility;
	}
	
	/**
	 * 
	 * @return - defense
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * 
	 * @return - speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @return - the characters maximum possible health
	 */
	public int getMaxHitPoints() {
		return maxHitPoints + tempHitPoints;
	}
	
	/**
	 * 
	 * @return - the characters current health
	 */
	public int getCurrentHitPoints() {
		return currentHitPoints;
	}
	
	/**
	 * 
	 * @return - how much money the character has
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * 
	 * @param amount - of money to be added to character
	 */
	public void addMoney(int amount) {
		money += amount;
	}
	
	/**
	 * 
	 * @return - the character's items
	 */
	public ArrayList<Item> getPouch() {
		return pouch;
	}
	
	/**
	 * 
	 * @return - the characters armor class
	 */
	public int getArmorClass() {
		int AC = (defense-10)/2;
		Armor[] equipped = getEquippedArmor();
		for (int i = 0; i < equipped.length; i++) {
			AC += equipped[i].getDefence();
		}
		return AC;
	}
	
	/**
	 * 
	 * @return - whether the character is alive or not
	 */
	public Boolean isAlive() {
		return currentHitPoints > 0;
	}
	
	/**
	 * 
	 * @return - the name of the character
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * subtracts damage taken from your current health
	 * @param hit - how much damage you should take
	 */
	public void takeDamage(int hit) {
		currentHitPoints -= hit;
	}
	
	/**
	 * 
	 * @return - array of the characters equipped armor
	 */
	public Armor[] getEquippedArmor() {
		Armor[] equipped = new Armor[5];
		equipped[0] = helm;
		equipped[1] = chestPiece;
		equipped[2] = gloves;
		equipped[3] = leggings;
		equipped[4] = boots;
		return equipped;
	}
	
	/**
	 * 
	 * @return - the chatacter's equipped melee weapon
	 */
	public Weapon getMeleeWeapon() {
		return meleeWeapon;
	}

	/**
	 * 
	 * @return - the character's ranged weapon
	 */
	public Weapon getRangedWeapon() {
		return rangedWeapon;
	}
	
	/**
	 * fill armor slots with unequipped item holder
	 */
	protected void fillArmorWithUnequipped() {
		helm = new UnequippedA();
		chestPiece = new UnequippedA();
		gloves = new UnequippedA();
		leggings = new UnequippedA();
		boots = new UnequippedA();
	}
	
	/**
	 * fill weapon slots with unequipped item holder
	 */
	protected void fillWeaponsWithUnequipped() {
		rangedWeapon = new Weapon();
		meleeWeapon = new Weapon();
	}
	
	
}
