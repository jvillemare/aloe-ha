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
	FULLSUN("Full-sun"),
	
	/**
	 * 66% sun most of the time, <code>0.5</code> to <code>0.9</code>
	 */
	PARTIALSHADE("Partial-shade"),
	
	/**
	 * 33% sun some of the time, <code>0.3</code> to <code>0.5</code>
	 */
	PARTIALSUN("Partial-sun"),
	
	/**
	 * 0% sun none of the time, <code>0.0</code> to <code>0.3</code>.
	 */
	FULLSHADE("Full-shade"),

	/**
	 * Unknown level of shade, <code>-1.0</code>
	 */
	ANY("My Plot has different levels");
	
	private String sunlight = null;

	private Sunlight(String sun){
		sunlight = sun;
	}
	public String getSunlight() {
		return sunlight;
	}
	
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
