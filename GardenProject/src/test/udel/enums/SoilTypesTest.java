package test.udel.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import main.udel.enums.SoilTypes;

public class SoilTypesTest {

	@Test
	public void testSoilType() {
		SoilTypes sl = SoilTypes.PEATY;
		assertTrue(sl.getSoilType().equals("peaty"));
	}

}
