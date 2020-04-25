package udel.GardenProject.enums;

public enum PlantDataSource {
	
	UDEL("University of Delaware Flora Database"), 
	NPC("Chesapeake Bay Native Plant Center"), 
	SUNNYEDGE("Sunny Edge Plants in Arden, Newark"),
	NRCS("USDA\"s Natural Resources Conservation Service");
	
	private String source;
	
	private PlantDataSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return this.source;
	}
}
