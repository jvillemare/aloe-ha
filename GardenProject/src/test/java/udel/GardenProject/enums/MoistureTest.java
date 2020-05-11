package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Moisture;

public class MoistureTest {

	@Test
	public void testWinter() {
		Moisture m = Moisture.DRY;
		assertTrue(m.name().toLowerCase().equals("dry"));
		assertTrue(m.getMoisture("Wet").equals(Moisture.DAMP));
		assertTrue(m.getMoisture("Moist").equals(Moisture.MOIST));
		assertTrue(m.getMoisture("Dry").equals(m));
		assertTrue(m.getMoisture("wet-med").equals(Moisture.MOIST_DAMP));
		assertTrue(m.getMoisture("dry-moist").equals(Moisture.DRY_MOIST));
		assertTrue(m.getMoisture("non-exist")==null);
	}

}
