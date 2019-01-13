package main.abstractgui.code;

import java.util.ArrayList;
import java.util.List;

public enum Alignment { TOP_TOP, TOP_BOTTOM, BOTTOM_BOTTOM, BOTTOM_TOP,LEFT_LEFT, LEFT_RIGHT, RIGHT_RIGHT, RIGHT_LEFT, NOT_SET;

	
	public static List<Alignment> getAllowedAlignments(Facing facing){
		List<Alignment> returnValue = new ArrayList<>();
		if(facing==Facing.NORTH||facing == Facing.SOUTH) {
			returnValue.add(Alignment.TOP_TOP);
			returnValue.add(Alignment.TOP_BOTTOM);
			returnValue.add(Alignment.BOTTOM_TOP);
			returnValue.add(Alignment.BOTTOM_BOTTOM);
		} else {
			returnValue.add(Alignment.LEFT_LEFT);
			returnValue.add(Alignment.LEFT_RIGHT);
			returnValue.add(Alignment.RIGHT_RIGHT);
			returnValue.add(Alignment.RIGHT_LEFT);
		}
		return returnValue;
		
	}
	
}
