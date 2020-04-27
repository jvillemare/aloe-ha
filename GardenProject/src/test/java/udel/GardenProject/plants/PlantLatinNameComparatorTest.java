package udel.GardenProject.plants;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
import udel.GardenProject.enums.SoilTypes;

public class PlantLatinNameComparatorTest {

	@Test
	public void SameLatinTest() {
		Plant a = new Plant(null, "Pine", null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(null, "Pine", null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(true, false);
		assertTrue(comp.compare(a, b) == 0);
	}

	@Test
	public void aZTest() {
		Plant a = new Plant(null, "Apple", null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(null, "Zebra", null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(true, false);
		assertTrue(comp.compare(a, b) < 0);
	}

	@Test
	public void zATest() {
		Plant a = new Plant(null, "Zebra", null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(null, "Apple", null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(true, false);
		assertTrue(comp.compare(a, b) > 0);
	}

	@Test
	public void constructorTest() {
		Plant a = new Plant(null, "Zebra", null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(null, "Apple", null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(false, false);
		assertTrue(comp.compare(a, b) < 0);
	}

	@Test
	public void nullTest() {
		Plant a = new Plant(null, null, null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(null, "Apple", null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(false, false);
		assertTrue(comp.compare(a, b) > 0);
	}

	@Test
	public void commonTest() {
		String[] c1 = { "plant" };
		String[] c2 = { "flower" };
		Plant a = new Plant(c1, null, null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(c2, null, null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator();
		assertTrue(comp.compare(a, b) > 0);
	}

	@Test
	public void commonConstructorTest() {
		String[] c1 = { "plant" };
		String[] c2 = { "flower" };
		Plant a = new Plant(c2, null, null, null, 0, null, null, null, false, true, null, null);
		Plant b = new Plant(c1, null, null, null, 0, null, null, null, false, true, null, null);
		PlantNameComparator comp = new PlantNameComparator(true);
		assertTrue(comp.compare(a, b) < 0);
	}

}
