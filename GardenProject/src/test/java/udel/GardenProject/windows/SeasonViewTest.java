package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.SeasonView;

public class SeasonViewTest {

	@Test
	public void testSeasonView() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		SeasonView s = new SeasonView(m);
		assertTrue(s.getTitle().equals("Garden Previewer"));
	}
}
