package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Game {
	private Stack<String> deck = new Stack<String>();
	private List<String> playerHand = new ArrayList<String>() ;
	private List<String> dealerHand = new ArrayList<String>();
	private String currentHand;
	
	public String start(String mode) {
		if(mode == "c") {  //generates cards calls method to deal with player console input
			String suite = null;
			String value;
			String card;
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
		}
		else {
			
		}
		return mode;
	}
	public Stack<String> getDeck() {return deck;}
	public Stack<String> shuffleDeck(Stack<String> d) { 
		List<String> de = new ArrayList<String>(d);
		Collections.shuffle(de, new Random());
		Stack<String> deck = new Stack<String>();
		deck.addAll(d);
		return deck;
		
	}
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
