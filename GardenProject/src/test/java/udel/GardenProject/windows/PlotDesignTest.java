package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.windows.PlotDesign;

public class PlotDesignTest {

	@Test
	public void plotDesignTest() {
		Model m = new Model(0, 1);
		PlotDesign p = new PlotDesign(m);
		System.out.println(p.getTitle());
		assertTrue(p.getTitle().equals("Plot Designer"));
	}
	
	@Test
	public void getObstacleTest() {
		Model m = new Model(0, 1);
		PlotDesign p = new PlotDesign(m);
		p.getObstacle();
	}

	@Test
	public void setObstacleTest() {
		Model m = new Model(0, 1);
		PlotDesign p = new PlotDesign(m);
		p.setObstacle(new Object());
		assertTrue(p.setObstacle(new Object()) instanceof Object);
	}
	
	@Test
	public void getPlantTest() {
		Model m = new Model(0, 1);
		PlotDesign p = new PlotDesign(m);
		p.getPlant(); // TODO: Have a real plant name be loaded when PlantLoader implemented
		
	}
	
	@Test
	public void setPlantTest() {
		Model m = new Model(0, 1);
		PlotDesign p = new PlotDesign(m);
		p.setPlant(new Plant(null, null, null, null, 0, null, null, null, false, false));
	}
	
}
