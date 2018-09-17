package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
	private Stack<String> deck = new Stack<String>();
	private List<String> playerHand = new ArrayList<String>() ;
	private List<String> dealerHand = new ArrayList<String>();
	private String currentHand;
	
	public void setDeck(Stack<String> d) {deck = d; };
	public void setHand(String h) {
		if(h == "p") {
			playerHand.add(deck.pop());
			playerHand.add(deck.pop());
		}
		else {
			playerHand.add(deck.pop());
			playerHand.add(deck.pop());
		}
	}
	public void hit() {
		if(currentHand == "p") {
			playerHand.add(deck.pop());
		}
		else {
			dealerHand.add(deck.pop());
		}
	}
	public String displayPlayerHand() {
		String hand="";
		for(int i = 0; i< playerHand.size(); i++ ) {
			if(i==playerHand.size()-1) {
				hand= hand + playerHand.get(i);
			}
			else { hand= hand + playerHand.get(i) + " ";}
		}
		return hand;
	}
	
	public String displayDealerHand() {
		String hand="";
		for(int i = 0; i< dealerHand.size(); i++ ) {
			if(i==dealerHand.size()-1) {
				hand= hand + dealerHand.get(i);
			}
			else { hand= hand + dealerHand.get(i) + " ";}
		}
		return hand;
	}
}
