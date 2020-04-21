package main.udel.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Welcome screen that first appears when the user starts the program.
 * 
 * @author Team 0
 */
public class Welcome extends Window {
	
	private Group root;
	private Scene scene;
	
	public Welcome() {
		super("Welcome Menu");
		
		Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
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
