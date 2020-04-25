package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantInfo extends Window {
	
	private Scene scene;
	
	private Plant plantArray[];
	private Button backButton;

	public PlantInfo(Model m) {
		super(m, "Plant Info: "); 
		// this will have to change to the name of the plant they've clicked on
		
		
		
		// pop up window
		
		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Questionnaire);
            }
        });
		
		
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	/**
	 * Change PlantInfo's scene and display a plant's info by it's latin name.
	 * 
	 * @param plantLatinName Linnaeus Genus species plant name.
	 */
	public void displayPlant(String plantLatinName) {
		// TODO: Keep String or use different param?
	}
	
}
