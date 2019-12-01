import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author pedro
 * 
 * Criar_Regra é a interface para criar uma regra 
 * e definesse o conjunto de métricas que são dadas 
 * e compará-las
 *
 */
public class Criar_Regra {

	private JPanel panel;
	private JPanel input_panel;
	private JPanel bottom_panel;

	private String panelName = "Criar Regra";

	private JTextField input;
	private JTextField rule_name;

	public Criar_Regra() {
		init();
	}

	private void init() {
		
		panel 			= new JPanel();
		input_panel 	= new JPanel();
		bottom_panel	= new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		input_panel.setLayout(new GridLayout(3,2));
		bottom_panel.setLayout(new GridLayout(2,1));
		
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		JButton mais = new JButton("+");

		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTheRules();
				
			}
		});
		
		input 		= new JTextField("Input");
		rule_name 	= new JTextField("Insert rule name");

		input_panel.add(rule_name, BorderLayout.CENTER);
		input_panel.add(input, BorderLayout.NORTH);

		bottom_panel.add(save, BorderLayout.SOUTH);
		bottom_panel.add(load, BorderLayout.SOUTH);
		
		addBoxes();
		
		panel.add(mais, BorderLayout.WEST);
		panel.add(input_panel, BorderLayout.CENTER);
		panel.add(bottom_panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates boxes that are used to create rules
	 */
	private void addBoxes() {
		
		String[] l = new String[] {"LOC", "CYCLO", "ATFD", "LAA"};
		String[] l1 = new String[] {"<=", ">="};
		String[] l2 = new String[] {"AND", "OR"};
		
		JComboBox<String> box1 = new JComboBox<String>(l);
		JComboBox<String> box2 = new JComboBox<String>(l1);
		JComboBox<String> box3 = new JComboBox<String>(l);
		JComboBox<String> box4 = new JComboBox<String>(l1);
		JComboBox<String> box5 = new JComboBox<String>(l2);
		
		input_panel.add(box1, BorderLayout.CENTER);
		input_panel.add(box2, BorderLayout.CENTER);
		input_panel.add(box3, BorderLayout.CENTER);
		input_panel.add(box4, BorderLayout.CENTER);
		
		panel.add(box5, BorderLayout.EAST);
		
	}

	protected void loadTheRules() {
		
		//TODO after possibility to save rules in the file
		JFileChooser jfc = new JFileChooser(".");

		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			File selectedFile = jfc.getSelectedFile();

		}
		
	}

	/*return the panel name don´t touch */
	public String getName() {
		return panelName;
	}

	/*return the panel  don´t touch */
	public JPanel getPanel() {
		return panel;
	}
	/**
	 * Este metodo devolve o nome que o user da a uma regra nova
	 * @return
	 */

	public String getRuleName() {
		return rule_name.getName();
	}

	/**
	 * Este metodo devolve o valor que o user indica
	 * @return
	 */

	public String getInputValue() {
		return input.getName();
	}
	
	/**
	 * Este painel devolve as combo boxes, inputs e nome da regra 
	 * @return 
	 */
	
	public JPanel getPanel1() {
		return input_panel;
	}

}