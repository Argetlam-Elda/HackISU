package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import items.*;

/**
 * @author Mitchell Bennett, Colt Rogness
 */
public class Cell {
	
	/**
	 * the world that this cell is in
	 */
	private World world;
	
	
	/**
	 * flavor text for the cell
	 */
	private String flavor;
	
	
	/**
	 * min combat rating of enemies
	 */
	private int combatMin;
	
	
	/**
	 * max combat rating
	 */
	private int combatMax;
	
	
	/**
	 * its in the name
	 */
	private int maxEnemies;
	
	
	/**
	 * ArrayList of monsters, holds the enemies in this cell so they don't spread
	 */
	private ArrayList<Monster> enemies;
	
	
	/**
	 * items dropped by monsters/spawned into the world
	 */
	public ArrayList<Item> items;
	
	
	/**
	 * gold dropped by monsters in the cell
	 */
	public int gold;
	
	
	/**
	 * creates a cell object with the given values
	 * 
	 * @param f
	 *            - flavor text
	 * @param cMin
	 *            - min enemy level
	 * @param cMax
	 *            - max enemy level
	 * @param numEnemies
	 *            - max number of enemies
	 * @throws FileNotFoundException
	 *             - because why not
	 */
	public Cell(World w, String f, int cMin, int cMax, int numEnemies) throws FileNotFoundException {
		world = w;
		flavor = f;
		combatMin = cMin;
		combatMax = cMax;
		maxEnemies = numEnemies;
		
		items = new ArrayList<Item>();
		resetEnemies();
	}
	
	
	/**
	 * @return - flavor text
	 */
	public String getFlavor() {
		return flavor;
	}
	
	
	/**
	 * get the min and max levels of monsters in the cell
	 * 
	 * @return - array [min, max] (levels)
	 */
	public int[] getLevels() {
		int[] minMax = new int[2];
		minMax[0] = combatMin;
		minMax[1] = combatMax;
		return minMax;
	}
	
	
	/**
	 * @return - the max number of enemies allowed in the cell.
	 */
	public int getMaxEnemies() {
		return maxEnemies;
	}
	
	
	/**
	 * get a list of enemies in the cell
	 * 
	 * @return - an ArrayList of the enemies in this cell
	 */
	public ArrayList<Monster> getEnemies() {
		return enemies;
	}
	
	
	/**
	 * resets the enemy array
	 */
	public void resetEnemies() {
		Random rand = new Random();
		int numEnemies = rand.nextInt(maxEnemies + 2);
		int skip;
		enemies = new ArrayList<Monster>();
		for (int i = 0; i < numEnemies; i++) { // spawns correct amount
			skip = rand.nextInt(60);
			for (int j = 0; j < world.allMonsters.size(); j++) {
				if (world.allMonsters.get(j).getChallangeRating() >= combatMin && world.allMonsters.get(j).getChallangeRating() <= combatMax) {
					if (skip == 0) {
						enemies.add(world.allMonsters.get(j).clone());
						break;
					}
					else {
						skip--;
					}
				}
				if (j == world.allMonsters.size() - 1 && skip != 0) {
					j = -1;
				}
			}
		}
	}
	
	
	/**
	 * adds the given items to the cell, to hold until the player picks them up
	 * 
	 * @param add
	 *            - ArrayList of items to be added
	 */
	public void addItems(ArrayList<Item> add) {
		items.addAll(add);
	}
	
	
	/**
	 * get all the items dropped in the cell
	 * 
	 * @return - all items in the cell
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	
	/**
	 * gets one item with the given name
	 * 
	 * @param name
	 *            - name of the item to return
	 * @return - an item with the given name, or null if it doesn't exist
	 */
	public Item getItem(String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().trim().equalsIgnoreCase(name)) {
				return items.remove(i);
			}
		}
		return null;
	}
	
	/**
	 * get the gold currently in the cell
	 * @return - amount of gold
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * adds gold to the cell's stockpile
	 * @param g
	 */
	public void addGold(int g) {
		gold += g;
	}
	
	/**
	 * take all gold from the cell
	 * @return - the amount of gold that was in the cell
	 */
	public int takeGold() {
		int temp = gold;
		gold = 0;
		return temp;
	}
}
