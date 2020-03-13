package garden;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Contains the start and main, ticks the application.
 * 
 * @author jvillemare
 */
public class Controller extends Application {
	
	private Model model;
	private View view;
	
	@Override
	public void start(Stage primaryStage) {
		view = new View(primaryStage);
		model = new Model(view.getCanvasWidth(), view.getCanvasHeight());
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        launch(args);
    }
    
}
