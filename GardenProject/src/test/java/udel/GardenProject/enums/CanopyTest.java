package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class CanopyTest {

	@Test
	public void testCanopy() {
		Canopy floor=Canopy.FLOOR;
		assertTrue(floor.getMaximumHeight()==15);
		assertTrue(floor.getAverageCanopyHeight()==7.5);
		assertTrue(Canopy.UNDERSTORY.getAverageCanopyHeight()==35);
		assertTrue(floor.getCanopyFromHeight(15.0).equals(floor));
		assertTrue(floor.getCanopyFromHeight(-1)==null);
		assertTrue(floor.getCanopyFromHeight(9999)==null);
	}


}
