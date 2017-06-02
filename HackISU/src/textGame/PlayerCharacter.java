package textGame;

import java.util.ArrayList;

import items.*;

/**
 * @author Colt Rogness
 */
public class PlayerCharacter extends People {
	
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
	 * the characer's equipped melee weapon
	 */
	protected Weapon meleeWeapon;
	
	
	/**
	 * the characer's equipped ranged weapon
	 */
	protected Weapon rangedWeapon;
	
	
	/**
	 * constructs a new PC, with default stats and hit points, no armor and a foam sword
	 * 
	 * @param name
	 *            - Player charater's name
	 */
	public PlayerCharacter(String name) {
		super.name = name;
		defense = 0;
		maxHitPoints = 15;
		tempHitPoints = 0;
		currentHitPoints = maxHitPoints;
		
		pouch = new ArrayList<Item>();
		
		// equipped items
		fillArmorWithUnequipped();
		fillWeaponsWithUnequipped();
	}
	
	
	/**
	 * equips the given weapon
	 * 
	 * @param equip
	 *            - piece of armor to be equipped
	 */
	public void equip(Item equip) {
		if (equip == null) {
			
		}
		else if (equip.getType() == ItemType.MELEE) {
			if (!meleeWeapon.getName().equals("")) {
				pouch.add(meleeWeapon);
			}
			meleeWeapon = (Weapon) equip;
		}
		else if (equip.getType() == ItemType.RANGED) {
			if (!rangedWeapon.getName().equals("")) {
				pouch.add(rangedWeapon);
			}
			rangedWeapon = (Weapon) equip;
		}
		else if (equip.getType() == ItemType.BOOTS) {
			if (!boots.getName().equals("")) {
				pouch.add(boots);
			}
			boots = (Armor) equip;
		}
		else if (equip.getType() == ItemType.CHESTPIECE) {
			if (!chestPiece.getName().equals("")) {
				pouch.add(chestPiece);
			}
			chestPiece = (Armor) equip;
		}
		else if (equip.getType() == ItemType.GLOVES) {
			if (!gloves.getName().equals("")) {
				pouch.add(gloves);
			}
			gloves = (Armor) equip;
		}
		else if (equip.getType() == ItemType.HELM) {
			if (!helm.getName().equals("")) {
				pouch.add(helm);
			}
			helm = (Armor) equip;
		}
		else if (equip.getType() == ItemType.LEGGINGS) {
			if (!leggings.getName().equals("")) {
				pouch.add(leggings);
			}
			leggings = (Armor) equip;
		}
	}
	
	
	public void equip(String name) {
		for (int i = 0; i < pouch.size(); i++) {
			if (pouch.get(i).getName().trim().equalsIgnoreCase(name)) {
				equip(pouch.get(i));
				pouch.remove(i);
				return;
			}
		}
	}
	
	
	/**
	 * unequips the item passed to it and adds it to the pouch
	 * 
	 * @param unequip
	 *            - Item to be unequipped
	 */
	public void unequip(Item unequip) {
		if (unequip == null) {
			
		}
		else if (unequip.getType() == ItemType.MELEE) {
			if (!meleeWeapon.getName().equals("")) {
				pouch.add(meleeWeapon.clone());
			}
			meleeWeapon = new Melee();
		}
		else if (unequip.getType() == ItemType.RANGED) {
			if (!rangedWeapon.getName().equals("")) {
				pouch.add(rangedWeapon.clone());
			}
			rangedWeapon = new Ranged();
		}
		else if (unequip.getType() == ItemType.BOOTS) {
			if (!boots.getName().equals("")) {
				pouch.add(boots.clone());
			}
			boots = new Boots();
		}
		else if (unequip.getType() == ItemType.CHESTPIECE) {
			if (!chestPiece.getName().equals("")) {
				pouch.add(chestPiece.clone());
			}
			chestPiece = new Chestpiece();
		}
		else if (unequip.getType() == ItemType.GLOVES) {
			if (!gloves.getName().equals("")) {
				pouch.add(gloves.clone());
			}
			gloves = new Gloves();
		}
		else if (unequip.getType() == ItemType.HELM) {
			if (!helm.getName().equals("")) {
				pouch.add(helm.clone());
			}
			helm = new Helm();
		}
		else if (unequip.getType() == ItemType.LEGGINGS) {
			if (!leggings.getName().equals("")) {
				pouch.add(leggings.clone());
			}
			leggings = new Leggings();
		}
	}
	
	
	/**
	 * @param item
	 *            - item to be dropped
	 * @return - the item that was dropped, if you had that item. else, drops unequipped placeholder
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
	 * @param item
	 *            - item to be dropped
	 * @return - the item that was dropped, if you had that item. else, drops unequipped placeholder
	 * @throws Exception
	 */
	public Item dropItem(int i) throws Exception {
		if (pouch.size() > i) {
			Item temp = pouch.get(i);
			pouch.remove(i);
			return temp;
		}
		else {
			throw new Exception("There is no item at given location in the player's pouch");
		}
	}
	
	
	/**
	 * sell the given item if it is in the player's pouch
	 * 
	 * @param item
	 *            - item to be sold
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
	 * sell the item at the given index of the player's pouch
	 * 
	 * @param i
	 *            - index of item to be sold
	 * @throws Exception
	 */
	public void sell(int i) throws Exception {
		if (pouch.size() > i) {
			money += pouch.get(i).getValue();
			pouch.remove(i);
		}
		else {
			throw new Exception("There is no item at given location in the player's pouch");
		}
	}
	
	
	/**
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
	
	
	@Override
	public int getDefense() {
		int AC = defense;
		AC += helm.getDefense();
		AC += chestPiece.getDefense();
		AC += boots.getDefense();
		AC += leggings.getDefense();
		return AC;
	}
	
	
	/**
	 * @return - the chatacter's equipped melee weapon
	 */
	public Weapon getMeleeWeapon() {
		return meleeWeapon;
	}
	
	
	/**
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
	
	
	public void heal(int i) {
		currentHitPoints = Math.min(maxHitPoints + tempHitPoints, currentHitPoints + 1);
	}
	
}
