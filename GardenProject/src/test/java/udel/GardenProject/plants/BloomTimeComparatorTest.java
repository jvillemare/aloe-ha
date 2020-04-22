package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.BloomTimeComparator;
import udel.GardenProject.plants.Plant;

public class BloomTimeComparatorTest {

	@Test
	public void defaultConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BloomTimeComparator b = new BloomTimeComparator();
		Field privateDescending = BloomTimeComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(b) == true);
	}

	@Test
	public void booleanConstructorTest()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BloomTimeComparator b = new BloomTimeComparator(true);
		Field privateDescending = BloomTimeComparator.class.getDeclaredField("descending");
		privateDescending.setAccessible(true);
		assertTrue(privateDescending.getBoolean(b) == true);
	}
	
	@Test
	public void sameSeasonTest() {
		BloomTimeComparator b = new BloomTimeComparator(true);
		Plant pine = new Plant(null, null, null, Seasons.WINTER, 0, null, null, null);
		Plant flower = new Plant(null, null, null, Seasons.WINTER, 0, null, null, null);
		assertTrue(b.compare(pine, flower) == 0);
	}

	@Test
	public void ascendSeasonTest() {
		BloomTimeComparator b = new BloomTimeComparator(true);
		Plant flower = new Plant(null, null, null, Seasons.SUMMER, 0, null, null, null);
		Plant pine = new Plant(null, null, null, Seasons.FALL, 0, null, null, null);
		assertTrue(b.compare(flower, pine) < 0);
	}
	
	@Test
	public void descendSeasonTest() {
		BloomTimeComparator b = new BloomTimeComparator(false);
		Plant flower = new Plant(null, null, null, Seasons.SUMMER, 0, null, null, null);
		Plant pine = new Plant(null, null, null, Seasons.FALL, 0, null, null, null);
		assertTrue(b.compare(flower, pine) > 0);
	}
}
