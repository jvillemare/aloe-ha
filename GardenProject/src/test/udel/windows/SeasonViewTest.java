package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.windows.SeasonView;

public class SeasonViewTest {

	@Test
	public void testSeasonView() {
		SeasonView s = new SeasonView();
		System.out.println(s.getTitle());
		assertTrue(s.getTitle().equals("Garden Previewer"));
	}
}
