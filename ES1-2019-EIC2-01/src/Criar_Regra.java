import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;


import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author pedro
 * 
 * Criar_Regra é a interface para criar uma regra 
 * e definesse o conjunto de métricas que são dadas 
 * e compará-las
 *
 */
public class Criar_Regra {

	private JPanel panel;
	private JPanel input_panel;
	private JPanel bottom_panel;

	private String panelName = "Criar Regra";

	private JTextField input;
	private JTextField rule_name;

	public Criar_Regra() {
		init();
	}

	private void init() {
		
		panel 			= new JPanel();
		input_panel 	= new JPanel();
		bottom_panel	= new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));

		input_panel.setLayout(new GridLayout(1,3));

		input_panel.setLayout(new GridLayout(3,2));
		bottom_panel.setLayout(new GridLayout(2,1));
		
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		JButton mais = new JButton("+");


		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTheRules();
				
			}
		});
		
		input 		= new JTextField("Input");
		rule_name 	= new JTextField("Insert rule name");


		panel.add(rule_name,BorderLayout.NORTH);

		input_panel.add(rule_name, BorderLayout.CENTER);
		input_panel.add(input, BorderLayout.NORTH);

		bottom_panel.add(save, BorderLayout.SOUTH);
		bottom_panel.add(load, BorderLayout.SOUTH);
		
		addBoxes();
		
		panel.add(mais, BorderLayout.WEST);
		panel.add(input_panel, BorderLayout.CENTER);
		panel.add(bottom_panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates boxes that are used to create rules
	 */
	private void addBoxes() {

		
		String[] l = new String[] {"LOC", "CYCLO", "ATFD", "LAA"};
		String[] l1 = new String[] {"<=", ">="};
		String[] l2 = new String[] {"AND", "OR"};
		
		JComboBox<String> box1 = new JComboBox<String>(l);
		JComboBox<String> box2 = new JComboBox<String>(l1);


		JComboBox<String> box3 = new JComboBox<String>(l);
		JComboBox<String> box4 = new JComboBox<String>(l1);
		JComboBox<String> box5 = new JComboBox<String>(l2);

		
		input_panel.add(box1, BorderLayout.CENTER);
		input_panel.add(box2, BorderLayout.CENTER);

		input_panel.add(input, BorderLayout.CENTER);
		panel.add(input_panel, BorderLayout.CENTER);
		
		JButton mais = new JButton("+");
		mais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> box3 = new JComboBox<String>(l);
				JComboBox<String> box4 = new JComboBox<String>(l1);
				input = new JTextField("Input");
				input_panel.add(box3, BorderLayout.CENTER);
				input_panel.add(box4, BorderLayout.CENTER);
				input_panel.add(input, BorderLayout.CENTER);
				panel.add(input_panel, BorderLayout.CENTER);
			}
		});
		
		panel.add(mais, BorderLayout.WEST);
		
		input_panel.add(box3, BorderLayout.CENTER);
		input_panel.add(box4, BorderLayout.CENTER);

		
		panel.add(box5, BorderLayout.EAST);
		

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Scanner s = new Scanner(System.in);
					File file = new File("regras.txt");
					if(!file.exists()) {
						file.createNewFile();
					}
					/*String s1 = rule_name.getText();
					String s2 = (String) box1.getSelectedItem();
					String s3 = (String) box2.getSelectedItem();
					String s4 = (String) input.getSelectedText();
					PrintWriter pw = new PrintWriter(file);
					pw.println(s1 + " ("+s2 + " " + s3 + " " + s4+ ") ");
					s.nextLine();
					pw.close();
					s.close();
					JFileChooser fs = new JFileChooser(new File("c:\\"));
					fs.setDialogTitle("Save File");
					int result = fs.showSaveDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						String s1 = rule_name.getText();
						String s2 = (String) box1.getSelectedItem();
						String s3 = (String) box2.getSelectedItem();
						File f = fs.getSelectedFile();
						try {
							FileWriter fw = new FileWriter(f.getPath());
							fw.write(s1 + " ("+s2 + " " + s3 + " ) ");
							fw.flush();
							//String s3 = s.next();
							fw.close();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}*/
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(save, BorderLayout.SOUTH);

	}

	protected void loadTheRules() {
		
		//TODO after possibility to save rules in the file
		JFileChooser jfc = new JFileChooser(".");

		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			File selectedFile = jfc.getSelectedFile();

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
	/**
	 * Este metodo devolve o nome que o user da a uma regra nova
	 * @return
	 */

	public String getRuleName() {
		return rule_name.getName();
	}

	/**
	 * Este metodo devolve o valor que o user indica
	 * @return
	 */

	public String getInputValue() {
		return input.getName();
	}
	
	/**
	 * Este painel devolve as combo boxes, inputs e nome da regra 
	 * @return 
	 */
	
	public JPanel getPanel1() {
		return input_panel;
	}

}