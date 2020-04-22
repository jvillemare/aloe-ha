package main.udel.enums;

/**
 * Contains the different sunlight and shade levels. Used in Questionnaire and 
 * Plant Info.
 * 
 * 
 * @author Team 0
 */
public enum Sunlight {
	
	FULLSUN("Full-sun"),
	PARTIALSHADE("Partial-shade"),
	PARTIALSUN("Partial-sun"),
	FULLSHADE("Full-shade");
	
	private String sunlight = null;

	private Sunlight(String sun){
		sunlight = sun;
	}
	public String getSunlight() {
		return sunlight;
	}
	

}
