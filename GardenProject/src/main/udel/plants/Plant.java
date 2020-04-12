package plants;

import enums.Moisture;
import enums.Plants;
import enums.Seasons;
import enums.SoilTypes;

/**
 * For every plant, there is a corresponding Plant object 
 * 
 * @author Team 0
 */
public class Plant {

	/**
	 * Name of the plant.
	 */
	Plants name;
	
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
	
	Plant(Plants name, String description, Seasons bloomTime, double light,
			Moisture moisture, SoilTypes soilType) {
		this.name = name;
		this.description = description;
		this.bloomTime = bloomTime;
		this.light = light;
		this.moisture = moisture;
		this.soilType = soilType;
	}
	
}
