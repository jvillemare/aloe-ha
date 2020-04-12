package test.udel.garden;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import main.udel.garden.*;

public class ModelTest {

	@Test
	public void testObject() throws NoSuchFieldException, SecurityException, 
		IllegalArgumentException, IllegalAccessException {
		Model m = new Model(0, 1);
		assertTrue(m instanceof Model);
		
		Field privateWidth = Model.class.getDeclaredField("width");
		privateWidth.setAccessible(true);
		assertTrue(privateWidth.getInt(m) == 0);
		
		Field privateHeight = Model.class.getDeclaredField("height");
		privateHeight.setAccessible(true);
		assertTrue(privateHeight.getInt(m) == 1);
	}

}
