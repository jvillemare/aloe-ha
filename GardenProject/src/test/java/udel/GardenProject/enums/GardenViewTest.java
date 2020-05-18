package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class GardenViewTest {

	@Test
	public void test() {
		GardenView g=GardenView.TOPVIEW;
		assertTrue(g.getView().equals("Top View"));
	}

}
