package launcher;


public class Carta {
	
	private char suit;
	private int value;
	
	public Carta(char suit, int val) {
		if (val == 'A') value = 14;
		else if (val == 'K') value = 13;
		else if (val == 'Q') value = 12;
		else if (val == 'J') value = 11;
		else if (val == 'T') value = 10;
		else value = val - 48;
		
		this.suit = suit;
		
	}
	
	public int getValue() {
		return value;
	}
	
	public char getSuit() {
		return suit;
	}

}