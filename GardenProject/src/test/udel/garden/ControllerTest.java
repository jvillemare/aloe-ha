package test.udel.garden;

import org.junit.Test;
import junit.framework.TestCase;

import main.udel.garden.*;

public class ControllerTest extends TestCase {

	@Test
	public void testObject() {
		assertTrue(new Controller() instanceof Controller);
	}
	
}
