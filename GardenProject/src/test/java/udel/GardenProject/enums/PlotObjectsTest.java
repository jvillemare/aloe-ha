package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotObjectsTest {

	@Test
	public void testPlotObjects() {
		PlotObjects p=PlotObjects.Bench;
		assertTrue(p.getType().equals("special"));
		assertTrue(p.isTypicallyInGarden());
	}

}
