package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Game;
import logic.Player;

public class MainWindow extends JFrame {
	
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
		JButton aux = new JButton("Repartir");
		aux.setBounds(650, 40, 90, 30);
		aux.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.repartir();
				pintarCartas(game.getCartas());
				
			}
		});
		return aux;
	}
	
	private void pintarCartas(List<Player> players) {
		//Player 1
		JLabel aux = players.get(0).getFirstCard().toImage();
		tablero.add(aux);
		//No muestra la primera carta del jugador 1 (existe la carta, pero no se carga bien en el JPanel)
		
	}

}
