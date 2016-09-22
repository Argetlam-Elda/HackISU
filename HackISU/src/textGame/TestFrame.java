package textGame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Brandon Elizondo, Colt Rogness
 *
 */
@SuppressWarnings("serial")
public class TestFrame extends JFrame implements KeyListener {
	
	/**
	 * the giant box that makes the window a different color
	 */
	private JPanel contentPane;
	
	/**
	 * Main text screen, the huge one you retard
	 */
	public JTextArea console;
	
	/**
	 * Top of top info screen
	 */
	public JTextField playerPane;
	
	/**
	 * Top of middle info screen
	 */
	public JTextField weaponPane;
	
	/**
	 * User input bar
	 */
	public JTextField userInput;
	
	/**
	 * Bottom of top info screen
	 */
	public JTextArea playerInfo;
	
	/**
	 * Bottom of middle info screen
	 */
	public JTextArea weaponInfo;
	
	/**
	 * Top of bottom info screen
	 */
	public JTextField MonsterPane;
	
	/**
	 * Bottom of bottom info screen
	 */
	public JTextArea monsterInfo;
	
	/**
	 * stores last command for convenience
	 */
	public String previousCommand = "";
	
	/**
	 * for arrow key convenience
	 */
	public String currentCommand = "";
	
	/**
	 * for arrow key convenience
	 */
	public boolean upHasRun;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		console = new JTextArea();
		console.setBounds(12, 12, 1040, 499);
		console.setEditable(false);
		contentPane.add(console);
		
		playerInfo = new JTextArea();
		playerInfo.setBounds(1062, 45, 190, 90);
		playerInfo.setEnabled(false);
		playerInfo.setEditable(true);
		contentPane.add(playerInfo);
		
		playerPane = new JTextField();
		playerPane.setBounds(1062, 14, 190, 20);
		playerPane.setEnabled(false);
		playerPane.setEditable(false);
		contentPane.add(playerPane);
		playerPane.setColumns(10);
		
		weaponPane = new JTextField();
		weaponPane.setBounds(1062, 146, 190, 20);
		weaponPane.setEnabled(false);
		weaponPane.setEditable(false);
		weaponPane.setColumns(10);
		contentPane.add(weaponPane);
		
		weaponInfo = new JTextArea();
		weaponInfo.setBounds(1062, 177, 190, 202);
		weaponInfo.setEnabled(false);
		weaponInfo.setEditable(false);
		contentPane.add(weaponInfo);
		
		userInput = new JTextField();
		userInput.setBounds(10, 522, 694, 28);
		contentPane.add(userInput);
		userInput.setColumns(10);
		
		MonsterPane = new JTextField();
		MonsterPane.setEnabled(false);
		MonsterPane.setEditable(false);
		MonsterPane.setColumns(10);
		MonsterPane.setBounds(1062, 390, 190, 20);
		contentPane.add(MonsterPane);
		
		monsterInfo = new JTextArea();
		monsterInfo.setEnabled(false);
		monsterInfo.setEditable(false);
		monsterInfo.setBounds(1062, 421, 190, 129);
		contentPane.add(monsterInfo);
		userInput.addKeyListener(this);
	}
	
	/**
	 * this runs every time a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				previousCommand = userInput.getText();
				textControl();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			if (!upHasRun) {
				currentCommand = userInput.getText();
			}
			if (!previousCommand.equals("")) {
				userInput.setText(previousCommand);
				upHasRun = true;
			}
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			if (upHasRun) {
				userInput.setText(currentCommand);
				upHasRun = false;
			}
		}
	}
	
	/**
	 * this runs every time a key is released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	/**
	 * this runs every time a key is typed
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * trims the text printed to the console once it gets to long
	 */
	public void mainTextCropper() {
		int lineCount = console.getLineCount();
		
		if (lineCount >= 22) {
			String temp = "";
			Scanner textDelete = new Scanner(console.getText());
			textDelete.useDelimiter("\\s*>>\\s*");
			
			for (int i = 0; i < 12; i++) {
				textDelete.next();
			}
			while (textDelete.hasNext()) {
				temp += ">>" + textDelete.next() + "\n";
			}
			temp = temp.trim() + "\n";
			console.setText(temp);
			textDelete.close();
		}
	}
	
	/**
	 * trims the whitespace of the input string and prints your command to the
	 * console, then calls the game's command method
	 * 
	 * @throws FileNotFoundException
	 */
	public void textControl() throws FileNotFoundException {
		String enteredText;
		enteredText = userInput.getText().trim();
		
		Scanner scan = new Scanner(enteredText);
		if ((enteredText.length() != 0 && scan.hasNext())) {
			userInput.setText("");
			console.setText(console.getText() + ">>" + enteredText + "\n");
		}
		Main.gameCommand(enteredText);
		mainTextCropper();
		scan.close();
	}
	
	/**
	 * this method runs when you hit the up arrow so you can reuse your last
	 * command
	 */
	public void getPreviousCommand() {
		Scanner textDelete = new Scanner(console.getText());
		textDelete.useDelimiter("\\s*>>\\s*");
		
		String temp = "";
		
		for (int i = 0; i < console.getLineCount() - 2; i++) {
			textDelete.next();
			
		}
		while (textDelete.hasNext()) {
			temp = textDelete.next();
		}
		previousCommand = temp;
		textDelete.close();
	}
	
}
