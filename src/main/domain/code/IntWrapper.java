package main.domain.code;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class IntWrapper implements Wrapper {
	
	private Integer value = 0;	
	
	@Override
	public Class<?> getClassReference(){
		return value.getClass();
	}
	
	@Override
	public Function<IntWrapper, Integer> getFunction() {
		Function<IntWrapper, Integer> function = (e)->{
			return value;
		};
		return function;
	}
	
	@Override
	public BiConsumer<IntWrapper, Integer> setFunction(){
		BiConsumer<IntWrapper, Integer> function = (IntWrapper e, Integer t)->{
			value = t;
		};
		return function;
	}
}
