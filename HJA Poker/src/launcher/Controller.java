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
	
	private List<Carta> cardList;
	
	public Controller() {
		cardList = new ArrayList<Carta>();
	}
	
	public void run() {			
		HashMap<Character, Integer> suitMap = new HashMap<Character, Integer>();

		suitMap.put('s', 0);
		suitMap.put('h', 0);
		suitMap.put('d', 0);
		suitMap.put('c', 0);
		
		for (int i = 0; i < cardList.size(); i++) {
			char aux = cardList.get(i).getSuit();
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
	 	
	 	System.out.println("PalosIguales: " + paloRepetido + " " + maxPalos + '\n');
	 	
	 	int valoresConsecutivos = 0, maxValoresConsecutivos = 0, valoresGutshot = 0, maxvaloresGutshot = 0;
		boolean draw = false;
		for (int i = 0; i < cardList.size() - 1 && valoresConsecutivos != 4; i++){
			if (cardList.get(i).getValue() == cardList.get(i+1).getValue() + 1) {
				valoresConsecutivos++;
				valoresGutshot++;
			}
			else if (cardList.get(i).getValue() == cardList.get(i+1).getValue() + 2) {
				if (draw)
					valoresGutshot = valoresConsecutivos;
				else
					draw = true;
				
				valoresGutshot++;
				maxValoresConsecutivos = Math.max(maxValoresConsecutivos, valoresConsecutivos);
				valoresConsecutivos = 0;
			}
			else if (cardList.get(i).getValue() > cardList.get(i+1).getValue() + 2){
				maxValoresConsecutivos = Math.max(maxValoresConsecutivos, valoresConsecutivos);
				maxvaloresGutshot = Math.max(maxvaloresGutshot, valoresGutshot);
				valoresGutshot = 0;
				valoresConsecutivos = 0;
				draw = false;
			}
		}
		maxValoresConsecutivos = Math.max(maxValoresConsecutivos, valoresConsecutivos);
		maxvaloresGutshot = Math.max(maxvaloresGutshot, valoresGutshot);
		
	 	// if (valoresConsecutivos >= 4) System.out.println("Straight");
	 	// if (valoresConsecutivos == 4 && Draw) System.out.println("Straight Gutshot");
		// if (valoresConsecutivos == 3) System.out.println("Straight open-ended");
	 	
	 	System.out.println("ValoresConsecutivos: " + maxValoresConsecutivos + " " + "ValoresGutshotConsecutivos: " + maxvaloresGutshot + '\n');
		
		int cont = 0;
		int parejas = 0;
		int trios = 0;
		int poker = 0;
		
		for (int i = 0; i < cardList.size() - 1; i++){
			if (cardList.get(i).getValue() == cardList.get(i+1).getValue())
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
		
		System.out.println("Parejas: " + parejas);
		System.out.println("Trios: " + trios);
		System.out.println("Poker: " + poker);
		
		if(maxPalos >= 5 && maxValoresConsecutivos >= 4 && cardList.get(0).getValue() == 14) 
			System.out.println("Royal Flush");	
		else if(maxPalos >= 5 && maxValoresConsecutivos >= 4) 
			System.out.println("Straight Flush");	
		else if(poker > 0) 
			System.out.println("Poker");
		else if(trios > 0 && parejas > 0) 
			System.out.println("Full House");
		else if(maxPalos >= 5) 
			System.out.println("Flush");
		else if(maxValoresConsecutivos >= 4) 
			System.out.println("Straight");
		else if(trios > 0) 
			System.out.println("Trio");
		else if(parejas > 1) 
			System.out.println("Double Pair");
		else if(parejas > 0) 
			System.out.println("Pair");
		else 
			System.out.println("High Card");
		
		// DRAW:
		/*if(trios > 0) System.out.println("Draw, Poker");
		else if(trios > 0 || (trios > 0 && parejas > 0)) System.out.println("Draw, Full House");
		else if(parejas > 0) {
			System.out.println("Draw, Trio");	
			System.out.println("Draw, Double Pair");
		}
		*/
		//else if(!pair && !poker) System.out.println("Draw, Pair");
		
	}
	
	
	private static void parseArgs(String[] args) {
		
	}
	
	public void loadDeck(BufferedReader in) throws Exception {
		int value;
		char suit = ' '; 
			while ((value = in.read()) != -1) {
				suit = (char) in.read();
				Carta c = new Carta(suit, value);
				cardList.add(c);
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

}

