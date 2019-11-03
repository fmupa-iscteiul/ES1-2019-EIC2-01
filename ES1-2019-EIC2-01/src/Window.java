import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	
	 private JFrame frame;
	 private JPanel panel;
	 private JPanel tagPanel;
	 private Map<String,JPanel> selectablePanels = new HashMap<String, JPanel>();
	 private static Window INSTANCE;
	 
	 private static int width= 500;
	 private static int lenght = 500;
	 
	private Window() {
		INSTANCE = this;
	}
	
	public static  Window getInstance() {
		if(INSTANCE == null)
			INSTANCE = new Window();
		return INSTANCE;
	}

	 void init() {
			frame = new JFrame();
			panel = new JPanel();
			tagPanel = new JPanel();
			
			
			tagPanel.setLayout(new GridLayout(1,3));
			
			for(String s :selectablePanels.keySet()) {
				JButton button = new JButton(s);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
							JPanel panel = selectablePanels.get(s);
							if(!panel.equals(null)) 
								changePanel(panel);		
					}
				});
				
				tagPanel.add(button);
			}
			
			panel.setLayout( new BorderLayout());
			panel.add(tagPanel,  BorderLayout.NORTH);
			//panel.add(selectTagPanel,BorderLayout.CENTER);
			
			frame.add(panel);
			
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			
		}
	 
	 private void changePanel(JPanel panel) {
		 this.panel.removeAll();
		 this.panel.add(tagPanel,BorderLayout.NORTH);
		 this.panel.add(panel,BorderLayout.CENTER);
		 this.panel.repaint();
		 this.panel.revalidate();
	 }
	 
	 public void open() {
		 init();
		 frame.setSize(width, lenght);
		 frame.setResizable(false);
		 frame.setVisible(true);
	 }
	 
	 public void addTagPainel(String name,JPanel panel) {
		 if(panel != null) 
			 selectablePanels.put(name, panel);
	 }

}
