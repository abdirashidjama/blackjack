package blackjack;

import junit.framework.TestCase;
import java.util.*;


public class CardCompareTest extends TestCase {
	public void testCheckBust() {
		Game solver = new Game();
		ArrayList<String> cards = new ArrayList<String>() {{
			add("H9");
			add("AK");
			add("S6");
				
		}};
		assertEquals(true, solver.checkBust(cards));
	}
	
	public void testBetterHand() {
		Game solver = new Game();
		ArrayList<String> playerhand = new ArrayList<String>() {{
			add("H8");
			add("AK");
		}};
		ArrayList<String> dealerhand = new ArrayList<String>() {{
			add("D9");
			add("HJ");
		}};
		assertEquals(true, solver.betterHand(playerhand, dealerhand));
	}

}
