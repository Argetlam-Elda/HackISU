package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// import game.Cell;
import game.World;
import weapons.Weapon;

public class Main {

	private static boolean isInit;
	private static PlayerCharacter player;
	private static World map;
	private static int locationX;
	private static int locationY;
	private static ArrayList<Monster> monsters;

	static String text;
	static TestFrame test = new TestFrame();

	public static void main(String[] args) {
		test.setVisible(true);
		isInit = false;
		SetMainText("What is your name?");
	}

	public static void updatePlayer() {
		WriteToInfoTop(player.getName(), "Agility: " + player.getAgility() + "\nStrength: " + player.getStrength()
		+ "\nDefence: " + player.getDefense() + "\nSpeed: " + player.getSpeed() + "\nHP: " + player.getCurrentHitPoints());
	}
	
	public static void updateMonster() {
		if (monsters == null || monsters.isEmpty()) {
			WriteToInfoBot("Monsters", "");
		}
		else {
		WriteToInfoBot("Monsters", monsters.get(0).getTitle() + ", "  + monsters.size() + "x\nHP" + monsters.get(monsters.size()-1).getCurrentHitPoints() + "");
		}
	}

	public static void firstRun(String command) throws FileNotFoundException {
		isInit = true;
		player = new PlayerCharacter(command);
		SetMainText("Your name is " + player.getName());
		updatePlayer();
		map = new World("world1-1.txt");
		locationX = map.getStartX();
		locationY = map.getStartY();
		SetMainText(map.grid[locationX][locationY].getFlavor());
		monsters = map.grid[locationX][locationY].getEnemies();
		if (monsters != null && monsters.size() > 0) {
			SetMainText("Look Out! There's a monster!");
		}
	}
	
	public static void SetVariables(String command) throws FileNotFoundException {
		if (isInit) {
			if (command.equalsIgnoreCase("/systemResize")) {
				test.resizeControl();
			}
			else if (command.equals("kill")) {
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
		updateMonster();
	}

	public static void attack(String command) {
		if (!player.getMeleeWeapon().equals(new Weapon()) && command.equalsIgnoreCase("MELEE")) {
			monsters.get(monsters.size()-1).takeDamage(player.getMeleeWeapon().getDamage());
		}
		else if (!player.getRangedWeapon().equals(new Weapon()) && command.equalsIgnoreCase("RANGED")) {
			monsters.get(monsters.size()-1).takeDamage(player.getRangedWeapon().getDamage());
		}
		
		if (!monsters.get(monsters.size()-1).isAlive()) {
			monsters.remove(monsters.size()-1);
		}
		if (monsters.isEmpty()) {
			SetMainText("You killed it. Are you the monster?");
		}
		else {
			for (int i = 0; i < monsters.size(); i++) {
				player.takeDamage(monsters.get(i).getDamage());
			}
		}
	}

	public static void move(String command) {
		if (command.equalsIgnoreCase("NORTH")) {
			if (locationX > 0) {
				locationX -= 1;
			}
			else {
				SetMainText("There is nothing to the north");
			}
		}
		if (command.equalsIgnoreCase("SOUTH")) {
			if (locationX < map.grid.length - 1) {
				locationX += 1;
			}
			else {
				SetMainText("There is nothing to the south");
			}
		}
		if (command.equalsIgnoreCase("EAST")) {
			if (locationY < map.grid[0].length - 1) {
				locationY += 1;
				
			}
			else {
				SetMainText("There is nothing to the east");
			}
		}
		if (command.equalsIgnoreCase("WEST")) {
			if (locationY > 0) {
				locationY -= 1;
			}
			else {
				SetMainText("There is nothing to the west");
			}
		}
		SetMainText(map.grid[locationX][locationY].getFlavor());
		monsters = map.grid[locationX][locationY].getEnemies();
		if (monsters != null && monsters.size() > 0) {
			SetMainText("Look Out! There's a monster!");
		}
	}

	public static void WriteToInfoMid(String top, String bottom) {
		test.textField_1.setText(top + "\n");

		test.textArea_2.setText(bottom + "\n");

	}

	public static void WriteToInfoBot(String top, String bottom) {
		test.textField_2.setText(top + "\n");

		test.textArea_3.setText(bottom + "\n");

	}

	public static void WriteToInfoTop(String top, String bottom) {
		test.textField.setText(top);

		test.textArea_1.setText(bottom + "\n");
	}

	public static void SetMainText(String newText) {
		test.textArea.setText(test.textArea.getText() + ">>" + newText + "\n");
	}

}
