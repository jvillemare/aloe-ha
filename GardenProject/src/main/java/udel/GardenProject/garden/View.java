package udel.GardenProject.garden;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	public View(Stage theStage, Window w) {
		this.theStage = theStage;
		update(w);
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
	public static int getCanvasWidth() {
		return canvasWidth;
	}

	/**
	 * Height of the canvas that is displayed to the user.
	 * 
	 * @return canvas height in pixels.
	 */
	public static int getCanvasHeight() {
		return canvasHeight;
	}

}
