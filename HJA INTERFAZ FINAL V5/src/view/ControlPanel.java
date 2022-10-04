package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import launcher.Controller;


public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;	

	private Controller ctrl;
	
	private JButton partButton;		
	private JButton runButton;		
	private JButton exitButton;
		
	public ControlPanel(Controller _ctrl) {
		ctrl = _ctrl;
		initGUI();
		
	}
	
	public void initGUI() {
		setLayout(new BorderLayout());
		JToolBar mainP = new JToolBar();
		this.add(mainP);	
		
		JToolBar buttons = new JToolBar();
		mainP.add(buttons); 
						
		
		// BOTON PARA ELEGIR EL APARTADO
		partButton = new JButton();
		partButton.setToolTipText("Choose Part Button"); // LOS TICKS QUE SE EJECUTAN
		partButton.setIcon(loadImage("resources/icons/chooseButton.png"));
		partButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				choosePartClass();
				
				
			}
		});
		
		runButton = new JButton();
		runButton.setToolTipText("Run button"); // LOS TICKS QUE SE EJECUTAN
		runButton.setIcon(loadImage("resources/icons/run.png"));
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					start();
				} catch (FileNotFoundException e1) {					
					e1.printStackTrace();
				}				
			}
		});
		
		
		exitButton = new JButton();
		exitButton.setToolTipText("Exit button"); 
		exitButton.setIcon(loadImage("resources/icons/exit.png"));		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opciones = {"No", "Yes"}; 
				if(JOptionPane.showOptionDialog(null, "Are you sure yo want to quit?", "Exit", 0, 0, null, opciones, opciones[1]) == 1) {
					System.exit(0);
				}
								
			}
		});		
		
					
		buttons.add(partButton);
		buttons.add(runButton);
		
		buttons.add(Box.createGlue());
		buttons.addSeparator();	
		buttons.add(exitButton);
		
	}
	
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
		
		
	// SOLO FALTA QUE HACER CUANDO SE CIERRA LA VENTANA
	protected void choosePartClass() {		
		ChoosePartClassDialog partDialog = new ChoosePartClassDialog(null); // (Frame) this.getTopLevelAncestor()

		
		// LISTA DE ENTEROS DE LAS DIFERENTES PARTES DEL PROYECTO
		List<Integer> parts = new ArrayList<Integer>();
		for (int i = 1; i <= 4; i++) {
			parts.add(i);
		}
		
		int estado = partDialog.open(parts); // ABRE LA VENTANA EMERGENTE
		
		
		if (estado == 1) {			
			
			try {				
				ctrl.setPart(partDialog.getPart());
				ctrl.setIn(partDialog.getInField());
				
				ctrl.loadDecks();
								
			} catch (Exception e) {
				JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
						"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}			
			
		} 
	}
	
	
	// HAY QUE MIRAR EL TIEMPO DE EJECUCION CON EL BOTON DE RUN
	protected void start() throws FileNotFoundException {		
		partButton.setEnabled(false);		
		runButton.setEnabled(false);		
		
		run_sim();
	}
	
	// HAY QUE MIRARLO
	private void run_sim() throws FileNotFoundException {
		
		if (ctrl.getPart() == 1) {
			ctrl.run1();
		}
		else if(ctrl.getPart() == 2) {
			ctrl.run2();
		}
		else if(ctrl.getPart() == 3) {
			ctrl.run3();
		}
		else if(ctrl.getPart() == 4) {
			ctrl.run4();
		}
		
		
		enableToolBar(true);		
		
		repaint();	
		//super.updateUI();
		super.paintComponent(getGraphics());
		
	}
	
	void enableToolBar(boolean b) {
		partButton.setEnabled(b);		
		runButton.setEnabled(b);
	}
	
	
}
