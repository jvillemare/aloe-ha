package main.udel.enums;

/**
 * All plants that can be used in this application.
 * 
 * @author Team 0
 */
public enum Plants {
	
	PINETREE("Tracheophytes", "Pinophyta", "Pinopsida", "Pinaceae", "Pinoideae", 
			"Pinus", "Pine Tree");
	
	// TODO: All critical?
	String clade;
	String divison;
	String plantClass;
	String family;
	String subfamily;
	String genus;
	String commonName;
	
	/**
	 * Enum constructor.<br><br>
	 * 
	 * Kingdom is assumed to be Plantae since users are only concerned with
	 * plants in their garden, not tigers.
	 * 
	 * @param clade
	 * @param division
	 * @param plantClass
	 * @param family
	 * @param subfamily
	 * @param genus
	 * @param commonName
	 */
	Plants(String clade, String division, String plantClass, String family, 
			String subfamily, String genus, String commonName) {
		this.clade = clade;
		this.divison = division;
		this.plantClass = plantClass;
		this.family = family;
		this.subfamily = subfamily;
		this.genus = genus;
		this.commonName = commonName;
	}
	
	// TODO: Getters for all Strings, or just make it public?
	//public String getPlantName() {
		//return this.clade +", " + this.
	//}
	//}
	
	
}
