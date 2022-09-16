package launcher;

import java.util.List;

public class Carta {
	private String name;
	
	private char suit;
	
	private List<Carta> cardList;
	
	private int valor;
	
	public Carta(/*String name, */char suit, int valor) {
		//this.name = name;
		this.suit = suit;
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}



}