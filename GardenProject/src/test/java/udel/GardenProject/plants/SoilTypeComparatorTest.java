package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plants.SoilTypeComparator;

public class SoilTypeComparatorTest {

	@Test
	public void defaultConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SoilTypeComparator c = new SoilTypeComparator();
		Field privateDescending = SoilTypeComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(c) == true);
	}

	@Test
	public void booleanConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SoilTypeComparator c = new SoilTypeComparator(true);
		Field privateDescending = SoilTypeComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(c) == true);
		assertTrue(c.compare(null, null) == 0);
	}

	
	@Test
	public void sameSoilTest() {
		SoilTypeComparator c = new SoilTypeComparator(true);
		Plant pine = new Plant(null, null, null, null, 0, null, SoilTypes.CLAY, null, false, false);
		Plant flower = new Plant(null, null, null, null, 0, null, SoilTypes.CLAY, null, false, false);
		assertTrue(c.compare(pine, flower) == 0);
	}
	
	public void ascendTest() {
		SoilTypeComparator c = new SoilTypeComparator(false);
		Plant pine = new Plant(null, null, null, null, 0, null, SoilTypes.SANDY, null, false, false);
		Plant flower = new Plant(null, null, null, null, 0, null, SoilTypes.CLAY, null, false, false);
		assertTrue(c.compare(pine, flower) > 0);
	}
	
	public void descendTest() {
		SoilTypeComparator c = new SoilTypeComparator(true);
		Plant pine = new Plant(null, null, null, null, 0, null, SoilTypes.LOAMY, null, false, false);
		Plant flower = new Plant(null, null, null, null, 0, null, SoilTypes.CLAY, null, false, false);
		assertTrue(c.compare(pine, flower) < 0);
	}
}
