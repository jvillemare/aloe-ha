package test.udel.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import main.udel.enums.Seasons;

public class SeasonTest {

	@Test
	public void testWinter() {
		Seasons w = Seasons.WINTER;
		assertTrue(w.getSeason().equals("Winter"));
	}
	
	

}
