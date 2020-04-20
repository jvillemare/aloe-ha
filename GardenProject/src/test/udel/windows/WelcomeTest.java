package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.windows.Welcome;

public class WelcomeTest {

	@Test
	public void testWelcome() {
		Welcome w = new Welcome();
		assertTrue(w.getTitle().equals("Welcome Menu"));
	}
}
