package auxAvaliarDefeitos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projetoES1.App;
import projetoES1.Avaliar_Defeitos;
import projetoES1.Criar_Regra;
import projetoES1.Regra;

public class JTableSample implements Observer {
	private JFrame mainFrame;
	private JTable table;

	private JPanel panel;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultTableModel;
	private JButton newColumn;
	private int numOfColumns;
	private final int MAX_NUM_COLUMNS = 5;
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

	private void open() {
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.add(scrollPane, BorderLayout.CENTER);
		mainFrame.add(panel, BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.setLocation(150, 150);
		mainFrame.setVisible(true);
	}

	public JPanel getPanel() {
		return panel;

	}

	public JScrollPane getScroll() {
		return scrollPane;
	}

	public JFrame getFrame() {
		return mainFrame;
	}

	public void addTableColumn() {
		if (numOfColumns <= MAX_NUM_COLUMNS) {
			TableColumn colX = new TableColumn();
			Regra regra = (Regra) rulesBox.getSelectedItem();
			Vector indicadores = new Vector();
			indicadores = getRuleIndicators(regra);
			System.out.println(regra.toString());
			colX.setHeaderValue(regra.toString());
			colX.setIdentifier(regra.toString());
			defaultTableModel.addColumn(regra.toString(), indicadores);
			numOfColumns++;
		}

	}

	public Vector getRuleIndicators(Regra regra) {
		Vector indicadores = new Vector();
		// Deixa a primeira celula em branco
		indicadores.add(null);
		int indexParametro1;
		int indexParametro2;
		 
		/* Se estiver a comparar para o indicador is long method
		 * vai apenas comparar as colunas do loc e do cyclo
		 * se não compara as colunas do atfd e laa
		 */
		if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_long_method"){
			indexParametro1 = 4; //LOC
			indexParametro2 = 5; //CYCLO
		}else{
			indexParametro1 = 6; //ATFD
			indexParametro2 = 7; //LAA
		}
		
		// ROW 4 - 5 - 6 - 7 8 11
		// LOC - CYCLO - ATFD - LAA is_long_method is_feature_envy
		try {
			if (App.file == null)
				return null;
			FileInputStream in = new FileInputStream(App.file);
			XSSFWorkbook wb = new XSSFWorkbook(App.file);

			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				boolean regraAcertou = true;
					// PARAMETRO 1
				if (row.getCell(indexParametro1).getCellType() == CellType.NUMERIC){
					System.out.print("Param1: " + row.getCell(indexParametro1).getNumericCellValue() + " ");
					
				}
				
				
					// PARAMETRO 2
				if (row.getCell(indexParametro2).getCellType() == CellType.NUMERIC)
					System.out.println("Param2: " + row.getCell(indexParametro2).getNumericCellValue());
				if (row.getCell(indexParametro2).getCellType() == CellType.STRING)
					System.out.println("Param2: " + row.getCell(indexParametro2).getStringCellValue());




			}

			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		return indicadores;
	}
	
	public boolean getRuleEvaluation(Regra regra, int a, int b ){
		if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_long_method"){
			
			
			
		}else{
			
			
		}
		return false;
		
	}
	
	public boolean getRuleEvaluation(Regra regra, int a , double b){
		return false;
	}



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JTableSample jts = new JTableSample();
				jts.open();
				;
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Criar_Regra && arg1 instanceof Regra) {
			rulesBox.addItem((Regra) arg1);
		}

	}

	public void setRegras(LinkedList<Regra> regras) {
		for (Regra r : regras) {
			rulesBox.addItem(r);
		}

	}

}