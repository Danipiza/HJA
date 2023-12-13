package logic;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	
	private String name;
	private char symbolN;
	private String suit;
	private char symbolS;
	private int value;
	private boolean realCard;

	
	public Card(String cN, char sym, String sN, char s, int v) {
		if(v == -1) {
			realCard = false;
		} else {
			realCard = true;
			this.name = cN;
			this.symbolN = Character.toUpperCase(sym);
			if (symbolN == '1') symbolN = 'T';
			this.suit = sN;
			this.symbolS = s;
			this.value = v;
		}
		
	}
	
	public boolean getRealCard() {
		return realCard;
	}
	
	public String getName() {
		return name;
	}

	public char getSymbolN() {
		return symbolN;
	}

	public String getSuit() {
		return suit;
	}

	public char getSymbolS() {
		return symbolS;
	}

	public int getValue() {
		return value;
	}
	
	public boolean sameCard(char symbolNAux, char symbolSAux) {
		if(symbolNAux == this.symbolN && symbolSAux == this.symbolS)
			return true;
		return false;
	}


	public String toString() {
		return "Value: " + value + "; Suit: " + suit;
	}
	
	public JLabel toImage() {
		return new JLabel(new ImageIcon("resources/icons/" + name + "_of_" + suit + ".png"));
	}

}
