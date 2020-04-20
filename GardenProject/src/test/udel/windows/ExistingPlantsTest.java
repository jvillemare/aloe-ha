package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.enums.Moisture;
import main.udel.enums.Plants;
import main.udel.enums.Seasons;
import main.udel.enums.SoilTypes;
import main.udel.plants.Plant;
import main.udel.windows.ExistingPlants;

public class ExistingPlantsTest {

	@Test
	public void ExistingPlantsTest() {
		ExistingPlants w = new ExistingPlants();
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().equals("ExistingPlants"));
	}
	
	@Test
	public void getExistingPlantTest() {
		ExistingPlants w = new ExistingPlants();
		w.getExistingPlants();
		assertTrue(w.getExistingPlants() instanceof Plant[]);
	}
	
	@Test
	public void setExistingPlantsTest() {
		ExistingPlants w = new ExistingPlants();
		Plant p = new Plant(null, null, null, 0, null, null);
		w.setExistingPlants(p);
		
	}

}
