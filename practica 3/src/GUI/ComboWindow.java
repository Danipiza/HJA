package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


import java.util.Map.Entry;

public class ComboWindow extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private int totalCombos;
	
	private List<List<String>> comboList;
	
	private List<Integer> numList;	
	
	private String[] nameList = {"str. flush", "four", "fullhouse", "flush", "straight", "three", "two pair", "over pair",
			"top pair", "pp below tp", "middle pair", "weak pair", "ace high", "no made hand"};
	
	private List<JProgressBar> jbList;
	
	private List<JLabel> jLabelList;
	
	public ComboWindow() {
		jbList = new LinkedList<JProgressBar>();
		comboList = new LinkedList<List<String>>();
		numList = new LinkedList<Integer>();
		
		jLabelList = new LinkedList<JLabel>();
		
		for (int i = 0; i < 14; i++) {
			comboList.add(new LinkedList<String>());
			numList.add(0);
		}
		
		initGUI();				
		
	}
	
	private void initGUI(){
		setLayout(new BorderLayout());
		
		
		for (int i = 0; i < 14; i++) {			
			JLabel nameText = new JLabel(nameList[i]);
			nameText.setBounds(10,10+42*i, 140, 40); 
			nameText.setFont(new Font("Arial", Font.PLAIN, 20));
			add(nameText);
			
			JProgressBar jb;
			jb = new JProgressBar(); 
			jb.setBounds(145,10+42*i,100,40); 				
			jbList.add(jb);
			add(jb);						
			
			JLabel listText = new JLabel("0 []");
			listText.setBounds(255,10+42*i, 2000, 40); 
			listText.setFont(new Font("Arial", Font.PLAIN, 20));							
			add(listText);	
			jLabelList.add(listText);

			repaint();				
		}	
		
		JLabel listText = new JLabel("");									
		add(listText);
		
		/*JLabel combosText = new JLabel("Total number of combos: " + totalCombos);	
		combosText.setBounds(30,1000, 50, 40); 
		combosText.setFont(new Font("Arial", Font.PLAIN, 20));
		add(combosText);*/
		
		
	}
	
	private void clear() {
		comboList = new LinkedList<List<String>>();
		numList = new LinkedList<Integer>();
		
		for (int i = 0; i < 14; i++) {
			comboList.add(new LinkedList<String>());
			numList.add(0);
			jbList.get(i).setValue(0);
		}
		
		
		for(int i = 0; i < 14;i++) {
			jLabelList.get(i).setText("0 []");					
		}
		
		totalCombos = 0;
	}
	
	public void addCombos(Set<String> range, List<String> board) {
		updateCombos(range, board, 1);
	}
	
	public void updateCombos(Set<String> range, List<String> board, int mode) {
		if(mode != 1) {
			clear();
		}
		boardSorting(board);
		
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
			
			//System.out.println(combinaciones);
			
			//FLUSH
			
			aux = "";
			if (maxPalos < 5) {
				if (maxPalos == 3) {
					if (tipo == "suited") {
						aux += rangeHand.charAt(0); aux += paloRepetido; aux += rangeHand.charAt(1); aux += paloRepetido; 
						//System.out.println(aux + combinaciones.contains(aux));
						if(combinaciones.contains(aux)) {
							combinaciones.remove(aux);
							if (isStraightFlush(aux,board)) {
								//comboList.get(0).add(aux);
								
								comboList.get(0).add(aux);
								numList.set(0, numList.get(0) + combinaciones.size());	
								jLabelList.get(0).setText(numList.get(0)+ " " + comboList.get(0));
							}
							else {
								comboList.get(3).add(aux);
								
								comboList.get(3).add(aux);
								numList.set(3, numList.get(3) + combinaciones.size());	
								jLabelList.get(3).setText(numList.get(3)+ " " + comboList.get(3));
								
							}
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
								comboList.get(0).add(aux);
							else
								comboList.get(3).add(aux);
						}
					}
					else {
						for (char suit : boardSuits) {
							if (suit != paloRepetido) {
								aux += rangeHand.charAt(0) + paloRepetido + rangeHand.charAt(1) + suit; 
								if(combinaciones.contains(aux)) {
									combinaciones.remove(aux);
									if (isStraightFlush(aux,board))
										comboList.get(0).add(aux);
									else
										comboList.get(3).add(aux);
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
			//System.out.println(straightCombination);
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
			
			
			
			// SAME CARDS
			//System.out.println(board);
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
			//System.out.println(repeat1 + " " + repeat2);
			
			// RESULT 
			String add = rangeHand;
			add += " (" + combinaciones.size() + ")";
			if (repeat1 == 4 || repeat2 == 4) {
				comboList.get(1).add(add);
				numList.set(1, numList.get(1) + combinaciones.size());	
				jLabelList.get(1).setText(numList.get(1)+ " " + comboList.get(1));
			}
			else if (!boardFullHouse && rangeFullHouse) {
				comboList.get(2).add(add);
				numList.set(2, numList.get(2) + combinaciones.size());	
				jLabelList.get(2).setText(numList.get(2)+ " " + comboList.get(2));
			}
			else if (handStraight) {
				comboList.get(4).add(add);
				numList.set(4, numList.get(4) + combinaciones.size());	
				jLabelList.get(4).setText(numList.get(4)+ " " + comboList.get(4));
			}
			else if (repeat1 == 3 || repeat2 == 3) {
				comboList.get(5).add(add);
				numList.set(5, numList.get(5) + combinaciones.size());	
				jLabelList.get(5).setText(numList.get(5)+ " " + comboList.get(5));
			}
			else if (repeat1 == 2 && repeat2 == 2) {
				comboList.get(6).add(add);
				numList.set(6, numList.get(6) + combinaciones.size());	
				jLabelList.get(6).setText(numList.get(6)+ " " + comboList.get(6));
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) > toInt(board.get(0).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) > toInt(board.get(0).charAt(0))) {
				comboList.get(7).add(add);
				numList.set(7, numList.get(7) + combinaciones.size());	
				jLabelList.get(7).setText(numList.get(7)+ " " + comboList.get(7)); 
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) == toInt(board.get(0).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) == toInt(board.get(0).charAt(0))) {
				comboList.get(8).add(add);
				numList.set(8, numList.get(8) + combinaciones.size());	
				jLabelList.get(8).setText(numList.get(8)+ " " + comboList.get(8));
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) < toInt(board.get(0).charAt(0)) && toInt(rangeHand.charAt(0)) > toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) < toInt(board.get(0).charAt(0)) && toInt(rangeHand.charAt(1)) > toInt(board.get(1).charAt(0))) {
				comboList.get(9).add(add);
				numList.set(9, numList.get(9) + combinaciones.size());	
				jLabelList.get(9).setText(numList.get(9)+ " " + comboList.get(9));
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) == toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) == toInt(board.get(1).charAt(0))) {
				comboList.get(10).add(add);
				numList.set(10, numList.get(10) + combinaciones.size());	
				jLabelList.get(10).setText(numList.get(10)+ " " + comboList.get(10));
			}
			else if (repeat1 == 2 && toInt(rangeHand.charAt(0)) < toInt(board.get(1).charAt(0)) ||
					repeat2 == 2 && toInt(rangeHand.charAt(1)) < toInt(board.get(1).charAt(0))) {
				comboList.get(11).add(add);
				numList.set(11, numList.get(11) + combinaciones.size());
				jLabelList.get(11).setText(numList.get(11)+ " " + comboList.get(11));
			}	
			else if (rangeHand.charAt(0) == 'A' || rangeHand.charAt(1) == 'A') {
				comboList.get(12).add(add);
				numList.set(12, numList.get(12) + combinaciones.size());
				jLabelList.get(12).setText(numList.get(12)+ " " + comboList.get(12));
			}
			else {
				comboList.get(13).add(add);
				numList.set(13, numList.get(13) + combinaciones.size());
				jLabelList.get(13).setText(numList.get(13)+ " " + comboList.get(13));
			}
		}
		
		// ORDENA TODAS LAS LISTAS				
		//finalSorting(comboList.get(1)); //finalSorting(straightFlush);
		finalSorting(comboList.get(2));
		//finalSorting(comboList.get(3));
		finalSorting(comboList.get(4));
		finalSorting(comboList.get(5));
		finalSorting(comboList.get(6));
		finalSorting(comboList.get(7));
		finalSorting(comboList.get(8));
		finalSorting(comboList.get(9));
		finalSorting(comboList.get(10));
		finalSorting(comboList.get(11));
		finalSorting(comboList.get(12));
		finalSorting(comboList.get(13));
		
		
		for(int i = 0; i < 14; i++) {
			//System.out.println(nameList[i] + " " +  numList.get(i) + " " + comboList.get(i));
			totalCombos += numList.get(i);
		}		
		
		for(int i = 0; i < 14; i++) {
			int aux = (numList.get(i) * 100)/totalCombos;
			
			jbList.get(i).setValue(aux);
		}
		
		//System.out.println();
		//System.out.println("Numero total de combos " + totalCombos);
	}	
		
	private boolean isStraightFlush(String aux, List<String> board) {
		List<String> combinacion = new LinkedList<String>();
		for (String s : board)
			combinacion.add(s);
		
		combinacion.add(aux.substring(0,2));
		combinacion.add(aux.substring(2));
		boardSorting(combinacion);
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


	private void boardSorting(List<String> board) {
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
	
	private void finalSorting(List<String> hand) {
		Collections.sort(hand, new SortFinal());
	}
	
	class SortFinal implements Comparator<String>{
		public int compare(String s1, String s2) {
			if (toInt(s1.charAt(0)) > toInt(s2.charAt(0)))
				return -1;
			else if (toInt(s1.charAt(0)) < toInt(s2.charAt(0)))
				return 1;
			else {
				if (toInt(s1.charAt(1)) > toInt(s2.charAt(0)))
					return -1;
				else if (toInt(s1.charAt(1)) < toInt(s2.charAt(1)))
					return 1;
				else {
					if (s1.charAt(2) == 's')
						return -1;
					else return 1;
				}
			}
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
