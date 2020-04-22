package udel.GardenProject.garden;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import javafx.stage.Stage;
import junit.framework.TestCase;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.windows.AllPlants;
import udel.GardenProject.windows.Window;

public class ControllerTest extends TestCase {

	@Test
	public void testObject() {
		assertTrue(new Controller() instanceof Controller);
	}
	
	// TODO: More tests to Controller for future Handlers
	
}
