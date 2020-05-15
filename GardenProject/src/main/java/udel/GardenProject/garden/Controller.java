package udel.GardenProject.garden;

import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.enums.CommandLineArgs;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.windows.Window;

/**
 * Contains the start and real main, setups different components of the 
 * application.
 * 
 * @version 1.0
 * @author Team 0
 */
public class Controller extends Application {
	
	/**
	 * Reference to Model for MVC.
	 */
	private Model model;
	
	/**
	 * Reference to View for MVC.
	 */
	private View view;
	
	/**
	 * Reference to the application parameters (launch args).
	 */
	private Map<String, String> params;
	
	@Override
	public void start(Stage primaryStage) {
		// load model then view, because view will show the stage when there's
		// nothing there because the model is still loading.
		model = new Model(this, view.getCanvasWidth(), view.getCanvasHeight());
		view = new View(this, primaryStage);
		
		params = getParameters().getNamed();
		for(String potentialArg : params.keySet()) {
			CommandLineArgs arg;
			try {
				arg = CommandLineArgs.valueOf(potentialArg);
			} catch(IllegalArgumentException e) {
				System.out.println("Invalid arg " + potentialArg 
						+ ", skipping...");
				continue;
			}
			switch(arg) {
				case OpenProject:
					model.loadSession(params.get(arg.name()));
					model.setWindow(Windows.PlotDesign);
					break;
			}
		}
	}
	
	/**
	 * Update the View with the new current window.
	 * @param w	New current window.
	 */
	public void update(Window w) {
		view.update(w);
	}
	
	/**
	 * Get the current window from Model. Only invoked for the View's 
	 * constructor.
	 * @return	Current window.
	 */
	public Window getCurrentWindow() {
		return model.getWindow();
	}
	
	/**
	 * Get the launch parameters passed by JavaFX.
	 * @return <code>--key=value</code> pairs of command line flags.
	 */
	public Map<String, String> getParams() {
		return this.params;
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
