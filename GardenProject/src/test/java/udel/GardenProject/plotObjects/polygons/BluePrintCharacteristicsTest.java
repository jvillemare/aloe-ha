package udel.GardenProject.plotObjects.polygons;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Sunlight;

public class BluePrintCharacteristicsTest {



	@Test
	public void testSetAll() {
		BluePrintCharacteristics b=new BluePrintCharacteristics(null, 0, 0, null, null, null);
		b.setAll(Canopy.CANOPY, Moisture.DAMP, SoilTypes.ANY, Sunlight.ANY);
		assertTrue(b.getCanopy().equals(Canopy.CANOPY));
		assertTrue(b.getMoisture().equals(Moisture.DAMP));
		assertTrue(b.getSoilType().equals(SoilTypes.ANY));
		assertTrue(b.getSunlight().equals(Sunlight.ANY));
	}


	@Test
	public void testSetCanopy() {
		BluePrintCharacteristics b=new BluePrintCharacteristics(null, 0, 0, null, null, null);
		b.setCanopy(Canopy.CANOPY);
		assertTrue(b.getCanopy().equals(Canopy.CANOPY));
	}


	@Test
	public void testSetMoisture() {
		BluePrintCharacteristics b=new BluePrintCharacteristics(null, 0, 0, null, null, null);
		b.setMoisture(Moisture.DAMP);
		assertTrue(b.getMoisture().equals(Moisture.DAMP));
	}


	@Test
	public void testSetSoilType() {
		BluePrintCharacteristics b=new BluePrintCharacteristics(null, 0, 0, null, null, null);
		b.setSoilType(SoilTypes.ANY);
		assertTrue(b.getSoilType().equals(SoilTypes.ANY));
	}


	@Test
	public void testSetSunlight() {
		BluePrintCharacteristics b=new BluePrintCharacteristics(null, 0, 0, null, null, null);
		b.setSunlight(Sunlight.ANY);
		assertTrue(b.getSunlight().equals(Sunlight.ANY));
	}

}
