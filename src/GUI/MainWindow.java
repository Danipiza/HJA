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
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainWindow extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, HandButton> preflopHands;
	private HashMap<Integer, String[]> percentRange;
	
	private Border RSBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

	public MainWindow() {
		preflopHands = new HashMap<String, HandButton>();
		percentRange = new HashMap<Integer, String[]>();
		initPercentRange();
		initGUI();
	}
	
	private void initGUI() {
		
		setSize(700,1000);  
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
	        	 clear();
	        	 inputToGUI(rangeInput.getText());
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
		        	 selectAll();
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
		        	 clear();
		         }
		      });
		    extraOptions.add(clear, 1, 0);
		    
		
			JLabel clearLabel = new JLabel("Clear");
			clearLabel.setBounds(5,60,60,40);
			clearLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			extraOptions.add(clearLabel, 2, 0);
			    
				 			
		    JLabel sliderText = new JLabel("Slider based range: ");
		    sliderText.setBounds(10, 660, 300, 50); 
		    sliderText.setFont(new Font("Arial", Font.PLAIN, 20));
			add(sliderText);
			
			JSlider percentageInput= new JSlider();  
			percentageInput.setBounds(200, 660, 380, 50); 
			
			percentageInput.setMajorTickSpacing(25);
			percentageInput.setMinorTickSpacing(5);
			percentageInput.setPaintTicks(true);
			percentageInput.setPaintLabels(true);
			
			percentageInput.addChangeListener(new ChangeListener() {				
				@Override
				public void stateChanged(ChangeEvent e) {
					clear();
					
					int porcentaje = percentageInput.getValue() +5;
					if(porcentaje >=104) {
						porcentaje =104;
						sliderToGUI(porcentaje);
					}
					else if(porcentaje != 5) {
						sliderToGUI(porcentaje);
					}
					repaint();
				}
			});						
			
			add(percentageInput);
			
	}
	
	void selectAll() {
		for (Entry<String, HandButton> hb : preflopHands.entrySet()) {
			hb.getValue().clicked();
		}
	}
	
	void clear() {
		for (Entry<String, HandButton> hb : preflopHands.entrySet()) {
			hb.getValue().clear();
		}
	}
	
	private void sliderToGUI(double sliderValue) {
		int sliderInt = (int) (Math.floor(sliderValue/5));
		for (int i = 1; i < sliderInt; i++) {
			for (String instr : percentRange.get(5*i)) {
				preflopHands.get(instr).textActivated();
			} 			
		}
		String[] lastIntr = percentRange.get(5*sliderInt);
		int sliderMod = (int) ((sliderValue % 5) * lastIntr.length / 5);
		for (int i = 0; i <= sliderMod; i++) {
			preflopHands.get(lastIntr[i]).textActivated();
		}
		
		
	}
	
	
	private void inputToGUI(String input) {
		String cards[] = input.replace(" ", "").split(",");
		try {
			for (int i = 0; i < cards.length; i++) {
				if (cards[i].toString().contains("-")) {
					String parts[] = cards[i].toString().split("-");
					intervalToGUI(parts[0], parts[1]);
				}
				else
					instructionToGUI(cards[i].toString());
			}			
		} catch(Exception E) {
			JOptionPane.showMessageDialog(this, "Error: Wrong Identifier");
		}
		
	}
	
	private void instructionToGUI(String instr1) {
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
		else {
			preflopHands.get(instr1).textActivated();
		}
	}
	
	private void intervalToGUI(String instr1, String instr2) {	
		instr2 = instr2.replace(instr2.charAt(1), toChar(toInt(instr2.charAt(1)) - 1));
		try {
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
		catch(Exception E) {
			JOptionPane.showMessageDialog(this, "Introduce the higher number in the range first");
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
	
	/*
	 * 5  | AA, KK, QQ, JJ, TT, 99, 88, AKs, AQs, AJs, KQs, AKo,
	 * 10 | 77, A9s, ATs, KJs, KTs, QJs, QTs, AQo, AJo, KQo
	 * 15 | A7s A8s A9s K9s QTs JTs ATo KJo KTo KJo KTo
	 * 20 | 66 A6s A5s A4s K8s Q9s J9s T9s A9o QTo JTo
	 * 25 | A2s A3s K6s K7s Q8s J8s T8s A7o A8o K9o
	 * 30 | 55 K5s Q7s 98s A5o Q9o J9o JTo
	 * 35 | A2s K4s K3s Q6s J7s T7s 97s 87s A4o K9 K9o K8o T9o
	 * 40 | 44 K2s Q4s Q5s A3o K7o Q8o J8o 
	 * 45 | J6s T6s 96s 86s 76s A2o K6o T8o 98o
	 * 50 | 33 Q2s Q3s J4s J5s 65s K5o Q7o J7o T7o 
	 * 55 | J3s T5s 95s 85s 75s K4o Q6o 97o 87o
	 * 60 | 22 J2s T4s T3s 64s 54s K2o K3o Q5o  
	 * 65 | T2s 94s 84s 74s Q4o J6o 86o 76o
	 * 70 | 93s 63s 53s 43s Q3o J5o T6o 96o 
	 * 75 | 92s 83s 73s 52s Q2o J4o 75o 65o
	 * 80 | 82s 62s J3o T5o 95o 85o 54o
	 * 85 | 72s 43s 32s J2o T4o T3o 74o 64o 
	 * 90 | T2o 94o 93o 84o 53o
	 * 95 | 92o 83o 73o 63o 52o 43o
	 * 100| 82o 72o 62o 42o 32o 	
	*/
	
	private void initPercentRange() {		
		percentRange.put(0, new String[]  {});
		percentRange.put(5, new String[]  { "AA", "KK", "QQ", "JJ", "TT", "99", "88", "AKs", "AQs", "AJs", "KQs", "AKo"});
		percentRange.put(10, new String[] { "77", "A9s", "ATs", "KJs", "KTs", "QJs", "QTs", "AQo", "AJo", "KQo"});
		percentRange.put(15, new String[] { "A7s", "A8s", "A9s", "K9s", "QTs", "JTs", "ATo", "KJo", "KTo", "KJo", "QJo", "KTo"});
		percentRange.put(20, new String[] { "66", "A6s", "A5s", "A4s", "K8s", "Q9s", "J9s", "T9s", "A9o", "QTo", "JTo"});
		percentRange.put(25, new String[] { "A2s", "A3s", "K6s", "K7s", "Q8s", "J8s", "T8s", "A7o", "A8o", "K9o"});		
		percentRange.put(30, new String[] { "55", "K5s", "Q7s", "98s", "A5o", "Q9o", "J9o", "JTo"});
		percentRange.put(35, new String[] { "A2s", "K4s", "K3s", "Q6s", "J7s", "T7s", "97s", "87s", "A4o", "K9o", "K8o", "T9o"});
		percentRange.put(40, new String[] { "44", "K2s", "Q4s", "Q5s", "A3o", "K7o", "Q8o", "J8o" });
		percentRange.put(45, new String[] { "J6s", "T6s", "96s", "86s", "76s", "A2o", "A6o", "K6o", "T8o", "98o"});
		percentRange.put(50, new String[] { "33", "Q2s", "Q3s", "J4s", "J5s", "65s", "K5o", "Q7o", "J7o", "T7o"});
		percentRange.put(55, new String[] { "J3s", "T5s", "95s", "85s", "75s", "K4o", "Q6o", "97o", "87o"});
		percentRange.put(60, new String[] { "22", "J2s", "T4s", "T3s", "64s", "54s", "K2o", "K3o", "Q5o" });
		percentRange.put(65, new String[] { "T2s", "94s", "84s", "74s", "Q4o", "J6o", "86o", "76o"});
		percentRange.put(70, new String[] { "93s", "63s", "53s", "43s", "Q3o", "J5o", "T6o", "96o" });
		percentRange.put(75, new String[] { "92s", "83s", "73s", "52s", "Q2o", "J4o", "75o", "65o"});
		percentRange.put(80, new String[] { "82s", "62s", "J3o", "T5o", "95o", "85o", "54o"});
		percentRange.put(85, new String[] { "72s", "43s", "42s", "32s", "J2o", "T4o", "T3o", "74o", "64o" });
		percentRange.put(90, new String[] { "T2o", "94o", "93o", "84o", "53o"});
		percentRange.put(95, new String[] { "92o", "83o", "73o", "63o", "52o", "43o"});
		percentRange.put(100, new String[] { "82o", "72o", "62o", "42o", "32o" });
		
	}	
}
