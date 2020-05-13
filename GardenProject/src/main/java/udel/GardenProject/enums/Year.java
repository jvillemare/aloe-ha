package udel.GardenProject.enums;

/**
 * General year descriptions.
 * Used in Season View
 * 
 * @author Team 0
 */
public enum Year {
	YEAR0("0 Years"),
	YEAR1("1 Year"),
	YEAR2("2 Years");

	private String year;
	
	private Year(String year) {
		this.year = year;
	}
	
	public String getYear() {
		return this.year;
	}
}
