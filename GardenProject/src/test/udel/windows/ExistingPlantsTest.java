package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Model;
import main.udel.plants.Plant;
import main.udel.windows.ExistingPlants;

public class ExistingPlantsTest {

	@Test
	public void existingPlantsTest() {
		Model m = new Model(0, 1);
		ExistingPlants w = new ExistingPlants(m);
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().equals("ExistingPlants"));
	}
	
	@Test
	public void getExistingPlantTest() {
		Model m = new Model(0, 1);
		ExistingPlants w = new ExistingPlants(m);
		w.getExistingPlants();
		assertTrue(w.getExistingPlants() instanceof Plant[]);
	}
	
	@Test
	public void setExistingPlantsTest() {
		Model m = new Model(0, 1);
		ExistingPlants w = new ExistingPlants(m);
		Plant p = new Plant(null, null, null, null, 0, null, null, null);
		w.setExistingPlants(p);
		
	}

}
