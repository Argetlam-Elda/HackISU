package textGame;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import java.awt.Font;
//import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
//import java.awt.event.WindowEvent;
import java.util.Scanner;
//import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements KeyListener {

	private JPanel contentPane;

	// Main text screen, the huge one you retard
	public JTextArea textArea;

	// Top info, top screen
	public JTextField textField;

	// Bottom info, top screen
	public JTextField textField_1;

	// User input
	public JTextField userInput;

	// Top info, bottom screen
	public JTextArea textArea_1;

	// Bottom info, bottom screen
	public JTextArea textArea_2;

	/**
	 * top middle info screen
	 */
	public JTextField textField_2;

	/**
	 * bottom middle info screen
	 */
	public JTextArea textArea_3;

	/**
	 * stores last command for conveniece
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
				} catch (Exception e) {
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				previousCommand = userInput.getText();
				textControl();
			} catch (FileNotFoundException e) {
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

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void resizeControl() {

	}

	public void MainTextCount() {
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

	public void textControl() throws FileNotFoundException {
		String enteredText;
		enteredText = userInput.getText().trim();

		Scanner scan = new Scanner(enteredText);
		if ((enteredText.length() != 0 && scan.hasNext())) {
			userInput.setText("");
			textArea.setText(textArea.getText() + ">>" + enteredText + "\n");
		}
		Main.SetVariables(enteredText);
		MainTextCount();
		Main.text = enteredText;
		// System.out.println("Main's text string is:" + Main.text);
		scan.close();
	}

	public void GetPreviousCommand() {
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
