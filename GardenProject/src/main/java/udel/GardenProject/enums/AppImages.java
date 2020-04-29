package udel.GardenProject.enums;

/**
 * All the images loaded by the project. Defined here so that can be loaded once
 * by the Model.
 * 
 * @author Team 0
 */
public enum AppImages {
	
	ground_plant("/buttonImages/"), 
	plant("/buttonImages/"), 
	seed("/buttonImages/"), 
	tree("/buttonImages/");
	
	private String prefix;
	
	AppImages(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return this.prefix;
	}

}
