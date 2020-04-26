package udel.GardenProject.plants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Sunlight;

/**
 * Load all the plants from local file databases.
 * 
 * @author Team 0
 */
public class PlantLoader {

	public static final String floraPath = "src/main/resources/plantData/udel-flora.json";
	public static final String sunnyPath = "src/main/resources/plantData/sunny-edge-plants-data.json";
	public static final String nativePath = "src/main/resources/plantData/native-plant-center.json";
	public static final String nrcsPath = "src/main/resources/plantData/nrcs-data.json";

	private static final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private static final String[] seasons = { "Winter", "Spring", "Summer", "Fall" };
	private static Plant[] plants;

	/**
	 * Loads plants from local file Udel-Flora.json <br>
	 * Default plant attributes:
	 * <li>commonNames: null
	 * <li>latinName: given latinName in file
	 * <li>description: "Description: "
	 * <li>bloomTime: null
	 * <li>light: -1 moisture: null
	 * <li>soilType: null
	 * <li>canopy: null
	 * <li>delawareNative: false
	 * <li>source: {PlantDataSource.UDEL}
	 * 
	 * @return an ArrayList<Plant> with the plants from Udel-Flora.json
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Plant> loadFlora() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Plant> result = new ArrayList<>();
		File myObj = new File(floraPath);
		Scanner myReader = new Scanner(myObj);
		String data = myReader.nextLine();
		myReader.close();
		JSONObject obj = new JSONObject(data);
		Set<String> latinNames = obj.keySet();
		PlantDataSource[] source = { PlantDataSource.UDEL };
		for (String plant : latinNames) {
			String latin = null;
			String[] common = null;
			String description = "Description: ";
			boolean[] bloom = null;
			double light = -1;
			Moisture moisture = null;
			SoilTypes soilType = null;
			boolean nativ = false;
			boolean invade = false;
			Canopy canopy = null;
			String[] images = null;
			latin = plant;
			String com = obj.getJSONObject(plant).getString("Synonym");
			if (com.equals("--")) {
				common = null;
			} else {
				common = com.split(";");
			}
			invade = getBool(obj.getJSONObject(plant).getString("Invasive"));
			nativ = getBool(obj.getJSONObject(plant).getString("Native Plant"));
			bloom = bloomCalc(obj.getJSONObject(plant).getString("Flowering Period"), "seasons");
			description = description + System.lineSeparator() + "Meaning of Name: "
					+ obj.getJSONObject(plant).getString("Meaning of Scientific Name");
			description = description + System.lineSeparator() + "Life Form: "
					+ obj.getJSONObject(plant).getString("Life Form");
			description = description + System.lineSeparator() + "Habitat: "
					+ obj.getJSONObject(plant).getString("Habitat");
			description = description + System.lineSeparator() + "State Status: "
					+ obj.getJSONObject(plant).getString("State Status");
			description = description + System.lineSeparator() + "Piedmont Status: "
					+ obj.getJSONObject(plant).getString("Piedmont Status");
			description = description + System.lineSeparator() + "Coastal Plain Status: "
					+ obj.getJSONObject(plant).getString("Coastal Plain Status");
			description = description + System.lineSeparator() + "Global Status: "
					+ obj.getJSONObject(plant).getString("Global Status");
			description = description + System.lineSeparator() + "Federal Status: "
					+ obj.getJSONObject(plant).getString("Federal Status");
			description = description + System.lineSeparator() + "Invasive Watchlist: "
					+ obj.getJSONObject(plant).getString("Invasive Watchlist");
			description = description + System.lineSeparator() + "Physiographic Province: "
					+ obj.getJSONObject(plant).getString("Physiographic Province");
			description = description + System.lineSeparator() + "Additional Info: "
					+ obj.getJSONObject(plant).getString("Additional Info");
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade,
					source, images));
		}
		return result;
	}

	/**
	 * Loads plants from local file native-plant-center.json <br>
	 * Default plant attributes:
	 * <li>commonNames: null
	 * <li>latinName: given latinName in file
	 * <li>description: "Description: "
	 * <li>bloomTime: null
	 * <li>light: -1 moisture: null
	 * <li>soilType: null
	 * <li>canopy: null
	 * <li>delawareNative: false
	 * <li>source: {PlantDataSource.NPC}
	 * 
	 * @return an ArrayList<Plant> with the plants from native-plant-center.json
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<Plant> loadNative() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Plant> result = new ArrayList<>();
		File myObj = new File(nativePath);
		Scanner myReader = new Scanner(myObj);
		int count = 0;
		String data = myReader.nextLine();
		myReader.close();
		JSONObject obj = new JSONObject(data);
		Set<String> latinNames = obj.keySet();
		PlantDataSource[] source = { PlantDataSource.NPC };
		for (String plant : latinNames) {
			String latin = null;
			String[] common = null;
			String description = "Description: ";
			boolean[] bloom = null;
			double light = -1;
			Moisture moisture = null;
			SoilTypes soilType = null;
			boolean nativ = false;
			boolean invade = false;
			Canopy canopy = null;
			String[] images = null;
			latin = plant;
			JSONArray alias = obj.getJSONObject(plant).getJSONArray("alias");
			common = new String[alias.length()];
			for (int i = 0; i < alias.length(); i++) {
				common[i] = alias.getString(i);
			}
			if (obj.getJSONObject(plant).getString("states").contains("DE")) {
				nativ = true;
			}
			if (obj.getJSONObject(plant).has("plant types")) {
				String type = obj.getJSONObject(plant).getString("plant types");
				if (type.contains("Grass") || type.contains("Herb") || type.contains("Fern")
						|| type.contains("medium shrub") || obj.getJSONObject(plant).has("ground cover")
						|| type.contains("Low Shrub")) {
					canopy = canopy.FLOOR;
				} else if (type.contains("Understory") || type.contains("Tall Shrub")) {
					canopy = Canopy.UNDERSTORY;
				} else if (type.contains("Canopy")) {
					canopy = Canopy.CANOPY;
				}
			}
			String sun = obj.getJSONObject(plant).getString("sun exposure");
			if (sun.contains("Full Sun")) {
				if (sun.contains("Partial Sun")) {
					if (sun.contains("Shade")) {
						light = .67;
					} else {
						light = .83;
					}
				} else {
					light = 1;
				}
			} else if (sun.contains("Partial Sun")) {
				if (sun.contains("Shade")) {
					light = .33;
				} else {
					light = .5;
				}
			} else if (sun.contains("Shade")) {
				light = .17;
			}
			if (obj.getJSONObject(plant).has("soil texture")) {
				String soTy = obj.getJSONObject(plant).getString("soil texture");
				switch (soTy) {
				// Lack of breaks intentional -> Want same result as following case.
				case "Clay":
					soilType = SoilTypes.CLAY;
					break;
				case "Loamy":
				case "Loamy, Sandy":
					soilType = SoilTypes.LOAMY;
					break;
				case "Sandy":
					soilType = SoilTypes.SANDY;
					break;
				case "Clay, Loamy, Sandy":
					soilType = SoilTypes.ANY;
					break;
				}
			}
			if (obj.getJSONObject(plant).has("soil moisture")) {
				String soM = obj.getJSONObject(plant).getString("soil moisture");
				switch (soM) {
				// Lack of breaks intentional -> Want same result as following case.
				case "Dry":
					moisture = Moisture.DRY;
					break;
				case "Moist":
				case "Dry, Moist, Wet":
					moisture = Moisture.MOIST;
					break;
				case "Moist, Wet":
					moisture = Moisture.MOIST_DAMP;
					break;
				case "Dry, Moist":
					moisture = Moisture.DRY_MOIST;
					break;
				case "Flooded, Moist, Wet":
				case "Flooded, Wet":
				case "Dry, Flooded, Moist, Wet":
				case "Wet":
					moisture = Moisture.DAMP;
					break;
				}
			}
			if (obj.getJSONObject(plant).get("image").getClass().equals(String.class)) {
				images = new String[1];
				images[0] = obj.getJSONObject(plant).getString("image");
			} else {
				JSONArray im = obj.getJSONObject(plant).getJSONArray("image");
				int len = im.length();
				images = new String[len];
				for (int i = 0; i < len; i++) {
					images[i] = im.getString(i);
				}
			}
			if (obj.getJSONObject(plant).has("blooms")) {
				bloom = bloomCalc(obj.getJSONObject(plant).getString("blooms"), "months");
			}
			description = description + System.lineSeparator() + "Family: "
					+ obj.getJSONObject(plant).getString("family");
			description = description + System.lineSeparator() + "Habitat: "
					+ obj.getJSONObject(plant).getString("habitat");
			description = description + System.lineSeparator() + "Regions: "
					+ obj.getJSONObject(plant).getString("regions");
			description = description + System.lineSeparator() + "States: "
					+ obj.getJSONObject(plant).getString("states");
			if ((obj.getJSONObject(plant).has("height"))) {
				description = description + System.lineSeparator() + "Height: "
						+ obj.getJSONObject(plant).getString("height");
			}
			if (obj.getJSONObject(plant).has("flower color")) {
				description = description + System.lineSeparator() + "Flower Color: "
						+ obj.getJSONObject(plant).getString("flower color");
			}
			if (obj.getJSONObject(plant).has("fall color")) {
				description = description + System.lineSeparator() + "Fall Color: "
						+ obj.getJSONObject(plant).getString("fall color");
			}
			if (obj.getJSONObject(plant).has("fruit")) {
				description = description + System.lineSeparator() + "Fruit: "
						+ obj.getJSONObject(plant).getString("fruit");
			}
			if (obj.getJSONObject(plant).has("ground cover")) {
				description = description + System.lineSeparator() + "Ground Cover: " + "Provides Ground Cover";
			}
			if (obj.getJSONObject(plant).has("notes")) {
				description = description + System.lineSeparator() + "Other: "
						+ obj.getJSONObject(plant).getString("notes");
			}
			if (obj.getJSONObject(plant).has("Link")) {
				description = description + System.lineSeparator() + "Link: "
						+ obj.getJSONObject(plant).getString("Link");
			} else {
				description = description + System.lineSeparator() + "Link: "
						+ obj.getJSONObject(plant).getString("link");
			}
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade,
					source, images));
		}
		return result;
	}

	/**
	 * Loads plants from local file sunny-edge-plants-data.json <br>
	 * Default plant attributes:
	 * <li>commonNames: null
	 * <li>latinName: given latinName in file
	 * <li>description: "Description: "
	 * <li>bloomTime: null
	 * <li>light: -1 moisture: null
	 * <li>soilType: null
	 * <li>canopy: null
	 * <li>delawareNative: false
	 * <li>source: {PlantDataSource.NPC}
	 * 
	 * @return an ArrayList<Plant> with the plants from sunny-edge-plants-data.json
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Plant> loadSunny() throws FileNotFoundException {
		ArrayList<Plant> result = new ArrayList<>();
		File myObj = new File(sunnyPath);
		Scanner myReader = new Scanner(myObj);
		String data = myReader.nextLine();
		myReader.close();
		JSONObject obj = new JSONObject(data);
		Set<String> latinNames = obj.keySet();
		PlantDataSource[] source = { PlantDataSource.SUNNYEDGE };
		for (String plant : latinNames) {
			String latin = null;
			String[] common = null;
			String description = "Description: ";
			boolean[] bloom = null;
			double light = -1;
			Moisture moisture = null;
			SoilTypes soilType = null;
			boolean nativ = false;
			boolean invade = false;
			Canopy canopy = null;
			String[] images = null;
			latin = obj.getJSONObject(plant).getString("latin");
			common = new String[1];
			common[0] = plant;
			String sun = obj.getJSONObject(plant).getString("light");
			sun = sun.replace(" ", "");
			int ind = sun.indexOf("-");
			if (ind != -1) {
				sun = sun.substring(0, ind) + "/" + sun.substring(ind + 1);
			}
			sun = sun.replace(".", "");
			switch (sun) {
			// Lack of breaks intentional -> Want same result as following case.
			case "sun":
				light = 1;
				break;
			case "sun/ptshade":
			case "sun/ptsun":
				light = .83;
				break;
			case "sun/shade":
			case "ptshade":
				light = .67;
				break;
			case "ptsun":
				light = .5;
				break;
			}
			String water = obj.getJSONObject(plant).getString("water use");
			ind = water.indexOf("-");
			if (ind != -1) {
				water = water.substring(0, ind) + "/" + water.substring(ind + 1);
			}
			water = water.replace(".", "");
			water = water.trim();
			switch (water) {
			// Lack of breaks intentional -> Want same result as following case.
			case "moist":
			case "med/moist":
			case "medium":
				moisture = Moisture.MOIST;
				break;
			case "dry":
				moisture = Moisture.DRY;
				break;
			case "moist/dry":
			case "dry/wet":
			case "dry/moist":
				moisture = Moisture.DRY_MOIST;
				break;
			case "wet":
				moisture = Moisture.DAMP;
				break;
			case "medium/wet":
			case "wet/medium":
			case "moist/medium":
			case "wet/med":
				moisture = Moisture.MOIST_DAMP;
				break;
			}
			String layer = obj.getJSONObject(plant).getString("canopy");
			layer = layer.replace(" ", "");
			switch (layer) {
			// Lack of breaks intentional -> Want same result as following case.
			case "overstory":
				canopy = Canopy.EMERGENT;
				break;
			case "substory":
				canopy = Canopy.UNDERSTORY;
				break;
			case "shrublayer":
			case "herblayer":
			case "groundcovers:verylowgrowing":
				canopy = Canopy.FLOOR;
				break;
			}
			bloom = bloomCalc(obj.getJSONObject(plant).getString("bloom"), "months");
			description = description + System.lineSeparator() + "Color: "
					+ obj.getJSONObject(plant).getString("color");
			description = description + System.lineSeparator() + "Height: "
					+ obj.getJSONObject(plant).getString("height").replace("\\u2019", "'");
			description = description + System.lineSeparator() + "Fruit: "
					+ obj.getJSONObject(plant).getString("fruit");
			description = description + System.lineSeparator() + "Other: "
					+ obj.getJSONObject(plant).getString("notes");
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade,
					source, images));
		}
		return result;
	}

	/**
	 * Loads plants from local file nrcs-data.json <br>
	 * Default plant attributes:
	 * <li>commonNames: null
	 * <li>latinName: given latinName in file
	 * <li>description: "Description: "
	 * <li>bloomTime: null
	 * <li>light: -1 moisture: null
	 * <li>soilType: null
	 * <li>canopy: null
	 * <li>delawareNative: false
	 * <li>source: {PlantDataSource.NRCS}
	 * 
	 * @return an ArrayList<Plant> with the plants from nrcs-data.json
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Plant> loadNRCS() throws FileNotFoundException {
		ArrayList<Plant> result = new ArrayList<>();
		File myObj = new File(nrcsPath);
		Scanner myReader = new Scanner(myObj);
		String data = myReader.nextLine();
		myReader.close();
		JSONObject obj = new JSONObject(data);
		Set<String> latinNames = obj.keySet();
		PlantDataSource[] source = { PlantDataSource.NRCS };
		for (String plant : latinNames) {
			String latin = null;
			String[] common = null;
			String description = "Description: ";
			boolean[] bloom = null;
			double light = -1;
			Moisture moisture = null;
			SoilTypes soilType = null;
			boolean nativ = false;
			boolean invade = false;
			Canopy canopy = null;
			String[] images = null;
			latin = plant;
			JSONArray im = obj.getJSONObject(plant).getJSONArray("images");
			int len = im.length();
			if (len != 0) {
				images = new String[len];
				for (int i = 0; i < len; i++) {
					images[i] = im.getString(i);
				}
			}
			if (obj.getJSONObject(plant).getBoolean("profile_present")) {
				common = new String[1];
				common[0] = obj.getJSONObject(plant).getString("common_name");
				if (obj.getJSONObject(plant).getString("native_status").contains("L48 N")) {
					nativ = true;
				} else if (obj.getJSONObject(plant).getString("native_status").contains("L48 I")) {
					nativ = false;
				}
				description = description + System.lineSeparator() + "Symbol: "
						+ obj.getJSONObject(plant).getString("symbol");
				description = description + System.lineSeparator() + "Group: "
						+ obj.getJSONObject(plant).getString("group");
				description = description + System.lineSeparator() + "Family: "
						+ obj.getJSONObject(plant).getString("family");
				description = description + System.lineSeparator() + "Duration: "
						+ obj.getJSONObject(plant).getString("duration");
				description = description + System.lineSeparator() + "Growth Habitat: "
						+ obj.getJSONObject(plant).getString("growth_habitat");
			}
			if (obj.getJSONObject(plant).getBoolean("characteristics_present")) {
				JSONObject morph = obj.getJSONObject(plant).getJSONObject("Morphology/Physiology");
				String ht = morph.getString("Height, Mature (feet)");
				if (!ht.equals("")) {
					double height = Double.valueOf(ht);
					if (height > 164) {
						canopy = Canopy.EMERGENT;
					} else if (height > 98) {
						canopy = Canopy.CANOPY;
					} else if (height > 16) {
						canopy = Canopy.UNDERSTORY;
					} else {
						canopy = Canopy.FLOOR;
					}
				}
				JSONObject growthReq = obj.getJSONObject(plant).getJSONObject("Growth Requirements");
				boolean[] st = { false, false, false };
				if (growthReq.getString("Adapted to Coarse Textured Soils").equals("Yes")) {
					st[1] = true;
				}
				if (growthReq.getString("Adapted to Fine Textured Soils").equals("Yes")) {
					st[0] = true;
				}
				if (growthReq.getString("Adapted to Medium Textured Soils").equals("Yes")) {
					st[2] = true;
				}
				if (st[0] && st[1] && st[2]) {
					soilType = SoilTypes.ANY;
				} else if (st[0] && st[1]) {
					soilType = SoilTypes.CLAY;
				} else if (st[0] && st[2]) {
					soilType = SoilTypes.LOAMY;
				} else if (st[1] && st[2]) {
					soilType = SoilTypes.LOAMY;
				} else if (st[0]) {
					soilType = SoilTypes.CLAY;
				} else if (st[1]) {
					soilType = SoilTypes.SANDY;
				} else if (st[2]) {
					soilType = SoilTypes.LOAMY;
				}
				String moist = growthReq.getString("Moisture Use");
				switch (moist) {
				case "Low":
					moisture = Moisture.DRY;
					break;
				case "Medium":
					moisture = Moisture.MOIST;
					break;
				case "High":
					moisture = Moisture.DAMP;
					break;
				}
				String shade = growthReq.getString("Shade Tolerance");
				switch (shade) {
				case "Tolerant":
					light = .33;
					break;
				case "Intolerant":
					light = 1;
					break;
				case "Intermediate":
					light = .67;
					break;
				}
				JSONObject reproduce = obj.getJSONObject(plant).getJSONObject("Reproduction");
				String bp = reproduce.getString("Bloom Period");
				switch (bp) {
				case "Spring":
				case "Early Spring":
				case "Mid Spring":
				case "Late Spring":
					bloom = bloomCalc("Spring", "seasons");
					break;
				case "Early Summer":
				case "Mid Summer":
				case "Late Summer":
				case "Summer":
					bloom = bloomCalc("Summer", "seasons");
					break;
				case "Fall":
					bloom = bloomCalc("Fall", "seasons");
				case "Winter":
				case "Late Winter":
					bloom = bloomCalc("Winter", "seasons");
					break;
				}
				JSONObject sus = obj.getJSONObject(plant).getJSONObject("Suitability/Use");
				description = description + System.lineSeparator() + "Height at 20 Years, Maximum (feet): "
						+ morph.getString("Height at 20 Years, Maximum (feet)");
				description = description + System.lineSeparator() + "Height, Mature (feet): "
						+ morph.getString("Height, Mature (feet)");
				description = description + System.lineSeparator() + "Shape and Orientation: "
						+ morph.getString("Shape and Orientation");
				description = description + System.lineSeparator() + "Lifespan: " + morph.getString("Lifespan");
				description = description + System.lineSeparator() + "Flower Color: " + morph.getString("Flower Color");
				description = description + System.lineSeparator() + "Foliage Color: "
						+ morph.getString("Foliage Color");
				description = description + System.lineSeparator() + "Foliage Porosity Summer: "
						+ morph.getString("Foliage Porosity Summer");
				description = description + System.lineSeparator() + "Foliage Porosity Winter: "
						+ morph.getString("Foliage Porosity Winter");
				description = description + System.lineSeparator() + "Foliage Texture: "
						+ morph.getString("Foliage Texture");
				description = description + System.lineSeparator() + "Leaf Retention: "
						+ morph.getString("Leaf Retention");
				description = description + System.lineSeparator() + "Moisture Use: "
						+ growthReq.getString("Moisture Use");
				description = description + System.lineSeparator() + "Fruit/Seed Color: "
						+ morph.getString("Fruit/Seed Color");
				description = description + System.lineSeparator() + "Fruit/Seed Conspicuous: "
						+ morph.getString("Fruit/Seed Conspicuous");
				description = description + System.lineSeparator() + "Active Growth Period: "
						+ morph.getString("Active Growth Period");
				description = description + System.lineSeparator() + "Growth Form: " + morph.getString("Growth Form");
				description = description + System.lineSeparator() + "Growth Rate: " + morph.getString("Growth Rate");
				description = description + System.lineSeparator() + "After Harvest Regrowth Rate: "
						+ morph.getString("After Harvest Regrowth Rate");
				description = description + System.lineSeparator() + "Bloat: " + morph.getString("Bloat");
				description = description + System.lineSeparator() + "C:N Ratio: " + morph.getString("C:N Ratio");
				description = description + System.lineSeparator() + "Coppice Potential: "
						+ morph.getString("Coppice Potential");
				description = description + System.lineSeparator() + "Fall Conspicuous: "
						+ morph.getString("Fall Conspicuous");
				description = description + System.lineSeparator() + "Fire Resistant: "
						+ morph.getString("Fire Resistant");
				description = description + System.lineSeparator() + "Flower Conspicuous: "
						+ morph.getString("Flower Conspicuous");
				description = description + System.lineSeparator() + "Known Allelopath: "
						+ morph.getString("Known Allelopath");
				description = description + System.lineSeparator() + "Low Growing Grass: "
						+ morph.getString("Low Growing Grass");
				description = description + System.lineSeparator() + "Nitrogen Fixation: "
						+ morph.getString("Nitrogen Fixation");
				description = description + System.lineSeparator() + "Resprout Ability: "
						+ morph.getString("Resprout Ability");
				description = description + System.lineSeparator() + "Toxicity: " + morph.getString("Toxicity");
				description = description + System.lineSeparator() + "CaCO3 Tolerance: "
						+ growthReq.getString("CaCO3 Tolerance");
				description = description + System.lineSeparator() + "Cold Stratification Required: "
						+ growthReq.getString("Cold Stratification Required");
				description = description + System.lineSeparator() + "Drought Tolerance: "
						+ growthReq.getString("Drought Tolerance");
				description = description + System.lineSeparator() + "Fertility Requirement: "
						+ growthReq.getString("Fertility Requirement");
				description = description + System.lineSeparator() + "Fire Tolerance: "
						+ growthReq.getString("Fire Tolerance");
				description = description + System.lineSeparator() + "Frost Free Days, Minimum: "
						+ growthReq.getString("Frost Free Days, Minimum");
				description = description + System.lineSeparator() + "Hedge Tolerance: "
						+ growthReq.getString("Hedge Tolerance");
				description = description + System.lineSeparator() + "pH, Minimum: "
						+ growthReq.getString("pH, Minimum");
				description = description + System.lineSeparator() + "pH, Maximum: "
						+ growthReq.getString("pH, Maximum");
				description = description + System.lineSeparator() + "Planting Density per Acre, Minimum: "
						+ growthReq.getString("Planting Density per Acre, Minimum");
				description = description + System.lineSeparator() + "Planting Density per Acre, Maximum: "
						+ growthReq.getString("Planting Density per Acre, Maximum");
				description = description + System.lineSeparator() + "Precipitation, Minimum: "
						+ growthReq.getString("Precipitation, Minimum");
				description = description + System.lineSeparator() + "Precipitation, Maximum: "
						+ growthReq.getString("Precipitation, Maximum");
				description = description + System.lineSeparator() + "Root Depth, Minimum (inches): "
						+ growthReq.getString("Root Depth, Minimum (inches)");
				description = description + System.lineSeparator() + "Salinity Tolerance: "
						+ growthReq.getString("Salinity Tolerance");
				description = description + System.lineSeparator() + "Shade Tolerance: "
						+ growthReq.getString("Shade Tolerance");
				description = description + System.lineSeparator() + "Temperature, Minimum (F): "
						+ growthReq.getString("Temperature, Minimum (F)");
				description = description + System.lineSeparator() + "Commercial Availability: "
						+ reproduce.getString("Commercial Availability");
				description = description + System.lineSeparator() + "Fruit/Seed Abundance: "
						+ reproduce.getString("Fruit/Seed Abundance");
				description = description + System.lineSeparator() + "Fruit/Seed Period Begin: "
						+ reproduce.getString("Fruit/Seed Period Begin");
				description = description + System.lineSeparator() + "Fruit/Seed Period End: "
						+ reproduce.getString("Fruit/Seed Period End");
				description = description + System.lineSeparator() + "Fruit/Seed Persistence: "
						+ reproduce.getString("Fruit/Seed Persistence");
				description = description + System.lineSeparator() + "Propagated by Bare Root: "
						+ reproduce.getString("Propagated by Bare Root");
				description = description + System.lineSeparator() + "Propagated by Bulb: "
						+ reproduce.getString("Propagated by Bulb");
				description = description + System.lineSeparator() + "Propagated by Container: "
						+ reproduce.getString("Propagated by Container");
				description = description + System.lineSeparator() + "Propagated by Corm: "
						+ reproduce.getString("Propagated by Corm");
				description = description + System.lineSeparator() + "Propagated by Cuttings: "
						+ reproduce.getString("Propagated by Cuttings");
				description = description + System.lineSeparator() + "Propagated by Seed: "
						+ reproduce.getString("Propagated by Seed");
				description = description + System.lineSeparator() + "Propagated by Sod: "
						+ reproduce.getString("Propagated by Sod");
				description = description + System.lineSeparator() + "Propagated by Sprigs: "
						+ reproduce.getString("Propagated by Sprigs");
				description = description + System.lineSeparator() + "Propagated by Tubers: "
						+ reproduce.getString("Propagated by Tubers");
				description = description + System.lineSeparator() + "Seed per Pound: "
						+ reproduce.getString("Seed per Pound");
				description = description + System.lineSeparator() + "Seed Spread Rate: "
						+ reproduce.getString("Seed Spread Rate");
				description = description + System.lineSeparator() + "Seedling Vigor: "
						+ reproduce.getString("Seedling Vigor");
				description = description + System.lineSeparator() + "Small Grain: "
						+ reproduce.getString("Small Grain");
				description = description + System.lineSeparator() + "Vegetative Spread Rate: "
						+ reproduce.getString("Vegetative Spread Rate");
				description = description + System.lineSeparator() + "Berry/Nut/Seed Product: "
						+ sus.getString("Berry/Nut/Seed Product");
				description = description + System.lineSeparator() + "Christmas Tree Product: "
						+ sus.getString("Christmas Tree Product");
				description = description + System.lineSeparator() + "Fodder Product: "
						+ sus.getString("Fodder Product");
				description = description + System.lineSeparator() + "Fuelwood Product: "
						+ sus.getString("Fuelwood Product");
				description = description + System.lineSeparator() + "Lumber Product: "
						+ sus.getString("Lumber Product");
				description = description + System.lineSeparator() + "Naval Store Product: "
						+ sus.getString("Naval Store Product");
				description = description + System.lineSeparator() + "Nursery Stock Product: "
						+ sus.getString("Nursery Stock Product");
				description = description + System.lineSeparator() + "Palatable Browse Animal: "
						+ sus.getString("Palatable Browse Animal");
				description = description + System.lineSeparator() + "Palatable Graze Animal: "
						+ sus.getString("Palatable Graze Animal");
				description = description + System.lineSeparator() + "Palatable Human: "
						+ sus.getString("Palatable Human");
				description = description + System.lineSeparator() + "Post Product: " + sus.getString("Post Product");
				description = description + System.lineSeparator() + "Protein Potential: "
						+ sus.getString("Protein Potential");
				description = description + System.lineSeparator() + "Pulpwood Product: "
						+ sus.getString("Pulpwood Product");
				description = description + System.lineSeparator() + "Veneer Product: "
						+ sus.getString("Veneer Product");
			}
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade,
					source, images));
		}
		return result;
	}

	/**
	 * Helper function to generate boolean from string
	 * 
	 * @param yesNo - String containing one of the following String literals: "Yes"
	 *              or "No"
	 * @return boolean - "Yes" returns true and "No" returns false
	 */
	public static boolean getBool(String yesNo) {
		if (yesNo.equals("Yes")) {
			return true;
		}
		if (yesNo.equals("No")) {
			return false;
		}
		return false;
	}

