package GUI;

import java.awt.Color;
import javax.swing.JButton;

public class HandButton extends JButton{

	private static final long serialVersionUID = 1L;
	private Color color;
	private String hand;
	
	
	
	public HandButton(String h, Color c) {
		hand = h;
		color = c;
		setBackground(c);
		
	}
	
	public void fill() {
		setBackground(Color.yellow);
	}
	public void clear() {
		setBackground(color);
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	
	
}
