import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Avaliar_Defeitos {

	private JPanel panel; 
	private String panelName = "Avaliar_Defeitos"; 
	public Avaliar_Defeitos() {
		init();
	}

	private void init() {

		
		panel = new JPanel();
		GridLayout layout = new GridLayout(2,0);
		panel.setLayout(layout);
		JPanel panelUP = new JPanel();
		JPanel panelDOWN = new JPanel();
		
		
		 DefaultTableModel model = new DefaultTableModel();
		   
		
		String[] nomeColuna = {"Indicadores", "yo"};
		
		Object[][] indicadores = {
			    {"DCI", " "},
			    {"DII", " "},
			    {"AII", " "},
			    {"ACI", " "},
		};
		
		//Tabela
		JTable tabelaDefeitos = new JTable(indicadores, nomeColuna);

		JLabel labelParametro = new JLabel("Parametro:");
		
		String[] lista_defeitos = {"is_long_method", "is_feature_envy"};
		JComboBox boxDefeitos = new JComboBox(lista_defeitos);
		
		boxDefeitos.setSize(100, 100);;
		panelUP.add(labelParametro);
		panelUP.add(boxDefeitos);
		panelDOWN.add(tabelaDefeitos);
		
		panel.add(panelUP);
		panel.add(panelDOWN);

	}
	
	/*return the panel name don�t touch */
	public String getName() {
		return panelName;
	}
	
	/*return the panel  don�t touch */
	public JPanel getPanel() {
		return panel;
	}
}
