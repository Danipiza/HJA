package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private static final String cardValue[] = {"ace", "king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
	private static final String cardSuit[] = {"hearts", "diamonds", "clubs", "spades"};
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
			int ganador = ganaJugador(boardAux); // DEVUELVE LA POSICION DEL JUGADOR EN EL ARRAY PARA SUMARLO ALARRAY DE PUNTOS
			puntosJug[ganador]++;
		}		
		
		porcentajes(); // FUNCION QUE DIVIDE LOS PUNTOS DE CADA JUGADOR Y LAS 2 MILLONES DE ITERACIONES
	}	
	
	// FUNCION QUE CALCULA QUIEN GANA DADO UN TABLERO QUE PUEDE CAMBIAR Y LAS CARTAS DE CADA JUGADOR SON CONSTANTES
	public int ganaJugador(List<Card> boardAux) {
		int ret = 0;		
		
		
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
			Card c1 = new Card(mazo.get(num).getValue(), mazo.get(num).getSuit()); //Repartimos la primera carta
			mazo.remove(num);
			
			num = rand.nextInt(mazo.size());
			Card c2 = new Card(mazo.get(num).getValue(), mazo.get(num).getSuit()); //Repartimos la segunda carta
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
	
	public double getPorcentajes(int i) {
		return porcentajesJug[i];
	}
	
}
