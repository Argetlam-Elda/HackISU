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
 * @author Colt Rogness, Brandon Elizando
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
	public JTextArea textArea;
	
	/**
	 * Top of top info screen
	 */
	public JTextField textField;
	
	/**
	 * Bottom of top info screen
	 */
	public JTextField textField_1;
	
	/**
	 * User input bar
	 */
	public JTextField userInput;
	
	/**
	 * Top of bottom info screen
	 */
	public JTextArea textArea_1;
	
	/**
	 * Bottom of bottom info screen
	 */
	public JTextArea textArea_2;
	
	/**
	 * top of middle info screen
	 */
	public JTextField textField_2;
	
	/**
	 * bottom of middle info screen
	 */
	public JTextArea textArea_3;
	
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
	public TestFrame () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 12, 1040, 367);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(1062, 45, 112, 90);
		textArea_1.setEnabled(false);
		textArea_1.setEditable(true);
		contentPane.add(textArea_1);
		
		textField = new JTextField();
		textField.setBounds(1062, 14, 112, 20);
		textField.setEnabled(false);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(1062, 146, 112, 20);
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(1062, 177, 112, 80);
		textArea_2.setEnabled(false);
		textArea_2.setEditable(false);
		contentPane.add(textArea_2);
		
		userInput = new JTextField();
		userInput.setBounds(10, 522, 694, 28);
		contentPane.add(userInput);
		userInput.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(1062, 268, 112, 20);
		contentPane.add(textField_2);
		
		textArea_3 = new JTextArea();
		textArea_3.setEnabled(false);
		textArea_3.setEditable(false);
		textArea_3.setBounds(1062, 299, 112, 80);
		contentPane.add(textArea_3);
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
		int lineCount = textArea.getLineCount();
		
		if (lineCount >= 22) {
			String temp = "";
			Scanner textDelete = new Scanner(textArea.getText());
			textDelete.useDelimiter("\\s*>>\\s*");
			
			for (int i = 0; i < 12; i++) {
				textDelete.next();
			}
			while (textDelete.hasNext()) {
				temp += ">>" + textDelete.next() + "\n";
			}
			textArea.setText(temp);
			textDelete.close();
		}
	}
	
	/**
	 * trims the whitespace of the input string and prints your command to the console, then calls the game's command method
	 * @throws FileNotFoundException
	 */
	public void textControl() throws FileNotFoundException {
		String enteredText;
		enteredText = userInput.getText().trim();
		
		Scanner scan = new Scanner(enteredText);
		if ((enteredText.length() != 0 && scan.hasNext())) {
			userInput.setText("");
			textArea.setText(textArea.getText() + ">>" + enteredText + "\n");
		}
		Main.gameCommand(enteredText);
		mainTextCropper();
		scan.close();
	}
	
	/**
	 * this method runs when you hit the up arrow so you can reuse your last command
	 */
	public void getPreviousCommand() {
		Scanner textDelete = new Scanner(textArea.getText());
		textDelete.useDelimiter("\\s*>>\\s*");
		
		String temp = "";
		
		for (int i = 0; i < textArea.getLineCount() - 2; i++) {
			textDelete.next();
			
		}
		while (textDelete.hasNext()) {
			temp = textDelete.next();
		}
		previousCommand = temp;
		textDelete.close();
	}
	
}
