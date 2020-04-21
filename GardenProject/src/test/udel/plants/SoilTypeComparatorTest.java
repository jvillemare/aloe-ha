package test.udel.plants;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import main.udel.enums.Seasons;
import main.udel.enums.SoilTypes;
import main.udel.plants.Plant;
import main.udel.plants.SoilTypeComparator;

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
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.CLAY, null);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.CLAY, null);
		assertTrue(c.compare(pine, flower) == 0);
	}
	
	public void ascendTest() {
		SoilTypeComparator c = new SoilTypeComparator(false);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.SANDY, null);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.CLAY, null);
		assertTrue(c.compare(pine, flower) > 0);
	}
	
	public void descendTest() {
		SoilTypeComparator c = new SoilTypeComparator(true);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.LOAMY, null);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, SoilTypes.CLAY, null);
		assertTrue(c.compare(pine, flower) < 0);
	}
}
