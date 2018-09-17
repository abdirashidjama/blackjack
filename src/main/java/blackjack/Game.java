package blackjack;

import java.util.List;
import java.util.Stack;

public class Game {
	private Stack<String> deck;
	private List<String> playerhand;
	private List<String> dealerhand;
	private String currentHand;
	
	public void setDeck(Stack d) {deck = d; };
	public void setHand(String h) {
		if(h == "p") {
			playerhand.add(deck.pop());
			playerhand.add(deck.pop());
		}
		else {
			playerhand.add(deck.pop());
			playerhand.add(deck.pop());
		}
	}
	public void hit() {
		if(currentHand == "p") {
			playerhand.add(deck.pop());
		}
		else {
			dealerhand.add(deck.pop());
		}
	}
}
