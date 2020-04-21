package main.udel.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.enums.Windows;
import main.udel.garden.Model;

/**
 * Welcome screen that first appears when the user starts the program.
 * 
 * @author Team 0
 */
public class Welcome extends Window {
	
	private Group root;
	private Scene scene;
	Button helloWorld;
	
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
		this.scene = new Scene(this.root);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	
}
