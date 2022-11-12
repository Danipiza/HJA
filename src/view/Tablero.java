package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tablero extends JPanel {
	
    private Image backgroundImage;
	
	public Tablero() {
		setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("resources/Tablero.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        
    }
    

}
