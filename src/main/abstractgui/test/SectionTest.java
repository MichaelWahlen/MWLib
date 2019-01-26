package main.abstractgui.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.abstractgui.code.Section;
import main.abstractgui.code.SectionToFrame;
import main.abstractgui.code.ValidationError;
import main.abstractgui.code.WrapperExecute;
import main.abstractgui.code.WrapperView;
import main.code.utilities.KeyGenerator;
import main.domain.code.IntWrapper;
import main.domain.code.StringWrapper;
import main.domain.code.User;

class SectionTest {	
	
	static User user = new User();
	static WrapperView<StringWrapper, String> viewString = new WrapperView<>(user.getFirstName());
	static WrapperView<IntWrapper, Integer> viewInteger = new WrapperView<>(user.getStartScale());
	static WrapperExecute<User> executeString = new WrapperExecute<>(user);
	static WrapperExecute<User> executeInt = new WrapperExecute<>(user);
	
	
	@BeforeAll
	static void setup() {
		executeString.provideExecuteFunction(user.getExecute1());
		executeInt.provideExecuteFunction(user.retrieveIncreaseScale());
	}
	
	@Test
	@DisplayName("Combining views and multiple nested sessions")	
	void test8() throws ValidationError{		
		viewString.set("Macey");
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		User user4 = new User();
		WrapperView<StringWrapper, String> view1 = new WrapperView<>(user1.getFirstName());
		WrapperView<StringWrapper, String> view2 = new WrapperView<>(user2.getFirstName());
		WrapperView<StringWrapper, String> view3 = new WrapperView<>(user3.getFirstName());
		WrapperView<StringWrapper, String> view4 = new WrapperView<>(user4.getFirstName());	
		view1.set("User 1");
		view2.set("User 2");
		view3.set("User 3");	
		view4.set("User 4");
		Section rasterItem1 = Section.build(true, viewString,executeString,2);		
		Section rasterItem2 = Section.build(false, view2,executeString,0);		
		Section rasterItem3 = Section.build(true, viewString,executeString,2);
		Section rasterItem4 = Section.build(false, view3,executeString,2);		
		Section rasterItem5 = Section.build(false, viewInteger,executeInt,2);
		Section rasterItem6 = Section.build(false, view4,executeString,2);
		try {
			rasterItem1.addChild(rasterItem2);
			rasterItem1.addChild(rasterItem3);
			rasterItem3.addChild(rasterItem4);
			rasterItem3.addChild(rasterItem5);
			rasterItem3.addChild(rasterItem6);
		} catch (ValidationError e) {			
			e.printStackTrace();
		}			
		rasterItem5.set(91818);
		assertAll("",
				()->assertEquals("User 2",rasterItem2.get(String.class)),
				()->assertEquals("User 3",rasterItem4.get(String.class)),
				()->assertEquals("Macey",rasterItem1.get(String.class)),
				()->assertEquals(91818, (int) rasterItem5.get(Integer.class)),
				()->assertEquals(91818, (int) viewInteger.get())
				);	
		rasterItem2.set("User xx");
		assertEquals("User xx",rasterItem2.get(String.class));
		SectionToFrame sectionTF = new SectionToFrame(rasterItem1);
		sectionTF.showFrame();
		//while(true) {}
	}
	
	@Test
	@DisplayName("Calling the view function for set and get on String")
	void testView9() {
		viewString.set("Macey");
		assertEquals("Macey",viewString.get());		
	}
	
	@Test
	@DisplayName("Calling the view function for set and get on integer")
	void testView() {
		viewInteger.set(10);
		assertEquals(10,(int) viewInteger.get());
		viewInteger.set(1);
		assertEquals(1,(int) viewInteger.get());		
	}
	
	@Test
	@DisplayName("Calling the view String function for execute")
	void testView2() {
		viewString.set("Macey");
		assertEquals("Macey",viewString.get());
		executeString.execute();
		assertEquals("John",viewString.get());				
	}
	
