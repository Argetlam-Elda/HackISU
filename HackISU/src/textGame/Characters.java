package textGame;

import java.util.ArrayList;
import items.*;

/**
 * @author Colt Rogness
 */
public abstract class Characters {
	
	/**
	 * the characer's defense
	 */
	protected int defense;
	
	
	/**
	 * the characer's max health
	 */
	protected int maxHitPoints;
	
	
	/**
	 * TODO - the characer's bonus hit points, not yet implemented
	 */
	protected int tempHitPoints;
	
	
	/**
	 * the characer's current health
	 */
	protected int currentHitPoints;
	
	
	/**
	 * the characer's pouch, holds their items and unequipped armor and weapons
	 */
	protected ArrayList<Item> pouch;
	
	
	/**
	 * the characer's wallet value
	 */
	protected int money;
	
	
	/**
	 * the characer's name or species for mobs
	 */
	protected String name;
	
	
	/**
	 * @return - the characters maximum possible health
	 */
	public int getMaxHitPoints() {
		return maxHitPoints + tempHitPoints;
	}
	
	
	/**
	 * @return - the characters current health
	 */
	public int getCurrentHitPoints() {
		return currentHitPoints;
	}
	
	
	/**
	 * @return - how much money the character has
	 */
	public int getMoney() {
		return money;
	}
	
	
	/**
	 * @param amount
	 *            - of money to be added to character
	 */
	public void addMoney(int amount) {
		money += amount;
	}
	
	
	/**
	 * sell an item with the given name if there is one in the player's pouch
	 * 
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
	 * @return - the character's items
	 */
	public ArrayList<Item> getPouch() {
		return pouch;
	}
	
	
	/**
	 * @return - the characters base defense plus armor's defense
	 */
	public int getDefense() {
		return defense;
	}
	
	
	/**
	 * @return - whether the character is alive or not
	 */
	public Boolean isAlive() {
		return currentHitPoints > 0;
	}
	
	
	/**
	 * forget names, players have titles
	 * 
	 * @return - the title of the character
	 */
	public String getTitle() {
		return name;
	}
	
	
	/**
	 * subtracts damage taken from your current health
	 * 
	 * @param hit
	 *            - how much damage you should take
	 */
	public void takeDamage(int hit) {
		currentHitPoints -= Math.max(0, hit - getDefense());
	}
	
	
	/**
	 * @param item
	 *            - adds the item to the characters pouch
	 */
	public void addItem(Item item) {
		pouch.add(item);
	}
	
}
