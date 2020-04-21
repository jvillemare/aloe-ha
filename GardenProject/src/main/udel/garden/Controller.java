package main.udel.garden;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Contains the start and main, ticks the application.
 * 
 * @author Team 0
 */
public class Controller extends Application {
	
	private Model model;
	private View view;
	
	@Override
	public void start(Stage primaryStage) {
		model = new Model(view.getCanvasWidth(), view.getCanvasHeight());
		view = new View(primaryStage, model.getWindow());
		
		new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                //increment the x and y coordinates, alter direction if necessary
                model.update();
                //input the x coordinates, y coordinates, and direction picture
                view.update(model.getWindow());
                
                // TODO: Is this how we want to handle it later?
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
	}
	
    public static void main( String[] args ) {
        System.out.println("GardenProject starting...");
        launch(args);
    }
    
}
