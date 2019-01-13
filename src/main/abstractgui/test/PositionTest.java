package main.abstractgui.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.abstractgui.code.Position;
import main.abstractgui.code.ValidationError;

class PositionTest {

	@Test
	@DisplayName("Creating a position with valid x, y")
	void test() {
		try {
			Position position = Position.build(10, 20);
			assertAll("Assert proper setting of x and y", 
					()->assertEquals(10,position.getX()),
					()->assertEquals(20,position.getY())
					);
		} catch (ValidationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Creating a position with invalid x")
	void test2() {
	    Throwable exception = assertThrows(ValidationError.class, () -> {
	    	Position position = Position.build(-10, 20);
	    });
	    assertEquals("Absolute location coordinates are negative./nx: -10 y: 20",exception.getMessage());
	}
	
	@Test
	@DisplayName("Creating a position with invalid y")
	void test3() {
	    Throwable exception = assertThrows(ValidationError.class, () -> {
	    	Position position = Position.build(0,-20);
	    });
	    assertEquals("Absolute location coordinates are negative./nx: 0 y: -20",exception.getMessage());
	}
	
	@Test
	@DisplayName("Creating a position with invalid x and y")
	void test4() {	    
		Throwable exception = assertThrows(ValidationError.class, () -> {
			Position position = Position.build(-50,-20);
	    });
	    assertEquals("Absolute location coordinates are negative./nx: -50 y: -20",exception.getMessage());
	}
	
	@Test
	@DisplayName("When invalid no object created")
	void test5() {	    
		Position position;
		try {
			assertNull(position = Position.build(-50,-20));
		} catch (ValidationError e) {
			
		}
	   
	}

}
