package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import view.MainWindow;

public class Main {

	private static String _inFile = "entrada.txt";
	private static String _outFile = null;
	private static int apartado = 0; //A leer
	
	
	public static void main(String[] args) throws IOException {
	
		//System.out.println(args[1]);
		_inFile = args[1];
		if (args.length == 4) {
			apartado = Integer.parseInt(args[0]);
			if (args[3].equals("gui"))
				startGUIMode();
			else
				startBatchMode();
		}
		else System.out.println("Debe haber 4 argumentos: El modo de juego, el fichero de entrada, de salida y el modo de interfaz:"
				+ "gui o c");
			
	}
	
	private static void startGUIMode() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(_inFile)));
			Controller ctrl = new Controller();
			ctrl.loadDeck4(br);
			MainWindow window = new MainWindow(ctrl, apartado, br);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Se carga el mazo
		
	}
	
	private static void startBatchMode() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(_inFile)));
			Controller ctrl = new Controller();
			apartado = 1;
			if(apartado == 1) {
				ctrl.loadDeck1(br); //Se carga el mazo
				ctrl.run1();
			} else if(apartado == 2) {
				ctrl.loadDeck2(br); //Se carga el mazo
				ctrl.run2();				
			} else if(apartado == 3) {
				ctrl.loadDeck3(br); //Se carga el mazo
				ctrl.run3();
			} else if(apartado == 4) {
				ctrl.loadDeck4(br); //Se carga el mazo
				ctrl.run4();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // FICHERO DE LECTURA

}

}
