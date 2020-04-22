package udel.GardenProject.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import udel.GardenProject.enums.SoilTypes;

public class SoilTypesTest {

	@Test
	public void testSoilType() {
		SoilTypes sl = SoilTypes.PEATY;
		assertTrue(sl.name().toLowerCase().equals("peaty"));
	}

}
