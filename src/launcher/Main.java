package launcher;
import javax.swing.SwingUtilities;

import GUI.MainWindow;


public class Main {
	public static void main(String[] args) {
		System.out.println("sus");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(); 
			}
		});
	}
}
