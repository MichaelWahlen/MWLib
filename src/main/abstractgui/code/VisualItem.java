package main.abstractgui.code;

public class VisualItem {
	
	private String label;	
	
	public VisualItem(String label) {
		setLabel(label);
	}
	
	public String getString() {
		return "I am visual";
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
