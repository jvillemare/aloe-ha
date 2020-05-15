package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class PlotPathTest {

	@Test
	public void testPlotPath() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PlotPath p = new PlotPath(m, 0, 0);
		assertTrue(p.getWindowImage().equals("/viewImages/path.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/plotPath.png"));
		assertTrue(p.getPlotX()==0);
		assertTrue(p.getPlotY()==0);
		assertTrue(p.getHeight()==1);
	}

}
