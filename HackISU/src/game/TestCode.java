package game;

import java.io.FileNotFoundException;

public class TestCode {
	public static void main(String[] args) throws FileNotFoundException {
		World test = new World("worldTest.txt");
		String text = test.grid[0][0].getFlavor();
		System.out.println(text);
	}
}
