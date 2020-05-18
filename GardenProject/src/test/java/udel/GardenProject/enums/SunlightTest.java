package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.Sunlight;

public class SunlightTest {

	@Test
	public void testFullSun() {
		Sunlight sun = Sunlight.FULLSUN;
		assertTrue(sun.getSunlight().equals("Full-sun"));
		assertTrue(Sunlight.getSunlightByDouble(0.9).equals(Sunlight.FULLSUN));
		assertTrue(Sunlight.getSunlightByDouble(0.6).equals(Sunlight.PARTIALSHADE));
		assertTrue(Sunlight.getSunlightByDouble(0.4).equals(Sunlight.PARTIALSUN));
		assertTrue(Sunlight.getSunlightByDouble(0.2).equals(Sunlight.FULLSHADE));
		assertTrue(Sunlight.getSunlightByDouble(20).equals(Sunlight.ANY));
	}

}
