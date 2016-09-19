package weapons;

public class Ranged extends Weapon{
	
	/**
	 * constructs a new weapon
	 */
	public Ranged() {
		name = "unequipped";
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
	public Ranged(String name, int damage, int durability, int challengeRating, String flavorText) {
		super.name = name;
		this.damage = damage;
		super.durability = durability;
		super.flavorText = flavorText;
		super.challengeRating = challengeRating;
	}
	
	/**
	 * 
	 * @return - the weapons type, MELEE or RANGED
	 */
	public WeaponType getType() {
		return WeaponType.RANGED;
	}

	@Override
	public Weapon clone() {
		return new Ranged(name, damage, durability, challengeRating, flavorText);
	}
	
}
