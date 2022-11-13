package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final String cardValue[] = {"ace", "king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
	private static final String cardSuit[] = {"hearts", "diamonds", "clubs", "spades"};
	private static final int FLOP = 3;
	
	private List<Card> mazo;
	private List<Player> players;
	private List<Card> board;
	
	private Random rand;
	int paso;
	
	public Game() {
		initMazo();
		players = new ArrayList<Player>();
		rand = new Random();
		board = new ArrayList<Card>();
	}
	
	public void jugar() {
		switch(paso) {
			case 0: //Pre-flop
				repartir();
				break;
			case 1: //flop
				flop();
				break;
			case 2: 
				addCardToBoard();
				break;
			case 3: 
				addCardToBoard();
				break;
			default: 				
			break;
		}
		calcularPorcentajes();
		paso++;
	}
	
	public void repartir() {
		players.clear();
		for (int i = 0; i < 6; i++) {
			int num = rand.nextInt(mazo.size());
			Card c1 = new Card(mazo.get(num).getValue(), mazo.get(num).getSuit()); //Repartimos la primera carta
			mazo.remove(num);
			
			num = rand.nextInt(mazo.size());
			Card c2 = new Card(mazo.get(num).getValue(), mazo.get(num).getSuit()); //Repartimos la segunda carta
			mazo.remove(num);
			
			Player p = new Player(c1,c2);
			players.add(p);
			
		}
	}
	
	private void calcularPorcentajes() {
		//TODO
	}
	
	private void flop() {
		board.clear();
		for (int i = 0; i < FLOP; i++) {
			addCardToBoard();
			
		}
		
	}
	
	private void addCardToBoard() {
		int num = rand.nextInt(mazo.size());
		Card c = new Card(mazo.get(num).getValue(), mazo.get(num).getSuit()); 
		mazo.remove(num);
		
		board.add(c);
	}
	
	public List<Player> getCartasPlayers() {
		return players;
	}
	
	public List<Card> getCartasBoard() {
		return board;
	}
		
	private void initMazo() {
		if (mazo != null) mazo.clear();
		else mazo = new ArrayList<>();
		
		for (String suit : cardSuit) {
			for (String value : cardValue) {
				Card aux = new Card(value, suit);
				mazo.add(aux);
			}
		}
	}
}
