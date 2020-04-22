package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Moisture;

public class MoistureTest {

	@Test
	public void testWinter() {
		Moisture m = Moisture.DRY;
		assertTrue(m.name().toLowerCase().equals("dry"));
	}

}
