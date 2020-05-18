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
	 * 100% full sun all the time.
	 */
	FULLSUN("Full-sun"),
	
	/**
	 * 66% sun most of the time.
	 */
	PARTIALSHADE("Partial-shade"),
	
	/**
	 * 33% sun some of the time.
	 */
	PARTIALSUN("Partial-sun"),
	
	/**
	 * 0% sun none of the time.
	 */
	FULLSHADE("Full-shade");
	
	private String sunlight = null;

	private Sunlight(String sun){
		sunlight = sun;
	}
	public String getSunlight() {
		return sunlight;
	}
	

}
