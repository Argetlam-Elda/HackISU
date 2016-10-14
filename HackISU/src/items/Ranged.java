package items;

public class Ranged extends Weapon{
	
	/**
	 * constructs a new weapon
	 */
	public Ranged() {
		name = "";
		damage = 0;
		durability = -1;
		flavorText = "";
		super.challengeRating = -1;
	}
	
	/**
	 * constructs a weapon with given values
	 * @param name - name of the weapon
	 * @param damage - amount of damage it does
	 * @param durability - number of hits it can take
	 * @param challengeRating - the challenge rating of the weapon
	 * @param flavorText - pretty self explanatory
	 */
	public Ranged(String name, int damage, String flavorText) {
		super.name = name;
		this.damage = damage;
		super.durability = 200;
		super.flavorText = flavorText;
		super.challengeRating = damage / 2;
		super.value = damage;
	}
	
	/**
	 * 
	 * @return - the weapons type, MELEE or RANGED
	 */
	public ItemType getType() {
		return ItemType.RANGED;
	}

	@Override
	public Weapon clone() {
		return new Ranged(name, durability, flavorText);
	}
	
}
