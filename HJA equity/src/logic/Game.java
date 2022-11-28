package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Game {
	
	private static final String cardValue[] =  {"2","3","4","5","6","7","8","9","10","jack","queen","king","ace"}; 
	private static final String cardSuit[] = {"clubs", "diamonds","hearts", "spades"};
	
	private ArrayList<Card> mazo;
	private List<Player> players;
	int activePlayers;
	private ArrayList<Card> board;
	
	// ARRAYS DE PUNTOS Y PORCENTAJES INICIALIZADOS A 0
	private double[] puntosJug = {0, 0, 0, 0, 0, 0};
	private double[] porcentajesJug = {0, 0, 0, 0, 0, 0};
	
	private Random rand;
	int paso;
	
	public Game() {
		players = new ArrayList<Player>();
		activePlayers = 6;
		rand = new Random();
		board = new ArrayList<Card>();
		for(int i = 0; i < 6; i++) {
			Player p = new Player(null, null);
			players.add(p);
		}
		initMazo();
		
	}
	
	public void jugar() {
		switch(paso) {
			case 0: 
				//AQUI SE SELECCIONAN LAS CARTAS DE CADA JUG
				repartir();		
				break;
			case 1: 
				// PREFLOP: REPARTIR LAS 2 CARTAS A LOS JUGADORES
				porcentajesPREFLOP();	
				break;
			case 2: 
				// FLOP: PONER 3 CARTAS EN EL TABLERO
				flop();
				porcentajesPOSTFLOP();				
				break;	
			case 3: 
				// TURN: PONER 1 CARTA EN EL TABLERO
				addCardToBoard();
				porcentajesTURN();
				break;
			case 4: 
				// RIVER: PONER 1 CARTA EN EL TABLERO
				addCardToBoard();
				ganador();				
				break;
			default: 				
			break;
		}
		
		paso++;
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
	
	public void repartir() {
		for (int i = 0; i < 6; i++) {
			if(!players.get(i).getHasCards()) {
				int num = rand.nextInt(mazo.size());
				Card c1 = mazo.get(num); //Repartimos la primera carta
				mazo.remove(num);
				
				num = rand.nextInt(mazo.size());
				Card c2 = mazo.get(num); //Repartimos la segunda carta
				mazo.remove(num);
				
				Player p = new Player(c1, c2);
				players.add(i, p);
			}
		}
	}
	
	public void porcentajesPREFLOP() {			
			List<Card> mesa = new ArrayList<Card>();
			List<Integer> ganadores = new ArrayList<Integer>();
			for (int p = 0; p < 36; p++)
		        for (int s = p+1; s < 37; s++)
		            for (int t = s+1; t < 38; t++)
		                for (int c = t+1; c < 39; c++)
		                    for (int q = c+1; q < 40; q++){
		                    	mesa.add(mazo.get(p));
		                    	mesa.add(mazo.get(s));
		                    	mesa.add(mazo.get(t));
		                    	mesa.add(mazo.get(c));
		                    	mesa.add(mazo.get(q));
		                    	// FUNCION QUE COMPRUEBA QUIEN GANA DE LOS 5 JUGADORES
		                    	ganadores = ganaJugador(mesa); // DEVUELVE LOS INDICES DE LOS JUGADORES EN EL ARRAY PARA SUMARLO AL ARRAY DE PUNTO
		                    	mesa.clear();                    	
		                    	
		                    	double porcionPuntos = 1.0 / ganadores.size();
		            			for(Integer punto : ganadores) {
		            				puntosJug[punto] += porcionPuntos; //SE LE SUMA A CADA JUGADOR QUE HA EMPATADO SU PORCION DE PUNTOS
		            			}
		            			ganadores.clear();
		                    }
			
			
		// FUNCION QUE DIVIDE LOS PUNTOS DE CADA JUGADOR Y LAS 658008 COMBINACIONES
		for (int i = 0; i < 6; i++) {
			porcentajesJug[i] = (puntosJug[i]/658008) * 100; // PORCENTAJE DE VICTORIAS DE CADA JUGADOR				puntosJug[i] = 0;
		}
	}	
	
	private void flop() {
		board.clear();
		for (int i = 0; i < 3; i++) {
			addCardToBoard();			
		}		
	}	
	
	public void porcentajesPOSTFLOP() {
			List<Card> mesa = new ArrayList<Card>();
			mesa.addAll(board);
			List<Integer> ganadores = new ArrayList<Integer>();	
			
			for (int p = 0; p < 36; p++)
		        for (int s = p+1; s < 37; s++) {
		        	mesa.add(mazo.get(p));
		        	mesa.add(mazo.get(s));
                	ganadores = ganaJugador(mesa);
                	mesa.remove(4);
                	mesa.remove(3);
                	double porcionPuntos = 1.0 / ganadores.size();
                	
        			for(Integer punto : ganadores) {
        				puntosJug[punto] += porcionPuntos;
        			}
		        }
			
			for (int i = 0; i < 6; i++) {
				porcentajesJug[i] = (puntosJug[i]/666) * 100; // PORCENTAJE DE VICTORIAS DE CADA JUGADOR
				puntosJug[i] = 0;
			}
	}
	
	private void addCardToBoard() {
		int num = rand.nextInt(mazo.size());
		Card c = mazo.get(num); 
		mazo.remove(num);
		
		board.add(c);
	}
	
	public void porcentajesTURN() {
		List<Card> mesa = new ArrayList<Card>();
		mesa.addAll(board);
		List<Integer> ganadores = new ArrayList<Integer>();	
		
		for (int p = 0; p < 36; p++) {
	        	mesa.add(mazo.get(p));
            	ganadores = ganaJugador(mesa);
            	mesa.remove(4);
            	double porcionPuntos = 1.0  / ganadores.size();
    			for(Integer punto : ganadores) {
    				puntosJug[punto] += porcionPuntos;
    			}
	        }
		
		for (int i = 0; i < 6; i++) {
			porcentajesJug[i] = (puntosJug[i]/36) * 100; // PORCENTAJE DE VICTORIAS DE CADA JUGADOR
			puntosJug[i] = 0;
		}		
	}
	
	public void ganador() { // QUIEN GANA DADO LOS VALORES DEL TABLERO Y LAS CARTAS DE LOS JUGADORES					
		// ESTE SE PONE EL TABLERO FINAL PORQUE NO HAY QUE REPETIR 2 MILLONES DE ITERACIONES
		List<Integer> ganadores = new ArrayList<Integer>();				
		ganadores = ganaJugador(board);
		for(Integer punto : ganadores) {
			puntosJug[punto] += 1;
		}
		for (int i = 0; i < 6; i++) {
			porcentajesJug[i] = (puntosJug[i]) * 100; // PORCENTAJE DE VICTORIAS DE CADA JUGADOR
			puntosJug[i] = 0;
		}
	}	
	
	// FUNCION QUE CALCULA QUIEN GANA DADO UN TABLERO QUE PUEDE CAMBIAR Y LAS CARTAS DE CADA JUGADOR SON CONSTANTES
	
	public List<Integer> ganaJugador(List<Card> mesa) {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++) {
			if (players.get(i).isActive())
				players.get(i).CardsValue(mesa);
    	}
    	
    	int it = 0;
		while (!players.get(it).isActive()) {
			it++;
		}
		Player aux = players.get(it);
    	ret.add(it);
		for (int i = it+1; i < 6; i++) {
			if (players.get(i).isActive()) {
				int comp = compare(aux,players.get(i));
				if (comp == 1) {
					aux = players.get(i);
					ret.clear(); //HAY NUEVA MEJOR MANO, SE BORRAN TODAS LAS QUE HABIA Y SE A�ADE ESTE JUGADOR
					ret.add(i);
				}
				else if(comp == 0) {
					ret.add(i); //SE A�ADE UN EMPATE
				}
			}
		}
		
		for (int i = 0; i < 6; i++) {
			players.get(i).clearPlayer();
		}
		
		return ret;
	}
	
	public void inputCards(HashMap<Integer, Pair<Card, Card>> inputs) { 
		for(Integer clave: inputs.keySet()) {
			Player p = new Player(inputs.get(clave).getFirst(), inputs.get(clave).getSecond());
			players.add(clave, p);
			mazo.remove(inputs.get(clave).getFirst());
			mazo.remove(inputs.get(clave).getSecond());
		}
	}
	
	public Card lookForCard(char symbolN, char symbolS) {
		for(Card c: mazo) {
			if(c.sameCard(symbolN, symbolS)) {
				return c;
			}
		}
		return null;
	}

	
	public List<Player> getCartasPlayers() {
		return players;
	}
	
	public void foldPlayer(int player) {
		players.get(player).fold();
		activePlayers--;
		
		switch(paso) {
		case 1: 
			porcentajesPREFLOP();				
			break;
		case 2: 
			porcentajesPOSTFLOP();				
			break;
		case 3: 
			porcentajesTURN();				
			break;
		case 4: 
			ganador();				
			break;
		default: 				
		break;
	}
		
	}
	
	public boolean foldIsValid() {
		return activePlayers > 1;
	}
	
	public List<Card> getCartasBoard() {
		return board;
	}
		
	public String getPorcentajes(int i) {
		String ret = "";
		if (paso > 0) {
			ret = String.valueOf(porcentajesJug[i]);
			if (ret.length() > 6) {
				ret = ret.substring(0, 7);
			}
			ret += "%";
		}
		return ret;
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
