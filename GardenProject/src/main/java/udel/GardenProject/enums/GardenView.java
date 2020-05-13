package udel.GardenProject.enums;

/**
 * General View descriptions.
 * Used in Season View
 * 
 * @author Team 0
 */
public enum GardenView {
	TOPVIEW("Top View"),
	WINDOWVIEW("Window View");
	
	private String view;
	
	private GardenView(String view){
		this.view = view;
	}
	
	public String getView() {
		return this.view;
	}
}