	/**
	 * Helper function to process a plant's bloom time
	 * 
	 * @param bloom - string containing a specific time or a range of times
	 * @param arr   - Name of array to compare data in bloom to
	 * @return boolean[] - an array of 12 booleans referring to the 12 months of
	 *         bloom time
	 */
	public static boolean[] bloomCalc(String bloom, String arr) {
		boolean[] year = { false, false, false, false, false, false, false, false, false, false, false, false };
		// Following 12 lines standardize bloom String
		if (bloom.equals("May-June, Sept.")) {
			bloom = "May-September";
		}
		if (bloom.equals("July August")) {
			bloom = bloom.replace("July August", "July-August");
		}
		bloom = bloom.replace(".- varies by location", "");
		bloom = bloom.replace(".", "");
		bloom = bloom.replace(" ", "");
		bloom = bloom.replace("Late", "");
		bloom = bloom.replace("Early", "");
		bloom = bloom.trim();
		if (bloom.equals("--")) {
			return null;
		}
		String[] sections = bloom.split("-");
		for (int i = 0; i < sections.length; i++) {
			sections[i] = StringUtils.capitalize(sections[i]);
			if (sections[i].equals("Summer")) {
				arr = "seasons";
			}
			if (sections[i].equals("Apr")) {
				sections[i] = "April";
			}
			if (sections[i].equals("Jun")) {
				sections[i] = "June";
			}
			if (sections[i].equals("Jul")) {
				sections[i] = "July";
			}
			if (sections[i].equals("Aug")) {
				sections[i] = "August";
			}
			if (sections[i].equals("Oct")) {
				sections[i] = "October";
			}
		}
		if (sections.length == 1) {
			if (arr.equals("seasons")) {
				int ind = find(seasons, sections[0]);
				switch (ind) {
				case 0:
					for (int j = 0; j < 3; j++) {
						year[j] = true;
					}
					break;
				case 1:
					for (int j = 3; j < 6; j++) {
						year[j] = true;
					}
					break;
				case 2:
					for (int j = 6; j < 9; j++) {
						year[j] = true;
					}
					break;
				case 3:
					for (int j = 9; j < 12; j++) {
						year[j] = true;
					}
					break;
				}
			} else {
				int ind = find(months, sections[0]);
				year[ind] = true;
			}
		} else {
			if (arr.equals("seasons")) {
				int beg = 3 * find(seasons, sections[0]);
				int end = 3 + 3 * find(seasons, sections[1]);
				for (int i = beg; i < end; i++) {
					year[i] = true;
				}
			} else {
				int beg = find(months, sections[0]);
				int end = find(months, sections[1]) + 1;
				for (int i = beg; i < end; i++) {
					year[i] = true;
				}
			}
		}
		return year;
	}

