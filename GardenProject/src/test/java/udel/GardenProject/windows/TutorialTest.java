package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.Tutorial;

public class TutorialTest {

	@Test
	public void testTutorial() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		Tutorial t = new Tutorial(m);
		assertTrue(t.getTitle().equals("Tutorial Window"));
	}

}
