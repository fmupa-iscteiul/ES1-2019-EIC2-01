import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Leitura_Ficheiro {
	
	private JPanel panel; 
	private String panelName = "file"; 
	private JTable tabela;
	private DefaultTableModel modeloTabela;
	private final  JFileChooser openFileChooser;
	
	public Leitura_Ficheiro() {
		init();
		openFileChooser = new JFileChooser();
		openFileChooser.setFileFilter(new FileNameExtensionFilter("xlsx text", "xlsx"));
	}
	
	private void init() {
		panel = new JPanel();
		tabela = new JTable();
		modeloTabela = new DefaultTableModel();
		tabela.setModel(modeloTabela);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);
		
		JButton search = new JButton("search");
		search.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int returnvalue = openFileChooser.showOpenDialog(panel);
				if(returnvalue == JFileChooser.APPROVE_OPTION) {
					try {
						FileInputStream in = new FileInputStream(openFileChooser.getSelectedFile());
						removeAllLine();
						showFileInWindow(in);
						App.file= openFileChooser.getSelectedFile();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(panel, "problem in read file");
					}catch(NullPointerException e2)	{
						JOptionPane.showMessageDialog(panel, "the file cannot open, please verify your excel file.");
					}
				}
				else {
					JOptionPane.showMessageDialog(panel, "file not uploaded");
					
				}
				
			}
		});
		
		panel.add(search);
		panel.add(scrollPane);
		
	}
	
	private void showFileInWindow(FileInputStream file) throws IOException {
		Workbook workbook = new XSSFWorkbook(file);
		 org.apache.poi.ss.usermodel.Sheet firstSheet =  workbook.getSheetAt(0);
		 modeloTabela.setRowCount(firstSheet.getLastRowNum());
		 modeloTabela.setColumnIdentifiers(getCells(firstSheet.getRow(0)));
		 for(int i =1; i<= firstSheet.getLastRowNum(); i++) {
			 Row nextRow = firstSheet.getRow(i);
				modeloTabela.insertRow(i-1, getCells(nextRow));
		 }
		 workbook.close();
	}
	
	private String[] getCells(Row row)  throws NullPointerException{
		DataFormatter dataformatter = new DataFormatter();
		String[] result = new String[row.getLastCellNum()];
		 for(int i=0; i< row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			result[i]= dataformatter.formatCellValue(cell);
		 }
		 return result;
	}
	
	private void removeAllLine() {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		for(int i =model.getRowCount() -1; i>= 0; i--)
			model.removeRow(i);
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
