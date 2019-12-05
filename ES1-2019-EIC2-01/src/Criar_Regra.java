import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
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
	private JTextField input2;
	private JTextField rule_name;
	private boolean mais_pressed = false;
	
	private String[] l = new String[] {"LOC", "CYCLO", "ATFD", "LAA"};
	private String[] l1 = new String[] {"<=", ">="};
	private String[] l2 = new String[] {"AND", "OR"};
	private int times_button_was_clicked = 0;
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	private JComboBox<String> box4;
	private JComboBox<String> box5; 

	public Criar_Regra() {
		init();
	}

	private void init() {
		
		panel 			= new JPanel();
		input_panel 	= new JPanel();
		bottom_panel	= new JPanel();
		
		panel.setLayout(new BorderLayout(10,10));
		input_panel.setLayout(new GridLayout(1,2));
		bottom_panel.setLayout(new GridLayout(2,1));
		
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		JButton mais = new JButton("+");
		
		input 		= new JTextField("Input");
		rule_name 	= new JTextField("Insert rule name");
		
		addBoxes();

		panel.add(rule_name, BorderLayout.NORTH);
		input_panel.add(input, BorderLayout.NORTH);

		bottom_panel.add(save, BorderLayout.SOUTH);
		bottom_panel.add(load, BorderLayout.SOUTH);
		
		panel.add(mais, BorderLayout.WEST);
		panel.add(input_panel, BorderLayout.CENTER);
		panel.add(bottom_panel, BorderLayout.SOUTH);
		
		mais.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moreBoxes();
			} 
		});
		
		save.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				saveRules();
			}
		});

		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTheRules();
			}
		});
	}
	
	protected void saveRules() {
		try {
			File file = new File("regras.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			if(!mais_pressed) {
				String s1 = rule_name.getText();
				String s2 = (String) box1.getSelectedItem();
				String s3 = (String) box2.getSelectedItem();
				String s4 = (String) input.getText();
				int news4 = Integer.parseInt(s4);
				if(s2 != null && s3 != null && s4 != null) {
					PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
					pw.println(s1 + " ( " +s2 + " " + s3 + " " + news4+ " ) ");
					pw.close();
				}
			} else {
				System.out.println("yo");
				String s1 = rule_name.getText();
				String s2 = (String) box1.getSelectedItem();
				String s3 = (String) box2.getSelectedItem();
				String s4 = (String) input.getText();
				int news4 = Integer.parseInt(s4);
				String s5 = (String) box5.getSelectedItem();
				String s6 = (String) box3.getSelectedItem();
				System.out.println(s6);
				String s7 = (String) box4.getSelectedItem();
				String s8 = (String) input2.getText();
				int news8 = Integer.parseInt(s8);
				if(s2 != null && s3 != null && s4 != null && s5 != null && s6 != null && s7 != null && s8 != null) {
					PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
					pw.println(s1 + " ( " +s2 + " " + s3 + " " + news4+ " ) " + s5 + " ( " +s6 + " " + s7 + " " + news8 + " ) ");
					pw.close();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	protected void moreBoxes() {
		// TODO Auto-generated method stub
		mais_pressed = true;
		if(times_button_was_clicked < 1) {
			box3 = new JComboBox<String>(l);
			box4 = new JComboBox<String>(l1);
			input2 = new JTextField("Input2");
			input_panel.add(box3, BorderLayout.CENTER);
			input_panel.add(box4, BorderLayout.CENTER);
			input_panel.add(input2, BorderLayout.CENTER);
			panel.add(input_panel, BorderLayout.CENTER);
			times_button_was_clicked++;
		}
		panel.updateUI();
	}

	/**
	 * Creates boxes that are used to create rules
	 */
	private void addBoxes() {
		
		box1 = new JComboBox<String>(l);
		box2 = new JComboBox<String>(l1);
		box5 = new JComboBox<String>(l2);

		input_panel.add(box1, BorderLayout.CENTER);
		input_panel.add(box2, BorderLayout.CENTER);
		
		panel.add(box5, BorderLayout.EAST);

	}

	protected void loadTheRules() {
		
		//TODO after possibility to save rules in the file
		JFileChooser file = new JFileChooser(".");

		file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = file.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			File selectedFile = file.getSelectedFile();

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