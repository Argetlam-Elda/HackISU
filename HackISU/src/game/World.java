package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import armor.Armor;
import armor.ArmorType;
import armor.Boots;
import armor.Chestpiece;
import armor.Gloves;
import armor.Helm;
import armor.Leggings;
import textGame.Monster;
import weapons.Melee;
import weapons.Ranged;
import weapons.Weapon;

/**
 * 
 * @author Mitchell Bennett, Colt Rogness
 *
 */
public class World {
	
	/**
	 * grid of map cells
	 */
	public Cell[][] grid;
	
	/**
	 * starting x position in the world
	 */
	private int startX;
	
	/**
	 * starting y position in the world
	 */
	private int startY;
	
	/**
	 * ArrayList of all monsters that can exist
	 */
	public ArrayList<Monster> allMonsters;
	
	/**
	 * ArrayList of all melee weapons that can exist
	 */
	public ArrayList<Melee> allMeleeWeapons;

	/**
	 * ArrayList of all ranged weapons that exist
	 */
	public ArrayList<Ranged> allRangedWeapons;
	
	/**
	 * ArrayLsit of all armor in this game
	 */
	public ArrayList<Armor> allArmor;
	
	/**
	 * constructor builds a world from a input file
	 * 
	 * @param inputFileName
	 *            - name of the file to build from
	 * @throws FileNotFoundException
	 *             - because there are files involved
	 */
	public World(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);
		
		String terrain; // terrain the cell will be set to
		String flavor; // flavortext for the cell
		String next;
		int combatMin; // min combat rating of enemies
		int combatMax; // max combat rating
		int maxEnemies; // max amount of enemies allowed
		
		int width = scan.nextInt();
		int length = scan.nextInt();
		grid = new Cell[width][length];
		startX = scan.nextInt();
		startY = scan.nextInt();
		readWeaponsFromFile();
		readArmorFromFile();
		readMonstersFromFile();
		
		// run through file to create world
		for (int i = 0; i < 3 && scan.hasNext(); i++) {
			for (int j = 0; j < 3 && scan.hasNext(); j++) {
				terrain = scan.next();
				next = scan.next();
				flavor = "";
				while (next.equals("/") == false) {
					flavor += "" + next;
					flavor += " ";
					next = scan.next();
				}
				combatMin = scan.nextInt();
				combatMax = scan.nextInt();
				maxEnemies = scan.nextInt();
				
				grid[i][j] = new Cell(this, terrain, flavor, combatMin, combatMax, maxEnemies);
				flavor = "";
			}
		}
		scan.close();
	}
	
	private void readArmorFromFile() throws FileNotFoundException {
		File file = new File("AllArmor.txt");
		Scanner scan = new Scanner(file);
		allArmor = new ArrayList<Armor>();
		while (scan.hasNext()) {
			int defence = scan.nextInt();
			int durability = scan.nextInt();
			String name = scan.nextLine();
			String type = scan.nextLine();
			name = name.trim();
			if (type.equalsIgnoreCase("helm")) {
				allArmor.add(new Helm(name, defence, durability));
			}
			if (type.equalsIgnoreCase("chestpiece")) {
				allArmor.add(new Chestpiece(name, defence, durability));
			}
			if (type.equalsIgnoreCase("gloves")) {
				allArmor.add(new Gloves(name, defence, durability));
			}
			if (type.equalsIgnoreCase("leggings")) {
				allArmor.add(new Leggings(name, defence, durability));
			}
			if (type.equalsIgnoreCase("boots")) {
				allArmor.add(new Boots(name, defence, durability));
			}
		}
		scan.close();
	}

	/**
	 * builds an ArrayList of all the monsters that can be found in the game
	 * 
	 * @throws FileNotFoundException
	 */
	private void readMonstersFromFile() throws FileNotFoundException {
		File file = new File("Monsters.txt");
		Scanner scan = new Scanner(file);
		allMonsters = new ArrayList<Monster>();
		while (scan.hasNext()) {
			int damage = scan.nextInt();
			int strength = scan.nextInt();
			int agility = scan.nextInt();
			int defence = scan.nextInt();
			int speed = scan.nextInt();
			int maxHP = scan.nextInt();
			String name = scan.nextLine();
			name = name.trim();
			allMonsters.add(new Monster(this, name, damage, strength, agility, defence, speed, maxHP));
		}
		scan.close();
	}
	
	/**
	 * builds an ArrayList of all the weapons that can be found in the game
	 * 
	 * @throws FileNotFoundException
	 */
	private void readWeaponsFromFile() throws FileNotFoundException {
		File file = new File("Melee_Weapons.txt");
		Scanner scan = new Scanner(file);
		allMeleeWeapons = new ArrayList<Melee>();
		while (scan.hasNext()) {
			int damage = scan.nextInt();
			int durability = scan.nextInt();
			String name = scan.nextLine();
			String flavor = scan.nextLine();
			allMeleeWeapons.add(new Melee(name, damage, durability, flavor));
		}
		scan.close();
		
		file = new File("Ranged_Weapons.txt");
		scan = new Scanner(file);
		allRangedWeapons = new ArrayList<Ranged>();
		while (scan.hasNext()) {
			int damage = scan.nextInt();
			int durability = scan.nextInt();
			String name = scan.nextLine();
			String flavor = scan.nextLine();
			allRangedWeapons.add(new Ranged(name, damage, durability, flavor));
		}
		scan.close();
	}
	
	/**
	 * 
	 * @return - starting x coord
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * 
	 * @return - starting y coord
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * overrides the toString method, prints the flavor texts
	 * 
	 * @return - string containing the flavor text of each cell
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				result += grid[i][j].getFlavor() + "\n";
			}
			result += "\n\n\n";
		}
		return result;
	}
	
	public Melee getMeleeWeapon(int CR) {
		Random rand = new Random();
		int skip = rand.nextInt(60);
		// skip = 0; // debug
		if (CR > 0) {
			for (int i = 0; i < allMeleeWeapons.size(); i++) {
				if (allMeleeWeapons.get(i).getChallengeRating() == CR) {
					if (skip == 0) {
						return allMeleeWeapons.get(i);
					}
					else {
						skip--;
					}
				}
				if (i == allMeleeWeapons.size() - 1) {
					i = -1;
				}
			}
		}
		return new Melee();
	}
	
	public Weapon getRangedWeapon(int CR) {
		Random rand = new Random();
		int skip = rand.nextInt(60);
		// skip = 0; // debug
		if (CR > 0) {
			for (int i = 0; i < allRangedWeapons.size(); i++) {
				if (allRangedWeapons.get(i).getChallengeRating() == CR) {
					if (skip == 0) {
						return allRangedWeapons.get(i);
					}
					else {
						skip--;
					}
				}
				if (i == allRangedWeapons.size() - 1) {
					i = -1;
				}
			}
		}
		return new Ranged();
	}
	
	public Armor getArmor(int CR, ArmorType type) {
		Random rand = new Random();
		int skip = rand.nextInt(60);
		if (CR > 0 && CR < 8) {
			for (int i = 0; i < allArmor.size(); i++) {
				if (allArmor.get(i).getChallengeRating() == CR && allArmor.get(i).getType() == type) {
					if (skip == 0) {
						return allArmor.get(i);
					}
					else {
						skip--;
					}
				}
				if (i == allArmor.size() - 1) {
					i = -1;
				}
			}
		}
		return new Chestpiece();
	}
	
	
}
