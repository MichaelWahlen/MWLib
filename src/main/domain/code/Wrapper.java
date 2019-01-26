package main.domain.code;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface Wrapper {

	BiConsumer<? extends Wrapper, ?> setFunction();
	Function<? extends Wrapper, ?> getFunction();
	Class<?> getClassReference();

}