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
	
	private String source;
	
	private PlantDataSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return this.source;
	}
}
