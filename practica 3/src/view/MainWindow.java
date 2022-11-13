package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Card;
import logic.Game;
import logic.Player;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	private JButton _btn;
	private JPanel tablero;
	
	public MainWindow(Game g) {
		game = g;
		initGUI();
	}
	
	private void initGUI()  {
		tablero = new Tablero();
		//setContentPane(tablero);	
		
		_btn = initRepartirButton();
		tablero.add(_btn);
		add(tablero);
		setSize(800,554); 
		setVisible(true);

	}
	
	private JButton initRepartirButton() {
		JButton aux = new JButton("Siguiente");
		aux.setBounds(50, 40, 90, 30);
		aux.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.jugar();
				pintarCartas();
			}
		});
		return aux;
	}
	
		
	
	private void pintarCartas() {
		pintarCartasJugadores();
		pintarCartasTablero();
		
	}
	
	private void pintarCartasTablero() {
		List<Card> board = game.getCartasBoard();
		int posX = 280;
		for (Card card : board) {
			JLabel carta = card.toImage();
			tablero.add(carta);
			carta.setBounds(posX, 210, 50, 73);
			posX += 50;
		}
		
	}
	
	private void pintarCartasJugadores() {
		List<Player> players = game.getCartasPlayers();
		
		//Player 1
		JLabel carta1j1 = players.get(0).getFirstCard().toImage();
		tablero.add(carta1j1);
		carta1j1.setBounds(225, 30, 50, 73);
		JLabel carta2j1 = players.get(0).getSecondCard().toImage();
		tablero.add(carta2j1);
		carta2j1.setBounds(280, 30, 50, 73);
		
		//Player 2
		JLabel carta1j2 = players.get(1).getFirstCard().toImage();
		tablero.add(carta1j2);
		carta1j2.setBounds(500, 30, 50, 73);
		JLabel carta2j2 = players.get(1).getSecondCard().toImage();
		tablero.add(carta2j2);
		carta2j2.setBounds(555, 30, 50, 73);
		
		
		//Player 3
		JLabel carta1j3 = players.get(2).getFirstCard().toImage();
		tablero.add(carta1j3);
		carta1j3.setBounds(675, 180, 50, 73);
		JLabel carta2j3 = players.get(2).getSecondCard().toImage();
		tablero.add(carta2j3);
		carta2j3.setBounds(730, 180, 50, 73);
		
		//Player 4
		JLabel carta1j4 = players.get(3).getFirstCard().toImage();
		tablero.add(carta1j4);
		carta1j4.setBounds(500, 320, 50, 73);
		JLabel carta2j4 = players.get(3).getSecondCard().toImage();
		tablero.add(carta2j4);
		carta2j4.setBounds(555, 320, 50, 73);

		//Player 5
		JLabel carta1j5 = players.get(4).getFirstCard().toImage();
		tablero.add(carta1j5);
		carta1j5.setBounds(225, 320, 50, 73);
		JLabel carta2j5 = players.get(4).getSecondCard().toImage();
		tablero.add(carta2j5);
		carta2j5.setBounds(280, 320, 50, 73);
		
		//Player 6
		JLabel carta1j6 = players.get(5).getFirstCard().toImage();
		tablero.add(carta1j6);
		carta1j6.setBounds(70, 190, 50, 73);
		JLabel carta2j6 = players.get(5).getSecondCard().toImage();
		tablero.add(carta2j6);
		carta2j6.setBounds(125, 190, 50, 73);
	}

}
