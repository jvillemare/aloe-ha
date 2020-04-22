package udel.GardenProject.enums;

/**
 * Contains different levels of moisture in soil
 * Used in Questionnaire and Plant Info
 * 
 * TODO: More research on enum
 * this seems complicated, consult the following link
 * https://observant.zendesk.com/hc/en-us/article_attachments/203343303/Mgmt_Line_diagram_2.png
 * or also google
 * 
 * @author Team 0
 */
public enum Moisture {
	
	DRY,
	DRY_MOIST,
	MOIST,
	MOIST_DAMP,
	DAMP;

	
	public Moisture getMoisture(String s) {
		if (s.equalsIgnoreCase("Wet") || s.equalsIgnoreCase("Damp")){
			return DAMP;
		}else if (s.equalsIgnoreCase("Moist") || s.equalsIgnoreCase("Average")) {
			return MOIST;
		}else if (s.equalsIgnoreCase("Dry")) {
			return DRY;
		}else if (s.equalsIgnoreCase("wet-med") || s.equalsIgnoreCase("Moist, Wet")) {
			return MOIST_DAMP;
		}else if (s.equalsIgnoreCase("dry-moist")) {
			return DRY_MOIST;
		}else {
			return null;
		}
	}
	
}

