package view;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;

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
	private JTextArea _text;
	private JButton _start;
	
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
		panel = new JPanel(new BorderLayout());
		setContentPane(panel);
		int frameWidth = 450;
		int frameHeight = 300;
		setSize(frameWidth,frameHeight);
		setLocation(250, 20);
		_btnFile = makeButtonFile();
		_comboMode = makeComboBox();
		_start = makeButtonStart();
		_text = new JTextArea();
		_text.setSize(panel.getWidth(), 100);
		_text.setVisible(true);
		panel.add(_text, BorderLayout.SOUTH);

		

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
					} catch (FileNotFoundException e1) {
						System.err.println("Error loading the file");
					}
					
				}
				fChooser.setEnabled(false);
			}});
		panel.add(button, BorderLayout.NORTH);
		button.setVisible(true);
		return button;
	}
	
	private JButton makeButtonStart() {
		JButton button = new JButton("Start");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseDeck();
			}});
		panel.add(button, BorderLayout.EAST);
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
				System.out.println(_mode);
				
			}
		});
		panel.add(aux, BorderLayout.CENTER);
		return aux;
	}
	
	void chooseDeck() {
		
		switch (_mode) {
		case 1:
			try {
				_ctrl.loadDeck1(is);
				
				_text.setText((_ctrl.run1()));
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");
			}
			break;
		case 2:
			try {
				_ctrl.loadDeck2(is);
				_text.setText((_ctrl.run2()));
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		case 3:
			try {
				_ctrl.loadDeck3(is);
				_ctrl.run3();
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		case 4:
			try {
				_ctrl.loadDeck4(is);
				_text.setText((_ctrl.run4()));
			} catch (Exception e) {
				System.out.println("Modo o mazo equivocado");

			}
			break;
		}
	}

}
