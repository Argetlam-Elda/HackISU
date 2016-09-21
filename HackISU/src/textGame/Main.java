package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import game.World;

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
	 * updates the player, weapons, and monsters display
	 */
	private static void updateInfo() {
		test.playerInfo.setText("Agility: " + player.getAgility() + "\nStrength: " + player.getStrength()
				+ "\nDefence: " + player.getDefense() + "\nSpeed: " + player.getSpeed() + "\nHP: "
				+ player.getCurrentHitPoints() + "\n");
		test.monsterInfo.setText("");
		if (monsters != null || !monsters.isEmpty()) {
			for (int i = 0; i < monsters.size(); i++) {
				test.monsterInfo.append(monsters.get(i).getTitle() + " Dmg: " + monsters.get(i).getDamage() + " HP: "
						+ monsters.get(i).getCurrentHitPoints() + "\n");
			}
		}
		test.weaponInfo.setText("Melee:\n   " + player.getMeleeWeapon().getName() + ": Dmg: "
				+ player.getMeleeWeapon().getDamage() + "\nRanged:\n   " + player.getRangedWeapon().getName()
				+ ": Dmg: " + player.getMeleeWeapon().getDamage() + "\n");
	}
	
	/**
	 * runs the first time to setup the game
	 * 
	 * @param command
	 *            - the command entered to do things to the game
	 * @throws FileNotFoundException
	 */
	private static void firstRun(String command) throws FileNotFoundException {
		isInit = true;
		player = new PlayerCharacter(command);
		map = new World("world1-1.txt");
		locationX = map.getStartX();
		locationY = map.getStartY();
		monsters = map.grid[locationX][locationY].getEnemies();
		
		test.playerPane.setText(player.getName());
		test.weaponPane.setText("Weapons\n");
		test.MonsterPane.setText("Monsters\n");
		
		player.equipWeapon(map.getMeleeWeapon(0));
		player.equipWeapon(map.getRangedWeapon(0));
		
		writeToMain("Your name is " + player.getName());
		writeToMain(map.grid[locationX][locationY].getFlavor());
		
		if (monsters != null && monsters.size() > 0) {
			writeToMain("Look Out! There's a monster!");
		}
	}
	
	/**
	 * runs every time a command is entered, calls whichever method is relevant
	 * 
	 * @param command
	 *            -the command entered to do things to the game
	 * @throws FileNotFoundException
	 *             - because files
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
		updateInfo();
	}
	
	/**
	 * runs when there is a monster in the area
	 * 
	 * @param command
	 *            -the command entered to do things to the game
	 */
	private static void attack(String command) {
		if (command.equalsIgnoreCase("MELEE")) {
			monsters.get(monsters.size() - 1).takeDamage(player.getMeleeWeapon().getDamage());
		}
		else if (command.equalsIgnoreCase("RANGED")) {
			monsters.get(monsters.size() - 1).takeDamage(player.getRangedWeapon().getDamage());
		}
		
		if (!monsters.get(monsters.size() - 1).isAlive()) {
			monsters.remove(monsters.size() - 1);
			writeToMain("You killed it. Are you the monster?");
		}
		if (!monsters.isEmpty()) {
			for (int i = 0; i < monsters.size(); i++) {
				player.takeDamage(monsters.get(i).getDamage());
			}
		}
	}
	
	/**
	 * runs when there is not a monster in the area
	 * 
	 * @param command
	 *            - the command entered to do things to the game
	 */
	private static void move(String command) {
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
	 * adds the given text to the bottom of the console
	 * 
	 * @param newText
	 *            - whatever needs printed
	 */
	private static void writeToMain(String newText) {
		test.console.setText(test.console.getText() + ">>" + newText + "\n");
	}
	
}
