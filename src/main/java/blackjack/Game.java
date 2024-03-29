package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Game {
	private Stack<String> deck = new Stack<String>(); 
	private List<String> playerHand = new ArrayList<String>() ;
	private List<String> dealerHand = new ArrayList<String>();
	private String currentHand;
	private int playerScore;
	private int dealerScore;
	private String winner;
	private String condition;
	private Stack <String> allCards = new Stack<String>(); //stack that contains all possible cards
	
	public Game() {  //start of game being called
		this.currentHand="player";  //its players turns
		this.playerScore = 0;
		this.dealerScore = 0;
		this.winner = "none";
		this.condition = "";
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
				this.allCards.push(card);
			}
		}
	}
	
	public String start(String mode) {
		currentHand = "player"; //its player turn first
		if(mode.equals("c")) {  //generates cards calls method to deal with player console input
			deck = allCards;
			return "c";
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
		Collections.shuffle(de);
		Stack<String> deck = new Stack<String>();
		deck.addAll(de);
		return deck;
		
	}
	public void setDeck(Stack<String> d) {deck = d; };
	public void setHand(String h) {
		if(h == "player") {
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
		if(currentHand == "player") {
			playerHand.add(deck.pop());
			this.getPlayerScore();
		}
		else if(currentHand == "dealer"){
			dealerHand.add(deck.pop());
			this.getDealerScore();
		}
	
		this.checkWin();
	}
	
	public void stand() {
		if(currentHand=="player") {
			currentHand = "dealer"; //its now dealers turn
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
	public String dealerPlay() {
		String move = "";
		boolean aces = false; //to detect if there is aces
		this.displayDealerHand();
		if(dealerHand.contains("HA") || dealerHand.contains("SA") || dealerHand.contains("CA") || dealerHand.contains("DA")) {
				aces = true;
		}
		
		if(this.getDealerScore() <= 16) {
			this.hit();
			move ="dealer hits";
		}
		else if(this.getDealerScore() == 17 && aces == true) {
			this.hit();
			move = "dealer hits";
		}
		else {
			this.stand();
			move = "dealer stands";
			}
		return move;
	}
	public String checkWin(){  //sets winner and they condition they won by
		//blackjack win conditions
		String win = "none";
		String cond = "";
		if(dealerScore == 21) {
			this.currentHand = "game over";
			win = "dealer";
			cond = "blackjack";
		}
		else if(playerScore == 21) {
			this.currentHand = "game over";
			win = "player";
			cond = "blackjack";
		}
		
		//a player bust winner condition
		else if(playerScore > 21) {
			this.currentHand = "game over";
			win = "dealer";
			cond = "bust";
		}
		
		else if(dealerScore > 21) {
			this.currentHand = "game over";
			win = "player";
			cond = "bust";
		}
		
		//game ended win condition by points
		
		else if(currentHand == "game over" ) { 
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
	
	public void consoleMode() {
		Scanner br = new Scanner(System.in);
		Game model= new Game(); //start game model
		model.start("c");//starts game chooses mode sets up deck accordingly
		model.setDeck(model.shuffleDeck(model.getDeck())); //gets and shuffle deck
		while(model.checkWin().equals("none")) {
			model.setHand("player");       //setting player and dealer hands
			model.setHand("dealer");
			System.out.println("Playerhand:  " + model.displayPlayerHand());   //printing dealer hands
			System.out.println("Dealerhand:  " + model.displayDealerHand());   // printing player hands
			if(model.checkWin() != "none") {break;}
			while(model.getCurrentTurn().equals("player")){
				System.out.println("options: "
						+ "(h) hit "
						+ "(s) stand ");
				String choice = "";
				choice = br.nextLine();   //taking users choice
				
				if(choice.equals("h")) {
					model.hit();
					System.out.println("Playerhand:  " + model.displayPlayerHand());
					System.out.println("Dealerhand:  " + model.displayDealerHand());
					if(model.checkWin() != "none") {break;}    //breaks game if their is a winner
				}
				else if(choice.equals("s")) {
					model.stand();
					System.out.println("Playerhand:  " + model.displayPlayerHand());
					System.out.println("Dealerhand:  " + model.displayDealerHand());
					break;
				}
			}
			while(model.getCurrentTurn().equals("dealer") && model.checkWin() == "none"){
				String move = model.dealerPlay();
				System.out.println(move);
				System.out.println("Playerhand:  " + model.displayPlayerHand());
				System.out.println("Dealerhand:  " + model.displayDealerHand());
				if(move.equals("dealer stands")) {
					break;
				}
				if(model.checkWin() != "none") {break;}
			}
		}
		System.out.println("final Playerhand:  " + model.displayPlayerHand());
		System.out.println("final Dealerrhand:  " + model.displayDealerHand());
		System.out.println("Winner is " + model.checkWin() + " " + model.getCondition());
	}
	
	public String fileInput(String filename) {
		
		String results=null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		FileReader freader = null;
		BufferedReader breader = null;
		Stack <String> deck = new Stack<String>();
		try { 
			freader = new FileReader(file);
			breader = new BufferedReader(freader);
			String currentLine;
			String commandLine = breader.readLine();
			String[] commands = commandLine.split("\\s+");
			this.deck.push(commands[1]);
			this.deck.push(commands[0]);
			this.setHand("player");
			this.deck.push(commands[3]);
			this.deck.push(commands[2]);
			this.setHand("dealer");
			results = "player receives cards " + commands[0] + " and " + commands[1] + "\n";
			results += "dealer receives cards " + commands[2] + " and " + commands[3] + "\n";
			int i = 4;
			while(i < commands.length) {
				if(allCards.contains(commands[i])) {
					this.deck.push(commands[i]);
					results += this.getCurrentTurn() + " hits and gets " + commands[i] + "\n";
					this.hit();
				}
				else if(commands[i].equals("S")) {
					results += this.getCurrentTurn() + " stands" + "\n";
					this.stand();
				}
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		results += "player has "+ this.getPlayerScore() + "pts\n";
		results += "dealer has "+ this.getDealerScore() + "pts\n";
		results =results + ("Winner is " + this.checkWin() + " by " + this.getCondition());
		return results;
	}
	
	public static void main(String args[]) {
		Game model= new Game();
		Scanner br = new Scanner(System.in);
		System.out.println("Enter (f) for file input or (c) console input: ");
		String mode = null;
		mode = br.nextLine();
		if (mode.equals("c")) {
			model.consoleMode();
		}
		else if(mode.equals("f")) {
			System.out.println("Enter file name: \n");
			String fileName = br.nextLine();
			System.out.println(model.fileInput(fileName));
			br.close();
		}
		else {}
	}
	
}
