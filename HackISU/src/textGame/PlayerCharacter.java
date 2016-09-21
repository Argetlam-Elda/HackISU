package textGame;

import java.util.ArrayList;

import armor.*;
import oneUseItems.*;
import weapons.*;

/**
 * 
 * @author Colt Rogness
 *
 */
public class PlayerCharacter extends People {
	
	/**
	 * for rings and such
	 */
	private Armor wearableItem1;
	
	/**
	 * for rings and such
	 */
	private Armor wearableItem2;
	
	/**
	 * for rings and such
	 */
	private Armor wearableItem3;
	
	/**
	 * for rings and such
	 */
	private Armor wearableItem4;
	
	/**
	 * constructs a new PC, with default stats and hp, no armor and a foam sword
	 * 
	 * @param name
	 *            - Player charater's name
	 */
	public PlayerCharacter(String name) {
		title = name;
		
		strength = 15;
		agility = 15;
		defense = 1;
		speed = 12;
		
		maxHitPoints = 12;
		tempHitPoints = 0;
		currentHitPoints = maxHitPoints + tempHitPoints;
		
		pouch = new ArrayList<Item>();
		
		// equipped items
		fillArmorWithUnequipped();
		fillWeaponsWithUnequipped();
		wearableItem1 = new Armor();
		wearableItem2 = new Armor();
		wearableItem3 = new Armor();
		wearableItem4 = new Armor();
	}
	
	/**
	 * @return - array of equipped armor and rings
	 */
	@Override
	public Armor[] getEquippedArmor() {
		Armor[] equipped = new Armor[9];
		equipped[0] = helm;
		equipped[1] = chestPiece;
		equipped[2] = gloves;
		equipped[3] = leggings;
		equipped[4] = boots;
		equipped[5] = wearableItem1;
		equipped[6] = wearableItem2;
		equipped[7] = wearableItem3;
		equipped[8] = wearableItem4;
		return equipped;
	}
	
	/**
	 * 
	 * @param equip
	 *            - piece of armor to be equipped
	 */
	public void equipWeapon(Weapon equip) {
		if (equip.getType() == WeaponType.MELEE) {
			Weapon temp = meleeWeapon;
			meleeWeapon = equip;
			equip = temp;
		}
		else if (equip.getType() == WeaponType.RANGED) {
			Weapon temp = rangedWeapon;
			rangedWeapon = equip;
			equip = temp;
		}
	}
	
	/**
	 * 
	 * @param unequip
	 *            - weapon to be unequipped
	 */
	public void unequipWeapon(Weapon unequip) {
		if (unequip.getType() == WeaponType.MELEE) {
			pouch.add(meleeWeapon.clone());
			meleeWeapon = new Melee();
		}
		else if (unequip.getType() == WeaponType.MELEE) {
			pouch.add(rangedWeapon.clone());
			rangedWeapon = new Melee();
		}
	}
	
	/**
	 * 
	 * @param equip
	 *            - piece of armor to be equipped
	 */
	public void equipArmor(Armor equip) {
		if (equip.getType() == ArmorType.BOOTS) {
			Armor temp = boots;
			boots = equip;
			equip = temp;
		}
		else if (equip.getType() == ArmorType.CHESTPIECE) {
			Armor temp = chestPiece;
			chestPiece = equip;
			equip = temp;
		}
		else if (equip.getType() == ArmorType.GLOVES) {
			Armor temp = gloves;
			gloves = equip;
			equip = temp;
		}
		else if (equip.getType() == ArmorType.HELM) {
			Armor temp = helm;
			helm = equip;
			equip = temp;
		}
		else if (equip.getType() == ArmorType.LEGGINGS) {
			Armor temp = leggings;
			leggings = equip;
			equip = temp;
		}
		else if (equip.getType() == ArmorType.WEARABLEITEM) {
			if (wearableItem1.getType() == ArmorType.ANY) {
				Armor temp = wearableItem1;
				wearableItem1 = equip;
				equip = temp;
			}
			else if (wearableItem2.getType() == ArmorType.ANY) {
				Armor temp = wearableItem2;
				wearableItem2 = equip;
				equip = temp;
			}
			else if (wearableItem3.getType() == ArmorType.ANY) {
				Armor temp = wearableItem3;
				wearableItem3 = equip;
				equip = temp;
			}
			else if (wearableItem4.getType() == ArmorType.ANY) {
				Armor temp = wearableItem4;
				wearableItem4 = equip;
				equip = temp;
			}
		}
	}
	
	/**
	 * 
	 * @param unequip
	 *            - piece of armor to be unequipped
	 */
	public void unEquipArmor(Armor unequip) {
		if (unequip.getType() == ArmorType.BOOTS) {
			pouch.add(boots.clone());
			boots = new Armor();
		}
		else if (unequip.getType() == ArmorType.CHESTPIECE) {
			pouch.add(chestPiece.clone());
			chestPiece = new Armor();
		}
		else if (unequip.getType() == ArmorType.GLOVES) {
			pouch.add(gloves.clone());
			gloves = new Armor();
		}
		else if (unequip.getType() == ArmorType.HELM) {
			pouch.add(helm.clone());
			helm = new Armor();
		}
		else if (unequip.getType() == ArmorType.LEGGINGS) {
			pouch.add(leggings.clone());
			leggings = new Armor();
		}
		else if (unequip.getType() == ArmorType.WEARABLEITEM) {
			if (wearableItem1.getType() == ArmorType.ANY) {
				pouch.add(wearableItem1.clone());
				wearableItem1 = new Armor();
			}
			else if (wearableItem2.getType() == ArmorType.ANY) {
				pouch.add(wearableItem2.clone());
				wearableItem2 = new Armor();
			}
			else if (wearableItem3.getType() == ArmorType.ANY) {
				pouch.add(wearableItem3.clone());
				wearableItem3 = new Armor();
			}
			else if (wearableItem4.getType() == ArmorType.ANY) {
				pouch.add(wearableItem4.clone());
				wearableItem4 = new Armor();
			}
		}
		
	}
	
	/**
	 * 
	 * @param item
	 *            - item to be dropped
	 * @return - the item that was dropped, if you had that item. else, drops
	 *         unequipped placeholder
	 */
	public Item dropItem(Item item) {
		for (int i = 0; i < pouch.size(); i++) {
			if (pouch.get(i) == item) {
				pouch.remove(i);
				return item;
			}
		}
		return new OneUseItem();
	}
}
