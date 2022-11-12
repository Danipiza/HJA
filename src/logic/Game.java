package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final String cardValue[] = {"ace", "king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
	private static final String cardSuit[] = {"hearts", "diamonds", "clubs", "spades"};
	
	private List<Card> mazo;
	private List<Player> players;
	
	private Random rand;
	int paso;
	
	public Game() {
		initMazo();
		players = new ArrayList<Player>();
		rand = new Random();
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
		//TODO Hacer primeros porcentajes
	}
	
	public List<Player> getCartas() {
		return players;
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
