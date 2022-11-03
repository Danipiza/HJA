package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;


public class ComboWindow {
	
	private List<String> straightFlush; private int numStraightFlush;
	private List<String> four;			private int numFour;
	private List<String> fullHouse;		private int numFullHouse;
	private List<String> flush;			private int numFlush;
	private List<String> straight;		private int numStraight;
	private List<String> three;			private int numThree;
	private List<String> doublePair;	private int numDoublePair;
	private List<String> overpair;		private int numOverpair;
	private List<String> topPair;		private int numTopPair;
	private List<String> ppBelowTp;		private int numPPBelowTp;
	private List<String> middlePair;	private int numMiddlePair;
	private List<String> weakPair;		private int numWeakPair;
	private List<String> aceHigh;		private int numAceHigh;
	private List<String> noMadeHand;	private int numNoMadeHand;
	
	private int totalCombos;
	
	public ComboWindow() {
		straightFlush = new LinkedList<String>(); 	numStraightFlush = 0;
		four = new LinkedList<String>();			numFour = 0;
		fullHouse = new LinkedList<String>();		numFullHouse = 0;
		flush = new LinkedList<String>();			numFlush = 0;
		straight = new LinkedList<String>();		numStraight = 0;
		three = new LinkedList<String>();			numThree = 0;
		doublePair = new LinkedList<String>();		numDoublePair = 0;
		overpair = new LinkedList<String>();		numOverpair = 0;
		topPair = new LinkedList<String>();			numTopPair = 0;
		ppBelowTp = new LinkedList<String>();		numPPBelowTp = 0;
		middlePair = new LinkedList<String>();		numMiddlePair = 0;
		weakPair = new LinkedList<String>();		numWeakPair = 0;
		aceHigh = new LinkedList<String>();			numAceHigh = 0;
		noMadeHand = new LinkedList<String>();		numNoMadeHand = 0;
		
		totalCombos = 0;
	}

	
	public void updateCombos(Set<String> range, List<String> board) {
		cardSorting(board);
		
		//BOARD FLUSH
		HashMap<Character, Integer> suitMap = new HashMap<Character, Integer>();
		
		suitMap.put('s', 0);
		suitMap.put('h', 0);
		suitMap.put('d', 0);
		suitMap.put('c', 0);
		
		for (int i = 0; i < board.size(); i++) {
			char suitAux = board.get(i).charAt(1);
			suitMap.put(suitAux, suitMap.get(suitAux) + 1);
		}

		
		int maxPalos = 0;
		char paloRepetido = ' ';
		for (Entry<Character, Integer> e : suitMap.entrySet()) {
			 if (maxPalos < e.getValue()) {
					paloRepetido = e.getKey();
					maxPalos = e.getValue();
				}
		}
		
		//BOARD STRAIGHT
		Boolean boardStraight;
		if (board.size() < 5) {
			boardStraight = false;
		}
		else {
			boardStraight = true;
			int s = 1;
			while (s < board.size() && boardStraight) {
				if (toInt(board.get(s-1).charAt(0)) != toInt(board.get(s).charAt(0)) + 1)
					boardStraight = false;
				s++;
			}
		}
		
		//BOARD FULL HOUSE
		int cont = 0;
		boolean pareja = false, trio = false;
		
		for (int i = 0; i < board.size() - 1; i++){
			if (board.get(i).charAt(0) == board.get(i+1).charAt(0))
				cont++;
			else {
				if (cont == 1) pareja = true;
				else if (cont == 2) trio = true;
				cont = 0;
			}
		}
		
		if (cont == 1) pareja = true;
		else if (cont == 2) trio = true;
		
		boolean boardFullHouse = false;
		if (pareja && trio) boardFullHouse = true;
		
		for (String rangeHand : range) {
			String tipo;
			List<String> combinaciones = new ArrayList<String>();
			
			//COMBINACIONES
			String aux = "";
			char[] boardSuits = { 'h', 'c', 'd', 's'};
			if (rangeHand.charAt(rangeHand.length() - 1) == 's') {
				tipo = "suited";
				for (int i = 0; i < 4; i++) {
					aux += rangeHand.charAt(0); aux += boardSuits[i]; aux += rangeHand.charAt(1); aux += boardSuits[i];
					boolean add = true;
					for (String card : board) {
						if (aux.substring(0, 2).equals(card) || aux.substring(2).equals(card))
							add = false;
					}
					
					if (add) combinaciones.add(aux);
					aux = "";
				}
			}
			else if (rangeHand.charAt(rangeHand.length() - 1) == 'o') {
				tipo = "offsuited";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (i != j) {
							aux += rangeHand.charAt(0);	aux += boardSuits[i]; aux += rangeHand.charAt(1); aux += boardSuits[j];
							boolean add = true;
							for (String card : board) {
								if (aux.substring(0, 2).equals(card) || aux.substring(2).equals(card))
									add = false;
							}
							
							if (add) combinaciones.add(aux);
							aux = "";
						}
					}
				}
			}
			else {
				tipo = "offsuited";
				for (int i = 0; i < 4; i++) {
					for (int j = i+1; j < 4; j++) {
						aux += rangeHand.charAt(0);	aux += boardSuits[i]; aux += rangeHand.charAt(1); aux += boardSuits[j];
						boolean add = true;
						for (String card : board) {
							if (aux.substring(0, 2).equals(card) || aux.substring(2).equals(card))
								add = false;
						}
						
						if (add) combinaciones.add(aux);
						aux = "";
					}
				}
			}
			
