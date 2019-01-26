package main.abstractgui.code;

import java.awt.GridLayout;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SectionToFrame {

	private final JFrame frame = new JFrame();
	private final JPanel topPanel = new JPanel();
	
	public SectionToFrame(Section section) {
		frame.add(topPanel);	
		recurse(section,topPanel);	
	}

	private void recurse(Section section, JPanel panel) {
		for(Entry<Integer, Section> entry:section.publishChildren().entrySet()) {
			Section localSection = entry.getValue(); 
			if(localSection.isContainer()) {
				JPanel newPanel = new JPanel();	
				newPanel.setLayout(new GridLayout(0,localSection.getColumns()));
				panel.add(newPanel);
				recurse(localSection, newPanel);
			} else {
				JPanel itemPanel = new JPanel(new GridLayout(1,2));						
				itemPanel.add(new JLabel(localSection.getLabelText()));
				itemPanel.add(new JLabel(localSection.get(localSection.getClassReference()).toString()));
				panel.add(itemPanel);	
			}		
		}
	}
	
	public void showFrame() {
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
