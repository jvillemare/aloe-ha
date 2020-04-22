package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;

/**
 * Welcome screen that first appears when the user starts the program.
 * 
 * @author Team 0
 */
public class Welcome extends Window {
	
	private Group root;
	private Scene scene;
	Button helloWorld;
	
	/**
	 * Create a Welcome screen window instance.
	 * 
	 * @param m Model
	 */
	public Welcome(Model m) {
		super(m, "Welcome Menu");
		
		helloWorld = new Button();
		helloWorld.setText("Say 'Hello World'");
		helloWorld.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                switchToWindow(Windows.AllPlants);
            }
        });
		
		this.root = new Group();
		root.getChildren().add(helloWorld);
		this.scene = new Scene(this.root, 150, 150);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	
}
