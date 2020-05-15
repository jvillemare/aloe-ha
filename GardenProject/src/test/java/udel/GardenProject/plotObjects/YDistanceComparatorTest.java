package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.polygons.PlotPool;

public class YDistanceComparatorTest {

	@Test
	public void testCompare() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		YDistanceComparator y = new YDistanceComparator();
		Plant p1=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		PlotPlant plot1 = new PlotPlant(m, p1, 1, 1);
		Plant p2=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		PlotPlant plot2 = new PlotPlant(m, p2, 1, 2);
		assertTrue(y.compare(plot1, plot1)==0);
		PlotPool plot3 = new PlotPool(m, 0, 0);
		assertTrue(y.compare(plot3, plot1)==-1);
		assertTrue(y.compare(plot1, plot3)==1);
		assertTrue(y.compare(plot1, plot2)==-1);
		assertTrue(y.compare(plot2, plot1)==1);
		y=new YDistanceComparator(false);
		assertTrue(y.compare(plot3, plot1)==1);
		assertTrue(y.compare(plot1, plot3)==-1);
		assertTrue(y.compare(plot1, plot2)==1);
		assertTrue(y.compare(plot2, plot1)==-1);
	}

}
