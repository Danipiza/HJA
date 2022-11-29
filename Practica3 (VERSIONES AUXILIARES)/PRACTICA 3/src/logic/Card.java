package logic;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	
	private String value;
	private String suit;
	
	public Card(String v, String s) {
		value = v;
		suit = s;
	}
		
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "Value: " + value + "; Suit: " +suit;
	}
	
	public JLabel toImage() {
		return new JLabel(new ImageIcon("resources/icons/" + value + "_of_" + suit + ".png"));
	}

}
