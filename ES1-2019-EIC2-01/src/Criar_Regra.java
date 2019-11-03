import javax.swing.JButton;
import javax.swing.JPanel;

public class Criar_Regra {
	
	private JPanel panel; 
	private String panelName = "Criar_Regra";
	
	public Criar_Regra() {
		init();
	}
	
	private void init() {
		panel = new JPanel();
		
		
		JButton ok = new JButton("ok");
		
		
		panel.add(ok);
		
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
