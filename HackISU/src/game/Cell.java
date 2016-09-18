package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import mobs.*;
import textGame.*;

public class Cell {

	private String terrain; // terrain the cell will be set to
	private String flavor; // flavortext for the cell
	private int combatMin; // min combat rating of enemies
	private int combatMax; // max combat rating
	private int maxEnemies; // its in the name
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
	public Cell(String t, String f, int cMin, int cMax, int numEnemies) throws FileNotFoundException {
		terrain = t;
		flavor = f;
		combatMin = cMin;
		combatMax = cMax;
		maxEnemies = numEnemies;

		resetEnemies();
	}

	// /**
	// * @return - the terrain type of the cell.
	// */
	// public Terrain getTerrain() {
	// if (terrain == "FOREST") return Terrain.FOREST;
	// else if(terrain == "MEADOW") return Terrain.MEADOW;
	// else if(terrain == "TOWN") return Terrain.TOWN;
	// else if(terrain == "VOLCANO") return Terrain.VOLCANO;
	// else{
	// return null; //TODO: just a placeholder
	// }
	// }

	/**
	 * @return - flavor text
	 */
	public String getFlavor() {
		return flavor;
	}

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

	/*
	 * Returns max amount of enemies allowed in the square.
	 */
	public int getMaxEnemies() {
		return maxEnemies;
	}

	public ArrayList<Monster> getEnemies() {
		return enemies;
	}

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
