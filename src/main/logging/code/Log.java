package main.logging.code;

public enum Log { TO_CONSOLE,TO_FILE,OFF;
	
	private Log() {
		
	}
	
	public static void log(Exception e, Log log) {
		switch(log) {
		case TO_CONSOLE:
			System.out.println(e.getMessage());
			break;
		case TO_FILE:	
			break;
		case OFF:
		default:
			break;
			
		}
	}
	
	public static void log(String string, Log log) {
		switch(log) {
		case TO_CONSOLE:
			System.out.println(string);
			break;
		case TO_FILE:	
			break;
		case OFF:
		default:
			break;
			
		}
	}
	
}
