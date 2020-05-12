package udel.GardenProject.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

/**
 * Displays a short table of where a user has recently saved Sessions on their
 * disk.
 * 
 * @author Team 0
 */
public class PreviousSaves extends Window {
	
	private Group root;
	private Scene scene;

	public PreviousSaves(Model model) {
		super(model, "Past Saved Sessions", Windows.PreviousSaves);
		// TODO Auto-generated constructor stub
		
		this.root = new Group();
		//root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	@Override
	public void refresh() {
		
	}

}
