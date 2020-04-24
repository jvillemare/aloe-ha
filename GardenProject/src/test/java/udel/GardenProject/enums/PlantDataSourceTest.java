package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlantDataSourceTest {

	@Test
	public void sourceTest() {
		PlantDataSource sun = PlantDataSource.SUNNYEDGE;
		assertTrue(sun.getSource().equals("Sunny Edge Plants in Arden, Newark"));
	}

}