	/**
	 * Takes in 2 boolean arrays of length 12. Each index corresponds to a month. It
	 * is used to combine bloomtimes
	 * 
	 * @param arr1 boolean array of length 12
	 * @param arr2 Another boolean array of length 12
	 * @return a boolean array where each index is true if either arr1 or arr2 had
	 *         true at that same index.
	 */
	public static boolean[] combineBoolArr(boolean[] arr1, boolean[] arr2) {
		boolean[] result = new boolean[12];
		for (int i = 0; i < 12; i++) {
			if (arr1[i] || arr2[i]) {
				result[i] = true;
			}
		}
		return result;
	}

	/**
	 * Helper function for bloomCalc to determine specific months or seasons
	 * 
	 * @param arr    - the specific static array that will be referenced
	 * @param target - the string within arr that is being found
	 * @return - returns the index of the first occurrence of the target. If target
	 *         is not found, it returns -1.
	 */
	public static int find(String[] arr, String target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Goes through all of the data from the imported files and removes any
	 * duplicates
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void merge() throws FileNotFoundException, IOException, ParseException {
		HashMap<String, Plant> merge = new HashMap<>();
		for (Plant p : loadFlora()) {
			merge.put(p.getLatinName(), p);
		}
		for (Plant p : loadNative()) {
			if (merge.get(p.getLatinName()) != null) {
				Plant in = merge.get(p.getLatinName());
				String latin = p.getLatinName();
				String[] pCommon = p.getCommonNames();
				String pDescription = p.getDescription();
				boolean[] pBloom = p.getBloomTime();
				double pLight = p.getLight();
				Moisture pMoisture = p.getMoisture();
				SoilTypes pSoilType = p.getSoilType();
				boolean pNativ = p.getDelawareNative();
				boolean pInvade = p.getInvasive();
				Canopy pCanopy = p.getCanopy();
				PlantDataSource[] pSource = p.getSource();
				String[] pImages = p.getImages();
				int pLen;
				if (pImages == null) {
					pLen = 0;
				} else {
					pLen = pImages.length;
				}
				String[] iCommon = in.getCommonNames();
				String iDescription = in.getDescription();
				boolean[] iBloom = in.getBloomTime();
				double iLight = in.getLight();
				Moisture iMoisture = in.getMoisture();
				SoilTypes iSoilType = in.getSoilType();
				boolean iNativ = in.getDelawareNative();
				boolean iInvade = in.getInvasive();
				Canopy iCanopy = in.getCanopy();
				PlantDataSource[] iSource = in.getSource();
				String[] iImages = in.getImages();
				int iLen;
				if (iImages == null) {
					iLen = 0;
				} else {
					iLen = iImages.length;
				}
				String[] images = new String[pLen + iLen];
				if (pCommon == null) {
					if (iCommon != null) {
						pCommon = iCommon;
					}
				} else {
					if (iCommon != null) {
						String[] all = new String[pCommon.length + iCommon.length];
						int count = 0;
						for (String c : pCommon) {
							all[count] = c;
							count++;
						}
						for (String c : iCommon) {
							all[count] = c;
							count++;
						}
						pCommon = all;
					}
				}
				pDescription = pDescription + iDescription.substring(iDescription.indexOf(":") + 3);
				if (pBloom == null) {
					pBloom = iBloom;
				} else if (iBloom != null) {
					for (int i = 0; i < iBloom.length; i++) {
						if (iBloom[i]) {
							pBloom[i] = true;
						}
					}
				}
				if (pLight == -1) {
					if (iLight == -1) {
						pLight = -1;
					} else {
						pLight = iLight;
					}
				} else {
					if (iLight != -1) {
						pLight = (iLight + pLight) / 2;
					}
				}
				if (pMoisture == null) {
					if (iMoisture != null) {
						pMoisture = iMoisture;
					}
				} else {
					if (iMoisture != null) {
						pMoisture = Moisture.values()[((pMoisture.ordinal() + iMoisture.ordinal()) / 2)];
					}
				}
				if (pSoilType == null) {
					if (iSoilType != null) {
						pSoilType = iSoilType;
					}
				} else {
					if (iSoilType != null) {
						pSoilType = SoilTypes.values()[((pSoilType.ordinal() + iSoilType.ordinal()) / 2)];
					}
				}
				if (pCanopy == null) {
					if (iCanopy != null) {
						pCanopy = iCanopy;
					}
				} else {
					if (iCanopy != null) {
						pCanopy = Canopy.values()[((pCanopy.ordinal() + iCanopy.ordinal()) / 2)];
					}
				}
				if (iNativ || pNativ) {
					pNativ = true;
				} else {
					pNativ = false;
				}
				if (iInvade || pInvade) {
					pInvade = true;
				} else {
					pInvade = false;
				}
				PlantDataSource newarr[] = new PlantDataSource[in.getSource().length + 1];
				for (int i = 0; i < in.getSource().length; i++) {
					newarr[i] = in.getSource()[i];
				}
				newarr[in.getSource().length] = pSource[0];
				int index = 0;
				for (int i = 0; i < pLen; i++) {
					images[index] = pImages[i];
					index++;
				}
				for (int i = 0; i < iLen; i++) {
					images[index] = iImages[i];
					index++;
				}
				merge.put(latin, new Plant(pCommon, latin, pDescription, pBloom, pLight, pMoisture, pSoilType, pCanopy,
						pNativ, pInvade, newarr, images));
				continue;
			}
			merge.put(p.getLatinName(), p);
		}

		for (Plant p : loadNRCS()) {
			if (merge.get(p.getLatinName()) != null) {
				Plant in = merge.get(p.getLatinName());
				String latin = p.getLatinName();
				String[] pCommon = p.getCommonNames();
				String pDescription = p.getDescription();
				boolean[] pBloom = p.getBloomTime();
				double pLight = p.getLight();
				Moisture pMoisture = p.getMoisture();
				SoilTypes pSoilType = p.getSoilType();
				boolean pNativ = p.getDelawareNative();
				boolean pInvade = p.getInvasive();
				Canopy pCanopy = p.getCanopy();
				PlantDataSource[] pSource = p.getSource();
				String[] pImages = p.getImages();
				int pLen;
				if (pImages == null) {
					pLen = 0;
				} else {
					pLen = pImages.length;
				}
				String[] iCommon = in.getCommonNames();
				String iDescription = in.getDescription();
				boolean[] iBloom = in.getBloomTime();
				double iLight = in.getLight();
				Moisture iMoisture = in.getMoisture();
				SoilTypes iSoilType = in.getSoilType();
				boolean iNativ = in.getDelawareNative();
				boolean iInvade = in.getInvasive();
				Canopy iCanopy = in.getCanopy();
				PlantDataSource[] iSource = in.getSource();
				String[] iImages = in.getImages();
				int iLen;
				if (iImages == null) {
					iLen = 0;
				} else {
					iLen = iImages.length;
				}
				String[] images = new String[pLen + iLen];
				if (pCommon == null) {
					if (iCommon != null) {
						pCommon = iCommon;
					}
				} else {
					if (iCommon != null) {
						String[] all = new String[pCommon.length + iCommon.length];
						int count = 0;
						for (String c : pCommon) {
							all[count] = c;
							count++;
						}
						for (String c : iCommon) {
							all[count] = c;
							count++;
						}
						pCommon = all;
					}
				}
				pDescription = pDescription + iDescription.substring(iDescription.indexOf(":") + 3);
				if (pBloom == null) {
					pBloom = iBloom;
				} else if (iBloom != null) {
					for (int i = 0; i < iBloom.length; i++) {
						if (iBloom[i]) {
							pBloom[i] = true;
						}
					}
				}
				if (pLight == -1) {
					if (iLight == -1) {
						pLight = -1;
					} else {
						pLight = iLight;
					}
				} else {
					if (iLight != -1) {
						pLight = (iLight + pLight) / 2;
					}
				}
				if (pMoisture == null) {
					if (iMoisture != null) {
						pMoisture = iMoisture;
					}
				} else {
					if (iMoisture != null) {
						pMoisture = Moisture.values()[((pMoisture.ordinal() + iMoisture.ordinal()) / 2)];
					}
				}
				if (pSoilType == null) {
					if (iSoilType != null) {
						pSoilType = iSoilType;
					}
				} else {
					if (iSoilType != null) {
						pSoilType = SoilTypes.values()[((pSoilType.ordinal() + iSoilType.ordinal()) / 2)];
					}
				}
				if (pCanopy == null) {
					if (iCanopy != null) {
						pCanopy = iCanopy;
					}
				} else {
					if (iCanopy != null) {
						pCanopy = Canopy.values()[((pCanopy.ordinal() + iCanopy.ordinal()) / 2)];
					}
				}
				if (iNativ || pNativ) {
					pNativ = true;
				} else {
					pNativ = false;
				}
				if (iInvade || pInvade) {
					pInvade = true;
				} else {
					pInvade = false;
				}
				PlantDataSource newarr[] = new PlantDataSource[in.getSource().length + 1];
				for (int i = 0; i < in.getSource().length; i++) {
					newarr[i] = in.getSource()[i];
				}
				newarr[in.getSource().length] = pSource[0];
				int index = 0;
				for (int i = 0; i < pLen; i++) {
					images[index] = pImages[i];
					index++;
				}
				for (int i = 0; i < iLen; i++) {
					images[index] = iImages[i];
					index++;
				}
				merge.put(latin, new Plant(pCommon, latin, pDescription, pBloom, pLight, pMoisture, pSoilType, pCanopy,
						pNativ, pInvade, newarr, images));
				continue;
			}
			merge.put(p.getLatinName(), p);
		}

		for (Plant p : loadSunny()) {
			if (merge.get(p.getLatinName()) != null) {
				Plant in = merge.get(p.getLatinName());
				String latin = p.getLatinName();
				String[] pCommon = p.getCommonNames();
				String pDescription = p.getDescription();
				boolean[] pBloom = p.getBloomTime();
				double pLight = p.getLight();
				Moisture pMoisture = p.getMoisture();
				SoilTypes pSoilType = p.getSoilType();
				boolean pNativ = p.getDelawareNative();
				boolean pInvade = p.getInvasive();
				Canopy pCanopy = p.getCanopy();
				PlantDataSource[] pSource = p.getSource();
				String[] pImages = p.getImages();
				int pLen;
				if (pImages == null) {
					pLen = 0;
				} else {
					pLen = pImages.length;
				}
				String[] iCommon = in.getCommonNames();
				String iDescription = in.getDescription();
				boolean[] iBloom = in.getBloomTime();
				double iLight = in.getLight();
				Moisture iMoisture = in.getMoisture();
				SoilTypes iSoilType = in.getSoilType();
				boolean iNativ = in.getDelawareNative();
				boolean iInvade = in.getInvasive();
				Canopy iCanopy = in.getCanopy();
				PlantDataSource[] iSource = in.getSource();
				String[] iImages = in.getImages();
				int iLen;
				if (iImages == null) {
					iLen = 0;
				} else {
					iLen = iImages.length;
				}
				String[] images = new String[pLen + iLen];
				if (pCommon == null) {
					if (iCommon != null) {
						pCommon = iCommon;
					}
				} else {
					if (iCommon != null) {
						String[] all = new String[pCommon.length + iCommon.length];
						int count = 0;
						for (String c : pCommon) {
							all[count] = c;
							count++;
						}
						for (String c : iCommon) {
							all[count] = c;
							count++;
						}
						pCommon = all;
					}
				}
				pDescription = pDescription + iDescription.substring(iDescription.indexOf(":") + 3);
				if (pBloom == null) {
					pBloom = iBloom;
				} else if (iBloom != null) {
					for (int i = 0; i < iBloom.length; i++) {
						if (iBloom[i]) {
							pBloom[i] = true;
						}
					}
				}
				if (pLight == -1) {
					if (iLight == -1) {
						pLight = -1;
					} else {
						pLight = iLight;
					}
				} else {
					if (iLight != -1) {
						pLight = (iLight + pLight) / 2;
					}
				}
				if (pMoisture == null) {
					if (iMoisture != null) {
						pMoisture = iMoisture;
					}
				} else {
					if (iMoisture != null) {
						pMoisture = Moisture.values()[((pMoisture.ordinal() + iMoisture.ordinal()) / 2)];
					}
				}
				if (pSoilType == null) {
					if (iSoilType != null) {
						pSoilType = iSoilType;
					}
				} else {
					if (iSoilType != null) {
						pSoilType = SoilTypes.values()[((pSoilType.ordinal() + iSoilType.ordinal()) / 2)];
					}
				}
				if (pCanopy == null) {
					if (iCanopy != null) {
						pCanopy = iCanopy;
					}
				} else {
					if (iCanopy != null) {
						pCanopy = Canopy.values()[((pCanopy.ordinal() + iCanopy.ordinal()) / 2)];
					}
				}
				if (iNativ || pNativ) {
					pNativ = true;
				} else {
					pNativ = false;
				}
				if (iInvade || pInvade) {
					pInvade = true;
				} else {
					pInvade = false;
				}
				PlantDataSource newarr[] = new PlantDataSource[in.getSource().length + 1];
				for (int i = 0; i < in.getSource().length; i++) {
					newarr[i] = in.getSource()[i];
				}
				newarr[in.getSource().length] = pSource[0];
				int index = 0;
				for (int i = 0; i < pLen; i++) {
					images[index] = pImages[i];
					index++;
				}
				for (int i = 0; i < iLen; i++) {
					images[index] = iImages[i];
					index++;
				}
				merge.put(latin, new Plant(pCommon, latin, pDescription, pBloom, pLight, pMoisture, pSoilType, pCanopy,
						pNativ, pInvade, newarr, images));
				continue;
			}
			merge.put(p.getLatinName(), p);
		}
		int count = 0;
		plants = new Plant[merge.keySet().size()];
		for (String key : merge.keySet()) {
			plants[count] = merge.get(key);
			count++;
		}
	}

	/**
	 * Adds all the plants from the local files to a single array of Plant 
	 * objects.
	 * 
	 * @return An array that contains all of the plants from the local files
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Plant> getPlants() throws FileNotFoundException, IOException, ParseException {
		merge();
		ArrayList<Plant> list = new ArrayList<>(Arrays.asList(plants));
		return list;
	}
}
