package main.abstractgui.code;

import java.util.function.Consumer;

public class WrapperExecute<G> {
	private Consumer<G> execute;
	private final G executable;
	
	public WrapperExecute(G valueWrapper) {
		executable = valueWrapper;
	}
	
	public void provideExecuteFunction(Consumer<G> function) {
		this.execute = function;
	}
	
	public void execute() {
		execute.accept(executable);
	}
}
