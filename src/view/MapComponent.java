package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import launcher.Card;
import launcher.Controller;
import launcher.Player;


public class MapComponent  extends JPanel {
	
	private static final long serialVersionUID = 1L;	
	private static final Color BG_COLOR = Color.WHITE;		
	private static final Color PLAYER_LABEL_COLOR = new Color(200, 100, 0);
		
	Controller ctrl;
	

	MapComponent(Controller ctrl) {		
		initGUI();
		this.ctrl = ctrl;
	}	
	
	private void initGUI() {			
		setPreferredSize(new Dimension (300, 200));
	}
	
	public void paintComponent(Graphics graphics) {		
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.setColor(BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (ctrl.getPart() >= 1 && ctrl.getPart() <= 4) {
			drawMap(g);
		}
		else {
			g.setColor(Color.BLACK);
			g.drawString("Waiting...", getWidth() / 2 - 50, getHeight() / 2);
			
		}
	}

	private void drawMap(Graphics g) {
		int x = 1, z = 30;
		
		g.setColor(PLAYER_LABEL_COLOR);
		
		// JUGADORES
		for (Player p : ctrl.getPlayers()) {
			int y = 20;
			g.drawString("JUGADOR " + x, y + 10, z-10);
			for (Card c : p.getCards()) {
				String aux = "";
				if(c.getSuit() == 'h') {
					aux = "hearts";
				}
				else if(c.getSuit() == 'd'){
					aux = "diamonds";
				}
				else if(c.getSuit() == 'c') {
					aux = "clubs";
				}
				else if(c.getSuit() == 's') {
					aux = "spades";
				}
				Image i = loadImage(c.getValue() + "_of_" + aux + ".png");;
				g.drawImage(i, y, z, 96, 96, this);
				y+=106;
			}
			
			x++;
			z+=116;
		}


		/*
		// MESA ------------------------------------------
		g.drawString("MESA", 1380, 20);
		g.drawImage(diamonds[0], 1370, 30, 96, 96, this);
		g.drawImage(diamonds[1], 1476, 30, 96, 96, this);
		g.drawImage(diamonds[2], 1582, 30, 96, 96, this);
		g.drawImage(diamonds[3], 1688, 30, 96, 96, this);
		g.drawImage(diamonds[4], 1794, 30, 96, 96, this);*/
		
	}	
	
	
	
	// FUNCION QUE CARGA LAS IMAGENES
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}	
	
	// FUNCION QUE ACTUALIZA EL MAPA
	public void update() {
		repaint();
	}
	
	
	
	
	
}
