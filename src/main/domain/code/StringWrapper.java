package main.domain.code;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class StringWrapper implements Wrapper {
	
	private String value = "";	
	
	@Override
	public Class<?> getClassReference(){
		return value.getClass();
	}
	
	@Override
	public Function<StringWrapper, String> getFunction() {
		Function<StringWrapper, String> function = (e)->{
			return value;
		};
		return function;
	}
	
	@Override
	public BiConsumer<StringWrapper, String> setFunction(){
		BiConsumer<StringWrapper, String> function = (StringWrapper e, String t)->{
			value = t;
		};
		return function;
	}
	
}
