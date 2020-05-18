package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;

public class PlotBenchTest {

	@Test
	public void test() {
		PlotBench p = new PlotBench(null,0,0);
		assertTrue(p.getPlotImage().equals("/viewImages/plotBench.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/bench.png"));
		assertTrue(p.getName().equals("Bench"));
	}

}
