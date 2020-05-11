package udel.GardenProject.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import udel.GardenProject.enums.SoilTypes;

public class SoilTypesTest {

	@Test
	public void testSoilType() {
		SoilTypes sl = SoilTypes.CLAY;
		assertTrue(sl.getName().toLowerCase().equals("clay"));
		assertTrue(sl.getSoilTexture("Clay").equals(sl));
		assertTrue(sl.getSoilTexture("Sandy").equals(SoilTypes.SANDY));
		assertTrue(sl.getSoilTexture("Loamy").equals(SoilTypes.LOAMY));
		assertTrue(sl.getSoilTexture("any").equals(SoilTypes.ANY));
	}

}
