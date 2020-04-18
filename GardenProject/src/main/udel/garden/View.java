package main.udel.garden;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

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
	
	public View(Stage theStage) {
		this.theStage = theStage;
		
		this.theStage = theStage;
		
		this.theStage.setTitle("Garden Project v0.1");

        this.root = new Group();
        this.theScene = new Scene(this.root);
        this.theStage.setScene(theScene);

        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.root.getChildren().add(this.canvas);
        this.gc = this.canvas.getGraphicsContext2D();
	}

	/**
	 * TODO: Figure out...
	 * 
	 * @param objects
	 */
	public void update(Object... objects) {

	}
	
	/**
	 * Width of the canvas that is displayed to the user.
	 * 
	 * @return canvas width in pixels.
	 */
	public int getCanvasWidth() {
		return this.canvasWidth;
	}
	
	/**
	 * Height of the canvas that is displayed to the user.
	 * 
	 * @return canvas height in pixels.
	 */
	public int getCanvasHeight() {
		return this.canvasHeight;
	}

}
