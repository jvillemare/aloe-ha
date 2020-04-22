package udel.GardenProject.garden;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.*;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.windows.AllPlants;
import udel.GardenProject.windows.Welcome;
import udel.GardenProject.windows.Window;

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
	
	@Test
	public void testUpdate() {
		Model m = new Model(0, 1);
		m.update();
		// TODO: Extend as update becomes defined
	}
	
	@Test
	public void testGetWindow() {
		Model m = new Model(0, 1);
		Window w = m.getWindow();
		assertTrue(w instanceof Welcome);
	}
	
	@Test
	public void testSetWindow() {
		Model m = new Model(0, 1);
		m.setWindow(Windows.AllPlants);
		assertTrue(m.getWindow() instanceof AllPlants);
	}
	
	@Test
	public void testSearchPlantsEmpty() {
		Model m = new Model(0, 1);
		Plant[] p1 = m.searchPlants("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		Plant[] p2 = m.searchPlants("bingo bongo nonsense hippity hoppity");
		Plant[] p3 = m.searchPlants("null****************");
		Plant[] p4 = m.searchPlants("n/a");
		
		assertTrue(p1.length == 0);
		assertTrue(p2.length == 0);
		assertTrue(p3.length == 0);
		assertTrue(p4.length == 0);
	}
	
	@Test
	public void testSearchPlantsHasResults() {
		Model m = new Model(0, 1);
		Plant[] p1 = m.searchPlants("pine");
		Plant[] p2 = m.searchPlants("");
		Plant[] p3 = m.searchPlants("dogwood");
		Plant[] p4 = m.searchPlants("Acer negundo");
		
		// could be multiples of these plants
		assertTrue(p1.length > 0);
		assertTrue(p2.length > 0);
		assertTrue(p3.length > 0);
		
		// should only be one of these plants
		assertTrue(p4.length == 1);
	}

}
