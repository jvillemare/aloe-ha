package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Controller;
import main.udel.windows.AllPlants;

public class AllPlantsTest {

	@Test
	public void test() {
		AllPlants w = new AllPlants();
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().contentEquals("Plant Database"));
	}

}
