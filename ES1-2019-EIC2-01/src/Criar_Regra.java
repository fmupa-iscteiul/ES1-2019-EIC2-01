import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		input_panel.setLayout(new GridLayout(2,1));

		JButton save = new JButton("save");

		input = new JTextField("Input");
		rule_name = new JTextField("Insert rule name");

		input_panel.add(rule_name, BorderLayout.NORTH);
		input_panel.add(input, BorderLayout.NORTH);

		panel.add(input_panel,BorderLayout.NORTH);
		panel.add(save, BorderLayout.SOUTH);

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

}