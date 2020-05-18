package udel.GardenProject.enums;

/**
 * Contains the different sunlight and shade levels. Used in Questionnaire and 
 * Plant Info.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum Sunlight {
	
	/**
	 * 100% full sun all the time, <code>0.9</code> to <code>1.0</code>
	 */
	FULLSUN("Full-sun", 0.9),
	
	/**
	 * 66% sun most of the time, <code>0.5</code> to <code>0.9</code>
	 */
	PARTIALSHADE("Partial-shade", 0.5),
	
	/**
	 * 33% sun some of the time, <code>0.3</code> to <code>0.5</code>
	 */
	PARTIALSUN("Partial-sun", 0.3),
	
	/**
	 * 0% sun none of the time, <code>0.0</code> to <code>0.3</code>.
	 */
	FULLSHADE("Full-shade", 0.0),

	/**
	 * Unknown level of shade, <code>-1.0</code>
	 */
	ANY("My Plot has different levels", 0.8); // assume 80% of light a day; average
	
	/**
	 * Friendly UI name.
	 */
	private String sunlight = null;
	
	/**
	 * Minimum amount of light this enum gurantees a plot will have, on average.
	 */
	private double minimumLight;

	/**
	 * Constructor
	 * @param sun				Friendly UI name.
	 * @param minimumLight		Minimum amount of light this enum gurantees a 
	 * 							plot will have, on average.
	 */
	private Sunlight(String sun, double minimumLight){
		this.sunlight = sun;
		this.minimumLight = minimumLight;
	}
	
	/**
	 * Getter.
	 * @return	Friendly UI name.
	 */
	public String getSunlight() {
		return this.sunlight;
	}
	
	/**
	 * Getter.
	 * @return	Minimum amount of light this enum gurantees a plot will have, 
	 * 			on average.
	 */
	public double getMinimumLight() {
		return this.minimumLight;
	}
	
	/**
	 * Convert a double percentage to a Sunlight enum object.
	 * @param percentage	as a double between 0.0 and 1.0
	 * @return	corresponding Sunlight enum if valid, or any if invalid.
	 */
	public static Sunlight getSunlightByDouble(double percentage) {
		if(percentage >= 0.9 && percentage <= 1.0) {
			return Sunlight.FULLSUN;
		}else if(percentage > 0.5 && percentage < 0.9) {
			return Sunlight.PARTIALSHADE;
		}else if(percentage >= 0.3 && percentage <= 0.5) {
			return Sunlight.PARTIALSUN;
		}else if(percentage >= 0.0 && percentage < 0.3) {
			return Sunlight.FULLSHADE;
		}else {
			return Sunlight.ANY;
		}
	}
	
}