	@Test
	@DisplayName("Calling the view Integer function for execute")
	void testViewInt() {
		viewInteger.set(1);
		assertEquals(1,(int) viewInteger.get());
		executeInt.execute();
		assertEquals(99,(int) viewInteger.get());				
	}
	
	@Test
	@DisplayName("Adding a field item which is not a container and validating key")
	void test1() throws ValidationError {	
		Section rasterItem = Section.build(false, viewString,executeString,2);
		rasterItem = Section.build(false, viewString,executeString,2);
		rasterItem = Section.build(false, viewString,executeString,2);
		rasterItem = Section.build(false, viewString,executeString,2);		
		assertEquals("Key: 4",rasterItem.toString());			
	}
	
	@Test
	@DisplayName("Adding a field to a non-container field")
	void test2() throws ValidationError {	
		int currentKey = KeyGenerator.lastProvidedKey();
		Section rasterItem1 = Section.build(false, viewString,executeString,2);
		Section rasterItem2 = Section.build(false, viewString,executeString,2);
		Throwable exception = assertThrows(ValidationError.class, () -> {
			rasterItem1.addChild(rasterItem2);
		});
		assertEquals("Trying to add a field to a non-container.\n" + "Parent: "+(currentKey+1)+" Child: "+(currentKey+2),exception.getMessage());		
	}
	
	@Test
	@DisplayName("Adding a field to field")
	void test3() throws ValidationError {	
		int currentKey = KeyGenerator.lastProvidedKey();
		Section rasterItem1 = Section.build(true, viewString,executeString,2);
		Section rasterItem2 = Section.build(false, viewString,executeString,2);	
		try {
			rasterItem1.addChild(rasterItem2);
		} catch (ValidationError e) {
			// should never be raised in this test case
			e.printStackTrace();
		}
		assertEquals("Key: "+(currentKey+2)+"\nDirect parent key: "+(currentKey+1),rasterItem2.toString());		
	}
	
	@Test
	@DisplayName("Validate that the returned ordered keys are indeed a deep copy")
	void test4() throws ValidationError {	
		int currentKey = KeyGenerator.lastProvidedKey();
		Section rasterItem1 = Section.build(true, viewString,executeString,2);
		Section rasterItem2 = Section.build(false, viewString,executeString,2);	
		try {
			rasterItem1.addChild(rasterItem2);
		} catch (ValidationError e) {
			// should never be raised in this test case
			e.printStackTrace();
		}
		List<Integer> origList = rasterItem1.publishOrdering();
		List<Integer> list1 = rasterItem1.publishOrdering();
		list1.add(currentKey+10);
		List<Integer> list2 = rasterItem1.publishOrdering();		
		assertAll("",			
				()->assertTrue(list2.containsAll(origList)),
				()->assertTrue(origList.containsAll(list2)),
				()->assertTrue(list1.contains(currentKey+10)),
				()->assertFalse(origList.contains(currentKey+10))
				);
		;		
	}
	
	@Test
	@DisplayName("Check content wise correct nesting")
	void test5() throws ValidationError {	
		Section rasterItem1 = Section.build(true, viewString,executeString,2);
		Section rasterItem2 = Section.build(false, viewString,executeString,2);	
		Section rasterItem3 = Section.build(true, viewString,executeString,2);
		Section rasterItem4 = Section.build(false, viewString,executeString,2);
		try {
			rasterItem1.addChild(rasterItem2);
			rasterItem1.addChild(rasterItem3);
			rasterItem3.addChild(rasterItem4);
		} catch (ValidationError e) {			
			e.printStackTrace();
		}
		List<Integer> item1Children = rasterItem1.publishOrdering();
		List<Integer> item3Children = rasterItem3.publishOrdering();
		assertAll("",
				()->assertTrue(item1Children.contains(rasterItem2.getKey())),
				()->assertTrue(item1Children.contains(rasterItem3.getKey())),
				()->assertTrue(item1Children.size()==2),
				()->assertTrue(item3Children.contains(rasterItem4.getKey())),
				()->assertTrue(item3Children.size()==1)
				);
	}
	
}
