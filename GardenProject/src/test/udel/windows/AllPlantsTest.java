package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Controller;
import main.udel.garden.Model;
import main.udel.windows.AllPlants;

public class AllPlantsTest {

	@Test
	public void test() {
		Model m = new Model(0, 1);
		AllPlants w = new AllPlants(m);
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().contentEquals("Plant Database"));
	}

}
