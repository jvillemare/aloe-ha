package udel.GardenProject.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

/**
 * Allow the user detail the background of their plot with labels, and specify
 * characteristics for some areas of their plot.
 * 
 * @version 1.0
 * @author Team 0
 */
public class BluePrint extends Window {
	
	private Group root;
	private Scene scene;

	/**
	 * Constructor.
	 * 
	 * @param model		Model reference.
	 */
	public BluePrint(Model model) {
		super(model, "Blue Print Your Plot", Windows.BluePrint);
		// TODO Auto-generated constructor stub
		
		this.root = new Group();
		//root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	@Override
	public void refresh() {
		
	}
	
}
