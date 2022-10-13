package launcher;


public class Card {
	
	private String name;
	private char symbol;
	private int value;
	private char suit;

	
	public Card(char suit, char val) {
		this.name = "";
		this.symbol = val;
		if (val == 'A') {
			value = 14;
			name = "Ace";
		}
		else if (val == 'K') {
			value = 13;
			name = "King";
		}
		else if (val == 'Q') {
			value = 12;
			name = "Queen";
		}
		else if (val == 'J') {
			value = 11;
			name = "Jack";
		}
		else if (val == 'T') {
			value = 10;
			name = "10";
		}
		else {
			value = val - 48;
			name += val;
		}
		
		this.suit = suit;
		
	}
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getValue() {
		return value;
	}
	
	public char getSuit() {
		return suit;
	}
	
}