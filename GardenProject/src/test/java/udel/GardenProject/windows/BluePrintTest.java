package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class BluePrintTest {

	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		BluePrint q = new BluePrint(m);
		assertTrue(q.getTitle().equals("Blue Print Your Plot"));
	}

}
