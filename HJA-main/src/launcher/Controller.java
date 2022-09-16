package launcher;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Controller {
	
	private List<Carta> cardList;
	
	public Controller() {
		cardList = new ArrayList<Carta>();
	}
	
	public void run() {				
		//Comparisons
		
		int countColor = 0;
		for(Carta c : cardList) {
			
		}
		
		
	}
	
	
	private static void parseArgs(String[] args) {
		
	}
	
	public void loadDeck(InputStream in) throws Exception {
		char value = ' ', suit = ' '; 
		boolean done = false;
		if (in != null) { //Comprobamos que el input es correcto
			value = (char) in.read();
			suit = (char) in.read();
			while (!done) {
				if (in.available() < 2) done = true; //Cuando ya no quede más por leer, se hará una última pasada con la ultima carta
				Carta c = new Carta(suit, Integer.parseInt(String.valueOf(value)));
				cardList.add(c);
				value = (char) in.read();
				suit = (char) in.read();
			}
		}
		else throw new Exception();
	}
	
	private void cardSorting(List<Carta> cardList) {
		Collections.sort(cardList, new SortLocation());
	}

	class SortLocation implements Comparator<Carta>{
		@Override
		public int compare(Carta c1, Carta c2) {
			if (c1.getValor() > c2.getValor())
				return -1;
			else if (c1.getValor() == c2.getValor())
				return 0;
			return 1;
		}
	}

}
