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
	}

}
