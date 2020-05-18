package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotBirdBathTest {

	@Test
	public void test() {
		PlotBirdBath p = new PlotBirdBath(null,0,0);
		assertTrue(p.getPlotImage().equals("/viewImages/plotBirdbath.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/birdBath.png"));
		assertTrue(p.getName().equals("Bird Bath"));
	}

}
