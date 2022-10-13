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
import javax.swing.JTextArea;
//import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import launcher.Controller;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller _ctrl;	
	
	private Border border = BorderFactory.createLineBorder(Color.black, 3);
	
	private ControlPanel _panel;
	private MapComponent _map;
	private JTextArea _text;
	
	public MainWindow(Controller ctrl) {
		super("PokerStars");
		_ctrl = ctrl;
		initGUI();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);		
		
		_panel = new ControlPanel(_ctrl);
		mainPanel.add(_panel, BorderLayout.PAGE_START);		

		JPanel viewPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewPanel, BorderLayout.CENTER);		
	
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));		
		viewPanel.add(mapPanel);
		
		// MESA
		_map = new MapComponent(_ctrl);
		JPanel tableView = createViewPanel(_map, "Table");
		tableView.setPreferredSize(new Dimension(1000, 560));
		mapPanel.add(tableView);		
		
		_panel.setMap(_map);
		
		_text = new JTextArea();
		JPanel textView = new JPanel(new BorderLayout());
		textView.setBorder(BorderFactory.createTitledBorder(border, "Information", TitledBorder.LEFT, TitledBorder.TOP));
		textView.add(_text);
		mainPanel.add(textView, BorderLayout.SOUTH);
		
		_panel.setTextArea(_text);
		
		_ctrl.setWindow(this);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel( new BorderLayout());
		
		// A�ADE UN BORDE
		p.setBorder(BorderFactory.createTitledBorder(border, title, TitledBorder.LEFT, TitledBorder.TOP));
		
		p.add(new JScrollPane(c));
		return p;
	}
	
	public void setText(String info) {
		_text.setText(info);
	}
}
