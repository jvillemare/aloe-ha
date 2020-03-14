package garden;

import org.junit.Test;

import junit.framework.TestCase;

public class ControllerTest extends TestCase {

	@Test
	public void testObject() {
		assertTrue(new Controller() instanceof Controller);
	}
	
}
