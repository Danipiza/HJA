package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import view.MainWindow;


public class Controller {
	
	private String input;
	
	private Player me;
	private List<Player> players;
	
	private int part;
	
	private String in;
	MainWindow window;

	
	public Controller() {
		input = "";
		me = new Player();
		players = new ArrayList<Player>();
		part = 0;
	}
		
	public void loadDeck1(BufferedReader in) throws Exception {		
		char value, suit = ' '; 
		for (int i = 0; i < 5; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			me.addCard(c);
		}
	}
	
	public String run1() {
		me.CardsValue();
		String output = input + '\n';
		output += " - Best hand: " + me.getHandName() + " with " + me.getBestHand() + '\n';
		
		if (me.getDrawStraight() == 1 && me.getMaxValue() < 5)
			output += " - Draw: Straight Gutshot" + '\n';
		else if (me.getDrawStraight() == 2 && me.getMaxValue() < 5)
			output += " - Draw: Open-ended Straight" + '\n';	
		if (me.getDrawFlush() && me.getMaxValue() < 6)
			output += " - Draw: Flush" + '\n';
	
		input = "";
		me.reset();
		
		return output;
	}
	
	
	public void loadDeck2(BufferedReader in) throws Exception {
		int commonCards;
		char value, suit = ' ';
		for (int i = 0; i < 2; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			me.addCard(c);
		}
		in.read(); commonCards = in.read(); in.read();
		input += ';'; input += (char) commonCards; input += ';';
		commonCards -= 48;
		for (int i = 0; i < commonCards; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			me.addCard(c);
		}
	}
	
	public String run2() {
		me.CardsValue();
		String output = input + '\n';
		output += " - Best hand: " + me.getHandName() + " with " + me.getBestHand() + '\n';
		
		if (me.getCardsSize() < 7) {
			if (me.getDrawStraight() == 1 && me.getMaxValue() < 5)
				output += " - Draw: Straight Gutshot" + '\n';
			else if (me.getDrawStraight() == 2 && me.getMaxValue() < 5)
				output += " - Draw: Open-ended Straight" + '\n';	
			if (me.getDrawFlush() && me.getMaxValue() < 6)
				output += " - Draw: Flush" + '\n';
		}
		
		input = "";
		me.reset();
		
		return output;
	}
	
	
	public void loadDeck3(BufferedReader in) throws Exception {
		players.clear();
		int numPlayers;
		char value, suit = ' '; 
		numPlayers = in.read();
		input += (char) numPlayers;
		numPlayers -= 48;
		for (int i = 0; i < numPlayers; i++) {
			Player newPlayer = new Player();
			input +=  (char) in.read(); input += (char) in.read(); input += (char) in.read();
			for (int j = 0; j < 2; j++) {
				value = (char) in.read();
				suit = (char) in.read();
				input += value;
				input += suit;
				Card c = new Card(suit, value);
				newPlayer.addCard(c);
			}
			newPlayer.setPlayerNum(i + 1);
			players.add(newPlayer);
		}
		input +=  (char) in.read();
		List<Card> commonCards = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			commonCards.add(c);
		}
		
		for (Player player : players) {
			player.addCards(commonCards);
		}
		
	}
	
	public String run3() throws FileNotFoundException {
		for (Player p : players) {
			p.CardsValue();
		}
		playerSorting(players);
		
		String output = input + '\n';
		for (Player p : players) {
			output += "J" + p.getPlayerNum() + ": " + p.getBestHand() + " (" + p.getHandName() + ")" + '\n';
		}
		
		input = "";
		
		return output;
	}	
	public void loadDeck4(BufferedReader in) throws Exception {
		int commonCardsNumber;
		char value, suit = ' ';
		for (int i = 0; i < 4; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			me.addCard(c);
		}
		in.read(); commonCardsNumber = in.read(); in.read();
		input += ';'; input += (char) commonCardsNumber; input += ';';
		commonCardsNumber -= 48;
		
		for (int i = 0; i < commonCardsNumber; i++) {
			value = (char) in.read();
			suit = (char) in.read();
			input += value;
			input += suit;
			Card c = new Card(suit, value);
			me.addCommonOmahaCard(c);
		}
		
	}
	
	public String run4() throws FileNotFoundException {
		me.OmahaCardsValue();
		String output = input + '\n';
		output += " - Best hand: " + me.getHandName() + " with " + me.getBestHand() + '\n';
		
		if (me.getDrawStraight() == 1 && me.getMaxValue() < 5)
			output += " - Draw: Straight Gutshot" + '\n';
		else if (me.getDrawStraight() == 2 && me.getMaxValue() < 5)
			output += " - Draw: Open-ended Straight" + '\n';	
		if (me.getDrawFlush() && me.getMaxValue() < 6)
			output += " - Draw: Flush" + '\n';
				
		return output;
	}
	
	private void playerSorting(List<Player> playerList) {
		Collections.sort(playerList, new SortPlayers());
	}
	class SortPlayers implements Comparator<Player>{
		@Override
		public int compare(Player p1, Player p2) {
			int ret = 0;
			if (p1.getMaxValue() > p2.getMaxValue()) ret = -1;
			else if (p1.getMaxValue() < p2.getMaxValue()) ret = 1;
			else {
				if (p1.getTieBreaker1() > p2.getTieBreaker1()) ret = -1;
				else if (p1.getTieBreaker1() < p2.getTieBreaker1()) ret = 1;
				else {
					if (p1.getTieBreaker2() > p2.getTieBreaker2()) ret = -1;
					else if (p1.getTieBreaker2() < p2.getTieBreaker2()) ret = 1;
					else {
						int i = 0;
						while (i < 5 && ret == 0) {
							if (p1.symbolValue(p1.getBestHand().charAt(2*i)) > 
							p2.symbolValue(p2.getBestHand().charAt(2*i))) ret = -1;
							else if (p1.symbolValue(p1.getBestHand().charAt(2*i)) < 
							p2.symbolValue(p2.getBestHand().charAt(2*i))) ret = 1;
							i++;
						}
					}
				}
			}	
			return ret;
		}
	}
	
	
	public int getPart() {
		return part;
	}
	
	public void setPart(int x) {
		this.part = x;
	}
	
	public String getIn() {
		return in;
	}
	
	public void setIn(String x) {
		this.in = x;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	
	public void loadDecks() throws Exception {
		BufferedReader br;
		
		br = new BufferedReader(new FileReader(new File(this.in+".txt")));
		
		// SE CARGA EL MAZO
		if(part == 1) {
			loadDeck1(br); 
			
		} else if(part == 2) {
			loadDeck2(br); 
			
		} else if(part == 3) {
			loadDeck3(br); 
			
		} else if(part == 4) {
			loadDeck4(br); 
			
		}
		
		
	
	}
	
	public void resetRun3() {
		players.clear();
	}
	
	public void setWindow(MainWindow w) {
		window = w;
	}
	public void setText(String info) {
		window.setText(info);
	}
		
}




