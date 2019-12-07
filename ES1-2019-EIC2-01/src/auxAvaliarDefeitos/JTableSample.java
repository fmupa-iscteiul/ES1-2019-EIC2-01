package auxAvaliarDefeitos;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import projetoES1.Criar_Regra;
import projetoES1.Regra;

public class JTableSample implements Observer {
	private JFrame mainFrame;
	private JTable table;

	private ArrayList<Regra> listaRegras;
	private TableColumnModel tableColumnModel;
	private JPanel panel;
	private String[] columnNamesArr;
	private ArrayList<String> columnNamesList;
	private JScrollPane scrollPane;
	private String[][] data;
	private DefaultTableModel defaultTableModel;
	private JButton newColumn;
	private JPanel panelButton;
	private int numOfColumns;
	private final int MAX_NUM_COLUMNS = 5;
	private Vector rowDataDCI = new Vector(4);
	private Vector rowDataDII = new Vector(4);
	private Vector rowDataAII = new Vector(4);
	private Vector rowDataACI = new Vector(4);
	private JComboBox rulesBox;

	public int counter = 1;

	public JTableSample() {
		// COMO ADICIONAR LINHAS

		/*
		 * addButton = new JButton("Add"); deleteButton = new JButton("Delete");
		 * 
		 * addButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Vector rowData = null;
		 * defaultTableModel.addRow(rowData); table.validate(); } });
		 * 
		 * deleteButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Vector rowData = null; int rowCount
		 * = defaultTableModel.getRowCount(); if(rowCount>0) {
		 * defaultTableModel.removeRow(rowCount-1); table.validate(); } } });
		 */
		initTable();

	}

	public void initTable() {
		mainFrame = new JFrame("JTableSample");
		numOfColumns = 1;
		defaultTableModel = new DefaultTableModel();
		table = new JTable(defaultTableModel);
		scrollPane = new JScrollPane(table);


		Vector indicatorNames = new Vector();
		indicatorNames.add(null);
		indicatorNames.add("DCI");
		indicatorNames.add("DII");
		indicatorNames.add("AII");
		indicatorNames.add("ACI");
		defaultTableModel.addColumn("Indicadores", indicatorNames);

		table.validate();
		panel = new JPanel();
		newColumn = new JButton("AddColumn");
		
		newColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTableColumn();
			}
		});
		
		rulesBox = new JComboBox();

		panel.add(rulesBox);
		panel.add(newColumn);
		
	}
	
	private void open(){
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.add(scrollPane, BorderLayout.CENTER);
		mainFrame.add(panel, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setLocation(150, 150);
		mainFrame.setVisible(true);
	}

	public JPanel getPanel(){
		return panel;
		
	}
	
	public JScrollPane getScroll(){
		return scrollPane;
	}
	
	public JFrame getFrame(){
		return mainFrame;
	}
	public void addTableColumn() {
		if (numOfColumns <= MAX_NUM_COLUMNS) {
			TableColumn colX = new TableColumn();
			Regra regra = (Regra) rulesBox.getSelectedItem();
			colX.setHeaderValue(regra.toString());
			colX.setIdentifier(regra.toString());
			Vector indicadores = getRuleIndicators(regra);
			defaultTableModel.addColumn(regra.toString(), indicadores);
			numOfColumns++;
		}

	}
	
	public Vector getRuleIndicators(Regra regra){
		Vector indicadores = new Vector();
		//Deixa a primeira celula em branco
		indicadores.add(null);
		
		
		return indicadores;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JTableSample jts = new JTableSample();
				jts.open();;
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Criar_Regra && arg1 instanceof Regra)
		{
			rulesBox.addItem((Regra)arg1);
		}
		
	}

	public void setRegras(LinkedList<Regra> regras) {
		for(Regra r: regras)
		{
			rulesBox.addItem(r);
		}
		
	}


}