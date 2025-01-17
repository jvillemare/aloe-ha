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
 * @version 1.0
 * @author Team 0
 */
public enum Moisture {
	
	/**
	 * Like the Sahara.
	 */
	DRY("Dry"),
	
	/**
	 * Like the Sahara with a drop of water.
	 */
	DRYMOIST("Dry Moist"),
	
	/**
	 * Moist.
	 */
	MOIST("Moist"),
	
	/**
	 * Extra moist.
	 */
	MOISTDAMP("Moist Damp"),
	
	/**
	 * Think literally anywhere in central South-east Asia.
	 */
	DAMP("Damp");

	/**
	 * Friendly name of the enum for the UI.
	 */
	private String name;
	
	/**
	 * Constructor.
	 * @param name	Friendly name of the enum for the UI.
	 */
	private Moisture(String name) {
		this.name = name;
	}
	
	/**
	 * Getter to Establish what String belongs to what moisture level. Similar 
	 * to <code>valueOf()</code>.
	 * @param s String that roughly resembles one of the enums.
	 * @return Moisture enum level, null if there's no match.
	 */
	public Moisture getMoisture(String s) {
		if (s.equalsIgnoreCase("Wet") || s.equalsIgnoreCase("Damp")){
			return DAMP;
		}else if (s.equalsIgnoreCase("Moist") || s.equalsIgnoreCase("Average")) {
			return MOIST;
		}else if (s.equalsIgnoreCase("Dry")) {
			return DRY;
		}else if (s.equalsIgnoreCase("wet-med") || s.equalsIgnoreCase("Moist, Wet")) {
			return MOISTDAMP;
		}else if (s.equalsIgnoreCase("dry-moist")) {
			return DRYMOIST;
		}else {
			return null;
		}
	}
	
	/**
	 * Returns the name of the Moisture Level.
	 * @return String name of Moisture level
	 */
	public String getFriendlyName() {
		return name;
	}
	
}

