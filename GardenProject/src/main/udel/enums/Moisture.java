package main.udel.enums;

/**
 * Contains different levels of moisture in soil
 * Used in Questionnaire and Plant Info
 * 
 * @author Team 0
 */
public enum Moisture {
	
	DRY,
	DRY_MOIST,
	MOIST,
	MOIST_DAMP,
	DAMP;

	//private int intensity = 0;
	
	private Moisture() {
		//this.intensity = intensity;
	}
	
	/*
	public int getIntensity() {
		return intensity;
	} */
	
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

