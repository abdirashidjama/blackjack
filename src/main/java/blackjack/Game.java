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
	private int playerScore;
	private int dealerScore;
	private String winner;
	private String condition;
	
	public Game() {  //start of game being called
		this.currentHand="p";  //its players turns
		this.playerScore = 0;
		this.dealerScore = 0;
		this.winner = "n/a";
		this.condition = "";
	}
	
	public String start(String mode) {
		currentHand = "p"; //its player turn first
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
					break;
					case 11: value = "J";
					break;
					case 12: value = "Q";
					break;
					case 13: value = "K";
					break;
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
	public String getCondition() {return condition;}
	public String getCurrentTurn() {return currentHand;}
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
			this.getPlayerScore();
		}
		else {
			dealerHand.add(deck.pop());
			dealerHand.add(deck.pop());
			this.getDealerScore();
		}
	}
	public void hit() {
		if(currentHand == "p") {
			playerHand.add(deck.pop());
			this.getPlayerScore();
		}
		else if(currentHand == "d"){
			dealerHand.add(deck.pop());
			this.getDealerScore();
		}
	
		this.checkWin();
	}
	
	public void stand() {
		if(currentHand=="p") {
			currentHand = "d"; //its now dealers turn
		}
		
		else {
			currentHand = "game over";
			this.getPlayerScore();
			this.checkWin();
		}
	}
	
	public int getPlayerScore() {
		int score = 0;
		int aces = 0;
		for(int i = 0; i< playerHand.size(); i++) {
			String card = playerHand.get(i).substring(1);
			switch(card) {  //changed project to java compliance 1.7 for this to work
			case "A": aces=aces +1;
			break;
			case "J": score=score+10;
			break;
			case "Q": score=score+10;
			break;
			case "K": score=score+10;
			break;
			default: score = score + Integer.valueOf(card);
			break;
			}
			
		}
		
		for(int i = 0; i< aces; i++) {
			if(score + 11 + aces-1 <= 21) {
				score = score + 11;
			}
			else{
				score = score + 1;
			}
		}
		this.playerScore= score;
		return score;
	}
	
	public int getDealerScore() {
		int score = 0;
		int aces = 0;
		for(int i = 0; i< dealerHand.size(); i++) {
			String card = dealerHand.get(i).substring(1);
			switch(card) {  //changed project to java compliance 1.7 for this to work
			case "A": aces++;
			break;
			case "J": score=score+10;
			break;
			case "Q": score=score+10;
			break;
			case "K": score=score+10;
			break;
			default: score = score + Integer.valueOf(card);
			break;
			}
			
		}
		
		for(int i = 0; i< aces; i++) {
			if(score + 11 + aces-1 <= 21) {
				score = score + 11;
			}
			else{
				score = score + 1;
			}
		}
		this.dealerScore = score;
		return score;
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
				if(i==0 || currentHand == "game over" ) {
					hand = hand + dealerHand.get(i);
				}
				else {
					hand = hand + "?";
				}
			}
			else { 
				hand= hand + dealerHand.get(i) + " ";
				}
		}
		return hand;
	}
	public void dealerPlay() {
		boolean aces = false; //to detect if there is aces
		this.displayDealerHand();
		if(dealerHand.contains("HA") || dealerHand.contains("SA") || dealerHand.contains("CA") || dealerHand.contains("DA")) {
				aces = true;
		}
		
		if(this.getDealerScore() <= 16) {
			this.hit();
		}
		else if(this.getDealerScore() == 17 && aces == true) {
			this.hit();
		}
		else {this.stand();}
	}
	public String checkWin(){  //sets winner and they condition they won by
		//blackjack win conditions
		String win = "n/a";
		String cond = "";
		if(dealerScore == 21) {
			win = "dealer";
			cond = "blackjack";
		}
		if(playerScore == 21) {
			win = "player";
			cond = "blackjack";
		}
		
		//a player bust winner condition
		if(playerScore > 21) {
			win = "dealer";
			cond = "bust";
		}
		
		if(dealerScore > 21) {
			win = "player";
			cond = "bust";
		}
		
		//game ended win condtion by points
		
		if(currentHand == "game over") { 
			if(playerScore > dealerScore) {
				win = "player";
				cond = "points";
			}
			else{
				win = "dealer";
				cond = "points";
			}
		}
		this.winner=win;
		this.condition = cond;
		return winner;
	}
}
