package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
import udel.GardenProject.enums.SoilTypes;

public class PlantLoaderTest {

	private static ArrayList<Plant> plants;

	@BeforeClass
	public static void setup() throws FileNotFoundException, IOException, ParseException {
		plants = PlantLoader.getPlants(); // Set up Plant array
	}

	@Test
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
		for (int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals(a.getLatinName())) {
				good = false;
				break;
			}
		}
		assertTrue(good);
	}

	@Test // Made sure a plant from the sunny data was added.
	public void sunnyElementTest() {
		Plant plant = null;
		for (int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Cornus seriacea")) {
				plant = plants.get(i);
				break;
			}
		}
		String descript = "Description: " + System.lineSeparator() + "Color: red twig winter" + System.lineSeparator()
				+ "Height: 7'" + System.lineSeparator() + "Fruit: berries August" + System.lineSeparator()
				+ "Other: red stems";
		assertTrue(plant.getDescription().equals(descript));
		String[] com = { "Red twig dogwood" };
		for (int i = 0; i < plant.getCommonNames().length; i++) {
			assertTrue(plant.getCommonNames()[i].equals(com[i]));
		}
		boolean[] bloom = { false, false, false, false, true, true, false, false, false, false, false, false };
		for (int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		assertTrue(plant.getMoisture().compareTo(Moisture.MOIST_DAMP) == 0);
		assertTrue(plant.getLight() == .83);
		assertTrue(plant.getLatinName().equals("Cornus seriacea"));
		assertFalse(plant.getDelawareNative());
		assertFalse(plant.getInvasive());
		assertTrue(plant.getCanopy() == Canopy.FLOOR);
		assertTrue(plant.getSoilType() == null);
		for (PlantDataSource s : plant.getSource()) {
			assertTrue(s.compareTo(PlantDataSource.SUNNYEDGE) == 0);
		}
		assertTrue(plant.getImages() == null);
	}

	@Test // Made sure a plant from the flora data was added.
	public void floraElementTest() {
		Plant plant = null;
		for (int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Acalypha rhomboidea")) {
				plant = plants.get(i);
				break;
			}
		}
		String descript = "Description: " + System.lineSeparator()
				+ "Meaning of Name: Acalypha: nettle; rhomboidea: diamond-shaped" + System.lineSeparator()
				+ "Life Form: Annual Herb" + System.lineSeparator()
				+ "Habitat: Thin canopy woods, edges, disturbed soils" + System.lineSeparator()
				+ "State Status: Common (S4, apparently secure)" + System.lineSeparator()
				+ "Piedmont Status: Common (S4, apparently secure)" + System.lineSeparator()
				+ "Coastal Plain Status: Common (S4, apparently secure)" + System.lineSeparator() + "Global Status: --"
				+ System.lineSeparator() + "Federal Status: --" + System.lineSeparator() + "Invasive Watchlist: --"
				+ System.lineSeparator() + "Physiographic Province: \u2611Piedmont\u2611Coastal Plain"
				+ System.lineSeparator() + "Additional Info: --";
		assertTrue(plant.getDescription().equals(descript));
		assertTrue(plant.getCommonNames() == null);
		assertTrue(plant.getDelawareNative());
		boolean[] bloom = { false, false, false, false, false, false, true, true, true, false, false, false };
		for (int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		assertTrue(plant.getMoisture() == null);
		assertTrue(plant.getLight() == -1);
		assertTrue(plant.getLatinName().equals("Acalypha rhomboidea"));
		assertFalse(plant.getInvasive());
		assertTrue(plant.getCanopy() == null);
		assertTrue(plant.getSoilType() == null);
		for (PlantDataSource s : plant.getSource()) {
			assertTrue(s.compareTo(PlantDataSource.UDEL) == 0);
		}
		assertTrue(plant.getImages() == null);
	}

	@Test // Made sure a plant from the native data was added.
	public void nativeElementTest() {
		Plant plant = null;
		for (int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Coreopsis verticillata")) {
				plant = plants.get(i);
				break;
			}
		}
		String des = "Description: " + System.lineSeparator() + "Family: Asteraceae" + System.lineSeparator()
				+ "Habitat: tolerates poor soils; dry open woods and clearings; roadsides" + System.lineSeparator()
				+ "Regions: Piedmont" + System.lineSeparator() + "States: DC, MD, VA, WV" + System.lineSeparator()
				+ "Height: 0.5 - 3.5ft;" + System.lineSeparator() + "Flower Color: yellow" + System.lineSeparator()
				+ "Fruit: Capsule;" + System.lineSeparator() + "Ground Cover: Provides Ground Cover"
				+ System.lineSeparator() + "Link: http://www.nativeplantcenter.net/plants/coreopsis-verticillata/";
		assertTrue(plant.getDescription().equals(des));
		assertTrue(plant.getCanopy() == Canopy.FLOOR);
		assertTrue(plant.getLight() == .83);
		assertTrue(plant.getSoilType() == SoilTypes.LOAMY);
		assertTrue(plant.getMoisture() == Moisture.DRY_MOIST);
		assertTrue(plant.getLatinName().equals("Coreopsis verticillata"));
		assertFalse(plant.getDelawareNative());
		assertFalse(plant.getInvasive());
		String[] com = { "threadleaf coreopsis" };
		for (int i = 0; i < plant.getCommonNames().length; i++) {
			assertTrue(plant.getCommonNames()[i].equals(com[i]));
		}
		boolean[] bloom = { false, false, false, false, false, true, true, true, true, true, false, false };
		for (int i = 0; i < plant.getBloomTime().length; i++) {
			assertTrue(plant.getBloomTime()[i] == bloom[i]);
		}
		for (PlantDataSource s : plant.getSource()) {
			assertTrue(s.compareTo(PlantDataSource.NPC) == 0);
		}
		String[] img = {
				"http://www.nativeplantcenter.net/wp-content/uploads/2016/05/Coreopsis-verticillata-USFWS-BES.jpg" };
		for (int i = 0; i < plant.getImages().length; i++) {
			assertTrue(plant.getImages()[i].equals(img.clone()[i]));
		}
	}

	@Test // Made sure a plant from the nrcs data was added.
			// Also tests mege function.
	public void nrcsElementTest() {
		Plant p = null;
		for (int i = 1; i < plants.size(); i++) {
			if (plants.get(i).getLatinName().equals("Monarda didyma")) {
				p = plants.get(i);
				break;
			}
		}
		String[] im = { "https://plants.sc.egov.usda.gov/gallery/standard/modi_002_svp.jpg",
				"https://plants.sc.egov.usda.gov/gallery/standard/modi_003_shp.jpg",
				"https://plants.sc.egov.usda.gov/gallery/standard/modi_sht.jpg",
				"https://plants.sc.egov.usda.gov/gallery/standard/modi_001_svd.jpg",
				"http://www.nativeplantcenter.net/wp-content/uploads/2016/05/DSC_3974.jpg",
				"http://www.nativeplantcenter.net/wp-content/uploads/2016/05/Monarda-didyma-USFWS-BES.jpg" };
		// Merged with plant from Native File
		for (int i = 0; i < p.getImages().length; i++) {
			assertTrue(p.getImages()[i].equals(im[i]));
		}
		boolean[] b = PlantLoader.bloomCalc("Summer", "seasons");
		for (int i = 0; i < 12; i++) {
			assertTrue(p.getBloomTime()[i] == b[i]);
		}
		assertTrue(p.getLatinName().equals("Monarda didyma"));
		String des = "Description: " + System.lineSeparator() + "Symbol: MODI" + System.lineSeparator() + "Group: Dicot"
				+ System.lineSeparator() + "Family: Lamiaceae" + System.lineSeparator() + "Duration: Perennial"
				+ System.lineSeparator() + "Growth Habitat: Forb/herb" + System.lineSeparator() + "Family: Lamiaceae"
				+ System.lineSeparator() + "Habitat: creek banks, floodplains, moist woods" + System.lineSeparator()
				+ "Regions: Mountain" + System.lineSeparator() + "States: DC, MD, NY, PA, VA, WV"
				+ System.lineSeparator() + "Height: 2 - 5ft;" + System.lineSeparator() + "Flower Color: red"
				+ System.lineSeparator() + "Fruit: Nut/Nut-like;" + System.lineSeparator()
				+ "Other: showy flowers; aromatic; herbal uses" + System.lineSeparator()
				+ "Link: http://www.nativeplantcenter.net/plants/monarda-didyma/" + System.lineSeparator()
				+ "Meaning of Name: Monarda: named for Nicolas Monardes; didyma: in pairs" + System.lineSeparator()
				+ "Life Form: Perennial Herb" + System.lineSeparator()
				+ "Habitat: Cultivated and a rare escape to disturbed areas" + System.lineSeparator()
				+ "State Status: Non-indigenous" + System.lineSeparator() + "Piedmont Status: Non-indigenous"
				+ System.lineSeparator() + "Coastal Plain Status: --" + System.lineSeparator() + "Global Status: --"
				+ System.lineSeparator() + "Federal Status: --" + System.lineSeparator() + "Invasive Watchlist: --"
				+ System.lineSeparator() + "Physiographic Province: ☑Piedmont☐Coastal Plain" + System.lineSeparator()
				+ "Additional Info: --";
		assertTrue(p.getDescription().equals(p.getDescription()));
		assertTrue(p.getCanopy() == Canopy.FLOOR);
		assertTrue(p.getDelawareNative());
		String[] com = { "Oswego tea", "beebalm", "scarlet beebalm"};
		for (int i = 0; i < p.getCommonNames().length; i++) {
			assertTrue(p.getCommonNames()[i].equals(com[i]));
		}
		assertFalse(p.getInvasive());
		assertTrue(p.getLight() == .83);
		boolean[] bt = {false, false, false, false, false, false, true, true, true, false, false, false};
		for(int i = 0; i<12; i++) {
			assertTrue(p.getBloomTime()[i] == bt[i]);
		}
		PlantDataSource[] ds = {PlantDataSource.UDEL, PlantDataSource.NPC, PlantDataSource.NRCS};
		for (int i = 0; i < p.getSource().length; i++) {
			assertTrue(p.getSource()[i] == ds[i]);
		}
		assertTrue(p.getSoilType() == SoilTypes.LOAMY);
		assertTrue(p.getMoisture() == Moisture.MOIST_DAMP);
	}
	
	@Test
	public void findTest() {
		String[] arr= {"test1", "test2"};
		assertTrue(PlantLoader.find(arr, "test3")==-1);
	}
	
	@Test
	public void combineBoolArrTest() {
		boolean[] arr1= {false,true,false,true,false,true,false,true,false,true,false,true};
		boolean[] arr2= {true,false,true,false,true,false,true,false,true,false,true,false};
		boolean[] res= PlantLoader.combineBoolArr(arr1, arr2);//{true,true,true,true,true,true,true,true,true,true,true,true};
		for (int i = 0; i < 12; i++) {
			assertTrue(res[i]);
		}
	}

	

}
