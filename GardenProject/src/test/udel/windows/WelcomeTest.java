package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.windows.Welcome;

public class WelcomeTest {

	@Test
	public void testWelcome() {
		Welcome w = new Welcome();
		System.out.println(w.getTitle());
		assertTrue(w.getTitle().equals("Welcome Menu"));
	}
}
