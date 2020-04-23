package udel.GardenProject.plants;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
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
	private String[] commonNames;
	
	/**
	 * Linnaeus <code>Genus species</code> name of the plant.
	 */
	private String latinName;
	
	/**
	 * Short description of the visual characteristics of the plant, or any
	 * other notable aspects.
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
	 * Moisture level required in soil for plant
	 */
	private Moisture moisture;
	
	/**
	 * Soil type required for plant
	 */
	private SoilTypes soilType;
	
	/**
	 *Height of plant
	 */
	private Canopy canopy;
	
	/**
	 * Delaware native plant status
	 */
	private boolean delawareNative;
	
	/**
	 * Invasive species status
	 */
	private boolean invasive;
	
	/**
	 * Source(s) where plant data came from 
	 */
	private PlantDataSource[] source;
	
	public Plant(String[] commonNames, String latinName, String description, 
			boolean[] bloomTime, double light, Moisture moisture, 
			SoilTypes soilType, Canopy canopy, boolean delawareNative, boolean invasive, PlantDataSource[] source) {
		this.commonNames = commonNames;
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

	public boolean[] getBloomTime() {
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
	
	public boolean getDelawareNative() {
		return this.delawareNative;
	}
	
	public boolean getInvasive() {
		return this.invasive;
	}
	
	public PlantDataSource[] getSource() {
		return this.source;
	}
	
}