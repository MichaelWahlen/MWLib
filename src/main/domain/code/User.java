package main.domain.code;


import java.util.function.Consumer;


public class User {
	
	private final StringWrapper firstName = new StringWrapper();
	private IntWrapper startScale = new IntWrapper();

	public IntWrapper getStartScale() {
		return startScale;
	}
	
	public StringWrapper getFirstName() {
		return firstName;
	}	
	
	public Consumer<User> getExecute1(){
		Consumer<User> function = e->{
			e.getFirstName().setFunction().accept(e.firstName, "John");			
		};
		return function;
	}	
		
	public Consumer<User> retrieveIncreaseScale(){
		Consumer<User> function = e->{
			e.getStartScale().setFunction().accept(e.startScale, 99);
		};
		return function;
	}
		
}
