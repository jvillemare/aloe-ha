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
	private Model model;
	
	public Welcome(Model m) {
		super("Welcome Menu");
		
		this.model = m;
		
		Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                m.setWindow(Windows.AllPlants);
            }
        });
		
		this.root = new Group();
		root.getChildren().add(btn);
		this.scene = new Scene(this.root);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	
}
