package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandLineArgsTest {

	@Test
	public void test() {
		CommandLineArgs c=CommandLineArgs.OpenProject;
		assertTrue(c instanceof CommandLineArgs);
	}

}
