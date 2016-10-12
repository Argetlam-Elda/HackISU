package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import items.*;

/**
 * 
 * @author Mitchell Bennett, Colt Rogness
 *
 */
public class Cell {
	
	/**
	 * the world that this cell is in
	 */
	private World world;
	
	/**
	 * terrain the cell will be set to
	 */
	private String terrain;
	
	/**
	 * flavortext for the cell
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
	 * ArrayList of monsters, holds the enemies in this cell so they don't
	 * spread
	 */
	private ArrayList<Monster> enemies;
	
	/**
	 * items dropped by monsters/spawned into the world
	 */
	public ArrayList<Item> items;
	
	/**
	 * creates a cell object
	 * 
	 * @param t
	 *            - terrain
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
	public Cell(World w, String t, String f, int cMin, int cMax, int numEnemies) throws FileNotFoundException {
		world = w;
		terrain = t;
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
	 * 
	 * @return - the terrain
	 */
	public String getTerrain() {
		return terrain;
	}
	
	/**
	 * @return - array [min, max] (levels)
	 */
	public int[] getLevels() {
		int[] minMax = new int[2];
		minMax[0] = combatMin;
		minMax[1] = combatMax;
		return minMax;
	}
	
	/**
	 * 
	 * @return - Returns max amount of enemies allowed in the square.
	 */
	public int getMaxEnemies() {
		return maxEnemies;
	}
	
	/**
	 * 
	 * @return - an array of the enemies in this cell
	 */
	public ArrayList<Monster> getEnemies() {
		return enemies;
	}
	
	/**
	 * refills the enemy array after they have been killed
	 */
	public void resetEnemies() {
		Random rand = new Random();
		int numEnemies = rand.nextInt(maxEnemies + 2);
		int skip;
		enemies = new ArrayList<Monster>();
		for (int i = 0; i < numEnemies; i++) { // spawns correct amount
			skip = rand.nextInt(60);
			for (int j = 0; j < world.allMonsters.size(); j++) {
				if (world.allMonsters.get(j).getChallangeRating() >= combatMin
						&& world.allMonsters.get(j).getChallangeRating() <= combatMax) {
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
	 * 
	 * @param add - ArrayList of items to be added
	 */
	public void addItems(ArrayList<Item> add) {
		items.addAll(add);
	}
	
	/**
	 * 
	 * @return - all items in the cell
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
}
