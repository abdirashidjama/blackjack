package blackjack;

import junit.framework.TestCase;
import java.util.*;


public class CardCompareTest extends TestCase {
	public void testCheckBust() {
		Game solver = new Game();
		ArrayList<String> cards = new ArrayList<String>() {{
			add("H9");
			add("AK");
		}};
		Stack deck = new Stack();
		deck.push("S6");
		solver.setDeck(deck);
		solver.hit();
		assertEquals(null, solver.handValue()); // bust hands are null
	}
	
	public void testBetterHand() { //sees if dealer has a better hand then player
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("H8");
		deck.push("A8");
		deck.push("S2");
		deck.push("H9");
		deck.push("AK");
		solver.setDeck(deck);
		solver.hit();
		solver.hit();
		solver.hit();
		solver.stand();  //player hits thrice has a total of 18
		assertEquals(18, solver.handValue()); //test to see if player hand held and is 18
		solver.hit();
		solver.hit();
		asserEquals(true, solver.betterhand()); //dealer hand is 19 test to see if better hand function shows dealer is equal or higher. 
		
	}
	
	public void testHandValue() {
		Game solver = new Game();
		ArrayList<String> cards = new ArrayList<String>() {{
			add("H9");
			add("AK");
		}};
		Stack deck = new Stack();
		deck.push("H9");
		deck.push("AK");
		solver.setDeck(deck);
		solver.hit();
		solver.hit();
		assertEquals(19,solver.handValue(cards)); //regular hand test
	}
	
	public void test52cards(){
		Game solver = new Game();
		assertEquals(52, solver.getDeck().size()); //testing list to make sure it has 52 cards
	}
	
	public void testcurrentTurn(){ //test if stand function changes whose turn it is
		Game solver = new Game();
		assertEquals("player", solver.getcurrentTurn());  //initially should be player
		Stack deck = new Stack();
		deck.push("H8");
		solver.setDeck(deck);
		solver.hit();
		solver.stand()
		assertEquals("dealer", solver.getcurrentTurn());  // should change to dealer after hold
	}
	

}
