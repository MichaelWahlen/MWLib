package main.abstractgui.code;

import java.util.function.BiConsumer;
import java.util.function.Function;
import main.domain.code.Wrapper;

/**
 * Class serves to wrap both an value and an object, meaning that it might be cleaner to split this...
 * @author Wahlen
 *
 * @param <G> the object that is used to call execute functions via this view. The wrapped value does not need to come from this object.
 * @param <T> the wrapped value that is set/got via this view
 * @param <E> the return type of the wrapper value
 */
public class WrapperView <T extends Wrapper,E> {	
	
	private Function<T,E> get;	
	private BiConsumer<T,E> set;
	private final T boundObject;
	
	private final Class<E> classReference;
	
	@SuppressWarnings("unchecked")
	public WrapperView(T valueWrapper) {
		this.boundObject = valueWrapper;		
		this.get= (Function<T, E>) valueWrapper.getFunction();
		this.set = (BiConsumer<T, E>) valueWrapper.setFunction();
		this.classReference = (Class<E>) valueWrapper.getClassReference();
	}

	public Class<E> getClassReference(){
		return classReference;
	}
	
	public void set(Object e) {		
		set.accept(boundObject,classReference.cast(e));		
	}
	
	public E get() {
		return classReference.cast(get.apply(boundObject));
	}


}
