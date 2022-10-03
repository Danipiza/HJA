package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import launcher.Controller;

public class Main {

	private static String _inFile = null;
	private static String _outFile = null;
	private static int apartado = 1; //A leer
	
	
	public static void main(String[] args) throws IOException {
	
		_inFile = args[1];
		_outFile = args[2];
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
				ctrl.loadDeck3(br); //Se carga el mazo
				ctrl.run3();
			}
			//MainWindow window = new MainWindow(ctrl, apartado, br);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Se carga el mazo
		
	}
	
	private static void startBatchMode() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(_inFile)));
			Controller ctrl = new Controller();
			String output = "";
		
			if(apartado == 1) {
				ctrl.loadDeck1(br); //Se carga el mazo
				output += ctrl.run1();
				while (br.read() != -1) {
					output += '\n';
					ctrl.loadDeck1(br); //Se carga el mazo
					output += ctrl.run1();
				}
			} else if(apartado == 2) {
				ctrl.loadDeck2(br); //Se carga el mazo
				output += ctrl.run2();
				while (br.read() != -1) {
					output += '\n';
					ctrl.loadDeck2(br); //Se carga el mazo
					output += ctrl.run2();
				}
			} else if(apartado == 3) {
				ctrl.loadDeck3(br); //Se carga el mazo
				output += ctrl.run3();
				while (br.read() != -1) {
					output += '\n';
					ctrl.loadDeck3(br); //Se carga el mazo
					output += ctrl.run3();
				}
			} else if(apartado == 4) {
				ctrl.loadDeck4(br); //Se carga el mazo
				output += ctrl.run4();
				while (br.read() != -1) {
					output += '\n';
					ctrl.loadDeck4(br); //Se carga el mazo
					output += ctrl.run4();
				}
			}
			
			print(output);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // FICHERO DE LECTURA

}
	
	public static void print(String output) throws FileNotFoundException {
		OutputStream out = new FileOutputStream(_outFile);
		PrintStream p = new PrintStream(out);
		p.println(output);
		p.close();
	}


}