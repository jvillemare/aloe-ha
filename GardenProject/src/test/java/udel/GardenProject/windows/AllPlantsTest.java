package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.AllPlants;

public class AllPlantsTest {


	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 10, 10);
		AllPlants w = new AllPlants(m);
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().contentEquals("Plant Database"));
	}

}
