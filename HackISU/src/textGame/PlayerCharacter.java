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
	public void equip(Weapon equip) {
		if (equip.getType() == ItemType.MELEE) {
			if (!meleeWeapon.getName().equals("")) {
				pouch.add(meleeWeapon);
			}
			meleeWeapon = equip.clone();
		}
		else if (equip.getType() == ItemType.RANGED) {
			if (!rangedWeapon.getName().equals("")) {
				pouch.add(rangedWeapon);
			}
			rangedWeapon = equip.clone();
		}
	}
	
	
	/**
	 * equip the given piece of armor
	 * 
	 * @param equip
	 *            - piece of armor to be equipped
	 */
	public void equip(Armor equip) {
		if (equip.getType() == ItemType.BOOTS) {
			if (!boots.getName().equals("")) {
				pouch.add(boots);
			}
			boots = equip;
		}
		else if (equip.getType() == ItemType.CHESTPIECE) {
			if (!chestPiece.getName().equals("")) {
				pouch.add(chestPiece);
			}
			chestPiece = equip;
		}
		else if (equip.getType() == ItemType.GLOVES) {
			if (!gloves.getName().equals("")) {
				pouch.add(gloves);
			}
			gloves = equip;
		}
		else if (equip.getType() == ItemType.HELM) {
			if (!helm.getName().equals("")) {
				pouch.add(helm);
			}
			helm = equip;
		}
		else if (equip.getType() == ItemType.LEGGINGS) {
			if (!leggings.getName().equals("")) {
				pouch.add(leggings);
			}
			leggings = equip;
		}
	}
	
	
	/**
	 * equip the item at location i in the player's pouch
	 * 
	 * @param i
	 *            - location of the item to equip
	 * @throws Exception
	 */
	public void equip(int i) throws Exception {
		Item temp = null;
		if (pouch.size() > i) {
			if (pouch.get(i).getType() == ItemType.MELEE) {
				temp = meleeWeapon.clone();
				meleeWeapon = ((Weapon) pouch.get(i)).clone();
			}
			else if (pouch.get(i).getType() == ItemType.RANGED) {
				temp = rangedWeapon.clone();
				rangedWeapon = ((Weapon) pouch.get(i)).clone();
			}
			else if (pouch.get(i).getType() == ItemType.BOOTS) {
				temp = (Armor) boots.clone();
				boots = (Armor) pouch.get(i).clone();
			}
			else if (pouch.get(i).getType() == ItemType.CHESTPIECE) {
				temp = (Armor) chestPiece.clone();
				chestPiece = (Armor) pouch.get(i).clone();
			}
			else if (pouch.get(i).getType() == ItemType.GLOVES) {
				temp = (Armor) gloves.clone();
				gloves = (Armor) pouch.get(i).clone();
			}
			else if (pouch.get(i).getType() == ItemType.HELM) {
				temp = (Armor) helm.clone();
				helm = (Armor) pouch.get(i).clone();
			}
			else if (pouch.get(i).getType() == ItemType.LEGGINGS) {
				temp = (Armor) leggings.clone();
				leggings = (Armor) pouch.get(i).clone();
			}
			else {
				throw new IllegalArgumentException("Cannot equip this item.");
			}
			if (temp != null) {
				pouch.remove(i);
				if (!temp.getName().equalsIgnoreCase("")) {
					pouch.add(temp);
				}
			}
		}
		else {
			throw new Exception("There is no item at location i in the player's pouch");
		}
	}
	
	
	/**
	 * @param unequip
	 *            - weapon to be unequipped
	 */
	public void unequipWeapon(Weapon unequip) {
		if (unequip.getType() == ItemType.MELEE) {
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
	}
	
	
	/**
	 * unequips the given piece of armor and adds it to the pouch
	 * 
	 * @param unequip
	 *            - piece of armor to be unequipped
	 */
	public void unEquipArmor(Armor unequip) {
		if (unequip.getType() == ItemType.BOOTS) {
			pouch.add(boots.clone());
			boots = new Boots();
		}
		else if (unequip.getType() == ItemType.CHESTPIECE) {
			pouch.add(chestPiece.clone());
			chestPiece = new Chestpiece();
		}
		else if (unequip.getType() == ItemType.GLOVES) {
			pouch.add(gloves.clone());
			gloves = new Gloves();
		}
		else if (unequip.getType() == ItemType.HELM) {
			pouch.add(helm.clone());
			helm = new Helm();
		}
		else if (unequip.getType() == ItemType.LEGGINGS) {
			pouch.add(leggings.clone());
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
		Armor[] equipped = getEquippedArmor();
		for (int i = 0; i < equipped.length; i++) {
			AC += equipped[i].getDefense();
		}
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
	
}
