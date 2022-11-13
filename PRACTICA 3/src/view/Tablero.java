package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tablero extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	
	public Tablero() {
		setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("resources/Tablero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        
    }
    

}
