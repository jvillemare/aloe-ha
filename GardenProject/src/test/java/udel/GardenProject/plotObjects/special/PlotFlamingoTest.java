package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotFlamingoTest {

	@Test
	public void test() {
		PlotFlamingo p = new PlotFlamingo(null,0,0);
		assertTrue(p.getPlotImage().equals("/viewImages/plotFlamingo.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/flamingo.png"));
		assertTrue(p.getName().equals("Flamingo"));
	}

}
