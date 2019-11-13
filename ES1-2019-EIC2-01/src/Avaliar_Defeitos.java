import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Avaliar_Defeitos {

	private JPanel panel; 
	private String panelName = "Avaliar_Defeitos"; 
	public Avaliar_Defeitos() {
		init();
	}

	private void init() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JTextField param = new JTextField("Parametro:");
		param.setBounds(50,100, 200,30);
		
		JTextField area = new JTextField();
		
		panel.add(param, BorderLayout.NORTH);
		panel.add(area, BorderLayout.CENTER);
		
	}
	
	/*return the panel name don´t touch */
	public String getName() {
		return panelName;
	}
	
	/*return the panel  don´t touch */
	public JPanel getPanel() {
		return panel;
	}
}
