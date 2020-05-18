package udel.GardenProject.enums;

/**
 * Specifies where the data for a Plant originated from, and a friendly name of
 * that database.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum PlantDataSource {
	
	/**
	 * Plant data originated from the 
	 * <a href="http://www.wrc.udel.edu/de-flora/">University of Delaware Flora 
	 * Database</a>.
	 */
	UDEL("University of Delaware Flora Database"), 
	
	/**
	 * Plant data originated from the 
	 * <a href="http://www.nativeplantcenter.net/plants/">Cheasapeake Bay Native
	 * Plant Center</a>.
	 */
	NPC("Chesapeake Bay Native Plant Center"), 
	
	/**
	 * Plant data originated from a spreadsheet on native forest edge plants
	 * provided by a client.
	 */
	SUNNYEDGE("Sunny Edge Plants in Arden, Newark"),
	
	/**
	 * Plant data originated from the 
	 * <a href="https://plants.sc.egov.usda.gov/java/characteristics">USDA's
	 * Natural Resource Conservation Service</a>.
	 */
	NRCS("USDA's Natural Resources Conservation Service");
	
	/**
	 * Friendly name of the enum data source for the UI.
	 */
	private String source;
	
	/**
	 * Constructor.
	 * @param source	Friendly name of the enum data source for the UI.
	 */
	private PlantDataSource(String source) {
		this.source = source;
	}
	
	/**
	 * Getter.
	 * @return	Friendly name of the enum for the UI.
	 */
	public String getSource() {
		return this.source;
	}
	
}
