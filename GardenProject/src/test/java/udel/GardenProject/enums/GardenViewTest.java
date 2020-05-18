package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class GardenViewTest {

	@Test
	public void test() {
		GardenView g=GardenView.WINDOWVIEW;
		assertTrue(g.getView().equals("Window View"));
	}

}
