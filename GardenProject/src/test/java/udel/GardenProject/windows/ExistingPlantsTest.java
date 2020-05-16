package udel.GardenProject.windows;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.Session;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.windows.ExistingPlants;

public class ExistingPlantsTest {

	@Test
	public void existingPlantsTest() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		ExistingPlants w = new ExistingPlants(m);
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().equals("ExistingPlants"));
	}
	
	// TODO: May have to strike this. ExistingPlants shouldn't have this
	//			attribute, as it should be stored in Session.
	/*
	@Test
	public void getExistingPlantTest() {
		Model m = new Model(0, 1);
		ExistingPlants w = new ExistingPlants(m);
		w.getExistingPlants();
		assertTrue(w.getExistingPlants() instanceof Plant[]);
	}*/
	
	// TODO: Also maybe strike this test since ExistingPlants shouldn't have
	//			this.
	/*
	@Test
	public void setExistingPlantsTest() {
		Session s = new Session();
		Plant p = new Plant(null, null, null, null, 0, null, null, null, false, false, null, null);
		HashMap<String, Plant> plants = new HashMap<String, Plant>();
		plants.put(p.getLatinName(), p);
		s.setExistingPlants(plants);
	}
	*/

}
