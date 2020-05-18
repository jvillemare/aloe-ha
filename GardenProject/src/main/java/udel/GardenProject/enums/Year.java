package udel.GardenProject.enums;

/**
 * General year descriptions. Used in Season View.
 * 
 * @author Team 0
 */
public enum Year {
	
	/**
	 * What a user's plot will look like now, out the window.
	 */
	YEAR0("0 Years"),
	
	/**
	 * What a user's plot will look like a year from now, out the window.
	 */
	YEAR1("1 Year"),
	
	/**
	 * What a user's plot will look like two years from now, out the window.
	 */
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
