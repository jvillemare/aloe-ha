package udel.GardenProject.windows;

import javafx.scene.Scene;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;

/**
 * Allow the user detail the background of their plot with labels, and specify
 * characteristics for some areas of their plot.
 * 
 * @author Team 0
 */
public class BluePrint extends Window {

	/**
	 * Constructor.
	 * 
	 * @param model
	 * @param title
	 * @param enumWindow
	 */
	public BluePrint(Model model) {
		super(model, "Blue Print Your Plot", Windows.BluePrint);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene getScene() {
		return null;
	}
	
	
}
