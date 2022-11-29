package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private JPanel tablero;
	private List<JLabel> cartas1;
	private List<JLabel> cartas2;
	private List<JLabel> porcentajes;
	
	public MainWindow(Game g) {
		game = g;
		initGUI();
	}
	
	private void initGUI()  {
		tablero = new Tablero();
		cartas1 = new ArrayList<JLabel>();
		cartas2 = new ArrayList<JLabel>();
		porcentajes = new ArrayList<JLabel>();
		//setContentPane(tablero);	
		
		pintarJugadores();
		initBotones();
		add(tablero);
		setSize(1400,970); 
		setVisible(true);
	}
	
	private void initBotones() {
		JButton sig = new JButton("Siguiente");
		sig.setBounds(50, 40, 90, 30);
		sig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.jugar();
				pintarCartasTablero();
				pintarPorcentajes();
				
			}
		});
		tablero.add(sig);
		
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
		fold4.setBounds(905, 655, 90, 30);
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
		fold5.setBounds(425, 655, 90, 30);
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
		add(porcentajeJ1);
		
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
		add(porcentajeJ2);
		
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
		add(porcentajeJ3);
		
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
		add(porcentajeJ4);

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
		add(porcentajeJ5);
		
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
		add(porcentajeJ6);
		
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