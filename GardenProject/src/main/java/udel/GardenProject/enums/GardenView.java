package udel.GardenProject.enums;

/**
 * General View descriptions.
 * Used in Season View
 * 
 * @author Team 0
 */
public enum GardenView {
	
	/**
	 * View a garden from the top, down.
	 */
	TOPVIEW("Top View"),
	
	/**
	 * View a garden from out a window, horizontally out.
	 */
	WINDOWVIEW("Window View");
	
	/**
	 * Friendly name of the enum for the UI.
	 */
	private String view;
	
	/**
	 * Constructor.
	 * @param view	Friendly name of the enum for the UI.
	 */
	private GardenView(String view){
		this.view = view;
	}
	
	/**
	 * Getter.
	 * @return	Friendly name of the enum for the UI.
	 */
	public String getView() {
		return this.view;
	}
	
}
