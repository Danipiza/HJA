package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import launcher.Controller;

public class MainWindow extends JFrame {
	
	private final String IMAGE_PATH = "resources/icons";

	private Controller _ctrl;
	private JFileChooser fChooser;
	private JButton _btnFile;
	private JComboBox<Integer> _comboMode;
	private JPanel panel;
	private int _mode;
	private BufferedReader is;
	
	public MainWindow(Controller ctrl, int mode, BufferedReader s) {
		super("Poker");
		_ctrl = ctrl;
		_mode = mode;
		is = s;
		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	private void initGUI() {
		panel = new JPanel(new FlowLayout());
		setContentPane(panel);
		int frameWidth = 450;
		int frameHeight = 300;
		setSize(frameWidth,frameHeight);
		setLocation(250, 20);
		_btnFile = makeButtonFile();
		_comboMode = makeComboBox(); 
		

	}
	
	private JButton makeButtonFile() {
		JButton button = new JButton(new ImageIcon(IMAGE_PATH+"/cards.png"));
		button.setToolTipText("Choose the file to load the deck");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fChooser == null) {
					fChooser = new JFileChooser();
				}
				fChooser.setEnabled(true);
				int option = fChooser.showOpenDialog(button);
				if (option != JFileChooser.CANCEL_OPTION) {
				
					File file = fChooser.getSelectedFile();
					try {
						is = new BufferedReader(new FileReader(file));
						chooseDeck();
						
					} catch (FileNotFoundException e1) {
						System.err.println("Error loading the file");
					}
					try {
						is.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				fChooser.setEnabled(false);
			}});
		panel.add(button);
		button.setVisible(true);
		return button;
	}
	
	private JComboBox makeComboBox() {
		JComboBox aux = new JComboBox<Integer>();
		aux.addItem(1);
		aux.addItem(2);
		aux.addItem(3);
		aux.addItem(4);
		aux.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				_mode = (int) e.getItem();
				
			}
		});
		panel.add(aux);
		return aux;
	}
	
	void chooseDeck() {
		if (_mode == 1) {
			
		}
		else if (_mode == 2) {
			
		}
		
		switch (_mode) {
		case 1:
			try {
				_ctrl.loadDeck1(is);
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");
			}
			break;
		case 2:
			try {
				_ctrl.loadDeck2(is);
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		case 3:
			try {
				_ctrl.loadDeck3(is);
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		case 4:
			try {
				_ctrl.loadDeck4(is);
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		}
	}

}
