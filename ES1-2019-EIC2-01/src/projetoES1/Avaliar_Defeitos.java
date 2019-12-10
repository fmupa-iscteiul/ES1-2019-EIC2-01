package projetoES1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import auxAvaliarDefeitos.JTableSample;

public class Avaliar_Defeitos {

	private JPanel panel;
	private String panelName = "Avaliar_Defeitos";
	private File path;
	private LinkedList<Regra> lista_regras;
	private static JTableSample[] jt;
	private static JComboBox boxDefeitos;
	private JPanel panelDOWN;
	private JPanel panelUP;

	public Avaliar_Defeitos() {
		init();
		path = App.file;
	}

	private void showErrorMessage() {
		panel = new JPanel();
		JOptionPane.showMessageDialog(panel, "Ficheiro não selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	private void init() {
		panel = new JPanel();
		GridLayout layout = new GridLayout(2, 0);
		panel.setLayout(layout);
		panelUP = new JPanel();
		panelDOWN = new JPanel();

		// Tabela
		jt = new JTableSample[2];
		jt[0] = new JTableSample();
		jt[1] = new JTableSample();

		// Texto antes de combobox com "is_long_method / is_feature_envy"
		JLabel labelParametro = new JLabel("Parametro:");

		// lista_regras = new LinkedList<Regra>();
		String[] lista_defeitos = { "is_long_method", "is_feature_envy" };
		boxDefeitos = new JComboBox(lista_defeitos);

		

		boxDefeitos.setSize(100, 100);
		
		boxDefeitos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTable();
			}

			
		});
		
		panelUP.add(labelParametro);
		panelUP.add(boxDefeitos);
		panelDOWN.setLayout(new BorderLayout());
		panelDOWN.add(jt[0].getPanel(), BorderLayout.NORTH);
		panelDOWN.add(jt[0].getScroll(), BorderLayout.CENTER);

		panel.add(panelUP);
		panel.add(panelDOWN);

	}

	public JTableSample getJTableSample(int index) {
		return jt[index];
	}

	private void comparar() {
		if (path == null) {
			showErrorMessage();
			return;
		}

	}
	
	private void changeTable() {
		if(boxDefeitos.getSelectedItem() == "is_long_method"){
			panelDOWN.removeAll();
			panelDOWN.add(jt[0].getPanel(), BorderLayout.NORTH);
			panelDOWN.add(jt[0].getScroll(), BorderLayout.CENTER);
			panel.updateUI();
		}else{
			panelDOWN.removeAll();
			panelDOWN.add(jt[1].getPanel(), BorderLayout.NORTH);
			panelDOWN.add(jt[1].getScroll(), BorderLayout.CENTER);
			panel.updateUI();
		}
		
	}

	// return the panel name don´t touch
	public String getName() {
		return panelName;
	}

	// return the panel don´t touch
	public JPanel getPanel() {
		return panel;
	}

	/*
	 * public void addRegra(Regra regra) { lista_regras.add(regra); }
	 */
	public void setRegras(LinkedList<Regra> regras) {
		for(Regra r: regras){
			if(r.getBox1().equals("LOC"))
				jt[0].addRegraToComboBox(r);
			else
				jt[1].addRegraToComboBox(r);
		}
	}

	public static JComboBox getBoxDefeitos() {
		return boxDefeitos;
	}
	
	public static JTableSample[] getJTables(){
		return jt;
	}
	
}
