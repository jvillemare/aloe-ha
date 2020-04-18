package main.udel.enums;

/**
 * Borrowed from 
 * https://learn.eartheasy.com/articles/know-your-garden-soil-how-to-make-the-most-of-your-soil-type/
 *
 * @author Team 0
 */
public enum SoilTypes {
	
	CLAY("clay"),
	SANDY("sandy"), 
	SILTY("silty"), 
	PEATY("peaty"), 
	CHALKY("chalky"), 
	LOAMY("loamy");
	
	
private String soilType = null;
	
	private SoilTypes(String s){
		soilType = s;
	}
	public String getSoilType() {
		return soilType;
	}

}
