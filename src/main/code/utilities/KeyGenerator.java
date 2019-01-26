package main.code.utilities;

public class KeyGenerator {
	private static int currentKey = 0;	
	
	public synchronized static int getNextKey() {
		currentKey++;
		return currentKey;
	}
	
	public synchronized static int lastProvidedKey() {
		return currentKey;
	}
	
}
