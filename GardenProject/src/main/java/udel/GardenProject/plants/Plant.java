package udel.GardenProject.plants;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
import udel.GardenProject.enums.SoilTypes;

/**
 * For every plant, there is a corresponding Plant object that contains as much
 * information that can be loaded by PlantLoader.
 * 
 * Plant data was gathered from public domain sources using Python scripts, and
 * the plant data is stored as <code>.json</code> files in
 * <code>main.udel.plants.data</code>
 * 
 * @author Team 0
 */
public class Plant {

	/**
	 * Common names of a plant. Can be null, or empty.
	 */
	private String[] commonNames;

	/**
	 * Linnaeus <code>Genus species</code> name of the plant.
	 */
	private String latinName;

	/**
	 * Short description of the visual characteristics of the plant, or any other
	 * notable aspects.
	 */
	private String description;

	/**
	 * When the plant reaches peak bloom.
	 */
	private boolean[] bloomTime;

	/**
	 * Percentage of light that plant normally receives from the sun during the
	 * average sun light hours of a day.
	 * 
	 * From 0.0 to 1.0
	 */
	private double light;

	/**
	 * Moisture level required in soil for plant.
	 */
	private Moisture moisture;

	/**
	 * Soil type required for plant.
	 */
	private SoilTypes soilType;

	/**
	 * Height of plant.
	 */
	private Canopy canopy;

	/**
	 * Delaware native plant status.
	 */
	private boolean delawareNative;

	/**
	 * Invasive species status.
	 */
	private boolean invasive;

	/**
	 * Source(s) where plant data came from.
	 */
	private PlantDataSource[] source;
	
	/**
	 * Strings of HTTP links to images of this plant.
	 */
	private String[] images;

	/**
	 * Constructor.
	 * @param commonNames		Alternative friendly names for plants.
	 * @param latinName			Official Linnaeus name, 
	 * 							<code>Genus species</code>.
	 * @param description		Non-standard description with all extra
	 * 							information gathered from JSON files.
	 * @param bloomTime			12-element boolean array, one for each month.
	 * 							True if it blooms during that month, false if
	 * 							not.	
	 * @param light				From 0.0 to 1.0, percentage of light the plant
	 * 							requires.			
	 * @param moisture			See <code>Moisture</code> enum.
	 * @param soilType			See <code>SoilType</code> enum.
	 * @param canopy			See <code>Canopy</code> enum.
	 * @param delawareNative	True if the plant is native to Delaware, U.S.A.,
	 * 							false if not.
	 * @param invasive			True if invasive, false if not.
	 * @param source			See <code>PlantDataSource</code> enum.
	 * @param images			String array of HTTP links to images of plant.
	 */
	public Plant(String[] commonNames, String latinName, String description, boolean[] bloomTime, double light,
			Moisture moisture, SoilTypes soilType, Canopy canopy, boolean delawareNative, boolean invasive,
			PlantDataSource[] source, String[] images) {
		this.commonNames = commonNames;
		// TODO: Add function here to process and fix latin name?
		this.latinName = latinName;
		this.description = description;
		this.bloomTime = bloomTime;
		this.light = light;
		this.moisture = moisture;
		this.soilType = soilType;
		this.canopy = canopy;
		this.delawareNative = delawareNative;
		this.invasive = invasive;
		this.source = source;
		this.images = images;
	}

	/**
	 * Getter.
	 * @return	All the common names for a plant. Not guranteed to be unique.
	 */
	public String[] getCommonNames() {
		return commonNames;
	}

	/**
	 * Getter.
	 * @return 	A plant's unique latin name with genus capitalized and species 
	 * 			all lower case in traditional Linnaeus classifcation. Example:
	 * 			<code>Pinus pine</code>.
	 */
	public String getLatinName() {
		return latinName;
	}
	
	/**
	 * Convert a friendly name or other type of String to a two-word Latin name.
	 * @param friendlyName
	 * @return
	 */
	public static String trimToLatinName(String friendlyName) {
		friendlyName = friendlyName.trim().toLowerCase();
		
		// remove any extra stuff after the second word
		if(StringUtils.countMatches(friendlyName, " ") > 1)
			friendlyName = friendlyName.substring(
					0, 
					friendlyName.indexOf(" ", friendlyName.indexOf(" ") + 1));
		
		// capitalize the first letter of the genus
		friendlyName = friendlyName.substring(0, 1).toUpperCase() +
				friendlyName.substring(1);
		
		return friendlyName;
	}
	
	/**
	 * Getter.
	 * @return Latin name plus the first common name in parentheses.
	 */
	public String getFriendlyName() {
		String friendlyName = this.latinName;
		
		if(this.commonNames != null)
			if(this.commonNames.length != 0)
				friendlyName += " (" + this.commonNames[0] + ")";
		
		return friendlyName;
	}

	/**
	 * Getter.
	 * @return	Long description of all miscellaneous plant data.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter.
	 * @return	When the plant blooms by month (12 elements). True if yes, false
	 * 			if not.
	 */
	public boolean[] getBloomTime() {
		return bloomTime;
	}

	/**
	 * Getter.
	 * @return	Light as a decimal percentage.
	 */
	public double getLight() {
		return light;
	}

	/**
	 * Getter.
	 * @return	Moisture enum.
	 */
	public Moisture getMoisture() {
		return moisture;
	}

	/**
	 * Getter.
	 * @return	SoilType enum.
	 */
	public SoilTypes getSoilType() {
		return soilType;
	}

	/**
	 * Getter.
	 * @return	Canopy enum.
	 */
	public Canopy getCanopy() {
		return this.canopy;
	}

	/**
	 * Getter.
	 * @return	If this plant is a Delaware native or not.
	 */
	public boolean getDelawareNative() {
		return this.delawareNative;
	}

	/**
	 * Getter.
	 * @return	If this plant is invasive to North America or not.
	 */
	public boolean getInvasive() {
		return this.invasive;
	}

	/**
	 * Getter.
	 * @return	The sources this plant was acquired from. Guaranteed to be one,
	 * 			and may be some or all data sources.
	 */
	public PlantDataSource[] getSource() {
		return this.source;
	}
	
	/**
	 * Getter.
	 * @return	HTTP links to images of plants.
	 */
	public String[] getImages() {
		return this.images;
	}
	
	/**
	 * Trimmed version of the URL for the images of Plants, purpose for copyright.
	 * 
	 * @param url
	 * @return trimmed version of the url
	 * @throws MalformedURLException 
	 */
	public static String getImageSourceDomain(String url) throws MalformedURLException {
		URL u = new URL(url);
		
		String trim;
		trim = u.getAuthority();
		
		return trim;
	}

}