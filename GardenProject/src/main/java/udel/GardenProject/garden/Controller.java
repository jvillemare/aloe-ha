package udel.GardenProject.garden;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.windows.Window;

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
		model = new Model(this, view.getCanvasWidth(), view.getCanvasHeight());
		view = new View(this, primaryStage);
	}
	
	/**
	 * Update the View with the new current window.
	 * @param w	New current window.
	 */
	public void update(Window w) {
		view.update(w);
	}
	
	/**
	 * Get the current window from Model.
	 * @return	Current window.
	 */
	public Window getCurrentWindow() {
		return model.getWindow();
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
