package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;

public class PlantLoaderTest {
	
	private static ArrayList<Plant> plants;
	
	
	@BeforeClass
    public static void setup() throws FileNotFoundException, IOException, ParseException {
        plants = PlantLoader.getPlants();
    }
	
	public void notEmptyTest() {
		assertTrue(plants.size() > 0);
	}

	@Test
	public void arrayTypeTest() {
		assertTrue(plants.get(0) instanceof Plant);
	}
	
	@Test
	public void duplicateTest() {
		boolean good = true;
		Plant a = plants.get(0);
		for(int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals(a.getLatinName())) {
				good = false;
				break;
			}
		}
		assertTrue(good);
	}
	
	@Test
	public void sunnyElementTest() {
		Plant plant = null;
		for(int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Cornus seriacea")) {
				plant = plants.get(i);
				break;
			}
		}
		String[] com = {"Red twig dogwood"};
		for (int i = 0; i < plant.getCommonNames().length; i++) {
			assertTrue(plant.getCommonNames()[i].equals(com[i]));
		}
		boolean[] bloom = {false, false, false, false, true, true, false, false, false, false, false, false};
		for(int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		assertTrue(plant.getMoisture().compareTo(Moisture.MOIST_DAMP) == 0);
	}

}
