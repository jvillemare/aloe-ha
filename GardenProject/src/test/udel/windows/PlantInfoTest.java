package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.windows.PlantInfo;

public class PlantInfoTest {

	@Test
	public void test() {
		PlantInfo p = new PlantInfo();
		System.out.println(p.getTitle());
		assertTrue(p.getTitle().equals("Plant Info: Pine Trees"));
	}

}
