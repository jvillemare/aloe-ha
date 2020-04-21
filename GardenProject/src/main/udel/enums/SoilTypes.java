package main.udel.enums;

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
 * Created PH ranges: 
 *   - acidic = lower PH 
 *   - alkaline = higher PH
 *   - adaptable = PH level can be adjusted by adding certain materials
 * 
 * Process follows this website's plant soil PH:
 * https://mtcubacenter.org/plants/moonbeam-whorled-tickseed/
 *
 * Contains different Soil Types 
 * Used in Questionnaire and Plant Info
 *
 * @author Team 0
 */
public enum SoilTypes {

	CLAY("clay"), 
	SANDY("sandy"), 
	LOAMY("loamy"),
	ANY("any soil");
	
	private String name = null;
	//private String ph = null;
	
	private SoilTypes(String s /*, String p */) {
		name = s;
		//ph = p;
		
	}
	
	public String getName() {
		return name;
	}
	
	/*public String getPHType() {
		return ph;
	}*/
	
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
