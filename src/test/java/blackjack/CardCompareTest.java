package blackjack;

import junit.framework.TestCase;


public class CardCompareTest extends TestCase {
	public void testCheckBust() {
		Game solver = new Game();
		String[] cards =  {"H9","AK", "S6"} 
		assertEquals(true, solver.checkBust(cards));
	}

}
