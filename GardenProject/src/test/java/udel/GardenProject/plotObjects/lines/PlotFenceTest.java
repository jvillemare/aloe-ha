package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class PlotFenceTest {

	@Test
	public void testPlotFence() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PlotFence p = new PlotFence(m, 0, 0, 10);
		assertTrue(p.getWindowImage().equals("/viewImages/fence.png"));
		assertTrue(p.getPlotImage().equals("/viewImages/plotFence.png"));
		assertTrue(p.getPlotX()==0);
		assertTrue(p.getPlotY()==0);
		assertTrue(p.getHeight()==10);
	}

}
