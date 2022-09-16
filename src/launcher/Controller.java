package launcher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Controller {
	
	private List<Carta> cardList;
	
	public void run() {
		
		//cardList = new ArrayList<>();
		
		
		//parse
		
		//Comparisons
		
		int countColor = 0;
		for(Carta c : cardList) {
			
		}
		
		
	}
	
	
	private static void parseArgs(String[] args) {
		
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
