package udel.GardenProject.enums;

/**
 * SoilTypes from 
 * https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/
 * 
 * Acidity Levels from:
 * https://en.wikipedia.org/wiki/Soil_pH
 * https://greenharvest.com.au/GreenGardenNotes/UnderstandingSoilpH.html
 * https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/
 * https://www.rhs.org.uk/advice/profile?pid=762
 *
 * Contains different Soil Types 
 * Used in Questionnaire and Plant Info
 *
 * @author Team 0
 */
public enum SoilTypes {

	CLAY("Clay"), 
	SANDY("Sandy"), 
	LOAMY("Loamy"),
	ANY("Any Soil");
	
	private String name = null;
	
	private SoilTypes(String s /*, String p */) {
		name = s;
		
	}
	
	public String getName() {
		return name;
	}
	
	public SoilTypes getSoilTexture(String s) {
		if (s.equalsIgnoreCase("Clay")) {
			return CLAY;
		}else if (s.equalsIgnoreCase("Sandy")) {
			return SANDY;
		}else if (s.equalsIgnoreCase("Loamy")) {
			return LOAMY;
		}else {
			return ANY;
		}
	}
	
	
	
}
