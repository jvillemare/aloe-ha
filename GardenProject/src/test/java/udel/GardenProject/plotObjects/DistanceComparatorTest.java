package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.plotObjects.special.PlotBirdBath;

public class DistanceComparatorTest {


	@Test
	public void testDistanceComparatorPlotObject() {
		PlotBirdBath p1=new PlotBirdBath(null, 20,20);
		DistanceComparator d=new DistanceComparator(p1);
		PlotBirdBath p2=new PlotBirdBath(null, 25,25);
		assertTrue(d.compare(p1, p2)<0);
		
	}

	@Test
	public void testCompare() {
		DistanceComparator d=new DistanceComparator(15,15);
		PlotBirdBath p1=new PlotBirdBath(null, 20,20);
		PlotBirdBath p2=new PlotBirdBath(null, 20,20);
		assertTrue(d.compare(p1, p2)==0);
		p2=new PlotBirdBath(null, 15,15);
		assertTrue(d.compare(p1, p2)>0);
		assertTrue(d.compare(p2, p1)<0);
	}

}
