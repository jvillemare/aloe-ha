package udel.GardenProject.enums;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import udel.GardenProject.enums.Seasons;

public class SeasonTest {


	@Test
	public void testSeason() throws Exception {
		boolean[] year1={false,false,true,false,false,true,false,false,true,false,false,true};
		boolean[] year2={true,false,false,true,false,false,true,false,false,true,false,false};
		boolean[] year3={false,true,false,false,true,false,false,true,false,false,true,false};
		boolean[] fake= {};
		ArrayList<Seasons> season = new ArrayList<Seasons>();
		season.add(Seasons.SPRING);
		season.add(Seasons.SUMMER);
		season.add(Seasons.FALL);
		season.add(Seasons.WINTER);
		Seasons w = Seasons.WINTER;
		assertTrue(w.getSeason().equals("Winter"));
		assertTrue(Seasons.getFilterSeason(year1).containsAll(season));
		assertTrue(Seasons.getFilterSeason(year2).containsAll(season));
		assertTrue(Seasons.getFilterSeason(year3).containsAll(season));
		Seasons.getFilterSeason(fake);

	}
	
	

}
