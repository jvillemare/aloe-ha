package main.udel.enums;

/**
 * TODO: What is this generally?
 * 
 * @author Team 0
 */

	// TODO: Actually determine enum
	// this seems complicated, consult the following link
	// https://observant.zendesk.com/hc/en-us/article_attachments/203343303/Mgmt_Line_diagram_2.png
	// or also google


public enum Moisture {
	
	// these will have to change depending on the 
	// files/websites we are grabbing information from 
	LOW("low"),
	MEDIUM("medium"),
	HIGH("high");
	
	private String moistureLevel = null;
	
	private Moisture(String m){
		moistureLevel = m;
	}
	public String getMoistureLevel() {
		return moistureLevel;
	}


}

