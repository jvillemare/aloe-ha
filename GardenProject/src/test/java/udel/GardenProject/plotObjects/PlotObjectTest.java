package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.polygons.PlotPool;

public class PlotObjectTest {

	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PlotPool plot = new PlotPool(m, 0, 0);
		assertTrue(plot.getPlotX()==0);
		assertTrue(plot.getPlotY()==0);
		plot.setPlotX(1);
		plot.setPlotY(1);
		assertTrue(plot.getPlotX()==1);
		assertTrue(plot.getPlotY()==1);
		assertTrue(plot.getWindowImage().equals("/viewImages/pool.png"));
		assertTrue(plot.getPlotImage().equals("/viewImages/plotPool.png"));
		assertTrue(plot.getHeight()==100);
	}

}
