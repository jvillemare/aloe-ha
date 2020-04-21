package test.udel.garden;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import javafx.stage.Stage;
import junit.framework.TestCase;

import main.udel.garden.Controller;
import main.udel.windows.AllPlants;
import main.udel.windows.Window;

public class ControllerTest extends TestCase {

	@Test
	public void testObject() {
		assertTrue(new Controller() instanceof Controller);
	}
	
	// TODO: More tests to Controller for future Handlers
	
}
