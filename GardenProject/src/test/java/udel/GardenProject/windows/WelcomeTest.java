package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.Welcome;

public class WelcomeTest {

	@Test
	public void testWelcome() {
		Model m = new Model(0, 1);
		Welcome w = new Welcome(m);
		assertTrue(w.getTitle().equals("Welcome Menu"));
	}
}
