package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorsTest {

	@Test
	public void test() {
		Colors c = Colors.ALICEBLUE;
		assertTrue(c.name().toLowerCase().equals("dry"));
	}

}
