package logic;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class Player {
	
	private Card c1, c2;
	private List<Card> Cards;
	
	private int playerNum;
	private int maxValue;
	private int tieBreaker1;
	private int tieBreaker2;
	private String bestHand;
	
	public Player() {
		
	}
	
	public void handValue(List<Card> hand) {
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
		@SuppressWarnings("unused")
		char paloRepetido = '_';
		 for (Entry<Character, Integer> e : suitMap.entrySet()) {
			 if (maxPalos < e.getValue()) {
					paloRepetido = e.getKey();
					maxPalos = e.getValue();
				}
		 }
	 	
		 int valoresConsecutivos = 0, maxValoresConsecutivos = 0;
	 	
		 for(int i = 1; i < hand.size(); ++i) {
	            if(hand.get(i).getValue() == hand.get(i-1).getValue()-1)
	            	valoresConsecutivos++;
	            else {
	            	maxValoresConsecutivos = Math.max(maxValoresConsecutivos,valoresConsecutivos);
	                valoresConsecutivos = 0;
	            }
	        }
	        
	        if (valoresConsecutivos == 3 && hand.get(4).getValue() == 2 && hand.get(0).getValue() == 14)
	        	maxValoresConsecutivos = 4;

		
		int cont = 0;
		String pareja1 = "";
		String pareja2 = "";
		String trio = "";
		String poker = "";
		
		for (int i = 0; i < hand.size() - 1; i++){
			if (hand.get(i).getValue() == hand.get(i+1).getValue())
				cont++;
			else {
				if (cont == 1) 
					if (pareja1.equals("")) pareja1 = hand.get(i).getName();
					else pareja2 = hand.get(i).getName();
				else if (cont == 2) trio = hand.get(i).getName();
				else if (cont == 3) poker = hand.get(i).getName();
				cont = 0;
			}
		}
		
		if (cont == 1) 
			if (pareja1.equals("")) pareja1 = hand.get(4).getName();
			else pareja2 = hand.get(4).getName();
		else if (cont == 2) trio = hand.get(4).getName();
		else if (cont == 3) poker = hand.get(4).getName();
		
		
		
		if(maxPalos == 5 && maxValoresConsecutivos == 4) { 
			if (maxValue < 9) 
				if (hand.get(0).getValue() == 14) {
					maxValue = 9; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
				}
				else {
					maxValue = 9; tieBreaker1 = hand.get(0).getValue();
				}
		}
		else if(!poker.equals("")) {
			if (maxValue < 8) {
				maxValue = 8; tieBreaker1 = symbolValue(poker.charAt(0)); tieBreaker2 = 0;
			}
		}
		else if(!trio.equals("") && !pareja1.equals("")) {
			if (maxValue < 7) {
				maxValue = 7; tieBreaker1 = symbolValue(trio.charAt(0)); tieBreaker2 = symbolValue(pareja1.charAt(0));
			}
		}
		else if(maxPalos == 5) {
			if (maxValue < 6) {
				maxValue = 6; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
			}
		}
		else if(maxValoresConsecutivos == 4) {
			if (maxValue < 5) {
				maxValue = 5; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
			}
		}
		else if(!trio.equals("")) {
			if (maxValue < 4) {
				maxValue = 4; tieBreaker1 = symbolValue(trio.charAt(0)); tieBreaker2 = 0;
			}
		}
		else if(!pareja1.equals("") && !pareja2.equals("")) {
			if (maxValue < 3) {
				maxValue = 3; tieBreaker1 = symbolValue(pareja1.charAt(0)); tieBreaker2 = symbolValue(pareja2.charAt(0));
			}
		}
		else if(!pareja1.equals("")) {
			if (maxValue < 2) {
				maxValue = 2; tieBreaker1 = symbolValue(pareja1.charAt(0)); tieBreaker2 = 0;
			}
		}
		else {
			if (maxValue < 1) {
				maxValue = 1; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
			}
		}
	}
	
	// TODO CAMBIAR
	public int symbolValue(char val) {
		int ret;
		if (val == 'a')	ret = 14;
		else if (val == 'k') ret = 13;
		else if (val == 'q') ret = 12;
		else if (val == 'j') ret = 11;
		else if (val == 't') ret = 10;
		else ret = val - 48;
		
		return ret;
	}
	
	public void CardsValue() {
		cardSorting(Cards);
		if (Cards.size() == 7) {
			List<Card> hand = new ArrayList<Card>();
			for (int i = 6; i >= 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					for (int x = 0; x < 7; x++) {
						if (x != i && x != j) {
							hand.add(Cards.get(x));
						}	
					}
					handValue(hand);
					hand.clear();
				}
			}	
		}
		else if (Cards.size() == 6) {
			List<Card> hand = new ArrayList<Card>();
			for (int i = 5; i >= 0; i--) {
				for (int x = 0; x < 6; x++) {
					if (x != i) {
						hand.add(Cards.get(x));
						
					}
				}
				handValue(hand);
				hand.clear();
			}
		}
		else {
			handValue(Cards);
		}			
			
	}
	
	public Player(Card c1, Card c2) {
		Cards.add(c1);
		Cards.add(c2);
	}
	
	public void addBoard(List<Card> c) {
		Cards.addAll(c);
	}
	
	public void removeBoard() {
		while(Cards.size() > 2)
			Cards.remove(2);
	}
	
	
	public Card getC1() {
		return c1;
	}

	public Card getC2() {
		return c2;
	}

	public List<Card> getCards() {
		return Cards;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getTieBreaker1() {
		return tieBreaker1;
	}

	public int getTieBreaker2() {
		return tieBreaker2;
	}

	public String getBestHand() {
		return bestHand;
	}

	private void cardSorting(List<Card> cardList) {
		Collections.sort(cardList, new SortLocation());
	}

	class SortLocation implements Comparator<Card>{
		@Override
		public int compare(Card c1, Card c2) {
			if (c1.getValue() > c2.getValue())
				return -1;
			else if (c1.getValue() == c2.getValue())
				return 0;
			return 1;
		}
	}
	
}
