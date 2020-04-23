package udel.GardenProject.plants;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.SoilTypes;

/**
 * Load all the plants from local file databases.
 * 
 * @author Team 0
 */
public class PlantLoader {

	public static final String floraPath = "src/main/resources/plantData/udel-flora.json";
	public static final String sunnyPath = "src/main/resources/plantData/sunny-edge-plants-data.json";
	public static final String nativePath = "src/main/resources/plantData/native-plant-center.json";

	private static final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private static final String[] seasons = { "Winter", "Spring", "Summer", "Fall" };
	private static ArrayList<Plant> plants = new ArrayList<>();

	/**
	 * Loads plants from local file Udel-Flora.json
	 * 
	 * Default plant attributes: commonNames: null latinName: given latinName in
	 * file description: "Description: " bloomTime: null light: -1 moisture: null
	 * soilType: null canopy: null delawareNative: false
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
			latin = plant;
			String com = obj.getJSONObject(plant).getString("Synonym");
			if (com.equals("--")) {
				common = null;
			} else {
				common = com.split(";");
			}
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
			invade = getBool(obj.getJSONObject(plant).getString("Invasive"));
			nativ = getBool(obj.getJSONObject(plant).getString("Native Plant"));
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade));

		}
		return result;
	}

	/**
	 * Loads plants from local file native-plant-center.json
	 * 
	 * Default plant attributes: commonNames: null latinName: given latinName in
	 * file description: "Description: " bloomTime: null light: -1 moisture: null
	 * soilType: null canopy: null delawareNative: false
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
			latin = plant;
			JSONArray alias = obj.getJSONObject(plant).getJSONArray("alias");
			List<String> comm = new ArrayList<>();
			for (int i = 0; i < alias.length(); i++) {
				comm.add(alias.getString(i));
			}
			if (obj.getJSONObject(plant).getString("states").contains("DE")) {
				nativ = true;
			}
			common = comm.toArray(new String[0]);
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
				case "Clay":
					soilType = SoilTypes.CLAY;
					break;
				case "Loamy":
					soilType = SoilTypes.LOAMY;
					break;
				case "Sandy":
					soilType = SoilTypes.SANDY;
					break;
				case "Clay, Loamy, Sandy":
					soilType = SoilTypes.ANY;
					break;
				case "Loamy, Sandy":
					soilType = SoilTypes.LOAMY;
					break;
				}
			}
			if (obj.getJSONObject(plant).has("soil moisture")) {
				String soM = obj.getJSONObject(plant).getString("soil moisture");
				switch (soM) {
				case "Dry":
					moisture = Moisture.DRY;
					break;
				case "Moist":
					moisture = Moisture.MOIST;
					break;
				case "Moist, Wet":
					moisture = Moisture.MOIST_DAMP;
					break;
				case "Dry, Moist":
					moisture = Moisture.DRY_MOIST;
					break;
				case "Dry, Moist, Wet":
					moisture = Moisture.MOIST;
					break;
				case "Flooded, Moist, Wet":
					moisture = Moisture.DAMP;
					break;
				case "Wet":
					moisture = Moisture.DAMP;
					break;
				case "Dry, Flooded, Moist, Wet":
					moisture = Moisture.DAMP;
					break;
				case "Flooded, Wet":
					moisture = Moisture.DAMP;
					break;
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
			if (obj.getJSONObject(plant).get("image").getClass().equals(String.class)) {
				description = description + System.lineSeparator() + "Image Link: "
						+ obj.getJSONObject(plant).getString("image");
			} else {
				JSONArray im = obj.getJSONObject(plant).getJSONArray("image");
				for (int i = 0; i < im.length(); i++) {
					String ima = im.getString(i);
					description = description + System.lineSeparator() + "Image Link: " + ima;
				}
			}
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade));
		}
		return result;
	}

	/**
	 * Loads plants from local file sunny-edge-plants-data.json
	 * 
	 * Default plant attributes: commonNames: null latinName: given latinName in
	 * file description: "Description: " bloomTime: null light: -1 moisture: null
	 * soilType: null canopy: null delawareNative: false
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
			case "sun":
				light = 1;
				break;
			case "sun/ptshade":
				light = .83;
				break;
			case "sun/shade":
			case "ptshade":
				light = .67;
				break;
			case "sun/ptsun":
				light = .83;
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
			result.add(new Plant(common, latin, description, bloom, light, moisture, soilType, canopy, nativ, invade));
		}
		return result;
	}

	/**
	 * Helper function to generate boolean from string
	 * 
	 * @param yesNo - String containing one of the following string literals: "Yes"
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
				String[] iCommon = in.getCommonNames();
				String iDescription = in.getDescription();
				boolean[] iBloom = in.getBloomTime();
				double iLight = in.getLight();
				Moisture iMoisture = in.getMoisture();
				SoilTypes iSoilType = in.getSoilType();
				boolean iNativ = in.getDelawareNative();
				boolean iInvade = in.getInvasive();
				Canopy iCanopy = in.getCanopy();
				if (pCommon == null) {
					pCommon = iCommon;
				} else if (iCommon == null) {
					pCommon = null;
				} else {
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
					if (iMoisture != null) {
						pSoilType = iSoilType;
					}
				} else {
					if (iMoisture != null) {
						pCanopy = Canopy.values()[((pCanopy.ordinal() + iCanopy.ordinal()) / 2)];
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
				merge.put(latin, new Plant(pCommon, latin, pDescription, pBloom, pLight, pMoisture, pSoilType, pCanopy,
						pNativ, pInvade));
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
				String[] iCommon = in.getCommonNames();
				String iDescription = in.getDescription();
				boolean[] iBloom = in.getBloomTime();
				double iLight = in.getLight();
				Moisture iMoisture = in.getMoisture();
				SoilTypes iSoilType = in.getSoilType();
				boolean iNativ = in.getDelawareNative();
				boolean iInvade = in.getInvasive();
				Canopy iCanopy = in.getCanopy();
				if (pCommon == null) {
					pCommon = iCommon;
				} else if (iCommon == null) {
					pCommon = null;
				} else {
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
					if (iMoisture != null) {
						pSoilType = iSoilType;
					}
				} else {
					if (iMoisture != null) {
						pCanopy = Canopy.values()[((pCanopy.ordinal() + iCanopy.ordinal()) / 2)];
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
				merge.put(latin, new Plant(pCommon, latin, pDescription, pBloom, pLight, pMoisture, pSoilType, pCanopy,
						pNativ, pInvade));
				continue;
			}
			merge.put(p.getLatinName(), p);
		}
		for (String key : merge.keySet()) {
			plants.add(merge.get(key));
		}
	}

	/**
	 * Adds all the plants from the local files to a single ArrayList<Plant>
	 * 
	 * @return an ArrayList<Plant> that contains all of the plants from the local
	 *         files
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Plant> getPlants() throws FileNotFoundException, IOException, ParseException {
		merge();
		return plants;
	}

}
