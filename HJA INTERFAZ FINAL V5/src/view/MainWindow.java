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
//import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import launcher.Controller;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller _ctrl;	
	
	private Border border = BorderFactory.createLineBorder(Color.black, 3);
	
	public MainWindow(Controller ctrl) {
		super("PokerStars");
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
		JPanel tableView = createViewPanel(new MapComponent(_ctrl), "Table");
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
