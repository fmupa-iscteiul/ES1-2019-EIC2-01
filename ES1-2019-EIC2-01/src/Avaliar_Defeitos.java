
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/*import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JTableSample {
	private JFrame mainFrame;
	private JTable table;

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
		 

	}

	public void initTable() {
		mainFrame = new JFrame("JTableSample");

		numOfColumns = 1;

		//NOME DE CADA COLUNA É METIDO NESTE ARRAY
		columnNamesList = new ArrayList<String>();
		columnNamesList.add("Indicadores");

		data = new String[1][columnNamesList.size()];

		columnNamesArr = new String[columnNamesList.size()];
		for (int i = 0; i < columnNamesList.size(); i++) {
			columnNamesArr[i] = columnNamesList.get(i);
			data[0][i] = "";
		}

		defaultTableModel = new DefaultTableModel(data, columnNamesArr);

		table = new JTable(defaultTableModel);
		tableColumnModel = table.getColumnModel();

		for (int i = 0; i < columnNamesList.size(); i++) {
			tableColumnModel.getColumn(i).setPreferredWidth(columnNamesList.get(i).length());
		}
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane = new JScrollPane(table);

		//DATA DE CADA LINHA
		// É DO TIPO VECTOR , QUE É UM ARRAY 
		rowDataDCI.add("DCI");
		rowDataDII.add("DII");
		rowDataAII.add("AII");
		rowDataACI.add("ACI");

		

		defaultTableModel.addRow(rowDataDCI);
		defaultTableModel.addRow(rowDataDII);
		defaultTableModel.addRow(rowDataAII);
		defaultTableModel.addRow(rowDataACI);

		table.validate();


		panel = new JPanel();
		panelButton = new JPanel();

		newColumn = new JButton("AddColumn");

		newColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowDataDCI.add("oo");
				rowDataDII.add("3");
				rowDataAII.add("4");
				rowDataACI.add("5");
				System.out.println(rowDataDCI.size());
				defaultTableModel.fireTableRowsUpdated(0, 4);
				addTableColumn();
				defaultTableModel.fireTableRowsUpdated(0, 4);
			}
		});



		// rules.getList()
		String[] listaRegras = { "Regra1" };
		JComboBox rulesBox = new JComboBox(listaRegras);

		panel.add(rulesBox);
		panel.add(newColumn);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.add(scrollPane, BorderLayout.CENTER);
		mainFrame.add(panel, BorderLayout.NORTH);
		mainFrame.add(panelButton, BorderLayout.SOUTH);
		mainFrame.pack();
		mainFrame.setLocation(150, 150);
		mainFrame.setVisible(true);
	}

	public void addTableColumn() {
		if (numOfColumns <= MAX_NUM_COLUMNS) {
			TableColumn colX = new TableColumn();
			colX.setHeaderValue("col" + numOfColumns);
			colX.setIdentifier("col" + numOfColumns);
			defaultTableModel.addColumn(colX);
			defaultTableModel.fireTableRowsUpdated(0, 4);
			numOfColumns++;
		}

		panel.add(newColumn);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JTableSample jts = new JTableSample();
				jts.initTable();
			}
		});
	}
}*/
public class Avaliar_Defeitos {

	private JPanel panel; 
	private String panelName = "Avaliar_Defeitos"; 
	private File path;
	private List<Regra> lista_regras;
	
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
		
		lista_regras = new LinkedList<Regra>();
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
		panelDOWN.add(tabelaDefeitos);
		panelDOWN.add(botaoComparar);
		
		panel.add(panelUP);
		panel.add(panelDOWN);

	}
	
	private void comparar(){
		if(path == null){
			showErrorMessage();
			return;
		}
		
	}
	
	/*return the panel name don´t touch */
	public String getName() {
		return panelName;
	}
	
	/*return the panel  don´t touch */
	public JPanel getPanel() {
		return panel;
	}

	public void addRegra(Regra regra) {
		lista_regras.add(regra);
	}
}

