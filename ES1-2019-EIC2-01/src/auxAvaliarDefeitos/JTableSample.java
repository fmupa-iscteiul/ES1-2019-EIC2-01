package auxAvaliarDefeitos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	private JButton addIPlasma;
	private JButton addPMD;
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
		indicatorNames.add("ADCI");
		indicatorNames.add("ADII");
		defaultTableModel.addColumn("Indicadores", indicatorNames);

		table.validate();
		panel = new JPanel();
		newColumn = new JButton("AddColumn");
		addIPlasma = new JButton("Add iPlasma");
		addPMD = new JButton("Add PMD");

		newColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTableColumn();
			}
		});
		
		addIPlasma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPMDorIPlasma("iPlasma");
			}
		});
		
		addPMD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPMDorIPlasma("PMD");
			}
		});

		rulesBox = new JComboBox();

		panel.add(rulesBox);
		panel.add(newColumn);
		panel.add(addIPlasma);
		panel.add(addPMD);

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

	public void addPMDorIPlasma(String nome){
		if(App.file == null){
			JOptionPane.showMessageDialog(panel, "File not loaded");
			System.out.println("Ficheiro nulo");
			return;
		}
		if (numOfColumns <= MAX_NUM_COLUMNS) {
			TableColumn colX = new TableColumn();
			Vector indicadores = new Vector();
			indicadores.add(null);
			indicadores.add(0);
			indicadores.add(0);
			indicadores.add(0);
			indicadores.add(0);
			
			//nr da coluna do is_long_method
			int indexReal = 8;
			int indexPMDorIPlasma;
			if(nome.equals("PMD")){
				indexPMDorIPlasma = 10;
				colX.setHeaderValue("PMD");
				colX.setIdentifier("PMD");
			}
			else{
				indexPMDorIPlasma = 9;
				colX.setHeaderValue("iPlasma");
				colX.setIdentifier("iPlasma");
			}
			try {
				if (App.file == null)
					return;
				FileInputStream in = new FileInputStream(App.file);
				XSSFWorkbook wb = new XSSFWorkbook(App.file);

				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;

				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					boolean avaliacaoReal;
					boolean avaliacaoPMD;
					
					System.out.println("Real: " + row.getCell(8).getBooleanCellValue() + " PMD ou iPlasma: " + row.getCell(indexPMDorIPlasma).getBooleanCellValue());
					
					avaliacaoPMD = row.getCell(indexPMDorIPlasma).getBooleanCellValue();
					avaliacaoReal = row.getCell(indexReal).getBooleanCellValue();
					indicadores = comparaRealComRegra(indicadores, avaliacaoReal, avaliacaoPMD);
					System.out.println("DCI - " + indicadores.get(1) );
					System.out.println("DII - " + indicadores.get(2) );
					System.out.println("ADCI - " + indicadores.get(3) );
					System.out.println("ADII - " + indicadores.get(4) );
				}

				wb.close();
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
			
			defaultTableModel.addColumn(colX.getHeaderValue(), indicadores);
			numOfColumns++;
		}
	}
	
	
	
	public JScrollPane getScroll() {
		return scrollPane;
	}

	public JFrame getFrame() {
		return mainFrame;
	}

	public void addTableColumn() {
		if(App.file == null){
			JOptionPane.showMessageDialog(panel, "File not loaded");
			System.out.println("Ficheiro nulo");
			return;
		}
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
		indicadores.add(0);
		indicadores.add(0);
		indicadores.add(0);
		indicadores.add(0);
		int indexParametro1;
		int indexParametro2;
		int indexColunaReal;
		 
		/* Se estiver a comparar para o indicador is long method
		 * vai apenas comparar as colunas do loc e do cyclo
		 * se não compara as colunas do atfd e laa
		 */
		if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_long_method"){
			indexParametro1 = 4; //LOC
			indexParametro2 = 5; //CYCLO
			indexColunaReal = 8;
		}else{
			indexParametro1 = 6; //ATFD
			indexParametro2 = 7; //LAA
			indexColunaReal = 11;
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
				double a = 0.0;
				double b = 0.0;
					// PARAMETRO 1
				if (row.getCell(indexParametro1).getCellType() == CellType.NUMERIC){
					System.out.print("Param1: " + row.getCell(indexParametro1).getNumericCellValue() + " ");
					a = row.getCell(indexParametro1).getNumericCellValue();
					
				}
					// PARAMETRO 2
				if (row.getCell(indexParametro2).getCellType() == CellType.NUMERIC){
					System.out.println("Param2: " + row.getCell(indexParametro2).getNumericCellValue());
					b = row.getCell(indexParametro2).getNumericCellValue();
				}
				if (row.getCell(indexParametro2).getCellType() == CellType.STRING){
					System.out.println("Param2: " + row.getCell(indexParametro2).getStringCellValue());
					b = Double.parseDouble(row.getCell(indexParametro2).getStringCellValue());
				}
				boolean avaliacaoDaRegra = false;
				
				if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_long_method"){
					avaliacaoDaRegra = getRuleEvaluation(regra, (int)a, (int)b);
				}else{
					avaliacaoDaRegra = getRuleEvaluation(regra, (int)a, b);
				}
				boolean avaliacaoReal = row.getCell(indexColunaReal).getBooleanCellValue();
				System.out.println();
				indicadores = comparaRealComRegra(indicadores, avaliacaoReal, avaliacaoDaRegra);
			}

			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		return indicadores;
	}
	
	public Vector comparaRealComRegra(Vector v, boolean real, boolean regra){
		// 1 - DCI
		// 2 - DII 
		// 3 - ADCI FALSE - FALSE
		// 4 - ADII
		if(isDCI(real, regra)){
			int incrementa = (int)v.get(1) + 1;
			v.set(1, (Object)incrementa);
		}else if(isDII(real, regra)){
			int incrementa = (int)v.get(2) + 1;
			v.set(2, (Object)incrementa);
		}else if(isADCI(real, regra)){
			int incrementa = (int)v.get(3) + 1;
			v.set(3, (Object)incrementa);
		}else if(isADII(real, regra)){
			int incrementa = (int)v.get(4) + 1;
			v.set(4, (Object)incrementa);
		}
		return v;
	}
	
	public boolean isDCI(boolean real, boolean regra){
		if(real && regra)
			return true;
		return false;
	}
	public boolean isDII(boolean real, boolean regra){
		if(!real && regra)
			return true;
		return false;
	}
	public boolean isADCI(boolean real, boolean regra){
		if(!real && !regra)
			return true;
		return false;
	}
	public boolean isADII(boolean real, boolean regra){
		if(real && !regra)
			return true;
		return false;
	}
	
	public boolean getRuleEvaluation(Regra regra, int a, int b){
		if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_long_method"){
		    if(regra.getAnd_Or().equals("AND")){
		    	int valorLOC = regra.getNumber1();
		    	int valorCYCLO = regra.getNumber2();
		    	if(a < valorLOC){
		    		return false;
		    	}else if( b < valorCYCLO){
		    		return false;
		    	}
		    	return true;
		    }
		    // SE FOR OR 
		    else{
		    	int valorLOC = regra.getNumber1();
		    	int valorCYCLO = regra.getNumber2();
		    	if( a < valorLOC && b < valorCYCLO){
		    		return false;
		    	}
		    	return true;
		    }
		}
		return false;
		
	}
	
	public boolean getRuleEvaluation(Regra regra, int a , double b){
		if(Avaliar_Defeitos.getBoxDefeitos().getSelectedItem() == "is_feature_envy"){
		    if(regra.getAnd_Or().equals("AND")){
		    	int valorATFD = regra.getNumber1();
		    	double valorLAA = regra.getNumber2LAA();
		    	if(a < valorATFD){
		    		return false;
		    	}else if( b > valorLAA){
		    		return false;
		    	}
		    	return true;
		    }
		    // SE FOR OR 
		    else{
		    	int valorATFD = regra.getNumber1();
		    	double valorLAA = regra.getNumber2LAA();
		    	if( a < valorATFD && b > valorLAA){
		    		return false;
		    	}
		    	return true;
		    }
		}
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
<<<<<<< HEAD
		Regra regra = (Regra) arg1;
		if(Avaliar_Defeitos.getJTables()[0] == this){
			if(regra.getBox1().equals("LOC")){
				rulesBox.addItem(regra);
			}
=======
		System.out.println("Updating");
		System.out.println((arg0).toString());
		if (arg0 instanceof Criar_Regra && arg1 instanceof Regra) {
			rulesBox.addItem((Regra) arg1);
>>>>>>> branch 'master' of https://github.com/fmupa-iscteiul/ES1-2019-EIC2-01.git
		}
<<<<<<< HEAD
		if(Avaliar_Defeitos.getJTables()[1] == this){
			if(regra.getBox1().equals("ATFD")){
				rulesBox.addItem(regra);
			}
		}
		
=======
		LinkedList<Regra> regras = ((Criar_Regra)arg0).getRegras_carregadas();
		int rulesBox_size = rulesBox.getItemCount();
		boolean found = false;
		Regra rule_to_add = null;
		for(Regra regra: regras) {
			for(int index = 0; index < rulesBox_size; index++) {
				if(rulesBox.getItemAt(index) == regra) {
					System.out.println("I have found the same rule");
					found = true;
				}
				else {
					rule_to_add = regra;
					found = false;
				}
			}
		}

		if(found == false && rule_to_add != null) {
			rulesBox.addItem(rule_to_add);
		}

>>>>>>> branch 'master' of https://github.com/fmupa-iscteiul/ES1-2019-EIC2-01.git
	}

	
	public void addRegraToComboBox(Regra regra){
		rulesBox.addItem(regra);
	}

}