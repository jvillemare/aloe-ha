package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.plotObjects.polygons.PlotPool;

public class PlotObjectTest {

	@Test
	public void test() {
		PlotPool plot=new PlotPool(0, 0);
		assertTrue(plot.getPlotX()==0);
		assertTrue(plot.getPlotY()==0);
		plot.setPlotX(1);
		plot.setPlotY(1);
		assertTrue(plot.getPlotX()==1);
		assertTrue(plot.getPlotY()==1);
		assertTrue(plot.getImage().equals("/viewImages/pool.png"));
		assertTrue(plot.getHeight()==100);
	}

}
