package udel.GardenProject.garden;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import udel.GardenProject.windows.Window;

/**
 * Creates the stage: Loads graphics, images, and defines what the user sees.
 * 
 * @author Team 0
 */
public class View {

	/**
	 * Local reference to stage.
	 */
	private Stage theStage;

	/**
	 * Default Scene width, can be overridden by Window objects.
	 */
	private final static int canvasWidth = 1300;

	/**
	 * DefaDefaultutl Scene height, can be overridden by Window objects.
	 */
	private final static int canvasHeight = 700;

	/**
	 * Reference to the current window object being displayed.
	 */
	private Window window;

	/**
	 * Constructor.
	 * 
	 * @param theStage JavaFX Stage instance.
	 * @param w        The first window to be displayed.
	 */

	public View(Stage theStage) {
		this.theStage = theStage;
		showSplashScreen();
        this.theStage.show();
	}

	/**
	 * Updates the current Window object (if need be) and stores a reference to the
	 * current window.
	 * 
	 * Uses the current window to set the stage's scene and title.
	 * 
	 * @param w The current Window object that should be shown to the user. If it is
	 *          changed, then that new Window is stored.
	 */
	public void update(Window w) {
		if (this.window == null || this.window.equals(w) == false) {
			this.window = w;
			theStage.setScene(this.window.getScene());
			theStage.setTitle(this.window.getTitle());
			System.out.println("View: Now showing " + this.window.getTitle() + " window");
		}
	}

	/**
	 * Width of the canvas that is displayed to the user.
	 * 
	 * @return canvas width in pixels.
	 */
	public int getCanvasWidth() {
		return canvasWidth;
	}

	/**
	 * Height of the canvas that is displayed to the user.
	 * 
	 * @return canvas height in pixels.
	 */
	public int getCanvasHeight() {
		return canvasHeight;
	}
	
	/**
	 * Splash screen that appears to the user right as the application is
	 * started. Only displays until the Model finishes loading in its 
	 * constructor.
	 */
	private void showSplashScreen() {
		// TODO: Doesn't display, fix
		Label loadingMessage = new Label("Starting application..");

        BorderPane pane = new BorderPane();
        pane.setPrefSize(620, 300);
        pane.setCenter(loadingMessage);
		
		Group root = new Group();
		root.getChildren().add(pane);
		this.theStage.setTitle("Aloe-ha ALPHA");
		this.theStage.setScene(new Scene(root, 620, 300));
	}

}
