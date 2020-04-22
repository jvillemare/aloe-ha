package udel.GardenProject.plants;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.Seasons;
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
	 * Common names of a plant.
	 */
	String[] commonNames;
	
	/**
	 * Linnaeus <code>Genus species</code> name of the plant.
	 */
	String latinName;
	
	/**
	 * Short description of the visual characteristics of the plant, or any
	 * other notable aspects.
	 */
	String description;
	
	/**
	 * When the plant reaches peak bloom.
	 */
	Seasons bloomTime;
	
	/**
	 * Percentage of light that plant normally receives from the sun during the
	 * average sun light hours of a day.
	 * 
	 * From 0.0 to 1.0 
	 */
	double light;
	
	Moisture moisture;
	
	SoilTypes soilType;
	
	private Canopy canopy;
	
	public Plant(String[] commonNames, String latinName, String description, 
			Seasons bloomTime, double light, Moisture moisture, 
			SoilTypes soilType, Canopy canopy) {
		this.commonNames = commonNames;
		this.latinName = latinName;
		this.description = description;
		this.bloomTime = bloomTime;
		this.light = light;
		this.moisture = moisture;
		this.soilType = soilType;
		this.canopy = canopy;
	}

	public String[] getCommonNames() {
		return commonNames;
	}
	
	public String getLatinName() {
		return latinName;
	}

	public String getDescription() {
		return description;
	}

	public Seasons getBloomTime() {
		return bloomTime;
	}

	public double getLight() {
		return light;
	}

	public Moisture getMoisture() {
		return moisture;
	}

	public SoilTypes getSoilType() {
		return soilType;
	}
	
	public Canopy getCanopy() {
		return this.canopy;
	}
	
}
