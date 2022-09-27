package launcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Player {
	
	private List<Card> Cards;
	private List<Card> OmahaCommonCards;
	
	private int playerNum;
	private int maxValue;
	private int tieBreaker1;
	private int tieBreaker2;
	private String handName;
	private String bestHand;
	
	private Boolean drawFlush;
	private int drawStraight;
	
	public Player() {
		maxValue = 0;
		tieBreaker1 = 0;
		tieBreaker2 = 0;
		drawFlush = false;
		Cards = new ArrayList<Card>();
		OmahaCommonCards = new ArrayList<Card>();
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
		

		
		int ret;
		
		if(maxPalos == 5 && valoresConsecutivo == 5) { 
			if (maxValue < 9) 
				if (hand.get(0).getValue() == 14) {
					maxValue = 9; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
					handName = "Royal Flush"; bestHand = handToString(hand);
				}
				else {
					maxValue = 9; tieBreaker1 = hand.get(0).getValue();
					handName = "Straight Flush"; bestHand = handToString(hand);
				}
		}
		else if(!poker.equals("")) {
			if (maxValue < 8) {
				maxValue = 8; tieBreaker1 = symbolValue(poker.charAt(0)); tieBreaker2 = 0;
				handName = "Four of a kind (" + poker + "s)"; bestHand = handToString(hand);
			}
		}
		else if(!trio.equals("") && !pareja1.equals("")) {
			if (maxValue < 7) {
				maxValue = 7; tieBreaker1 = symbolValue(trio.charAt(0)); tieBreaker2 = symbolValue(pareja1.charAt(0));
				handName = trio + "s full of " + pareja1 + "s"; bestHand = handToString(hand);
			}
		}
		else if(maxPalos == 5) {
			if (maxValue < 6) {
				maxValue = 6; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
				handName = "Flush"; bestHand = handToString(hand);
			}
		}
		else if(valoresConsecutivo == 5) {
			if (maxValue < 5) {
				maxValue = 5; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
				handName = "Straight"; bestHand = handToString(hand);
			}
		}
		else if(!trio.equals("")) {
			if (maxValue < 4) {
				maxValue = 4; tieBreaker1 = symbolValue(trio.charAt(0)); tieBreaker2 = 0;
				handName =  "Three of a kind (" + trio + "s)"; bestHand = handToString(hand);
			}
		}
		else if(!pareja1.equals("") && !pareja2.equals("")) {
			if (maxValue < 3) {
				maxValue = 3; tieBreaker1 = symbolValue(pareja1.charAt(0)); tieBreaker2 = symbolValue(pareja2.charAt(0));
				handName = "Two pair of " + pareja1 + "s and " + pareja2 + "s"; bestHand = handToString(hand);
			}
		}
		else if(!pareja1.equals("")) {
			if (maxValue < 2) {
				maxValue = 2; tieBreaker1 = symbolValue(pareja1.charAt(0)); tieBreaker2 = 0;
				handName = "Pair of " + pareja1 + "s"; bestHand = handToString(hand);
			}
		}
		else {
			if (maxValue < 1) {
				maxValue = 1; tieBreaker1 = hand.get(0).getValue(); tieBreaker2 = 0;
				handName = "High card " + hand.get(0).getName(); bestHand = handToString(hand);
			}
		}
		
		
		if (maxPalos == 4 && drawFlush == false)
			drawFlush = true;
		if (drawStraight == 0) {
	        if(valoresConsecutivo >= 3 && gutShot) drawStraight = 1;
	        else if(valoresConsecutivo == 4) drawStraight = 2;
		}
        
	}
	
	public int symbolValue(char val) {
		int ret;
		if (val == 'A')	ret = 14;
		else if (val == 'K') ret = 13;
		else if (val == 'Q') ret = 12;
		else if (val == 'J') ret = 11;
		else if (val == 'T') ret = 10;
		else ret = val - 48;
		
		return ret;
	}
	
	public String handToString(List<Card> hand) {
		String handString = "";
		for (int x = 0; x < 5; x++) {
			handString += hand.get(x).getSymbol();
			handString += hand.get(x).getSuit();
		}
		
		return handString;
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
	
	public void OmahaCardsValue() {
		//En este punto this.Cards contiene la 4 cartas de mano Omaha. Hacemos conbinatoria
		List<Card> hand = new ArrayList<Card>();
		for(int i = 0; i < Cards.size(); i++) {
			for(int j = 0; j < Cards.size(); j++) {
				if(i != j) {
					if(OmahaCommonCards.size() == 5) {
						for (int k = 4; k >= 0; k--) {
							for (int l = k - 1; l >= 0; l--) {
								hand.add(Cards.get(i));
								hand.add(Cards.get(j));
								for (int x = 0; x < 5; x++) {
									if (x != k && x != l) {
										hand.add(OmahaCommonCards.get(x));
									}	
								}
								cardSorting(Cards);
								handValue(hand);
								hand.clear();
							}
						}
					}
					else if(OmahaCommonCards.size() == 4) {
						for (int k = 3; k >= 0; k--) {
							hand.add(Cards.get(i));
							hand.add(Cards.get(j));
							for (int x = 0; x < 4; x++) {
								if (x != k) {
									hand.add(OmahaCommonCards.get(x));
								}
							}
							cardSorting(Cards);
							handValue(hand);
							hand.clear();
						}
					}
					else {
						hand.add(Cards.get(i));
						hand.add(Cards.get(j));
						hand.addAll(OmahaCommonCards);
						cardSorting(Cards);
						handValue(hand);
						hand.clear();
					}
				}
			}
		}	
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
	
	public void deleteCards() {
		Cards.clear();
	}
	
	public void addCard(Card c) {
		Cards.add(c);
	}
	
	public void addCards(List<Card> c) {
		Cards.addAll(c);
	}
	
	public void addCommonOmahaCard(Card c) {
		OmahaCommonCards.add(c);
	}
	
	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
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
	
	public String getHandName() {
		return handName;
	}

	public String getBestHand() {
		return bestHand;
	}
	
	public Boolean getDrawFlush() {
		return drawFlush;
	}
	
	public int getDrawStraight() {
		return drawStraight;
	}

	
}
