package main.abstractgui.code;

public class Position implements Positioning {
	
	private int anchorKey;
	private Facing facing = Facing.NOT_SET;
	private Alignment alignment = Alignment.NOT_SET;
	private int x;
	private int y;
	
	private Position() {
		
	}
	
	public static Position build(int x, int y) throws ValidationError {		
		validateAbsoluteLocation(x,y);		
		return set(x,y,new Position());
	}
	
	public void setAbsoluteLocation(int x, int y) throws ValidationError {
		validateAbsoluteLocation(x,y);
		set(x,y,this);
	}
	
	public static Position build(Facing facing, Alignment alignment, int anchorKey) throws ValidationError {		
		validateFacing(facing, alignment,anchorKey);
		return set(facing, alignment, anchorKey, new Position());
	}
	
	public void setRelativePosition(Facing facing, Alignment alignment, int anchorKey) throws ValidationError {
		validateFacing(facing, alignment,anchorKey);		
		set(facing, alignment, anchorKey, this);
	}
	
	private static Position set(Facing facing, Alignment alignment, int anchorKey, Position position) {
		position.setFacing(facing);
		position.setAlignment(alignment);
		position.setAnchorKey(anchorKey);
		return position;
	}
	
	private static Position set(int x, int y, Position position) {
		position.setX(x);
		position.setY(y);
		return position;
	}
	
	private static void validateAnchorKey(int anchorKey2) throws ValidationError {
		if(anchorKey2<=0) {
			throw new ValidationError("Invalid anchor key for relative positioning provided.");
		}		
	}

	private static void validateFacing(Facing facing, Alignment alignment, int anchorKey) throws ValidationError {
		validateAnchorKey(anchorKey);
		if(facing ==null || alignment == null) {
			throw new ValidationError("In position, trying to create facing with null values.");
		} 
		if(!Alignment.getAllowedAlignments(facing).contains(alignment)) {
			throw new ValidationError("Facing and alignment combination is not allowed./n"+"Alignment: "+alignment+" Facing: "+facing);
		}		
	}

	private static void validateAbsoluteLocation(int x, int y) throws ValidationError {
		if(x<0||y<0) {
			throw new ValidationError("Absolute location coordinates are negative./n"+"x: "+x+" y: "+y);
		}
		
	}
	
	@Override
	public int getAnchorKey() {
		return anchorKey;
	}
	
	private void setAnchorKey(int anchorKey) {
		this.anchorKey = anchorKey;
	}
	
	@Override
	public Facing getFacing() {
		return facing;
	}
	
		
	private void setFacing(Facing facing) {
		this.facing = facing;
	}
	
	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	private void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
	@Override
	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}
	
}
