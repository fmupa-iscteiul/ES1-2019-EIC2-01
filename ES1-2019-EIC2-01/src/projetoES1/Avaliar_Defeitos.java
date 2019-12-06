package projetoES1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.graphbuilder.struc.LinkedList;

import auxAvaliarDefeitos.JTableSample;



public class Avaliar_Defeitos {

	
	private JPanel panel; 
	private String panelName = "Avaliar_Defeitos"; 
	private File path;
	private List lista_regras;
	
	public Avaliar_Defeitos() {
		init();
		path = App.file;
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
		
		//Tabela
		JTableSample jt = new JTableSample();
		
		
		//Texto antes de combobox com "is_long_method / is_feature_envy"
		JLabel labelParametro = new JLabel("Parametro:");
		
		//lista_regras = new LinkedList<Regra>();
		String[] lista_defeitos = {"is_long_method", "is_feature_envy"};
		JComboBox boxDefeitos = new JComboBox(lista_defeitos);
		JButton botaoComparar = new JButton("Comparar");

		botaoComparar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if(path == null) {
						showErrorMessage();
						return;
					}
			}
		});
		
		boxDefeitos.setSize(100, 100);;
		panelUP.add(labelParametro);
		panelUP.add(boxDefeitos);
		panelDOWN.setLayout(new BorderLayout());
		panelDOWN.add(jt.getPanel(), BorderLayout.NORTH);
		panelDOWN.add(jt.getScroll(), BorderLayout.CENTER);
		panelDOWN.add(botaoComparar, BorderLayout.SOUTH);
		
		panel.add(panelUP);
		panel.add(panelDOWN);


	}
	
	private void comparar(){
		if(path == null){
			showErrorMessage();
			return;
		}
		
	}
	
	//return the panel name don´t touch 
	public String getName() {
		return panelName;
	}
	
	//return the panel  don´t touch 
	public JPanel getPanel() {
		return panel;
	}

	/*public void addRegra(Regra regra) {
		lista_regras.add(regra);
	}*/
	
	
	
	
}

