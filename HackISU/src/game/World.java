package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import textGame.Monster;
import weapons.Weapon;

public class World {
	
	/**
	 *  grid of map cells
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
	 * ArrayList of all weapons that can exist
	 */
	public ArrayList<Weapon> allWeapons;
	
	/**
	 * constructor builds a world from a input file
	 * @param inputFileName - name of the file to build from
	 * @throws FileNotFoundException - because there are files involved
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

				grid[i][j] = new Cell(terrain, flavor, combatMax, combatMin, maxEnemies);
				flavor = "";
			}
		}
		scan.close();
		readWeaponsFromFile();
		readMonstersFromFile();
	}

	/**
	 * builds an ArrayList of all the monsters that can be found in the game
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
			allMonsters.add(new Monster(name, damage, strength, agility, defence, speed, maxHP));
		}
		scan.close();
	}
	
	/**
	 * builds an ArrayList of all the weapons that can be found in the game
	 * @throws FileNotFoundException
	 */
	private void readWeaponsFromFile() throws FileNotFoundException {
		File file = new File("Weapons.txt");
		Scanner scan = new Scanner(file);
		allWeapons = new ArrayList<Weapon>();
		while (scan.hasNext()) {
			int damage = scan.nextInt();
			int durability = scan.nextInt();
			int challengeRating = scan.nextInt();
			String name = scan.nextLine();
			String flavor = scan.nextLine();
			allWeapons.add(new Weapon(name, damage, durability, challengeRating, flavor));
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

}
