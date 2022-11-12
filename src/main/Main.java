package main;

import javax.swing.SwingUtilities;

import logic.Game;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(new Game()); 
			}
		});
	}

}
