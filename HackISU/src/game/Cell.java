package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import mobs.*;
import textGame.*;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Cell {
	
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
	public Cell (String t, String f, int cMin, int cMax, int numEnemies) throws FileNotFoundException {
		terrain = t;
		flavor = f;
		combatMin = cMin;
		combatMax = cMax;
		maxEnemies = numEnemies;
		
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
		// if (maxEnemies > 0) {
		// int numMobs = rand.nextInt(maxEnemies) + 1;
		enemies = new ArrayList<Monster>();
		for (int i = 0; i < maxEnemies; i++) { // always spawns max
			enemies.add(new Goblin());
		}
		// }
	}
	
}
