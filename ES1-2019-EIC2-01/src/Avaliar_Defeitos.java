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
		
		JButton search = new JButton("search");
		
		JTextField area = new JTextField();
		area.setSize(20, 400);
		
		panel.add(search,BorderLayout.SOUTH);
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
