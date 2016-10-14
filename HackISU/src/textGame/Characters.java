package textGame;

import java.util.ArrayList;
import items.*;

/**
 * 
 * @author Colt Rogness
 *
 */
public abstract class Characters {
	
	/**
	 * the characer's strength
	 */
	protected int strength;
	
	/**
	 * the characer's agility
	 */
	protected int agility;
	
	/**
	 * the characer's
	 */
	protected int defense;
	
	/**
	 * the characer's
	 */
	protected int speed;
	
	/**
	 * the characer's max health
	 */
	protected int maxHitPoints;
	
	/**
	 * the characer's added HP for certain items
	 */
	protected int tempHitPoints;
	
	/**
	 * the characer's current health
	 */
	protected int currentHitPoints;
	
	/**
	 * the characer's helmet
	 */
	protected Armor helm;
	
	/**
	 * the characer's chest piece
	 */
	protected Armor chestPiece;
	
	/**
	 * the characer's boots
	 */
	protected Armor boots;
	
	/**
	 * the characer's leggings
	 */
	protected Armor leggings;
	
	/**
	 * the characer's gloves
	 */
	protected Armor gloves;

	/**
	 * the characer's pouch, holds their items and unequipped armor and weapons
	 */
	protected ArrayList<Item> pouch;
	
	/**
	 * the characer's equipped melee weapon
	 */
	protected Weapon meleeWeapon;
	
	/**
	 * the characer's equipped ranged weapon
	 */
	protected Weapon rangedWeapon;
	
	/**
	 * the characer's wallet value
	 */
	protected int money;
	
	/**
	 * the characer's title (Player Charater's name)
	 */
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
	 * sell the given item if it is in the player's pouch
	 * @param item - item to be sold
	 */
	public void sell(Item item) {
		for (int i = 0; i < pouch.size(); i++) {
			if (item.equals(pouch.get(i))) {
				money += pouch.get(i).getValue();
				pouch.remove(i);
			}
		}
	}
	
	/**
	 * sell an item with the given name if there is one in the player's pouch
	 * @param name
	 */
	public void sell(String name) {
		for (int i = 0; i < pouch.size(); i++) {
			if (name.equalsIgnoreCase(pouch.get(i).getName())) {
				money += pouch.get(i).getValue();
				pouch.remove(i);
				break;
			}
		}
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
	 * @return - the characters defense plus armor's defense class
	 */
	public int getDefense() {
		int AC = defense;
		Armor[] equipped = getEquippedArmor();
		for (int i = 0; i < equipped.length; i++) {
			AC += equipped[i].getDefense();
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
	 * @return - the title of the character
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * subtracts damage taken from your current health
	 * @param hit - how much damage you should take
	 */
	public void takeDamage(int hit) {
		currentHitPoints -= Math.max(0, hit - getDefense());
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
		helm = new Helm();
		chestPiece = new Chestpiece();
		gloves = new Gloves();
		leggings = new Leggings();
		boots = new Boots();
	}
	
	/**
	 * fill weapon slots with unequipped item holder
	 */
	protected void fillWeaponsWithUnequipped() {
		rangedWeapon = new Ranged();
		meleeWeapon = new Melee();
	}
	
	/**
	 * 
	 * @param item
	 *            - item to be dropped
	 * @return - the item that was dropped, if you had that item. else, drops
	 *         unequipped placeholder
	 */
	public Item dropItem(String name) {
		for (int i = 0; i < pouch.size(); i++) {
			if (pouch.get(i).getName().equals(name)) {
				Item temp = pouch.get(i);
				pouch.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param item - adds the item to the characters pouch
	 */
	public void addItem(Item item) {
		pouch.add(item);
	}
	
}
