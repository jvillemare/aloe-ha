package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.AllPlants;

public class AllPlantsTest {

	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		AllPlants w = new AllPlants(m);
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().contentEquals("Plant Database"));
	}

}
