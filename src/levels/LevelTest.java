package levels;

import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

public class LevelTest {

	public static void main(String[] args) {
		try {
			Level level = new Level("Resources/LevelFiles/custom.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] colsInRow = new int[32];
		Arrays.fill(colsInRow, 32);
		System.out.println(colsInRow);

		for(int i = 0; i < colsInRow.length; i++){
			System.out.println(colsInRow[i]);
		}

	}

}
