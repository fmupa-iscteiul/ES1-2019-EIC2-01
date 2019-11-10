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

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class File {
	
	private JPanel panel; 
	private String panelName = "file"; 

	
	public File() {
		init();

	}

	/**
	 * iniciação do painel e botão para procurar ficheiro excel
	 */
	
	private void init() {
		panel = new JPanel();

		
		JButton search = new JButton("search");
		search.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

					try {

					}catch(NullPointerException e2)	{
						JOptionPane.showMessageDialog(panel, "the file cannot open, please verify your excel file.");
					}
				}

		});
		
		panel.add(search);

		
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
