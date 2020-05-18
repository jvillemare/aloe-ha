package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotGnomeTest {

	@Test
	public void test() {
		PlotGnome p = new PlotGnome(null,0,0);
		assertTrue(p.getPlotImage().equals("/viewImages/plotGnome.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/gnome.png"));
		assertTrue(p.getName().equals("Gnome"));
	}

}
