package udel.GardenProject.enums;

/**
 * Contains the different sunlight and shade levels. Used in Questionnaire and 
 * Plant Info.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum Sunlight {
	
	FULLSUN("Full Sun"), //0.9-1.0
	PARTIALSHADE("Partial Shade"), //0.5-0.9
	PARTIALSUN("Partial Sun"), //3.0-5.0
	FULLSHADE("Full Shade"), // 0.0-3.0
	ANY("My Plot has different levels"); //-1.0
	
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
