package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class PlantSelectionTest {

	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PlantSelection q = new PlantSelection(m);
		assertTrue(q.getTitle().equals("Plant Selection"));
	}

}
