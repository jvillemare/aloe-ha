package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotPathTest {

	@Test
	public void testPlotPath() {
		PlotPath p=new PlotPath(0, 0);
		assertTrue(p.getImage().equals("/viewImages/path.png"));
		assertTrue(p.getPlotX()==0);
		assertTrue(p.getPlotY()==0);
		assertTrue(p.getHeight()==1);
	}

}
