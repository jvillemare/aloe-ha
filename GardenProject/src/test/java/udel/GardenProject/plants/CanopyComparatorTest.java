package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.plants.CanopyComparator;
import udel.GardenProject.plants.Plant;

public class CanopyComparatorTest {

	@Test
	public void defaultConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		CanopyComparator c = new CanopyComparator();
		Field privateDescending = CanopyComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(c) == true);
	}

	@Test
	public void booleanConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		CanopyComparator c = new CanopyComparator(true);
		Field privateDescending = CanopyComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(c) == true);
		assertTrue(c.compare(null, null) == 0);
	}
	
	@Test
	public void sameCanopyTest() {
		CanopyComparator c = new CanopyComparator(true);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.FLOOR);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.FLOOR);
		assertTrue(c.compare(pine, flower) == 0);
	}
	
	@Test
	public void descendTest() {
		CanopyComparator c = new CanopyComparator(true);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.EMERGENT);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.FLOOR);
		assertTrue(c.compare(pine, flower) < 0);
	}
	
	@Test
	public void ascendTest() {
		CanopyComparator c = new CanopyComparator(false);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.FLOOR);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, null, Canopy.EMERGENT);
		assertTrue(c.compare(pine, flower) < 0);
	}

}
