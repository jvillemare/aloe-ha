package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class YearTest {

	@Test
	public void test() {
		Year y=Year.YEAR0;
		assertTrue(y.getYear().equals("0 Years"));
	}

}
