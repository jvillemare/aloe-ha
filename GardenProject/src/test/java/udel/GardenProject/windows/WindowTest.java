package udel.GardenProject.windows;

import static org.junit.Assert.*;
import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.*;

public class WindowTest {

	@Test
	public void testWindow() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		Window w = new PlotDesign(m);
		assertTrue(w.getTitle().equals("Plot Designer"));
		assertTrue(w.getScene() instanceof javafx.scene.Scene);
	}
	
}
