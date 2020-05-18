package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class PreviousSavesTest {

	@Test
	public void test() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		PreviousSaves q = new PreviousSaves(m);
		assertTrue(q.getTitle().equals("Past Saved Sessions"));
	}

}
