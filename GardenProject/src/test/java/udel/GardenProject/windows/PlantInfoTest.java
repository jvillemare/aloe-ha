package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.PlantInfo;

public class PlantInfoTest {

	@Test
	public void constructorTest() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PlantInfo p = new PlantInfo(m);
		System.out.println(p.getTitle());
		assertTrue(p.getTitle().equals("Plant Info: Pine Trees"));
	}

}
