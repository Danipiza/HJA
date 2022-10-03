package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import launcher.Controller;




public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller _ctrl;	
	
	private Border border = BorderFactory.createLineBorder(Color.black, 3);	
	
	public MainWindow(Controller ctrl) {
		super("Poker");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);		
		
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);		
		
		JPanel viewPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewPanel, BorderLayout.CENTER);		
	
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));		
		viewPanel.add(mapPanel);
		
		// MESA
		JPanel tableView = createViewPanel(new MapByRoadComponent(_ctrl), "Table");
		tableView.setPreferredSize(new Dimension(500, 400));
		mapPanel.add(tableView);		
		
		
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel( new BorderLayout());
		
		// AÑADE UN BORDE
		p.setBorder(BorderFactory.createTitledBorder(border, title, TitledBorder.LEFT, TitledBorder.TOP));
		
		p.add(new JScrollPane(c));
		return p;
	}
	
	
	
}

/*
@SuppressWarnings("serial")
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
	
	
	private JComboBox<Integer> apartado;
	private DefaultComboBoxModel<Integer> apartadoModel;	
	private JTextField cartasMesa;
	
	public MainWindow(Controller ctrl, int mode, BufferedReader s) {
		super("Poker");
		_ctrl = ctrl;
		_mode = mode;
		is = s;
		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
*/