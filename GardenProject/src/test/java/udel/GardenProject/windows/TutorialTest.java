package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.Tutorial;

public class TutorialTest {

	@Test
	public void testTutorial() {
		Model m = new Model(0, 1);
		Tutorial t = new Tutorial(m);
		assertTrue(t.getTitle().equals("Tutorial Window"));
	}

}
