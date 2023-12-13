package view;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ChoosePartClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int estado; // 0 o 1
	
	private JComboBox<Integer> part;
	private DefaultComboBoxModel<Integer> partModel;	
	
	private JTextField inField;
		
	public ChoosePartClassDialog(Frame parent) {
		super(parent, true);
		initGUI();		
	}
	
	public ChoosePartClassDialog() {		
		super();
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
		
		inField = new JTextField(10);		
		
		JLabel identifierSpace = new JLabel("                 ");
		
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

		setPreferredSize(new Dimension(420, 200));
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
	

}