			System.out.println(combinaciones);
			
			//FLUSH
			
			aux = "";
			if (maxPalos < 5) {
				if (maxPalos == 3) {
					if (tipo == "suited") {
						aux += rangeHand.charAt(0); aux += paloRepetido; aux += rangeHand.charAt(1); aux += paloRepetido; 
						//System.out.println(aux + combinaciones.contains(aux));
						if(combinaciones.contains(aux)) {
							combinaciones.remove(aux);
							if (isStraightFlush(aux,board))
								straightFlush.add(aux);
							else
								flush.add(aux);
						}
						aux = "";
					}
				}
				else if (maxPalos == 4) {
					if (tipo == "suited") {
						aux += rangeHand.charAt(0); aux += paloRepetido; aux += rangeHand.charAt(1); aux += paloRepetido; 
						//System.out.println(aux + combinaciones.contains(aux));
						if(combinaciones.contains(aux)) {
							combinaciones.remove(aux);
							if (isStraightFlush(aux,board))
								straightFlush.add(aux);
							else
								flush.add(aux);
						}
					}
					else {
						for (char suit : boardSuits) {
							if (suit != paloRepetido) {
								aux += rangeHand.charAt(0) + paloRepetido + rangeHand.charAt(1) + suit; 
								if(combinaciones.contains(aux)) {
									combinaciones.remove(aux);
									if (isStraightFlush(aux,board))
										straightFlush.add(aux);
									else
										flush.add(aux);
								}
							}
						}
					}
				}
			}
			
			//STRAIGHT
			boolean handStraight = false;
			List<Character> straightCombination = new ArrayList<Character>();
			straightCombination.add(rangeHand.charAt(0)); straightCombination.add(rangeHand.charAt(1));
			for (int x = 0; x < board.size(); x++) {
				straightCombination.add(board.get(x).charAt(0));
			}
			if (!boardStraight) {
				charSorting(straightCombination);
				//System.out.println(straightCombination);
				
				int straightCards = 0, maxStraightCards = 0;
				for (int i = 1; i < straightCombination.size(); i++) {
					if (toInt(straightCombination.get(i-1)) == toInt(straightCombination.get(i)) + 1) {
						straightCards++;
					}
					else if (toInt(straightCombination.get(i-1)) > toInt(straightCombination.get(i)) + 1) {
						maxStraightCards = Math.max(maxStraightCards, straightCards);
					}
				}
				maxStraightCards = Math.max(maxStraightCards, straightCards);
				//System.out.println(maxStraightCards);
				if (straightCards > 3) handStraight = true;
				
			}
			
			//FULL HOUSE
			int contR = 0;
			boolean parejaR = false, trioR = false;
			System.out.println(straightCombination);
			for (int i = 0; i < straightCombination.size() - 1; i++){
				if (straightCombination.get(i) == straightCombination.get(i+1))
					contR++;
				else {
					if (contR == 1) parejaR = true;
					else if (contR == 2) trioR = true;
					contR = 0;
				}
			}
			if (contR == 1) parejaR = true;
			else if (contR == 2) trioR = true;
			
			boolean rangeFullHouse = false;
			if (parejaR && trioR) rangeFullHouse = true;
			
			
			
