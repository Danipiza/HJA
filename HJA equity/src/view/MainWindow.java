package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Card;
import logic.Game;
import logic.Pair;
import logic.Player;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	private JPanel tablero;
	private List<JLabel> cartas1;
	private List<JLabel> cartas2;
	private List<JLabel> porcentajes;
	JLabel input1Label;
	JTextField inputJug1;
	JLabel input2Label;
	JTextField inputJug2;
	JLabel input3Label;
	JTextField inputJug3;
	JLabel input4Label;
	JTextField inputJug4;
	JLabel input5Label;
	JTextField inputJug5;
	JLabel input6Label;
	JTextField inputJug6;
	boolean preflop;
	
	public MainWindow(Game g) {
		game = g;
		initGUI();
	}
	
	private void initGUI()  {
		tablero = new Tablero();
		cartas1 = new ArrayList<JLabel>();
		cartas2 = new ArrayList<JLabel>();
		porcentajes = new ArrayList<JLabel>();
		preflop = true;
		
		initBotones();
		add(tablero);
		setSize(1400,970); 
		setVisible(true);
	}
	
	
	
	private void initBotones() {
		JButton input = new JButton("introducir cartas");
		input.setBounds(50, 75, 150, 30);
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setInputs();
				input.setVisible(false);
			}
		});
		tablero.add(input);
		
		JButton sig = new JButton("Siguiente");
		sig.setBounds(50, 40, 90, 30);
		
		sig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(preflop) {
					hideLabels();
					input.setVisible(false);
				}				
				game.jugar();
				if(preflop) {
					pintarJugadores();
					setPreflop(false);
				} else
					pintarPorcentajes();
				pintarCartasTablero();
			}
		});
		tablero.add(sig);
		
		showInputs();		
		
		JButton fold1 = new JButton("Fold");
		fold1.setBounds(425, 270, 90, 30);
		fold1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(0);
					removePlayer(0);
					fold1.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold1);
		
		JButton fold2 = new JButton("Fold");
		fold2.setBounds(905, 275, 90, 30);
		fold2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(1);
					removePlayer(1);
					fold2.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold2);
		
		JButton fold3 = new JButton("Fold");
		fold3.setBounds(1210, 534, 90, 30);
		fold3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(2);
					removePlayer(2);
					fold3.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold3);
		
		JButton fold4 = new JButton("Fold");
		fold4.setBounds(905, 625, 90, 30);
		fold4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(3);
					removePlayer(3);
					fold4.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold4);
		
		JButton fold5 = new JButton("Fold");
		fold5.setBounds(425, 625, 90, 30);
		fold5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(4);
					removePlayer(4);
					fold5.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold5);
		
		JButton fold6 = new JButton("Fold");
		fold6.setBounds(155, 546, 90, 30);
		fold6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.foldIsValid()) {
					game.foldPlayer(5);
					removePlayer(5);
					fold6.setVisible(false);
					pintarPorcentajes();
				}
			}
		});
		tablero.add(fold6);
	}
	
	private void setPreflop(boolean b) {
		preflop = b;
	}
	
	private void showInputs() {
		//P1
		input1Label = new JLabel("Introduzca las cartas:");
		tablero.add(input1Label);
		input1Label.setBounds(300, 300, 125, 30);
		input1Label.setOpaque(true);
		inputJug1 = new JTextField(); 
		tablero.add(inputJug1);
		inputJug1.setBounds(425, 300, 90, 30);
		//P2 
		input2Label = new JLabel("Introduzca las cartas:");
		tablero.add(input2Label);
		input2Label.setBounds(780, 300, 125, 30);
		input2Label.setOpaque(true);
		inputJug2 = new JTextField(); 
		tablero.add(inputJug2);
		inputJug2.setBounds(905, 300, 90, 30);
		//P3
		input3Label = new JLabel("Introduzca las cartas:");
		tablero.add(input3Label);
		input3Label.setBounds(1085, 564, 125, 30);
		input3Label.setOpaque(true);
		inputJug3 = new JTextField(); 
		tablero.add(inputJug3);
		inputJug3.setBounds(1210, 564, 90, 30);
		//P4 
		input4Label = new JLabel("Introduzca las cartas:");
		tablero.add(input4Label);
		input4Label.setBounds(780, 655, 125, 30);
		input4Label.setOpaque(true);
		inputJug4 = new JTextField(); 
		tablero.add(inputJug4);
		inputJug4.setBounds(905, 655, 90, 30);	
		//P5
		input5Label = new JLabel("Introduzca las cartas:");
		tablero.add(input5Label);
		input5Label.setBounds(300, 655, 125, 30);
		input5Label.setOpaque(true);
		inputJug5 = new JTextField(); 
		tablero.add(inputJug5);
		inputJug5.setBounds(425, 655, 90, 30);
		//P6 
		input6Label = new JLabel("Introduzca las cartas:");
		tablero.add(input6Label);
		input6Label.setBounds(30, 576, 125, 30);
		input6Label.setOpaque(true);
		inputJug6 = new JTextField(); 
		tablero.add(inputJug6);
		inputJug6.setBounds(155, 576, 90, 30);
	}
	
	private void setInputs() {
		HashMap<Integer, Pair<Card, Card>> inputs = new HashMap <Integer, Pair<Card, Card>>();
		String input;
		String[] cards;
		
		//player 1
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug1.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(0, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}
		
		//Player 2
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug2.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(1, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}
		//Player 3
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug3.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(2, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}
		//Player 4
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug4.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(3, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}
		//Player 5
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug5.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(4, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}
		//Player 6
		try { //SI HAY ALGO ESCRITO EN EL JTextField...
			input = inputJug6.getText(); //i = c1,c2
			cards = input.split(",");
			inputs.put(5, new Pair<Card, Card>(getCardFromDeck(cards[0]), getCardFromDeck(cards[1])));
		} catch(Exception e) {
			//do Nothing
		}	
		
		hideLabels();
		game.inputCards(inputs);
	}
	
	private void hideLabels() {
		input1Label.setVisible(false);
		inputJug1.setVisible(false);
		input2Label.setVisible(false);
		inputJug2.setVisible(false);
		input3Label.setVisible(false);
		inputJug3.setVisible(false);
		input4Label.setVisible(false);
		inputJug4.setVisible(false);
		input5Label.setVisible(false);
		inputJug5.setVisible(false);
		input6Label.setVisible(false);
		inputJug6.setVisible(false);
	}
	
	Card getCardFromDeck(String card) {						
		return game.lookForCard(card.charAt(0), card.charAt(1));
	}
	
	protected void removePlayer(int i) {
		cartas1.get(i).setVisible(false);
		cartas2.get(i).setVisible(false);
		porcentajes.get(i).setVisible(false);
	}

	private void pintarJugadores() {		
		List<Player> players = game.getCartasPlayers();
		
		//Player 1		
		JLabel carta1j1 = players.get(0).getFirstCard().toImage();
		cartas1.add(carta1j1);
		tablero.add(carta1j1);
		carta1j1.setBounds(400, 75, 75, 110);
		JLabel carta2j1 = players.get(0).getSecondCard().toImage();
		cartas2.add(carta2j1);
		tablero.add(carta2j1);
		carta2j1.setBounds(480, 75, 75, 110);
		
		JLabel porcentajeJ1 = new JLabel();
		porcentajeJ1.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ1.setForeground(Color.white);
		porcentajeJ1.setBounds(430, 190, 120, 100);
		porcentajes.add(porcentajeJ1);
		tablero.add(porcentajeJ1);
		
		//Player 2
		JLabel carta1j2 = players.get(1).getFirstCard().toImage();
		cartas1.add(carta1j2);
		tablero.add(carta1j2);
		carta1j2.setBounds(880, 75, 75, 110);
		JLabel carta2j2 = players.get(1).getSecondCard().toImage();
		cartas2.add(carta2j2);
		tablero.add(carta2j2);
		carta2j2.setBounds(960, 75, 75, 110);
		
		JLabel porcentajeJ2 = new JLabel();
		porcentajeJ2.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ2.setForeground(Color.white);
		porcentajeJ2.setBounds(910, 195, 120, 100);
		porcentajes.add(porcentajeJ2);
		tablero.add(porcentajeJ2);
		
		//Player 3
		JLabel carta1j3 = players.get(2).getFirstCard().toImage();
		cartas1.add(carta1j3);
		tablero.add(carta1j3);
		carta1j3.setBounds(1185, 335, 75, 110);
		JLabel carta2j3 = players.get(2).getSecondCard().toImage();
		cartas2.add(carta2j3);
		tablero.add(carta2j3);
		carta2j3.setBounds(1265, 335, 75, 110);
		
		JLabel porcentajeJ3 = new JLabel();
		porcentajeJ3.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ3.setForeground(Color.white);
		porcentajeJ3.setBounds(1214, 454, 120, 100);
		porcentajes.add(porcentajeJ3);
		tablero.add(porcentajeJ3);
		
		//Player 4
		JLabel carta1j4 = players.get(3).getFirstCard().toImage();
		cartas1.add(carta1j4);
		tablero.add(carta1j4);
		carta1j4.setBounds(880, 772, 75, 110);
		JLabel carta2j4 = players.get(3).getSecondCard().toImage();
		cartas2.add(carta2j4);
		tablero.add(carta2j4);
		carta2j4.setBounds(960, 772, 75, 110);
		
		JLabel porcentajeJ4 = new JLabel();
		porcentajeJ4.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ4.setForeground(Color.white);
		porcentajeJ4.setBounds(910, 700, 120, 100);
		porcentajes.add(porcentajeJ4);
		tablero.add(porcentajeJ4);

		//Player 5
		JLabel carta1j5 = players.get(4).getFirstCard().toImage();
		cartas1.add(carta1j5);
		tablero.add(carta1j5);
		carta1j5.setBounds(400, 772, 75, 110);
		JLabel carta2j5 = players.get(4).getSecondCard().toImage();
		cartas2.add(carta2j5);
		tablero.add(carta2j5);
		carta2j5.setBounds(480, 772, 75, 110);
		
		JLabel porcentajeJ5 = new JLabel();	
		porcentajeJ5.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ5.setForeground(Color.white);
		porcentajeJ5.setBounds(430, 700, 120, 100);
		porcentajes.add(porcentajeJ5);
		tablero.add(porcentajeJ5);
		
		//Player 6
		JLabel carta1j6 = players.get(5).getFirstCard().toImage();
		cartas1.add(carta1j6);
		tablero.add(carta1j6);
		carta1j6.setBounds(130, 350, 75, 110);
		JLabel carta2j6 = players.get(5).getSecondCard().toImage();
		cartas2.add(carta2j6);
		tablero.add(carta2j6);
		carta2j6.setBounds(210, 350, 75, 110);
		
		JLabel porcentajeJ6 = new JLabel();		
		porcentajeJ6.setFont(new Font("Arial", Font.PLAIN, 25));
		porcentajeJ6.setForeground(Color.white);
		porcentajeJ6.setBounds(160, 466, 120, 100);
		porcentajes.add(porcentajeJ6);
		tablero.add(porcentajeJ6);
		
	}
	
	private void pintarCartasTablero() {
		List<Card> board = game.getCartasBoard();
		int posX = 490;
		for (Card card : board) {
			JLabel carta = card.toImage();
			tablero.add(carta);
			carta.setBounds(posX, 400, 75, 110);
			posX += 80;
		}
		
	}
	
	public void pintarPorcentajes() {
		for (int i = 0; i < 6; i++) {
			porcentajes.get(i).setText(game.getPorcentajes(i));
		}
	}

}