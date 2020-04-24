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
	 * local reference to stage.
	 */
	private Stage theStage;
	
	private final static int canvasWidth = 500;
	private final static int canvasHeight = 300;
	
	private Group root;
	private Scene theScene;
	private Canvas canvas;
	private GraphicsContext gc;
	
	private Window window;
	
	public View(Stage theStage, Window w) {
		this.theStage = theStage;
		
		update(w);
        this.theStage.show();
	}

	/**
	 * TODO: Figure out...
	 * 
	 * @param objects
	 */
	public void update(Window w) {
		if(this.window == null || this.window.equals(w) == false) {
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