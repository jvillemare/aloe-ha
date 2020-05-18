package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotOtherTest {

	@Test
	public void test() {
		PlotOther p = new PlotOther(null,0,0);
		assertTrue(p.getPlotImage().equals("/viewImages/plotGardenBed.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/gardenBed.png"));
		assertTrue(p.getName().equals("Other"));
	}

}
