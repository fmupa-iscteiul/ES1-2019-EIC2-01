import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Avaliar_Defeitos {

	private JPanel panel;
	private String panelName = "Avaliar_Defeitos";
	private String path;

	
	/* 
	 * 
	 * https://stackoverflow.com/questions/34128218/setting-a-column-name-for-a-jtable-with-editable-number-of-columns-and-rows
	 */
	
	
	public Avaliar_Defeitos() {
		init();
		path = App.path;

		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			// HSSFCell cell;

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				int colunaVerdadeiro = 8;
				int colunaIPlasma = 9;
				int colunaPMD = 10;
				row = sheet.getRow(i);

				boolean longMethodVerdadeiro = row.getCell(colunaVerdadeiro).getBooleanCellValue();
				boolean longMethodIPlasma = row.getCell(colunaIPlasma).getBooleanCellValue();
				boolean longMethodPMD = row.getCell(colunaPMD).getBooleanCellValue();

				// c == null || c.getCellType() == Cell.CELL_TYPE_BLANK

				if (row.getCell(7) == null) {
					row.createCell(7);
				}
				if (row.getCell(0) == null) {
					row.createCell(0);
				}

				System.out.println(row.getCell(0).getNumericCellValue() + " " + row.getCell(7).getNumericCellValue());

			}

			try (FileOutputStream outputStream = new FileOutputStream(
					"C:\\Users\\bruno\\Desktop\\Faculdade\\PI\\gerarCod.xls")) {
				wb.write(outputStream);
			}
			fs.close();
			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}


	}

	private void showErrorMessage() {
		panel = new JPanel();
		JOptionPane.showMessageDialog(panel, "Ficheiro não selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	private void init() {
		panel = new JPanel();
		GridLayout layout = new GridLayout(2, 0);
		panel.setLayout(layout);
		JPanel panelUP = new JPanel();
		JPanel panelDOWN = new JPanel();

		String[] nomeColuna = { "aaa", "aaaaa" };

		Object[][] indicadores = { { "DCI", " " }, { "DII", " " }, { "AII", " " }, { "ACI", " " }, };

		// Tabela
		JTable tabelaDefeitos = new JTable(indicadores, nomeColuna);
		JLabel labelParametro = new JLabel("Parametro:");

		String[] lista_defeitos = { "is_long_method", "is_feature_envy" };
		JComboBox boxDefeitos = new JComboBox(lista_defeitos);
		JButton botaoAddColuna = new JButton("+");
		JButton botaoComparar = new JButton("Comparar");

		botaoComparar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (path == null) {
					showErrorMessage();
					return;
				}
			}
		});

		boxDefeitos.setSize(100, 100);
		;
		panelUP.add(labelParametro);
		panelUP.add(boxDefeitos);
		panelDOWN.add(tabelaDefeitos);
		panelDOWN.add(botaoAddColuna);
		panelDOWN.add(botaoComparar);

		panel.add(panelUP);
		panel.add(panelDOWN);

	}

	private void comparar() {
		if (path == null) {
			showErrorMessage();
			return;
		}

	}

	private HSSFCell getCell(String path, int row, int column) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			if(fs == null){
				System.out.println("Path " + path + " não encontrado.");
				return null;
			}
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow rowFile;
			// HSSFCell cell;
			
			if(row <= 0 || row > sheet.getLastRowNum()){
				System.out.println("Linha " + row + " é inválida");
				return null;
			}
			if(column < 0 || column > sheet.getRow(row).getLastCellNum()){
				System.out.println("Coluna " + column + " é inválida");
				return null;
			}
			rowFile = sheet.getRow(row);

			fs.close();
			wb.close();
			
			return rowFile.getCell(column);
			
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return null;

	}

	private int[] avaliarRegra() {
		// DCI - DII - AII - ACI
		int[] contadores = new int[4];

		return contadores;
	}

	/* return the panel name don´t touch */
	public String getName() {
		return panelName;
	}

	/* return the panel don´t touch */
	public JPanel getPanel() {
		return panel;
	}
	
	
	
	
	
	
	
	
}
