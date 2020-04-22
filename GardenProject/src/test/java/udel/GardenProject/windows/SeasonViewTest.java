package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.SeasonView;

public class SeasonViewTest {

	@Test
	public void testSeasonView() {
		Model m = new Model(0, 1);
		SeasonView s = new SeasonView(m);
		assertTrue(s.getTitle().equals("Garden Previewer"));
	}
}
