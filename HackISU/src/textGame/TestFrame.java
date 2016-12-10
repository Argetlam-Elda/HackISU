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
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * @author Brandon Elizondo, Colt Rogness
 */
public class TestFrame extends JFrame implements KeyListener {
	
	/**
	 * serial id thing. idk, it kept yelling at me so i made one
	 */
	private static final long serialVersionUID = 1L;
	
	
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
	 * shows contents of the pouch
	 */
	public JTextArea pouchInfo;
	
	
	/**
	 * shows all items dropped in the area
	 */
	public JTextArea droppedItemDisplay;
	
	
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
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		console = new JTextArea();
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		console.setForeground(Color.GREEN);
		console.setFont(new Font("Monospaced", Font.PLAIN, 12));
		console.setBackground(Color.BLACK);
		console.setBounds(12, 12, 1040, 457);
		console.setEditable(false);
		contentPane.add(console);
		
		playerInfo = new JTextArea();
		playerInfo.setBorder(new LineBorder(Color.GREEN));
		playerInfo.setBackground(Color.BLACK);
		playerInfo.setForeground(Color.GREEN);
		playerInfo.setBounds(1062, 45, 190, 103);
		playerInfo.setEditable(false);
		contentPane.add(playerInfo);
		
		playerPane = new JTextField();
		playerPane.setBorder(null);
		playerPane.setBackground(Color.BLACK);
		playerPane.setForeground(Color.GREEN);
		playerPane.setBounds(1062, 24, 190, 20);
		playerPane.setEditable(false);
		contentPane.add(playerPane);
		playerPane.setColumns(10);
		
		weaponPane = new JTextField();
		weaponPane.setBorder(null);
		weaponPane.setBackground(Color.BLACK);
		weaponPane.setForeground(Color.GREEN);
		weaponPane.setBounds(1062, 170, 190, 20);
		weaponPane.setEditable(false);
		weaponPane.setColumns(10);
		contentPane.add(weaponPane);
		
		weaponInfo = new JTextArea();
		weaponInfo.setBorder(new LineBorder(Color.GREEN));
		weaponInfo.setBackground(Color.BLACK);
		weaponInfo.setForeground(Color.GREEN);
		weaponInfo.setBounds(1062, 190, 190, 129);
		weaponInfo.setEditable(false);
		contentPane.add(weaponInfo);
		
		userInput = new JTextField();
		userInput.setBorder(new MatteBorder(3, 3, 0, 0, (Color) new Color(0, 255, 0)));
		userInput.setBackground(Color.BLACK);
		userInput.setForeground(Color.GREEN);
		userInput.setBounds(10, 522, 694, 28);
		contentPane.add(userInput);
		userInput.setColumns(10);
		userInput.requestFocusInWindow();
		
		MonsterPane = new JTextField();
		MonsterPane.setBorder(null);
		MonsterPane.setBackground(Color.BLACK);
		MonsterPane.setForeground(Color.GREEN);
		MonsterPane.setEditable(false);
		MonsterPane.setColumns(10);
		MonsterPane.setBounds(1062, 340, 190, 20);
		contentPane.add(MonsterPane);
		
		monsterInfo = new JTextArea();
		monsterInfo.setBorder(new LineBorder(Color.GREEN));
		monsterInfo.setBackground(Color.BLACK);
		monsterInfo.setForeground(Color.GREEN);
		monsterInfo.setEditable(false);
		monsterInfo.setBounds(1062, 360, 190, 109);
		contentPane.add(monsterInfo);
		
		pouchInfo = new JTextArea();
		pouchInfo.setBorder(new LineBorder(Color.GREEN));
		pouchInfo.setBackground(Color.BLACK);
		pouchInfo.setForeground(Color.GREEN);
		pouchInfo.setEditable(false);
		pouchInfo.setBounds(714, 480, 538, 70);
		contentPane.add(pouchInfo);
		
		droppedItemDisplay = new JTextArea();
		droppedItemDisplay.setBackground(Color.BLACK);
		droppedItemDisplay.setForeground(Color.GREEN);
		droppedItemDisplay.setEditable(false);
		droppedItemDisplay.setBounds(12, 483, 692, 28);
		contentPane.add(droppedItemDisplay);
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
	private void mainTextCropper() {
		int lineCount = console.getLineCount();
		
		if (lineCount >= 28) {
			String temp = "";
			Scanner textDelete = new Scanner(console.getText());
			textDelete.useDelimiter("\\s*>>\\s*");
			
			for (int i = 0; i < lineCount - 27; i++) {
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
	 * trims the whitespace of the input string and prints your command to the console, then calls the game's command method
	 * 
	 * @throws FileNotFoundException
	 */
	private void textControl() throws FileNotFoundException {
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
	
}
