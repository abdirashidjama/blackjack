package blackjack;

import junit.framework.TestCase;
import java.util.*;

/*
 * For the purpose of testing and file input options deck can be set and hiting aswell as
 * deck settin
 */
public class CardCompareTest extends TestCase {
	public void testConsoleInput() {
		Game solver = new Game();
		solver.start('c');
	}
	
	public void testFileInput() {
		Game solver = new Game();
		assertFalse(null,solver.start('f'));
	}
	
	public void testInitPlayerDisplay() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setCards('p');
		assertEquals("A5 A4", solver.displayPlayerHand());
	}
	
	public void testInitDealerDisplay() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setCards("p");
		solver.setCards("d");
		assertEquals("A3 ?", solver.displayDealerHand());
	}
	
	public void testPlayerEndTurnDisplay(){
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setCards('p');
		solver.hit();
		solver.stand();
		assertEquals("A5 A4 A3", solver.displayPlayerHand());
	}
	
	public void testEndTurnDealerDisplay() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S2");
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setCards("p");
		solver.setCards("d");
		solver.hit();
		solver.stand();
		assertEquals("A3 A2 S2", solver.displayDealerHand());
	}
	
	public void testhit() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("H7");
		solver.setDeck(deck);
		solver.hit();
		assertEquals(7, solver.handValue());	
	}
	public void testmultihit() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setDeck(deck);
		solver.hit();
		solver.hit();
		solver.hit();
		solver.hit();
		assertEquals(14, solver.handValue());	
	}
	public void testdealerMultihit(){
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setDeck(deck);
		solver.stand();
		solver.hit();
		solver.hit();
		solver.hit();
		solver.hit();
		assertEquals(14, solver.handValue());	
	}
	
	public void teststand() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("A2");
		deck.push("A3");
		deck.push("A4");
		deck.push("A5");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		solver.stand();
		assertEquals("dealer", solver.getcurrentTurn()); //turn should change to dealer after stand
	}
	
	public void test52cards(){
		Game solver = new Game();
		solver.start('c');
		assertEquals(52, solver.getDeck().size()); //testing list to make sure it has 52 cards
	}
	
	public void testshuffle() {
		Game solver = new Game();
		String suite = null;
		String value;
		String card;
		Stack deck = new Stack();
		for (int i=0; i<=3; i++) {
			switch(i) {
			case 0: suite = "H";
			break;
			case 1: suite = "S";
			break;
			case 2: suite = "D";
			break;
			case 3: suite = "C";
			break;
			}
			for (int j=1; j<=13; j++) {
				switch(j) {
				case 1: value = "A";
				case 11: value = "J";
				break;
				case 12: value = "Q";
				break;
				case 13: value = "K";
				default: value = String.valueOf(j);
				break;
				}
				card = suite + value;
				deck.push(card);
			}
		}
		assertEquals(deck, solver.shuffle(deck));	
	}
	
	public void testPlayerBust() {
		Game solver = new Game();
		ArrayList<String> cards = new ArrayList<String>() {{
			add("H9");
			add("DK");
		}};
		
		Stack deck = new Stack();
		deck.push("S6");
		deck.push("H9");
		deck.push("DK");
		deck.push("CK");
		deck.push("S9");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		solver.hit();
		//assertEquals(null, solver.handValue()); // bust hands are null
		assertEquals("dealer", solver.checkWin());
	}
	
	public void testDealerBust() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S6");
		deck.push("H9");
		deck.push("DK");
		deck.push("DK");
		deck.push("S9");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		solver.stand();
		solver.hit();
		//assertEquals(null, solver.handValue()); // bust hands are null
		assertEquals("player", solver.checkWin());
	}
	
	public void testDealerUnder16() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S3");
		deck.push("H2");
		deck.push("S4");
		deck.push("HJ");
		deck.push("DK");
		deck.push("S9");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		solver.stand();
		solver.dealerPlay();
		solver.dealerPlay();
		solver.stand();
		assertEquals("HJ S4 H2 S3", solver.displayDealerHand());
	}
	
	public void testDealerSoft17() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("H2");
		deck.push("HA");
		deck.push("S6");
		deck.push("HJ");
		deck.push("CK");
		deck.push("S9");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		solver.stand();
		solver.hit();
		solver.dealerPlay();
		solver.stand();
		assertEquals("HJ S6 HA H2", solver.displayDealerHand());
	}
	
	public void testPlayerBlackJack() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("H2");
		deck.push("S7");
		deck.push("SA");
		deck.push("HK");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		assertEquals("player blackjack", solver.checkWin());
	}
	
	public void testDealerBlackJack() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("SA");
		deck.push("HK");
		deck.push("H2");
		deck.push("S7");
		solver.setDeck(deck);
		solver.setHands('p');
		solver.setHands('d');
		assertEquals("dealer blackjack", solver.checkWin());
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
	
	public void testPlayerScore() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("C8");
		deck.push("S10");
		deck.push("C2");
		deck.push("H9");
		deck.push("A5");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		solver.hit();
		assertEquals(16,solver.getPlayerScore());
	}
	
	public void testDealerScore() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S2");
		deck.push("C8");
		deck.push("S10");
		deck.push("H9");
		deck.push("S5");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		solver.stand();
		solver.hit();
		assertEquals(20,solver.getDealerScore()); 
	}
	
	public void testAceas11() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S2");
		deck.push("C8");
		deck.push("S10");
		deck.push("S7");
		deck.push("HA");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		assertEquals(18,solver.getPlayerScore()); //ace must be 11 to get 18
	}
	
	public void testAceas1() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("SA");
		deck.push("C7");
		deck.push("S10");
		deck.push("S10");
		deck.push("H8");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		solver.hit();
		assertEquals(19,solver.getPlayerScore()); //ace must be one to get 19 otherwise bust 
	}
	
	public void testAceas11then1() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("S7");
		deck.push("C7");
		deck.push("S10");
		deck.push("S8");
		deck.push("HA");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		solver.hit();
		assertEquals(16,solver.getPlayerScore()); //score should be 16 so theres no bust A goes 11 then 1
	}
	
	public void testAceas1and1(){
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("CA");
		deck.push("SA");
		deck.push("C7");
		deck.push("S10");
		deck.push("S10");
		deck.push("H8");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		solver.hit();
		assertEquals(20,solver.getPlayerScore()); //10 +8 +1 ace +1 ace
	}
	
	public void testAceasboth11and1() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("C3");
		deck.push("S5");
		deck.push("SA");
		deck.push("HA");
		solver.setDeck(deck);
		solver.setHands("p");
		solver.sethands("d");
		assertEquals(12,solver.getPlayerScore());
	}
	
	public void testJQKareTen() {
		Game solver = new Game();
		Stack deck = new Stack();
		deck.push("AJ");
		deck.push("HK");
		deck.push("SQ");
		solver.setDeck(deck);
		solver.hit();
		assertEquals(10, solver.getPlayerScore()); //if player is 10 then Q is worth 10
		solver.hit();
		assertEquals(20, solver.getPlayerScore()); //additional 10 points should be added from K
		solver.hold();
		solver.hit();
		assertEquals(10, solver.getPlayerScore()); //should be 10 from J
	}
	
	public void testcurrentTurn(){ //test if stand function changes whose turn it is
		Game solver = new Game();
		assertEquals("player", solver.getcurrentTurn());  //initially should be player
		Stack deck = new Stack();
		deck.push("H8");
		solver.setDeck(deck);
		solver.hit();
		solver.stand();
		assertEquals("dealer", solver.getcurrentTurn());  // should change to dealer after hold
	
	}

}
