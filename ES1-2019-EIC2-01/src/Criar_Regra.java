import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

	private String panelName = "Criar_Regra";

	private JTextField input;
	private JTextField rule_name;

	public Criar_Regra() {
		init();
	}

	private void init() {
		panel = new JPanel();
		input_panel = new JPanel();

		panel.setLayout(new BorderLayout(10,10));
		input_panel.setLayout(new GridLayout(3,2));

		JButton save = new JButton("Save");

		input = new JTextField("Input");
		rule_name = new JTextField("Insert rule name");

		input_panel.add(rule_name, BorderLayout.CENTER);
		input_panel.add(input, BorderLayout.NORTH);

		panel.add(input_panel,BorderLayout.NORTH);
		panel.add(save, BorderLayout.SOUTH);
		
		String[] l = new String[] {"LOC", "CYCLO", "ATFD", "LAA"};
		String[] l1 = new String[] {"<=", ">="};
		
		JComboBox<String> box1 = new JComboBox<String>(l);
		JComboBox<String> box2 = new JComboBox<String>(l1);
		JComboBox<String> box3 = new JComboBox<String>(l);
		JComboBox<String> box4 = new JComboBox<String>(l1);
		
		input_panel.add(box1, BorderLayout.CENTER);
		input_panel.add(box2, BorderLayout.CENTER);
		input_panel.add(box3, BorderLayout.CENTER);
		input_panel.add(box4, BorderLayout.CENTER);
		panel.add(input_panel, BorderLayout.CENTER);
		
		JButton mais = new JButton("+");
		
		panel.add(mais, BorderLayout.WEST);
		
		String[] l2 = new String[] {"AND", "OR"};
		
		JComboBox<String> box5 = new JComboBox<String>(l2);
		
		panel.add(box5, BorderLayout.EAST);

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