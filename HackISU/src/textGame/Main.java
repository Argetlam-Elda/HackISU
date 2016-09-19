package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// import game.Cell;
import game.World;
import weapons.Weapon;

/**
 * 
 * @author Colt Rogness
 *
 */
public class Main {
	
	/**
	 * tells whether the world has been initialized yet
	 */
	private static boolean isInit;
	
	/**
	 * the PC, stores stats and items
	 */
	private static PlayerCharacter player;
	
	/**
	 * the map, tells how many enemies and such there are
	 */
	private static World map;
	
	/**
	 * the player's x location on the map
	 */
	private static int locationX;
	
	/**
	 * the player's y location on the map
	 */
	private static int locationY;
	
	/**
	 * the monsters in the current cell, empty if there are none
	 */
	private static ArrayList<Monster> monsters;
	
	/**
	 * this is the window, don't fuck with it
	 */
	static TestFrame test = new TestFrame();
	
	/**
	 * runs once to setup JFrame
	 * 
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args) {
		test.setVisible(true);
		isInit = false;
		writeToMain("What is your name?");
	}
	
	/**
	 * updates the players display
	 */
	public static void updatePlayer() {
		writeToInfoTop(player.getName(),
				"Agility: " + player.getAgility() + "\nStrength: " + player.getStrength() + "\nDefence: "
						+ player.getDefense() + "\nSpeed: " + player.getSpeed() + "\nHP: "
						+ player.getCurrentHitPoints());
	}
	
	/**
	 * updates the monsters' displays
	 */
	public static void updateMonster() {
		if (monsters == null || monsters.isEmpty()) {
			writeToInfoBot("Monsters", "");
		}
		else {
			writeToInfoBot("Monsters", monsters.get(0).getTitle() + ", " + monsters.size() + "x\nHP"
					+ monsters.get(monsters.size() - 1).getCurrentHitPoints() + "");
		}
	}
	
	/**
	 * updates the Weapons display
	 */
	public static void updateWeapons() {
		writeToInfoMid("Weapons",
				"Melee:\n   " + player.getMeleeWeapon().getName() + "\n   Dmg: " + player.getMeleeWeapon().getDamage() + "\nRanged:\n   " + player.getRangedWeapon().getName() + "\n   Dmg: " + player.getMeleeWeapon().getDamage());
	}
	
	/**
	 * runs the first time to setup the game
	 * @param command - the command entered to do things to the game
	 * @throws FileNotFoundException
	 */
	public static void firstRun(String command) throws FileNotFoundException {
		isInit = true;
		player = new PlayerCharacter(command);
		writeToMain("Your name is " + player.getName());
		updatePlayer();
		map = new World("world1-1.txt");
		locationX = map.getStartX();
		locationY = map.getStartY();
		writeToMain(map.grid[locationX][locationY].getFlavor());
		monsters = map.grid[locationX][locationY].getEnemies();
		if (monsters != null && monsters.size() > 0) {
			writeToMain("Look Out! There's a monster!");
		}
	}
	
	/**
	 * runs every time a command is entered, calls whichever method is relevant
	 * @param command -the command entered to do things to the game
	 * @throws FileNotFoundException - because files
	 */
	public static void gameCommand(String command) throws FileNotFoundException {
		if (isInit) {
			if (command.equals("kill")) {
				System.exit(0);
			}
			else if (monsters == null || monsters.size() == 0) {
				move(command);
			}
			else {
				attack(command);
			}
		}
		else {
			firstRun(command);
		}
		updatePlayer();
		updateWeapons();
		updateMonster();
	}
	
	/**
	 * runs when there is a monster in the area
	 * @param command -the command entered to do things to the game
	 */
	public static void attack(String command) {
		if (!player.getMeleeWeapon().equals(new Weapon()) && command.equalsIgnoreCase("MELEE")) {
			monsters.get(monsters.size() - 1).takeDamage(player.getMeleeWeapon().getDamage());
		}
		else if (!player.getRangedWeapon().equals(new Weapon()) && command.equalsIgnoreCase("RANGED")) {
			monsters.get(monsters.size() - 1).takeDamage(player.getRangedWeapon().getDamage());
		}
		
		if (!monsters.get(monsters.size() - 1).isAlive()) {
			monsters.remove(monsters.size() - 1);
		}
		if (monsters.isEmpty()) {
			writeToMain("You killed it. Are you the monster?");
		}
		else {
			for (int i = 0; i < monsters.size(); i++) {
				player.takeDamage(monsters.get(i).getDamage());
			}
		}
	}
	
	/**
	 * runs when there is not a monster in the area
	 * @param command - the command entered to do things to the game
	 */
	public static void move(String command) {
		if (command.equalsIgnoreCase("NORTH")) {
			if (locationX > 0) {
				locationX -= 1;
			}
			else {
				writeToMain("There is nothing to the north");
			}
		}
		if (command.equalsIgnoreCase("SOUTH")) {
			if (locationX < map.grid.length - 1) {
				locationX += 1;
			}
			else {
				writeToMain("There is nothing to the south");
			}
		}
		if (command.equalsIgnoreCase("EAST")) {
			if (locationY < map.grid[0].length - 1) {
				locationY += 1;
				
			}
			else {
				writeToMain("There is nothing to the east");
			}
		}
		if (command.equalsIgnoreCase("WEST")) {
			if (locationY > 0) {
				locationY -= 1;
			}
			else {
				writeToMain("There is nothing to the west");
			}
		}
		writeToMain(map.grid[locationX][locationY].getFlavor());
		monsters = map.grid[locationX][locationY].getEnemies();
		if (monsters != null && monsters.size() > 0) {
			writeToMain("Look Out! There's a monster!");
		}
	}
	
	/**
	 * prints text to the status boxes on the side of the screen
	 * @param top - text to go in the top part of the box
	 * @param bottom - text to go in the bottom part of the box
	 */
	public static void writeToInfoMid(String top, String bottom) {
		test.textField_1.setText(top + "\n");
		
		test.textArea_2.setText(bottom + "\n");
		
	}
	
	/**
	 * prints text to the status boxes on the side of the screen
	 * @param top - text to go in the top part of the box
	 * @param bottom - text to go in the bottom part of the box
	 */
	public static void writeToInfoBot(String top, String bottom) {
		test.textField_2.setText(top + "\n");
		
		test.textArea_3.setText(bottom + "\n");
		
	}
	
	/**
	 * prints text to the status boxes on the side of the screen
	 * @param top - text to go in the top part of the box
	 * @param bottom - text to go in the bottom part of the box
	 */
	public static void writeToInfoTop(String top, String bottom) {
		test.textField.setText(top);
		
		test.textArea_1.setText(bottom + "\n");
	}
	
	/**
	 * adds the given text to the bottom of the console
	 * @param newText - whatever needs printed
	 */
	public static void writeToMain(String newText) {
		test.textArea.setText(test.textArea.getText() + ">>" + newText + "\n");
	}
	
}
