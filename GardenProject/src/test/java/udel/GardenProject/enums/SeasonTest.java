package udel.GardenProject.enums;

import static org.junit.Assert.*; 

import org.junit.Test;

import udel.GardenProject.enums.Seasons;

public class SeasonTest {

	@Test
	public void testWinter() {
		Seasons w = Seasons.WINTER;
		assertTrue(w.getSeason().equals("Winter"));
	}
	
	

}
