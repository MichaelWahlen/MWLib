package main.abstractgui.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.abstractgui.code.RasterItem;

class RasterTest {	
	
	@Test
	@DisplayName("Adding a raster item and validating key")
	void test() {	
		RasterItem rasterItem = RasterItem.build();
		rasterItem = RasterItem.build();
		rasterItem = RasterItem.build();
		assertEquals("Key: 3",rasterItem.toString());			
	}
	
	@Test
	@DisplayName("Add valid preferredsize")
	void test2(){
		RasterItem rasterItem = RasterItem.build();
		assertEquals(true,rasterItem.setPreferredSize(200, 50));
		assertAll("",
				()-> assertEquals(true,rasterItem.setPreferredSize(200, 50)),
				()-> assertEquals(50,rasterItem.getPreferredHeigth()),
				()-> assertEquals(200,rasterItem.getPreferredWidth())	
		);		
		;
	}
	
	@Test
	@DisplayName("Add invalid preferredsize")
	void test3(){
		RasterItem rasterItem = RasterItem.build();
		rasterItem.setPreferredSize(100, 200);
		assertAll("Negative h or w",
				()-> assertEquals(false,rasterItem.setPreferredSize(-10, 50)),
				()-> assertEquals(false,rasterItem.setPreferredSize(0,-10)),
				()-> assertEquals(false,rasterItem.setPreferredSize(-10,-10)),
				()-> assertEquals(200,rasterItem.getPreferredHeigth()),
				()-> assertEquals(100,rasterItem.getPreferredWidth())	
		);		
		;
	}

}
