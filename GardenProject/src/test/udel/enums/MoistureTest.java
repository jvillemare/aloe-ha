package test.udel.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.enums.Moisture;

public class MoistureTest {

	@Test
	public void testWinter() {
		Moisture m = Moisture.LOW;
		assertTrue(m.getMoistureLevel().equals("low"));
	}

	
	
	
}
