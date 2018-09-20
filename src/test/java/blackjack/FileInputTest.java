package blackjack;
import junit.framework.TestCase;
public class FileInputTest extends TestCase  {
	public void testplayFromFile() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards S10 and D3\n" +
				"dealer receives cards CQ and C5\n" + 
				"player hits and gets H5\n" + 
				"player hits and gets SA\n" + 
				"player stands\n" + 
				"dealer hits and gets CA\n" + 
				"dealer hits and gets D2\n" +
				"dealer stands\n" +
				"player has 19pts\n"+
				"dealer has 18pts\n"+
				"Winner is player by points";
		String test = solver.fileInput("PlayerByPoints.xml");
		
		assertEquals(results, test);
	}
	public void testFileDealerPoints() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards CK and C5\n" +
				"dealer receives cards CJ and SQ\n" + 
				"player hits and gets D3\n" + 
				"player stands\n" + 
				"dealer stands\n" +
				"player has 18pts\n"+
				"dealer has 20pts\n"+
				"Winner is dealer by points";
		String test = solver.fileInput("DealerPoints.txt");
		assertEquals(results, test);
	}
	
	public void testFileBothBlackJack() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards CK and DA\n" +
				"dealer receives cards DJ and HA\n" +
				"player has 21pts\n"+
				"dealer has 21pts\n"+
				"Winner is dealer by blackjack";
		String test = solver.fileInput("BothBlackJack.txt");
		assertEquals(results, test);
	}
	
	public void testFileDealerBlackJack() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards D4 and D10\n" +
				"dealer receives cards CJ and CA\n" +
				"player has 14pts\n"+
				"dealer has 21pts\n"+
				"Winner is dealer by blackjack";
		String test = solver.fileInput("DealerBlackJack.txt");
		assertEquals(results, test);
	}
	public void testFilePlayerBlackJack() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards CJ and CA\n" +
				"dealer receives cards D4 and D10\n" +
				"player has 21pts\n"+
				"dealer has 14pts\n"+
				"Winner is player by blackjack";
		String test = solver.fileInput("PlayerBlackJack.txt");
		assertEquals(results, test);
	}
	
	public void testFilePlayerBust() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards C8 and C10\n" +
				"dealer receives cards C4 and C7\n" +
				"player hits and gets D10\n" + 
				"player has 28pts\n"+
				"dealer has 11pts\n"+
				"Winner is dealer by bust";
		String test = solver.fileInput("PlayerBust.txt");
		assertEquals(results, test);
	}
	public void testFileDealerBust() {
		Game solver = new Game();
		//String fileName = "resources/PlayerByPoints.xml";
		String results = "player receives cards C9 and S10\n" +
				"dealer receives cards HK and C5\n" +
				"player stands\n" + 
				"dealer hits and gets D8\n" + 
				"player has 19pts\n"+
				"dealer has 23pts\n"+
				"Winner is player by bust";
		String test = solver.fileInput("DealerBust.txt");
		assertEquals(results, test);
	}
	
}