			//SAME CARDS
			System.out.println(board);
			int repeat1 = 0, repeat2 = 0;
			if (rangeHand.charAt(0) == rangeHand.charAt(1)) {
				for (String card : board)
					if (card.charAt(0) == rangeHand.charAt(0))
						repeat1++;
				
				repeat1 += 2;
			}
			else {
				for (String card : board) {
					if (card.charAt(0) == rangeHand.charAt(0))
						repeat1++;
					else if (card.charAt(0) == rangeHand.charAt(1))
						repeat2++;
				}
				repeat1++; repeat2++;
			}
			System.out.println(repeat1 + " " + repeat2);
			
			
			//result
			String add = rangeHand;
			add += " (" + combinaciones.size() + ")";
			if (repeat1 == 4 || repeat2 == 4) {
				four.add(add);
				numFour += combinaciones.size();
			}
			else if (!boardFullHouse && rangeFullHouse) {
				fullHouse.add(add);
				numFullHouse += combinaciones.size();
			}
			else if (handStraight) {
				straight.add(add);
				numStraight += combinaciones.size();
			}
			else if (repeat1 == 3 || repeat2 == 3) {
				three.add(add);
				numThree += combinaciones.size();
			}
			else if (repeat1 == 2 && repeat2 == 2) {
				doublePair.add(add);
				numDoublePair += combinaciones.size();
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) > toInt(board.get(0).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) > toInt(board.get(0).charAt(0))) {
				overpair.add(add);
				numOverpair += combinaciones.size();
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) == toInt(board.get(0).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) == toInt(board.get(0).charAt(0))) {
				topPair.add(add);
				numTopPair += combinaciones.size();
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) < toInt(board.get(0).charAt(0)) && toInt(rangeHand.charAt(0)) > toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) < toInt(board.get(0).charAt(0)) && toInt(rangeHand.charAt(1)) > toInt(board.get(1).charAt(0))) {
				ppBelowTp.add(add);
				numPPBelowTp += combinaciones.size();
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) == toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) == toInt(board.get(1).charAt(0))) {
				middlePair.add(add);
				numMiddlePair += combinaciones.size();
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) < toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) < toInt(board.get(1).charAt(0))) {
				weakPair.add(add);
				numWeakPair += combinaciones.size();
			}	
			else if (rangeHand.charAt(0) == 'A' || rangeHand.charAt(1) == 'A') {
				aceHigh.add(add);
				numAceHigh += combinaciones.size();
			}
			else {
				noMadeHand.add(add);
				numNoMadeHand += combinaciones.size();
			}
		}
		
		System.out.println("straight flush " + numStraightFlush + " " + straightFlush);
		System.out.println("four " + numFour + " " + four);
		System.out.println("fullHouse " + numFullHouse + " " + fullHouse);
		System.out.println("flush " + numFlush + " " + flush);
		System.out.println("straight " + numStraight + " " + straight);
		System.out.println("three " + numThree + " " + three);
		System.out.println("double pair " + numDoublePair + " " + doublePair);
		System.out.println("overpair " + numOverpair + " " + overpair);
		System.out.println("topPair " + numTopPair + " " + topPair);
		System.out.println("ppBelowTp " + numPPBelowTp + " " + ppBelowTp);
		System.out.println("middlePair " + numMiddlePair + " " + middlePair);
		System.out.println("weakPair " + numWeakPair + " " + weakPair);
		System.out.println("ace high " + numAceHigh + " " + aceHigh);
		System.out.println("no made hand " + numNoMadeHand + " " + noMadeHand);
		
		totalCombos =  numStraightFlush + numFour + numFullHouse + numFlush + numStraight + numThree + numDoublePair 
		+ numOverpair + numTopPair + numPPBelowTp + numMiddlePair + numWeakPair + numAceHigh + numNoMadeHand;
		
		System.out.println();
		System.out.println("Numero total de combos " + totalCombos);
	}
	
	
	private boolean isStraightFlush(String aux, List<String> board) {
		List<String> combinacion = new LinkedList<String>();
		for (String s : board)
			combinacion.add(s);
		
		combinacion.add(aux.substring(0,2));
		combinacion.add(aux.substring(2));
		cardSorting(combinacion);
		int cont = 0, max = 0;
		for (int i = 1; i < combinacion.size(); i++) {
			if (toInt(combinacion.get(i-1).charAt(0)) == toInt(combinacion.get(i).charAt(0)) + 1 
					&& combinacion.get(i-1).charAt(1) == combinacion.get(i).charAt(1) ) {
				cont++;
			}
			else {
				max = Math.max(max, cont);
				cont = 0;
			}
		}
		max = Math.max(max, cont);
		return max > 3;
	}


	private void cardSorting(List<String> board) {
		Collections.sort(board, new SortLocation());
	}

	class SortLocation implements Comparator<String>{
		@Override
		public int compare(String s1, String s2) {
			if (toInt(s1.charAt(0)) > toInt(s2.charAt(0)))
				return -1;
			else if (toInt(s1.charAt(0)) == toInt(s2.charAt(0)))
				return 0;
			return 1;
		}
	}
	
	private void charSorting(List<Character> hand) {
		Collections.sort(hand, new SortChar());
	}
	
	class SortChar implements Comparator<Character>{
		public int compare(Character c1, Character c2) {
			if (toInt(c1) > toInt(c2))
				return -1;
			else if (toInt(c1) == toInt(c2))
				return 0;
			return 1;
		}
	}
	
	public int toInt(char val) {
		int ret;
		if (val == 'A')	ret = 14;
		else if (val == 'K') ret = 13;
		else if (val == 'Q') ret = 12;
		else if (val == 'J') ret = 11;
		else if (val == 'T') ret = 10;
		else ret = val - 48;
		
		return ret;
	}
	
}
