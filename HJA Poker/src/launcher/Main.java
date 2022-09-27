package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

	private static String _inFile = "entrada.txt";
	private static String _outFile = null;
	private int apartado = 0; //A leer
	
	
	public static void main(String[] args) throws IOException {
	
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(new File(_inFile))); // FICHERO DE LECTURA
			Controller ctrl = new Controller();
			ctrl.loadDeck4(br); //Se carga el mazo
			ctrl.run4();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
