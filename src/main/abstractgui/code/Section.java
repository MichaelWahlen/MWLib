package main.abstractgui.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.code.utilities.KeyGenerator;

public class Section {	
	
	private final int key;	
	private final Label label = new Label("Default");
	private final WrapperView<? ,?> view;
	private final WrapperExecute<?> executeView;
	private Section parent;
	private final boolean isContainer;
	private final List<Integer> orderOfKeys = new ArrayList<>();
	private final Map<Integer, Section> children = new HashMap<>();
	private final int columns;
	
	public Map<Integer, Section> publishChildren(){
		// has to be a deep copy, of only information which is relevant for graphical representation.
		return children;
	}
	
	public List<Integer> publishOrdering(){		
		List<Integer> returnList = new ArrayList<>();
		orderOfKeys.forEach(e->{			
			returnList.add(Integer.valueOf(e));			
		});		
		return returnList;
	}
	
	public void addChild(final Section field) throws ValidationError {		
		insertChild(field, children.size());
	}	
	
	public void insertChild(final Section field, final int beforeKey) throws ValidationError {		
		validateInsertion(field, beforeKey);		
		int fieldKey = field.getKey();
		field.setParent(this);
		orderOfKeys.add(beforeKey,fieldKey);
		children.put(fieldKey, field);			
	}
	
	private void validateInsertion(final Section field, final int beforeKey) throws ValidationError {
		if(!isContainer()) {	
			throw new ValidationError("Trying to add a field to a non-container.\n" +"Parent: "+this.getKey()+" Child: "+field.getKey());
		}
		if(children.size()!=beforeKey&&!orderOfKeys.contains(key)) {
			throw new ValidationError("Item to insert before not found.\n"+"Key: "+ key);
		}	
		validateUpStream(field.getKey());
	}

	private void validateUpStream(final int key) throws ValidationError {
		if (!(parent==null)){
			 if(parent.getKey()==key) {
					throw new ValidationError("Trying to add a container to itself.\n"+"Key: "+key);
			 }
			 parent.validateUpStream(key);
		}	
	}
	
	public void execute() {
		executeView.execute();
	}
	
	public Class<?> getClassReference(){
		return view.getClassReference();
	}
	
	public <T> T get(Class<T> e) {
		return e.cast(view.get());	
	}	
	
	public void set(Object string) {		
		view.set(string);		
	}
	
	public int getKey() {
		return key;
	}
	
	private void setParent(final Section field) {
		this.parent = field;		
	}	

	public String getLabelText() {
		return label.getLabel();
	}

	public void setLabelText(String label) {
		this.label.setLabel(label);
	}
	
	public boolean isContainer() {
		return isContainer;
	}

	public int getColumns() {
		return columns;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Key: "+key);
		if(parent!=null) {
			stringBuilder.append("\n"+"Direct parent key: "+parent.key);
		}
		return stringBuilder.toString();		
	}
	
	/**
	 * Builds a section
	 * @param isContainer defines whether or not this section can hold other sections, or should only link to an object property
	 * @param view wraps an object property that can be shown and manipulated through this section
	 * @param executeView a object method that can be called through this section
	 * @param columns determines in how many columns the encapsulated elements should be shown
	 * @return the build section
	 * @throws ValidationError thrown when the provided parameter combination is not valid
	 */
	public static Section build(final boolean isContainer, WrapperView<?,?> view, WrapperExecute<?> executeView,int columns) throws ValidationError {		
		validate(isContainer, view, columns);
		Section returnValue = new Section(isContainer,view, executeView,columns);				
		return returnValue;
	}

	private Section(boolean isContainer, WrapperView<?,?> view,WrapperExecute<?> executeView, int columns) {
		this.key = KeyGenerator.getNextKey();
		this.isContainer = isContainer;
		this.view = view;
		this.columns = columns;		
		this.executeView = executeView;
	}	
	
	private static void validate(boolean isContainer2, WrapperView<?, ?> view2, int columns2) throws ValidationError {
		if(isContainer2 && columns2<1) {
			throw new ValidationError("Must provide column # > 0 for a container");
		}
		if(isContainer2 && view2==null) {
			throw new ValidationError("For containers a non-null view must be provided");
		}
	}

	

}
