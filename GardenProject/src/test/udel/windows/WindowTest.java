package test.udel.windows;

import static org.junit.Assert.*;
import org.junit.Test;
import main.udel.windows.*;

public class WindowTest {

	@Test
	public void testWindow() {
		Window w = new PlotDesign();
		assertTrue(w.getTitle().equals("Plot Designer"));
		assertTrue(w.getScene() instanceof javafx.scene.Scene);
	}
	
}
