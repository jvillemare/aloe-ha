package test.udel.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.enums.Seasons;
import main.udel.enums.Sunlight;

public class SunlightTest {

	@Test
	public void testFullSun() {
		Sunlight sun = Sunlight.FULLSUN;
		assertTrue(sun.getSunlight().equals("Full-sun"));
	}

}
