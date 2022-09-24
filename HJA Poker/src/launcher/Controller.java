package launcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Controller {
	
	private List<Carta> myCards;
	private List<List<Carta>> playerCards;
	
	private List<Carta> myOmahaCards;
	private List<Carta> OmahaCommonCards;
	
	private Boolean drawFlush;
	private int drawStraight;
	
	public Controller() {
		drawFlush = false;
		drawStraight = 0;
		myCards = new ArrayList<Carta>();
		playerCards = new ArrayList<List<Carta>>();
	}
	
	public int handValue(List<Carta> hand) {
		HashMap<Character, Integer> suitMap = new HashMap<Character, Integer>();

		suitMap.put('s', 0);
		suitMap.put('h', 0);
		suitMap.put('d', 0);
		suitMap.put('c', 0);
		
		for (int i = 0; i < 5; i++) {
			char aux = hand.get(i).getSuit();
			suitMap.put(aux, suitMap.get(aux) + 1);
		}

		
		int maxPalos = 0;
		char paloRepetido = '_';
		 for (Entry<Character, Integer> e : suitMap.entrySet()) {
			 if (maxPalos < e.getValue()) {
					paloRepetido = e.getKey();
					maxPalos = e.getValue();
				}
		 }
	 	
	 	//System.out.println("PalosIguales: " + paloRepetido + " " + maxPalos + '\n');
	 	
		 int valoresConsecutivo = 1;
	     Boolean gutShot = false;

	        for(int i = 1; i < hand.size(); ++i) {
	            if(hand.get(i).getValue() == hand.get(i-1).getValue()-1)
	            	valoresConsecutivo++;
	            else if(hand.get(i).getValue() == hand.get(i-1).getValue() - 2) {
	                if(!gutShot) gutShot = true;
	                else if(gutShot && valoresConsecutivo < 3) valoresConsecutivo = 1;
	            }
	            else if(hand.get(i).getValue() == hand.get(i-1).getValue()) {
	                // NADA
	            }
	            else {
	                valoresConsecutivo = 1;
	                gutShot = false;
	            }
	        }

			 	
	 	//System.out.println("ValoresConsecutivos: " + maxValoresConsecutivos + " " + "ValoresGutshotConsecutivos: " + valoresGutshot + '\n');
		
		int cont = 0;
		int parejas = 0;
		int trios = 0;
		int poker = 0;
		
		for (int i = 0; i < hand.size() - 1; i++){
			if (hand.get(i).getValue() == hand.get(i+1).getValue())
				cont++;
			else{
				if (cont == 1) parejas++;
				else if (cont == 2) trios++;
				else if (cont == 3) poker++;
				cont = 0;
			}
		}
		
		if (cont == 1) parejas++;
		else if (cont == 2) trios++;
		else if (cont == 3) poker++;
		

		
		int ret;
		
		if(maxPalos == 5 && valoresConsecutivo == 4) 
			ret = 9;	
		else if(poker == 1) 
			ret = 8;
		else if(trios == 1 && parejas == 1) 
			ret = 7;
		else if(maxPalos == 5) 
			ret = 6;
		else if(valoresConsecutivo == 4) 
			ret = 5;
		else if(trios == 1) 
			ret = 4;
		else if(parejas == 2) 
			ret = 3;
		else if(parejas == 1) 
			ret = 2;
		else 
			ret = 1;
		
		
		if (maxPalos == 4 && drawFlush == false)
			drawFlush = true;
		if (drawStraight == 0) {
	        if(valoresConsecutivo >= 3 && gutShot) drawStraight = 1;
	        else if(valoresConsecutivo == 4) drawStraight = 2;
		}
        
			
		return ret;
	}
	
	public int CardListValue(List<Carta> cardList) {	
		int aux, help = 1;
		Boolean drawFlushAux = false;
		int drawStraightAux = 0, maxValue = 0;
		
		
			if (cardList.size() == 7) {
				List<Carta> hand = new ArrayList<Carta>();
				for (int i = 6; i >= 0; i--) {
					for (int j = i - 1; j >= 0; j--) {
						for (int x = 0; x < 7; x++) {
							if (x != i && x != j) {
								hand.add(cardList.get(x));
							}
							
						}
						aux = handValue(hand);
						System.out.print(help + " ---> " + i + " " + j + " ");
						help++;
						System.out.println (aux);
						hand.clear();
						if (aux > maxValue) maxValue = aux;					
						
					}
				}
			}
			else if (cardList.size() == 6) {
				List<Carta> hand = new ArrayList<Carta>();
				for (int i = 5; i >= 0; i--) {
					for (int x = 0; x < 6; x++) {
						if (x != i) {
							hand.add(cardList.get(x));
							
						}
					}
					aux = handValue(hand);
					System.out.print(help + " ---> " + i + " ");
					help++;
					System.out.println (aux);
					hand.clear();
					if (aux > maxValue) maxValue = aux;
				}
			}
			else {
				aux = handValue(cardList);
				System.out.println (aux);
				if (aux > maxValue) maxValue = aux;
				if (drawStraightAux > 0) drawStraight = drawStraightAux;
				if (drawFlushAux == true) drawFlush = drawFlushAux;
			}
			
			System.out.println ("Best Hand: " + maxValue);
			if (drawStraight == 1)
				System.out.println ("Draw: Gutshot Straight");
			else if (drawStraight == 2)
				System.out.println ("Draw: Open-ended Straight");	
			if (drawFlush)
				System.out.println ("Draw: Flush");
			
			
			return maxValue;
			
	}
	
		
	public void loadDeck1(BufferedReader in) throws Exception {
		int value;
		char suit = ' '; 
		while ((value = in.read()) != -1) {
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			myCards.add(c);
		}
	}
	
	public void run1() {
		CardListValue(myCards);		
	}
	
	public void loadDeck2(BufferedReader in) throws Exception {
		int value, commonCards;
		char suit = ' ';
		for (int i = 0; i < 2; i++) {
			value = in.read();
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			myCards.add(c);
		}
		in.read(); commonCards = in.read() - 48; in.read();
		for (int i = 0; i < commonCards; i++) {
			value = in.read();
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			myCards.add(c);
		}
	}
	
	public void run2() {
		CardListValue(myCards);		
	}
	
	public void loadDeck3(BufferedReader in) throws Exception {
		int value, players;
		char suit = ' '; 
		players = in.read() - 48;
		for (int i = 0; i < players; i++) {
			List<Carta> newPlayer = new ArrayList<Carta>();
			in.read(); in.read(); in.read();
			for (int j = 0; j < 2; j++) {
				value = in.read();
				suit = (char) in.read();
				Carta c = new Carta(suit, value);
				newPlayer.add(c);
			}
			playerCards.add(newPlayer);
		}
		List<Carta> commonCards = new ArrayList<Carta>();
		value = in.read();
		while ((value = in.read()) != -1) {
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			commonCards.add(c);
		}
		
		for (List<Carta> player : playerCards) {
			player.addAll(commonCards);
		}
		
		
	}
	
	public void run3() {
		List<Integer> playerValues = new ArrayList<Integer>();
		for (List<Carta> player : playerCards) {
			playerValues.add(CardListValue(player));
		}
		while (!playerValues.isEmpty()) {
			int max = 0, index = 0;
			for (int i = 0; i < playerValues.size(); i++) {
				 if (playerValues.get(i) > max) {
						max = playerValues.get(i);
						index = i;
				}
			}
			System.out.println("J" + (index + 1) + ": ");
			playerValues.remove(index);
			playerCards.remove(index);
			
		}		
		
	}
	
	public void loadDeck4(BufferedReader in) throws Exception {
		int value, commonCards;
		char suit = ' '; 
		for (int i = 0; i < 4; i++){
			value = in.read();
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			myOmahaCards.add(c);
		}
		in.read(); commonCards = (int) in.read(); in.read();
		for (int i = 0; i < commonCards; i++){
			value = in.read();
			suit = (char) in.read();
			Carta c = new Carta(suit, value);
			OmahaCommonCards.add(c);
		}
	}
	
	private void cardSorting(List<Carta> cardList) {
		Collections.sort(cardList, new SortLocation());
	}

	class SortLocation implements Comparator<Carta>{
		@Override
		public int compare(Carta c1, Carta c2) {
			if (c1.getValue() > c2.getValue())
				return -1;
			else if (c1.getValue() == c2.getValue())
				return 0;
			return 1;
		}
	}

	/*
	int translateCard(int value) {
		int ret = value;
		if((char)value == 'A')
			ret = 14;
		else if((char)value == 'K')
			ret = 13;
		else if((char)value == 'Q')
			ret = 12;
		else if((char)value == 'J')
			ret = 11;
		else if((char)value == 'T')
			ret = 10;
		else
			ret = value - 48;
		
		return ret;
	}

	 */

}

