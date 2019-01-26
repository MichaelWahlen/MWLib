package main.abstractgui.code;

public class Label {
	
	private String description;	
	
	public Label(String label) {
		setLabel(label);
	}
	
	public String getLabel() {
		return description;
	}
	
	public void setLabel(String label) {
		this.description = label;
	}
}
