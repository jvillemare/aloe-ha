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
import udel.GardenProject.enums.SoilTypes;

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
		String descript = "Description: " + System.lineSeparator() + 
				"Color: red twig winter" + System.lineSeparator() + 
				"Height: 7'" + System.lineSeparator() + 
				"Fruit: berries August" + System.lineSeparator() + 
				"Other: red stems";
		assertTrue(plant.getDescription().equals(descript));
		String[] com = {"Red twig dogwood"};
		for (int i = 0; i < plant.getCommonNames().length; i++) {
			assertTrue(plant.getCommonNames()[i].equals(com[i]));
		}
		boolean[] bloom = {false, false, false, false, true, true, false, false, false, false, false, false};
		for(int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		assertTrue(plant.getMoisture().compareTo(Moisture.MOIST_DAMP) == 0);
		assertTrue(plant.getLight() == .83);
		assertTrue(plant.getLatinName().equals("Cornus seriacea"));
		assertFalse(plant.getDelawareNative());
		assertFalse(plant.getInvasive());
		assertTrue(plant.getCanopy() == Canopy.FLOOR);
		assertTrue(plant.getSoilType() == null);
	}
	
	@Test
	public void floraElementTest() {
		Plant plant = null;
		for(int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Acalypha rhomboidea")) {
				plant = plants.get(i);
				break;
			}
		}
		String descript = "Description: " + System.lineSeparator() + 
				"Meaning of Name: Acalypha: nettle; rhomboidea: diamond-shaped" + System.lineSeparator() + 
				"Life Form: Annual Herb" + System.lineSeparator() + 
				"Habitat: Thin canopy woods, edges, disturbed soils" + System.lineSeparator() + 
				"State Status: Common (S4, apparently secure)" + System.lineSeparator() + 
				"Piedmont Status: Common (S4, apparently secure)" + System.lineSeparator() + 
				"Coastal Plain Status: Common (S4, apparently secure)" + System.lineSeparator() + 
				"Global Status: --" + System.lineSeparator() + 
				"Federal Status: --" + System.lineSeparator() + 
				"Invasive Watchlist: --" + System.lineSeparator() + 
				"Physiographic Province: \u2611Piedmont\u2611Coastal Plain" + System.lineSeparator() + 
				"Additional Info: --";
		assertTrue(plant.getDescription().equals(descript));
		assertTrue(plant.getCommonNames() == null);
		assertTrue(plant.getDelawareNative());
		boolean[] bloom = {false, false, false, false, false, false, true, true, true, false, false, false};
		for(int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		assertTrue(plant.getMoisture() == null);
		assertTrue(plant.getLight() == -1);
		assertTrue(plant.getLatinName().equals("Acalypha rhomboidea"));
		assertFalse(plant.getInvasive());
		assertTrue(plant.getCanopy() == null);
		assertTrue(plant.getSoilType() == null);
	}
	
	@Test
	public void nativeElementTest() {
		Plant plant = null;
		for(int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Photinia melanocarpa")) {
				plant = plants.get(i);
				break;
			}
		}
		String des = "Description: " + System.lineSeparator() + 
				"Family: Rosaceae" + System.lineSeparator() + 
				"Habitat: bog, swamp, spring, dune, cliff, old field, clearings; wet or dry thickets, creek banks, wet acid sand, balds; thin soils and rock outcroppings" + System.lineSeparator() + 
				"Regions: Coastal Plain, Mountain, Piedmont" + System.lineSeparator() + 
				"States: DE, MD, NY, PA, VA, WV" + System.lineSeparator() + 
				"Height: 3 - 6ft; Spread" + System.lineSeparator() + 
				"Flower Color: white or pink-tinged" + System.lineSeparator() + 
				"Fall Color: crimson red" + System.lineSeparator() + 
				"Fruit: Berry; black" + System.lineSeparator() + 
				"Other: can be pruned as hedge" + System.lineSeparator() + 
				"Link: http://www.nativeplantcenter.net/plants/photinia-melanocarpa/" + System.lineSeparator() + 
				"Image Link: http://www.nativeplantcenter.net/wp-content/uploads/2016/05/Photinia-melanocarpa-Aronia-m-USFWS-BES.jpg";
		assertTrue(plant.getDescription().equals(des));
		assertTrue(plant.getCanopy() == Canopy.FLOOR);
		assertTrue(plant.getLight() == .83);
		assertTrue(plant.getSoilType() == SoilTypes.ANY);
		assertTrue(plant.getMoisture() == Moisture.MOIST);
		assertTrue(plant.getLatinName().equals("Photinia melanocarpa"));
		assertTrue(plant.getDelawareNative());
		assertFalse(plant.getInvasive());
		String[] com = {"black chokeberry"};
		for (int i = 0; i < plant.getCommonNames().length; i++) {
			assertTrue(plant.getCommonNames()[i].equals(com[i]));
		}
		boolean[] bloom = {false, false, false, true, true, false, false, false, false, false, false, false};
		for(int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
	}

}
