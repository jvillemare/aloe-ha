package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Model;
import main.udel.windows.PlantInfo;

public class PlantInfoTest {

	@Test
	public void constructorTest() {
		Model m = new Model(0, 1);
		PlantInfo p = new PlantInfo(m);
		System.out.println(p.getTitle());
		assertTrue(p.getTitle().equals("Plant Info: Pine Trees"));
	}

}
