package main.abstractgui.code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.constants.Constants;
import main.logging.code.Log;

public class RasterItem {
	
	private static int currentKey = 0;
	
	private final int key;
	
	private int parentKey;
	
	private final PreferredCoords size = PreferredCoords.build();
	
	private final Map<Positioning, RasterItem> children = new HashMap<>();
	
	private final Set<Integer> containedKeys = new HashSet<>();
	
	private VisualItem visualItem = new VisualItem("Default");
	
	private synchronized static int getNextKey() {
		currentKey++;
		return currentKey;
	}
	
	// if its a container the visual item should only have label/titel info, the children should make up the fields (or be containers themselves).
	public boolean isContainer() {		
		return children.size()==0;
	}
	
	private RasterItem() {
		key = getNextKey();
		containedKeys.add(key);
	}
	
	public int getKey() {
		return key;
	}
	
	public boolean setPreferredSize(int w, int h) {
		try {
			size.changeTo(w, h);
			return true;
		} catch (ValidationError e) {			
			Log.log(e, Constants.globalLogSetting);
			return false;
		}
	}	
	
	public static RasterItem build() {		
		RasterItem 	returnValue = new RasterItem();				
		return returnValue;
	}
	
	public void setVisualItem(VisualItem item) {
		this.visualItem = item;
	}
	
	public boolean addChild(Alignment alignment, Facing facing, int parentKey, RasterItem rasterItem) throws ValidationError {
		validateChild(parentKey);
		try {			
			children.put(Position.build(facing, alignment, parentKey), rasterItem);
			rasterItem.setParentKey(parentKey);
			return true;
		} catch (ValidationError e) {
			Log.log(e, Constants.globalLogSetting);
			return false;
		}
	}
	
	private void validateChild(int parentKey) throws ValidationError {
		if(!containedKeys.contains(parentKey))	{
			throw new ValidationError("Provided parent key not of this node, nor of its children.\n"+"Key: "+parentKey);
		}	
	}

	public boolean addChild(int x, int y, RasterItem rasterItem) {
		try {			
			children.put(Position.build(x, y), rasterItem);
			return true;
		} catch (ValidationError e) {
			Log.log(e, Constants.globalLogSetting);
			return false;
		}
	}
	
	// this is not desired, as I do not want to expose the buildup of the class, as it might get changed... 
	public Map<Positioning, RasterItem> getChildren(){
		return children;
	}
	
	public int getPreferredHeigth() {
		return size.getHeight();
	}
	
	public int getPreferredWidth() {
		return size.getWidth();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Key: "+key);
		return stringBuilder.toString();		
	}

	public int getParentKey() {
		return parentKey;
	}

	public void setParentKey(int parentKey) {
		this.parentKey = parentKey;
	}	
	
}
