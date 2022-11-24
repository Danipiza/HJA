package logic;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	
	private String name;
	private char symbolN;
	private String suit;
	private char symbolS;
	private int value;

	
	public Card(String cN, char sym, String sN, char s, int v) {
		this.name = cN;
		this.symbolN = sym;
		this.suit = sN;
		this.symbolS = s;
		this.value = v;
		
	}
	
	
		
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public char getSymbolN() {
		return symbolN;
	}



	public void setSymbolN(char symbolN) {
		this.symbolN = symbolN;
	}



	public String getSuit() {
		return suit;
	}



	public void setSuit(String suit) {
		this.suit = suit;
	}



	public char getSymbolS() {
		return symbolS;
	}



	public void setSymbolS(char symbolS) {
		this.symbolS = symbolS;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public String toString() {
		return "Value: " + value + "; Suit: " +suit;
	}
	
	public JLabel toImage() {
		return new JLabel(new ImageIcon("resources/icons/" + name + "_of_" + suit + ".png"));
	}

}
