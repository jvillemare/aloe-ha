package udel.GardenProject.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import udel.GardenProject.enums.SoilTypes;

public class SoilTypesTest {

	@Test
	public void testSoilType() {
		SoilTypes sl = SoilTypes.CLAY;
		assertTrue(sl.name().toLowerCase().equals("clay"));
	}

}
