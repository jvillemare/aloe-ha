package udel.GardenProject.enums;

/**
 * Specifies where the data for a Plant originated from, and a friendly name of
 * that database.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum PlantDataSource {
	
	UDEL("University of Delaware Flora Database"), 
	NPC("Chesapeake Bay Native Plant Center"), 
	SUNNYEDGE("Sunny Edge Plants in Arden, Newark"),
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
