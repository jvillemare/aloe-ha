package udel.GardenProject.enums;

/**
 * General year descriptions. Used in Season View.
 * 
 * @author Team 0
 */
public enum Year {
	
	YEAR0("0 Years"),
	YEAR1("1 Year"),
	YEAR2("2 Years");

	/**
	 * Friendly name of the enum for the UI.
	 */
	private String year;
	
	/**
	 * Constructor.
	 * @param year	Friendly name of the enum for the UI.
	 */
	private Year(String year) {
		this.year = year;
	}
	
	/**
	 * Getter.
	 * @return	Friendly name of the enum for the UI.
	 */
	public String getYear() {
		return this.year;
	}
	
}
