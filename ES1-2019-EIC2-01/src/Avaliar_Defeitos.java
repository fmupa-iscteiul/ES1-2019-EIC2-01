import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Avaliar_Defeitos {

	private JPanel panel; 
	private String panelName = "Avaliar_Defeitos"; 
	private File file;
	
	public Avaliar_Defeitos() {
		init();
		file = App.file;
		if(file == null)
		{
			showErrorMessage();
		}
	}

	
	
	private void showErrorMessage(){
		panel = new JPanel();
		JOptionPane.showMessageDialog(panel,
			    "Ficheiro não selecionado.",
			    "Erro",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void init() {
		panel = new JPanel();
		GridLayout layout = new GridLayout(2,0);
		panel.setLayout(layout);
		JPanel panelUP = new JPanel();
		JPanel panelDOWN = new JPanel();
				
		String[] nomeColuna = {"aaa", "aaaaa"};
		
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
		JButton botaoComparar = new JButton("Comparar");
		
		boxDefeitos.setSize(100, 100);;
		panelUP.add(labelParametro);
		panelUP.add(boxDefeitos);
		panelDOWN.add(tabelaDefeitos);
		panelDOWN.add(botaoComparar);
		
		panel.add(panelUP);
		panel.add(panelDOWN);

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
