package view;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launcher.Controller;



public class ChoosePartClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int estado; // 0 o 1
	
	private JComboBox<Integer> part;
	private DefaultComboBoxModel<Integer> partModel;	
	
	private JButton inField;
	private JFileChooser fChooser;
	private Controller _ctrl;
		
	public ChoosePartClassDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();		
	}
	
	public ChoosePartClassDialog(Controller ctrl) {		
		super();
		_ctrl = ctrl;
		initGUI();		
	}
	
	private void initGUI() {
		estado = 0;		
		
		setTitle("Parts of the proyect");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

		
		
		JLabel label = new JLabel("Choose part:");
		label.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(label);

		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
				
		partModel = new DefaultComboBoxModel<>();
		part = new JComboBox<>(partModel);		
		
		viewsPanel.add(part);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));
		
		JPanel identifierPanel = new JPanel();
		identifierPanel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(identifierPanel);
		
		
		JLabel identifier = new JLabel("InFile:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		identifierPanel.add(identifier);
		
		inField = makeButtonFile();	
		
		JLabel identifierSpace = new JLabel("    ");
		
		identifierPanel.add(identifierSpace);
		
		identifierPanel.add(inField);
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				ChoosePartClassDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		// BOTON DE CONFIRMADO
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				estado = 1;
				ChoosePartClassDialog.this.setVisible(false);
				
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(420, 240));
		pack();
		setResizable(false);
		setVisible(false);
		
	}
	
	public int open(List<Integer> CO2) {		
		for (Integer c : CO2) {
			partModel.addElement(c);
		}

		setLocation(getParent().getLocation().x + 300, getParent().getLocation().y + 360);

		setVisible(true);
		return estado;
	}
	
	
	public int getPart() {
		return (Integer) part.getSelectedItem();
	} 
	
	public String getInField() {
		return (String) inField.getText();
	}	
	
	private JButton makeButtonFile() {
		JButton button = new JButton(new ImageIcon("resources/icons/cards1.png"));
		button.setToolTipText("Choose the files to run the simulation");
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
				_ctrl.setIn(file);
					
				}
				fChooser.setEnabled(false);
			}});
		button.setVisible(true);
		return button;
	}
	

}
