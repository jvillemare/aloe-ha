package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotFenceTest {

	@Test
	public void testPlotFence() {
		PlotFence p=new PlotFence(0, 0, 10);
		assertTrue(p.getImage().equals("/viewImages/fence.png"));
		assertTrue(p.getPlotX()==0);
		assertTrue(p.getPlotY()==0);
		assertTrue(p.getHeight()==10);
	}

}
