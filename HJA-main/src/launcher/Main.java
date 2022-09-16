package launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
			InputStream in = new FileInputStream(new File(_inFile)); // FICHERO DE LECTURA
			OutputStream out; // FICHERO DE ESCRITURA
			
			if (_outFile == null) {
				out = System.out;
			} else	{
				out = new FileOutputStream(new File(_outFile));
			}
			Controller ctrl = new Controller();
			ctrl.loadDeck(in); //Se carga el mazo
			in.close();
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
		
		/* flush
		 * if (carta[0] == carta[1] == carta[2] == carta[3]) {
		 * 	if(carta[0] == carta[4]) 5
		 * 	else 4
		 * 
		 * 
		 * straight
		 * 
		 * 
		 * 
		int i = 0;
		boolean draw = true;
		while ((carta[i].elem == carta[i+1].elem + 1  && i < list.size() - 1) || draw)		
			if(carta[i].elem == carta[i+1].elem + 1  && i < list.size() - 1)) draw = false;
		}*/
		
	}

}
