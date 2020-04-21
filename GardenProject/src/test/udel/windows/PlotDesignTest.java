package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.plants.Plant;
import main.udel.windows.PlotDesign;

public class PlotDesignTest {

	@Test
	public void plotDesignTest() {
		PlotDesign p = new PlotDesign();
		System.out.println(p.getTitle());
		assertTrue(p.getTitle().equals("Plot Designer"));
	}
	
	@Test
	public void getObstacleTest() {
		PlotDesign p = new PlotDesign();
		p.getObstacle();
	}

	@Test
	public void setObstacleTest() {
		PlotDesign p = new PlotDesign();
		p.setObstacle(new Object());
		assertTrue(p.setObstacle(new Object()) instanceof Object);
	}
	
	@Test
	public void getPlantTest() {
		PlotDesign p = new PlotDesign();
		p.getPlant();
	}
	
	@Test
	public void setPlantTest() {
		PlotDesign p = new PlotDesign();
		p.setPlant(new Plant(null, null, null, null, 0, null, null, null));
	}
	
}
