package test.udel.windows;

import static org.junit.Assert.*;
import org.junit.Test;

import main.udel.garden.Model;
import main.udel.windows.*;

public class WindowTest {

	@Test
	public void testWindow() {
		Model m = new Model(0, 1);
		Window w = new PlotDesign(m);
		assertTrue(w.getTitle().equals("Plot Designer"));
		assertTrue(w.getScene() instanceof javafx.scene.Scene);
	}
	
}
