package main.abstractgui.code;

public class PreferredCoords {
	
	private int w;
	private int h;
	
	private PreferredCoords(int w, int h) {
		this.w = w;
		this.h = h;
	}	
	
	public static PreferredCoords build() {		
		PreferredCoords returnValue = new PreferredCoords(0,0);			
		return returnValue;
	}

	public int getWidth() {
		return w;
	}

	private static void validate(int w, int h) throws ValidationError {
		if(w<0||h<0) {
			throw new ValidationError("Negative heigth or width provided, when setting preferred size.\n"+"w:" +w+" h: "+h);
		}	
	}
	
	public int getHeight() {
		return h;
	}

	public void changeTo(int w, int h) throws ValidationError {
		validate(w,h);
		setPreferredWidth(w);
		setPreferredHeigth(h);
	}

	private void setPreferredWidth(int w) {
		this.w = w;
	}

	private void setPreferredHeigth(int h) {
		this.h = h;
	}
	
}
