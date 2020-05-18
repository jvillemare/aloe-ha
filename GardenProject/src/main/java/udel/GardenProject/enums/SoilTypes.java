package udel.GardenProject.enums;

/**
 * SoilTypes from 
 * <a href="https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/">EarthEasy.com</a>
 * <br><br>
 * Acidity Levels from:
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/Soil_pH">Wikipedia</a></li>
 * <li><a href="https://greenharvest.com.au/GreenGardenNotes/UnderstandingSoilpH.html">GreenHarvest.com.au</a></li>
 * <li><a href="https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/">EarthEasy.com</a></li>
 * <li><a href="https://www.rhs.org.uk/advice/profile?pid=762">RHS.org.uk</a></li>
 * </ul>
 *
 * Contains different Soil Types, used in Questionnaire and Plant Info.
 *
 * @version 1.0
 * @author Team 0
 */
public enum SoilTypes {

	CLAY("Clay"), 
	SANDY("Sandy"), 
	LOAMY("Loamy"),
	ANY("Any Soil");
	
	/**
	 * Friendly name of the enum for the UI.
	 */
	private String name = null;
	
	/**
	 * Friendly name of the enum for the UI.
	 * @param s	Friendly name of the enum for the UI.
	 */
	private SoilTypes(String s /*, String p */) {
		name = s;
	}
	
	/**
	 * Friendly name of the enum for the UI.
	 * @return	Friendly name of the enum for the UI.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Converts a UI text string back to an enum, similar to 
	 * <code>valueOf()</code>.
	 * @param s String, typically gathered from UI.
	 * @return corresponding SoilTypes, or SoilTypes.ANY if there's no match.
	 */
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
