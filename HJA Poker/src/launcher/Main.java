package launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Main {

	private static String _inFile = "entrada.txt";
	private static String _outFile = null;
	private int apartado = 0; //A leer
	
	
	
	
	public static void loadFile(InputStream in) {
	
		
		
		
		
		
	}
	
	public static void printFile(OutputStream out) {
		if(out == null) {
			out = new OutputStream() {
				public void write (int b) throws IOException{
				};
			};
		}
		
		PrintStream p = new PrintStream(out);
		
		p.println(" - Best hand: ");
		p.println(" - Draw: ");
		
			
		
		
		p.close();
	}
	
	public static void main(String[] args) throws IOException {
	
		try {
			//TODO Se usa commons cli para leer el apartado el nombre del archivo de salida y el de entrada
			BufferedReader br = new BufferedReader(new FileReader(new File(_inFile))); // FICHERO DE LECTURA
			OutputStream out; // FICHERO DE ESCRITURA
			
			if (_outFile == null) {
				out = System.out;
			} else	{
				out = new FileOutputStream(new File(_outFile));
			}
			Controller ctrl = new Controller();
			ctrl.loadDeck(br); //Se carga el mazo
			ctrl.run();
			
			//String in
			//Scanner inConsola = new Scanner(System.in);
			
			//printFile(out);
			//loadFile(in);			
			//in.close();
			//out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
