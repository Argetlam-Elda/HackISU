package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import armor.Armor;
import armor.ArmorType;
import game.World;
import weapons.Weapon;

/**
 * 
 * @author Brandon Elizondo, Colt Rogness
 *
 */
public class Main {
	
	/**
	 * tells whether the world has been initialized yet
	 */
	private static boolean isInit;
	
	/**
	 * tells whether you are equipping things
	 */
	private static boolean equipping;
	
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
	 * updates the player, weapons, armor, and monsters display
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
		test.weaponInfo.setText(player.getMeleeWeapon().getName() + ": Dmg: " + player.getMeleeWeapon().getDamage()
				+ "\n" + player.getRangedWeapon().getName() + ": Dmg: " + player.getMeleeWeapon().getDamage() + "\n\n");
		for (int i = 0; i < player.getEquippedArmor().length; i++) {
			if (!player.getEquippedArmor()[i].getName().equalsIgnoreCase("")) {
				test.weaponInfo.append(player.getEquippedArmor()[i].toString() + ": Def: " + player.getEquippedArmor()[i].getDefense() + "\n");
			}
		}
	}
	
	private static void updateLoot() {
		ArrayList<Item> items = map.grid[locationX][locationY].getItems();
		for (int i = 0; i < items.size(); i++) {
			writeToMain("You see a " + items.get(i).getName() + ": " + items.get(i).getFlavorText());
		}
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
		equipping = false;
		player = new PlayerCharacter(command);
		map = new World("world1-1.txt");
		locationX = map.getStartX();
		locationY = map.getStartY();
		monsters = map.grid[locationX][locationY].getEnemies();
		
		test.playerPane.setText(player.getName());
		test.weaponPane.setText("Weapons & Armor\n");
		test.MonsterPane.setText("Monsters\n");
		
		ArrayList<Item> add = new ArrayList<Item>();
		add.add(map.getMeleeWeapon(1));
		add.add(map.getRangedWeapon(1));
		add.add(map.getArmor(1, ArmorType.CHESTPIECE));
		// add.add();
		map.grid[locationX][locationY].addItems(add);
		// player.equipWeapon(map.getRangedWeapon(1));
		// player.equipWeapon(map.getMeleeWeapon(1));
		
		writeToMain("Your name is " + player.getName());
		writeToMain(map.grid[locationX][locationY].getFlavor());
		
		if (monsters != null && !monsters.isEmpty()) {
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
			else if (monsters != null && monsters.size() > 0) {
				attack(command);
			}
			else if (equipping) {
				equip(command);
			}
			else {
				move(command);
				other(command);
			}
		}
		else {
			firstRun(command);
		}
		updateInfo();
		
	}
	
	private static void equip(String command) {
		for (int i = 0; i < player.getPouch().size(); i++) {
			if (player.getPouch().get(i).getName().trim().equalsIgnoreCase(command)) {
				String temp = player.getPouch().get(i).getClass().getName();
				if (temp.equalsIgnoreCase("weapons.melee") || temp.equalsIgnoreCase("weapons.ranged")) {
					player.equip((Weapon) player.getPouch().get(i));
					player.pouch.remove(i);
				}
				else if (temp.equalsIgnoreCase("Armor.helm") || temp.equalsIgnoreCase("Armor.chestpiece")
						|| temp.equalsIgnoreCase("Armor.gloves") || temp.equalsIgnoreCase("Armor.leggings")
						|| temp.equalsIgnoreCase("Armor.boots")) {
					player.equip((Armor) player.getPouch().get(i));
					player.pouch.remove(i);
				}
			}
		}
		equipping = false;
	}
	
	private static void other(String command) {
		if (command.equals("loot all") && !map.grid[locationX][locationY].getItems().isEmpty()) {
			for (int i = map.grid[locationX][locationY].items.size() - 1; i >= 0; i--) {
				player.addItem(map.grid[locationX][locationY].items.get(i));
				map.grid[locationX][locationY].items.remove(i);
			}
		}
		if (command.equalsIgnoreCase("LOOK")) {
			updateLoot();
			writeToMain(map.grid[locationX][locationY].getFlavor());
		}
		if (command.equalsIgnoreCase("EQUIP")) {
			equipping = true;
			writeToMain(player.getPouch().toString());
		}
	}
	
	/**
	 * runs when there is a monster in the area
	 * 
	 * @param command
	 *            -the command entered to do things to the game
	 */
	private static void attack(String command) {
		if (command.equalsIgnoreCase("MELEE")) {
			monsters.get(0).takeDamage(player.getMeleeWeapon().getDamage());
		}
		else if (command.equalsIgnoreCase("RANGED")) {
			monsters.get(0).takeDamage(player.getRangedWeapon().getDamage());
		}
		
		if (!monsters.get(0).isAlive()) {
			ArrayList<Item> add = monsters.get(0).getPouch();
			map.grid[locationX][locationY].addItems(add);
			monsters.remove(0);
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
				map.grid[locationX][locationY].resetEnemies();
				locationX -= 1;
				writeToMain(map.grid[locationX][locationY].getFlavor());
			}
			else {
				writeToMain("There is nothing to the north");
			}
		}
		if (command.equalsIgnoreCase("SOUTH")) {
			if (locationX < map.grid.length - 1) {
				map.grid[locationX][locationY].resetEnemies();
				locationX += 1;
				writeToMain(map.grid[locationX][locationY].getFlavor());
			}
			else {
				writeToMain("There is nothing to the south");
			}
		}
		if (command.equalsIgnoreCase("EAST")) {
			if (locationY < map.grid[0].length - 1) {
				map.grid[locationX][locationY].resetEnemies();
				locationY += 1;
				writeToMain(map.grid[locationX][locationY].getFlavor());
			}
			else {
				writeToMain("There is nothing to the east");
			}
		}
		if (command.equalsIgnoreCase("WEST")) {
			if (locationY > 0) {
				map.grid[locationX][locationY].resetEnemies();
				locationY -= 1;
				writeToMain(map.grid[locationX][locationY].getFlavor());
			}
			else {
				writeToMain("There is nothing to the west");
			}
		}
		monsters = map.grid[locationX][locationY].getEnemies();
		
		if (monsters != null && !monsters.isEmpty()) {
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
