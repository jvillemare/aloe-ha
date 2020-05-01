package udel.GardenProject.garden;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
		// load model then view, because view will show the stage when there's
		// nothing there because the model is still loading.
		model = new Model(view.getCanvasWidth(), view.getCanvasHeight());
		view = new View(primaryStage);
		
		new AnimationTimer() {
            public void handle(long currentNanoTime) {
                model.update();
                view.update(model.getWindow());
                
                // TODO: Is this how we want to handle this?
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
	}
	
	@Override
	public void stop() {
		System.out.println("Stopping GardenProject...");
		
		model.stop();
		
		System.out.println("Goodbye.");
	}
	
    public static void main( String[] args ) {
        System.out.println("GardenProject starting...");
        launch(args);
    }
    
}
