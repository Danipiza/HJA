package logic;

public class Player {
	
	private Card card1, card2;
	
	public Player() {
		
	}
	
	public Player(Card c1, Card c2) {
		card1 = c1;
		card2 = c2;
	}
	
	public void setFirstCard(Card c) {
		card1 = c;
	}
	
	public void setSecondCard(Card c) {
		card2 = c;
	}
	
	public Card getFirstCard() {
		return card1;
	}
	
	public Card getSecondCard() {
		return card2;
	}
	
	
}
