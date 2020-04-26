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
		boolean[] arr = {false, false, false, false, false, false, false, false, false, false, false, false};
		Plant pine = new Plant(null, null, null, arr, 0, null, null, null, false, false, null, null);
		Plant flower = new Plant(null, null, null, arr, 0, null, null, null, false, false, null, null);
		assertTrue(b.compare(pine, flower) == 0);
	}

	@Test
	public void ascendSeasonTest() {
		BloomTimeComparator b = new BloomTimeComparator(true);
		boolean[] arr1 = {false, true, true, false, false, false, false, false, false, false, false, false};
		boolean[] arr2 = {false, false, true, true, false, false, false, false, false, false, false, false};
		Plant flower = new Plant(null, null, null, arr1, 0, null, null, null, false, false, null, null);
		Plant pine = new Plant(null, null, null, arr2, 0, null, null, null, false, false, null, null);
		assertTrue(b.compare(flower, pine) < 0);
	}
	
	@Test
	public void descendSeasonTest() {
		BloomTimeComparator b = new BloomTimeComparator(false);
		boolean[] arr1 = {false, true, true, false, false, false, false, false, false, false, false, false};
		boolean[] arr2 = {false, false, true, true, false, false, false, false, false, false, false, false};
		Plant flower = new Plant(null, null, null, arr1, 0, null, null, null, false, false, null, null);
		Plant pine = new Plant(null, null, null, arr2, 0, null, null, null, false, false, null, null);
		assertTrue(b.compare(flower, pine) > 0);
	}
}
