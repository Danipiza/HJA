package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class MainWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, HandButton> preflopHands;
	private HashMap<Integer, String> percentRange;
	
	private Border RSBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

	public MainWindow() {
		preflopHands = new HashMap<String, HandButton>();
		percentRange = new HashMap<Integer, String>();
		initPercentRange();
		initGUI();
	}
	
	private void initGUI() {
		
		setSize(1000,1000);  
	    setLayout(null);  	
		setVisible(true);  
		
		JLabel inputText = new JLabel("Input: ");
		inputText.setBounds(10,10, 100, 50); 
		inputText.setFont(new Font("Arial", Font.PLAIN, 20));
		add(inputText);
		
		JTextField rangeInput= new JTextField();  
		rangeInput.setBounds(80,10, 486, 50);  
		rangeInput.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 inputToSim(rangeInput.getText());
	         }
	      });
		add(rangeInput);
	
		JLayeredPane rangeSimulator = new JLayeredPane();
		rangeSimulator.setBounds(10, 80, 566, 566);
		rangeSimulator.setBorder(RSBorder);
		add(rangeSimulator);
		
		
		String aux = "";
		for (int j = 0; j < 13; j++)
			for (int i = 0; i < 13; i++) {
				HandButton b;
				if (i < j) {
					aux += toChar(14 - i);
					aux += toChar(14 - j);
					aux += 'o';
					b = new HandButton(aux, Color.gray);
					
				}
				else if (i == j) {
					aux += toChar(14 - i);
					aux += toChar(14 - i);
					b = new HandButton(aux, Color.green); 
				}
				else  {
					aux += toChar(14 - j);
					aux += toChar(14 - i);
					aux += 's';
					b = new HandButton(aux, Color.red); 
				}
								
			    b.setBounds(10 + (i*42),10 + (j* 42),40,40);
			    b.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			        	b.clicked();
			         }
			      });
			    rangeSimulator.add(b, 1, 0);
			    
			    JLabel l = new JLabel(aux);
			    l.setBounds(12 + (i*42),10 + (j* 42),40,40);
			    l.setFont(new Font("Arial", Font.PLAIN, 20));
			    rangeSimulator.add(l, 2, 0);
			    
			    preflopHands.put(aux, b);
			    aux = "";
			}
		
			JLayeredPane extraOptions = new JLayeredPane();
			extraOptions.setBounds(580, 80, 60, 200);
			extraOptions.setBorder(RSBorder);
			add(extraOptions);
			
			
			JButton all = new JButton();
		    all.setBounds(10,10,40,40);
		    all.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 inputToSim(percentRange.get(100));
		         }
		      });
		    extraOptions.add(all, 1, 0);
		    
		
			JLabel allLabel = new JLabel("All");
			allLabel.setBounds(18,10,40,40);
			allLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			extraOptions.add(allLabel, 2, 0);
			
			JButton clear = new JButton();
			clear.setBounds(10,60,40,40);
			clear.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 for (Entry<String, HandButton> hb : preflopHands.entrySet()) {
		        		 hb.getValue().clear();
		        	 } 
		         }
		      });
		    extraOptions.add(clear, 1, 0);
		    
		
			JLabel clearLabel = new JLabel("Clear");
			clearLabel.setBounds(5,60,60,40);
			clearLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			extraOptions.add(clearLabel, 2, 0);
			    
		    JLabel percentageText = new JLabel("Percentage based range: ");
		    percentageText.setBounds(10, 660, 300, 50); 
		    percentageText.setFont(new Font("Arial", Font.PLAIN, 20));
			add(percentageText);
			
			JTextField percentageInput= new JTextField();  
			percentageInput.setBounds(470 ,660, 100, 50);  
			percentageInput.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 inputToSim(percentRange.get(Integer.parseInt(percentageInput.getText())));
		         }
		      });
			add(percentageInput); 		
	}
	
	private void inputToSim(String input) {
		String aux = "", aux2 = "";
		Boolean interval = false;
		char iterator;
		for (int i = 0; i < input.length();i++) {
			iterator = input.charAt(i);
			if (iterator == ',') {
				instructionProcessor(aux, aux2, interval);
				aux = "";
				aux2 = "";
				interval = false;
			}
			else {
				if (interval) {
					aux2 += iterator;
				}
				else if (iterator == '-')
					interval = true;	
				else
					aux += iterator;
			}
		}
		
		instructionProcessor(aux, aux2, interval);
	}
	private void instructionProcessor(String instr1, String instr2, Boolean interval) {
		if (instr1.charAt(instr1.length()-1) == '+') {
			instr1 = instr1.substring(0, instr1.length()-1);
			if (instr1.length() == 3)
				while (instr1.charAt(0) != instr1.charAt(1)) {
					preflopHands.get(instr1).textActivated();
					instr1 = instr1.replace(instr1.charAt(1), toChar(toInt(instr1.charAt(1)) + 1));
				}
			else 
				while (instr1.charAt(0) != 'f') {
					preflopHands.get(instr1).textActivated();
					if (instr1.charAt(0) != 'A')
						instr1 = instr1.replace(instr1.charAt(0), toChar(toInt(instr1.charAt(0)) + 1));
					else
						instr1 = instr1.replace(instr1.charAt(0), 'f');
				}
				
		}
		else if (interval) {
			instr2 = instr2.replace(instr2.charAt(1), toChar(toInt(instr2.charAt(1)) - 1));
			if (instr1.length() == 3)
				while (!instr1.equals(instr2)) {
					preflopHands.get(instr1).textActivated();
					instr1 = instr1.replace(instr1.charAt(1), toChar(toInt(instr1.charAt(1)) - 1));
				}
			else 
				while (!instr1.equals(instr2)) {
					preflopHands.get(instr1).textActivated();
					instr1 = instr1.replace(instr1.charAt(0), toChar(toInt(instr1.charAt(0)) - 1));
				}
		}
		else {
			preflopHands.get(instr1).textActivated();
		}
		
	}
	
	public int toInt(char val) {
		int ret;
		if (val == 'A')	ret = 14;
		else if (val == 'K') ret = 13;
		else if (val == 'Q') ret = 12;
		else if (val == 'J') ret = 11;
		else if (val == 'T') ret = 10;
		else ret = val - 48;
		
		return ret;
	}
	
	public char toChar(int val) {
		char ret;
		if (val == 14)	ret = 'A';
		else if (val == 13) ret = 'K';
		else if (val == 12) ret = 'Q';
		else if (val == 11) ret = 'J';
		else if (val == 10) ret = 'T';
		else ret = (char) (val + 48);
		
		return ret;
	}
	
	
	
	private void initPercentRange() {
		percentRange.put(5, "88+,AJs+,KQs,AKo");
		percentRange.put(10, "77+,A9s+,KTs+,QTs+,AJo+,KQo");
		percentRange.put(15, "77+,A7s+,K9s+,QTs+,JTs,ATo+,KTo+,QJo");
		percentRange.put(20, "66+,A4s+,K8s+,Q9s+,J9s+,T9s,A9o+,KTo+,QTo+,JTo");
		percentRange.put(25, "66+,A2s+,K6s+,Q8s+,J8s+,T8s+,A7o+,K9o+,QTo+,JTo");
		percentRange.put(30, "55+,A2s+,K5s+,Q7s+,J8s+,T8s+,98s,A7o+,A5o,K9o+,Q9o+,J9o+,T9o");
		percentRange.put(35, "55+,A2s+,K3s+,Q6s+,J7s+,T7s+,97s+,87s,A4o+,K8o+,Q9o+,J9o+,T9o");
		percentRange.put(40, "44+,A2s+,K2s+,Q4s+,J7s+,T7s+,97s+,87s,A3o+,K7o+,Q8o+,J8o+,T9o");
		percentRange.put(45, "44+,A2s+,K2s+,Q4s+,J6s+,T6s+,96s+,86s+,76s,A2o+,K6o+,Q8o+,J8o+,T8o+,98o");
		percentRange.put(50, "33+,A2s+,K2s+,Q2s+,J4s+,T6s+,96s+,86s+,76s,65s,A2o+,K5o+,Q7o+,J7o+,T7o+,98o");
		percentRange.put(55, "33+,A2s+,K2s+,Q2s+,J3s+,T5s+,95s+,85s+,75s+,65s,A2o+,K4o+,Q6o+,J7o+,T7o+,97o+,87o");
		percentRange.put(60, "22+,A2s+,K2s+,Q2s+,J2s+,T3s+,95s+,85s+,75s+,64s+,54s,A2o+,K2o+,Q5o+,J7o+,T7o+,97o+,87o");
		percentRange.put(65, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,94s+,84s+,74s+,64s+,54s,A2o+,K2o+,Q4o+,J6o+,T7o+,97o+,86o+,76o");
		percentRange.put(70, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,93s+,84s+,74s+,63s+,53s+,43s,A2o+,K2o+,Q3o+,J5o+,T6o+,96o+,86o+,76o");
		percentRange.put(75, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,83s+,73s+,63s+,52s+,43s,A2o+,K2o+,Q2o+,J4o+,T6o+,96o+,86o+,75o+,65o");
		percentRange.put(80, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,82s+,73s+,62s+,52s+,43s,A2o+,K2o+,Q2o+,J3o+,T5o+,95o+,85o+,75o+,65o,54o");
		percentRange.put(85, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,82s+,72s+,62s+,52s+,42s+,32s,A2o+,K2o+,Q2o+,J2o+,T3o+,95o+,85o+,74o+,64o+,54o");
		percentRange.put(90, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,82s+,72s+,62s+,52s+,42s+,32s,A2o+,K2o+,Q2o+,J2o+,T2o+,93o+,84o+,74o+,64o+,53o+");
		percentRange.put(95, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,82s+,72s+,62s+,52s+,42s+,32s,A2o+,K2o+,Q2o+,J2o+,T2o+,92o+,83o+,73o+,63o+,52o+,43o");
		percentRange.put(100, "22+,A2s+,K2s+,Q2s+,J2s+,T2s+,92s+,82s+,72s+,62s+,52s+,42s+,32s,A2o+,K2o+,Q2o+,J2o+,T2o+,92o+,82o+,72o+,62o+,52o+,42o+,32o");
			
	}
}
