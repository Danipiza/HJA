package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final String cardValue[] =  {"2","3","4","5","6","7","8","9","10","jack","queen", "king","ace"}; 
	private static final String cardSuit[] = {"clubs", "diamonds","hearts", "spades"};
	private static final int FLOP = 3;
	private static final int BUCLE = 2000000;
	
	private ArrayList<Card> mazo;
	private List<Player> players;
	private ArrayList<Card> board;
	
	// ARRAYS DE PUNTOS Y PORCENTAJES INICIALIZADOS A 0
	private int[] puntosJug = {0, 0, 0, 0, 0, 0};
	private double[] porcentajesJug = {0, 0, 0, 0, 0, 0};
	
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
			case 0: 
				// PREFLOP: REPARTIR LAS 2 CARTAS A LOS JUGADORES
				repartir();	
				porcentajesPREFLOP();				
				break;
			case 1: 
				// FLOP: PONER 3 CARTAS EN EL TABLERO
				flop();
				porcentajesPOSTFLOP();				
				break;
			case 2: 
				// TURN: PONER 1 CARTA EN EL TABLERO
				addCardToBoard();
				porcentajesTURN();				
				break;
			case 3: 
				// RIVER: PONER 1 CARTA EN EL TABLERO
				addCardToBoard();
				//ganador();				
				break;
			default: 				
			break;
		}
		
		paso++;
	}

	@SuppressWarnings("unchecked")
	public void porcentajesPREFLOP() {		
		for(int i = 0; i < BUCLE; i++) {
			ArrayList<Card> boardAux = (ArrayList<Card>) board.clone();			
			ArrayList<Card> mazoAux = (ArrayList<Card>) mazo.clone();
			
			
			for(int j = 0; j < 5; j++) { // AÑADE 5 CARTAS ALEATORIAS AL TABLERO AUXILIAR
				int num = rand.nextInt(mazoAux.size());
				Card c = new Card(mazoAux.get(num).getValue(), mazoAux.get(num).getSuit()); 
				mazoAux.remove(num);
				
				boardAux.add(c);
			}
			
			// FUNCION QUE COMPRUEBA QUIEN GANA DE LOS 5 JUGADORES
			List<Integer> ganadores = ganaJugador(boardAux); // DEVUELVE LOS INDICES DE LOS JUGADORES EN EL ARRAY PARA SUMARLO AL ARRAY DE PUNTOS
			double porcionPuntos = 1 / ganadores.size();
			for(Integer punto : ganadores) {
				puntosJug[punto] += porcionPuntos; //SE LE SUMA A CADA JUGADOR QUE HA EMPATADO SU PORCION DE PUNTOS
			}			
		}		
		
		porcentajes(); // FUNCION QUE DIVIDE LOS PUNTOS DE CADA JUGADOR Y LAS 2 MILLONES DE ITERACIONES
	}	
	
	// FUNCION QUE CALCULA QUIEN GANA DADO UN TABLERO QUE PUEDE CAMBIAR Y LAS CARTAS DE CADA JUGADOR SON CONSTANTES
	public List<Integer> ganaJugador(List<Card> boardAux) {
		List<Integer> ret = new ArrayList<Integer>(); //LISTA CON LOS INDICES DE LOS JUGADORES GANADORES
		for (int i = 0; i < players.size(); i++) {
			players.get(i).CardsValue();
		}
		
		Player aux = players.get(0);
		for (int i = 1; i < players.size(); i++) {
			int c = compare(aux,players.get(i));
			if (c == 1) {
				aux = players.get(i);
				ret.clear(); //HAY NUEVA MEJOR MANO, SE BORRAN TODAS LAS QUE HABIA Y SE AÑADE ESTE JUGADOR
				ret.add(i);
			}
			else if(c == 0) {
				ret.add(i); //SE AÑADE UN EMPATE
			}
		}
		
		return ret;
	}
	
	
	
	public void porcentajes() {
		for (int i = 0; i < 6; i++) {
			porcentajesJug[i] = (puntosJug[i]/BUCLE) * 100; // PORCENTAJE DE VICTORIAS DE CADA JUGADOR
			puntosJug[i] = 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void porcentajesPOSTFLOP() {
		for(int i = 0; i < BUCLE; i++) { 
			ArrayList<Card> boardAux = (ArrayList<Card>) board.clone();			
			ArrayList<Card> mazoAux = (ArrayList<Card>) mazo.clone();
			for(int j = 0; j < 2; j++) { // AÑADE 2 CARTAS ALEATORIAS AL TABLERO AUXILIAR
				int num = rand.nextInt(mazoAux.size());
				Card c = new Card(mazoAux.get(num).getValue(), mazoAux.get(num).getSuit()); 
				mazoAux.remove(num);
				
				boardAux.add(c);
			}
			
			// FUNCION QUE COMPRUEBA QIEN GANA DE LOS 5 JUGADORES
			int ganador = ganaJugador(boardAux);
			puntosJug[ganador]++;
		}		
		
		porcentajes();
		
		
	}
	@SuppressWarnings("unchecked")
	public void porcentajesTURN() {
		for(int i = 0; i < BUCLE; i++) { 
			ArrayList<Card> boardAux = (ArrayList<Card>) board.clone();			
			ArrayList<Card> mazoAux = (ArrayList<Card>) mazo.clone();
			
			// AÑADE UNA CARTA AL TABLERO AUXILIAR
			int num = rand.nextInt(mazoAux.size());
			Card c = new Card(mazoAux.get(num).getValue(), mazoAux.get(num).getSuit()); 
			mazoAux.remove(num);
			
			boardAux.add(c);			
			
			// FUNCION QUE COMPRUEBA QIEN GANA DE LOS 5 JUGADORES
			int ganador = ganaJugador(boardAux);
			puntosJug[ganador]++;
		}		
		
		porcentajes();
		
		
	}
	
	public void ganador() { // QUIEN GANA DADO LOS VALORES DEL TABLERO Y LAS CARTAS DE LOS JUGADORES					
		// ESTE SE PONE EL TABLERO FINAL PORQUE NO HAY QUE REPETIR 2 MILLONES DE ITERACIONES
		int ganador = ganaJugador(board); 				
		puntosJug[ganador]++;
		
		porcentajes();		
	}	
	
	public void repartir() {
		players.clear();
		for (int i = 0; i < 6; i++) {
			int num = rand.nextInt(mazo.size());
			Card c1 = mazo.get(num); //Repartimos la primera carta
			mazo.remove(num);
			
			num = rand.nextInt(mazo.size());
			Card c2 = mazo.get(num); //Repartimos la segunda carta
			mazo.remove(num);
			
			Player p = new Player(c1,c2);
			players.add(p);
			
		}
	}
		
	private void flop() {
		board.clear();
		for (int i = 0; i < FLOP; i++) {
			addCardToBoard();			
		}		
	}
	
	private void addCardToBoard() {
		int num = rand.nextInt(mazo.size());
		Card c = mazo.get(num); 
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
		
		for (int i = 0; i < cardValue.length; i++) {
			for (int j = 0; j < cardSuit.length; j++) {
				Card aux = new Card(cardValue[i], cardValue[i].charAt(0), cardSuit[j],cardSuit[j].charAt(0),i+1);
				mazo.add(aux);
			}
		}
	}
	
	public double getPorcentajes(int i) {
		return porcentajesJug[i];
	}
	
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
