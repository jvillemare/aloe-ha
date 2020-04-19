package main.udel.enums;

/**
 * Borrowed from 
 * https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/
 *
 * @author Team 0
 */
public enum SoilTypes {
	
	CLAY("clay", "adaptable"), SANDY("sandy", "adaptable"), SILTY("silty", "adaptable"), 
	PEATY("peaty", "acidic"), CHALKY("chalky", "alkaline"), LOAMY("loamy", "adaptable");
	
	private String name = null;
	private String ph = null;
	
	private SoilTypes(String s, String p) {
		name = s;
		ph = p;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getPHType() {
		return ph;
	}
	

}
