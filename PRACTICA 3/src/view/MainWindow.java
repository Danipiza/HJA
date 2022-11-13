package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
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
		setSize(1400,970); 
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
		int posX = 490;
		for (Card card : board) {
			JLabel carta = card.toImage();
			tablero.add(carta);
			carta.setBounds(posX, 400, 75, 110);
			posX += 80;
		}
		
	}
	
	private void pintarCartasJugadores() {		
		List<Player> players = game.getCartasPlayers();
		
		//Player 1		
		JLabel carta1j1 = players.get(0).getFirstCard().toImage();
		tablero.add(carta1j1);
		carta1j1.setBounds(400, 75, 75, 110);
		JLabel carta2j1 = players.get(0).getSecondCard().toImage();
		tablero.add(carta2j1);
		carta2j1.setBounds(480, 75, 75, 110);
		
		JLabel porcentajeJ1 = new JLabel(game.getPorcentajes(0)+"%");		
		porcentajeJ1.setForeground(Color.white);
		porcentajeJ1.setBounds(440, 95, 100, 100);
		tablero.add(porcentajeJ1);
		
		
		
		//Player 2
		JLabel carta1j2 = players.get(1).getFirstCard().toImage();
		tablero.add(carta1j2);
		carta1j2.setBounds(880, 75, 75, 110);
		JLabel carta2j2 = players.get(1).getSecondCard().toImage();
		tablero.add(carta2j2);
		carta2j2.setBounds(960, 75, 75, 110);
		
		JLabel porcentajeJ2 = new JLabel(game.getPorcentajes(1)+"%");		
		porcentajeJ2.setForeground(Color.white);
		porcentajeJ2.setBounds(920, 105, 100, 100);
		tablero.add(porcentajeJ2);
		
		
		//Player 3
		JLabel carta1j3 = players.get(2).getFirstCard().toImage();
		tablero.add(carta1j3);
		carta1j3.setBounds(1185, 335, 75, 110);
		JLabel carta2j3 = players.get(2).getSecondCard().toImage();
		tablero.add(carta2j3);
		carta2j3.setBounds(1265, 335, 75, 110);
		
		JLabel porcentajeJ3 = new JLabel(game.getPorcentajes(2)+"%");		
		porcentajeJ3.setForeground(Color.white);
		porcentajeJ3.setBounds(1224, 365, 100, 100);
		tablero.add(porcentajeJ3);
		
		//Player 4
		JLabel carta1j4 = players.get(3).getFirstCard().toImage();
		tablero.add(carta1j4);
		carta1j4.setBounds(880, 772, 75, 110);
		JLabel carta2j4 = players.get(3).getSecondCard().toImage();
		tablero.add(carta2j4);
		carta2j4.setBounds(960, 772, 75, 110);
		
		JLabel porcentajeJ4 = new JLabel(game.getPorcentajes(3)+"%");		
		porcentajeJ4.setForeground(Color.white);
		porcentajeJ4.setBounds(920, 802, 100, 100);
		tablero.add(porcentajeJ4);

		//Player 5
		JLabel carta1j5 = players.get(4).getFirstCard().toImage();
		tablero.add(carta1j5);
		carta1j5.setBounds(400, 772, 75, 110);
		JLabel carta2j5 = players.get(4).getSecondCard().toImage();
		tablero.add(carta2j5);
		carta2j5.setBounds(480, 772, 75, 110);
		
		JLabel porcentajeJ5 = new JLabel(game.getPorcentajes(4)+"%");		
		porcentajeJ5.setForeground(Color.white);
		porcentajeJ5.setBounds(440, 802, 100, 100);
		tablero.add(porcentajeJ5);
		
		//Player 6
		JLabel carta1j6 = players.get(5).getFirstCard().toImage();
		tablero.add(carta1j6);
		carta1j6.setBounds(130, 350, 75, 110);
		JLabel carta2j6 = players.get(5).getSecondCard().toImage();
		tablero.add(carta2j6);
		
		carta2j6.setBounds(210, 350, 75, 110);
		JLabel porcentajeJ6 = new JLabel(game.getPorcentajes(5)+"%");		
		porcentajeJ6.setForeground(Color.white);
		porcentajeJ6.setBounds(170, 380, 100, 100);
		tablero.add(porcentajeJ6);
	}

}
