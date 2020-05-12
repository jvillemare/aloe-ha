package udel.GardenProject.garden;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	 * Pink background style to be used in Welcome
	 */
	private static String pinkBackgroundStyle = "-fx-background-color: #F6E8E8;";
	
	/**
	 * Dark green background style to be used in Welcome for buttons
	 */
	private static String darkGreenBackgroundStyle = "-fx-background-color: #63A331;";
	
	/**
	 * Light green background style to be used in Welcome for buttons
	 */
	private static String lightGreenBackgroundStyle = "-fx-background-color: #76C327;";
	
	/**
	 * White background style
	 */
	private static String whiteBackgroundStyle = "-fx-background-color: #FFFFFF;";
	
	/**
	 * Black text fill for works in buttons
	 */
	private static String blackTextFill = "-fx-text-fill: #000000;";
	
	/**
	 * The prefered Width of buttons at the bottom of each screen
	 */
	private static int buttonPrefWidth = 100;
	
	/**
	 * Standard button text size for all buttons at the bottom of the screen
	 */
	private static int buttonTextSize = 12;
	
	/**
	 * Seam-less background for each screen
	 */
	private static Background backgroundScreen;
	
	/**
	 * Text size for top messages and welcome buttons
	 */
	private static int textSizeForButtonsAndText = 20;
	
	/**
	 * The path to the background screen image
	 */
	private static String backgroundScreenPath = "/buttonImages/splash2.png";
	
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
	 * Refers to the pink background style
	 * 
	 * @return The string used in the .getStyle method to turn the background pink
	 */
	public static String getPinkBackgroundStyle() {
		return pinkBackgroundStyle;
	}

	/**
	 * Refers to the dark green background style
	 * 
	 * @return The string used in the .getStyle method to turn the background dark
	 *         green
	 */
	public static String getDarkGreenBackgroundStyle() {
		return darkGreenBackgroundStyle;
	}

	/**
	 * Refers to the light green background style
	 * 
	 * @return The string used in the .getStyle method to turn the background light
	 *         green
	 */
	public static String getLightGreenBackgroundStyle() {
		return lightGreenBackgroundStyle;
	}

	/**
	 * Refers to the White background style
	 * 
	 * @return The string used in the .getStyle method to turn the background white
	 */
	public static String getWhiteBackgroundStyle() {
		return whiteBackgroundStyle;
	}

	/**
	 * Black Text Fill
	 * 
	 * @return the string used to format text to be black
	 */
	public static String getBlackTextFill() {
		return blackTextFill;
	}

	/**
	 * Button Preferred width
	 * 
	 * @return the preferred width of a button
	 */
	public static int getButtonPrefWidth() {
		return buttonPrefWidth;
	}

	/**
	 * Standard text size
	 * 
	 * @return the button text size for all buttons except Welcome buttons
	 */
	public static int getButtonTextSize() {
		return buttonTextSize;
	}

	/**
	 * Width of the buttons on the Welcome screen
	 */
	public static int welcomeButtonWidth = 300;

	/**
	 * @return the width of a single button on the Welcome screen
	 */
	public static int getWelcomeButtonWidth() {
		return welcomeButtonWidth;
	}

	/**
	 * Sets the background screen depending on the width and height
	 */
	public static void setBackgroundScreen(Image image, int width, int height) {

		BackgroundSize backgroundSize = new BackgroundSize(width, height, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		backgroundScreen = new Background(backgroundImage);
	}

	/**
	 * Returns the background screen
	 */
	public static Background getBackgroundScreen() {
		return backgroundScreen;
	}

	/**
	 * Returns 20
	 * 
	 * @return the text size for messages and welcome buttons
	 */
	public static int getTextSizeForButtonsAndText() {
		return textSizeForButtonsAndText;
	}

	/**
	 * The string used in the getResourceAsStream function to set the font to
	 * Hack-Bold
	 */
	public static String hackBold = "/fonts/Hack-Bold.ttf";

	/**
	 * Gets the HackBold font string
	 * 
	 * @return String for Hack-Bold font style
	 */
	public static String getHackBold() {
		return hackBold;
	}

	/**
	 * The string used in the getResourceAsStream function to set the font to
	 * Hack-Italic
	 */
	public static String hackItalic = "/fonts/Hack-Italic.ttf";
	
	/**
	 * Local reference to Controller for Model communication.
	 */
	private Controller c;

	/**
	 * Gets the HackItalic font string
	 * 
	 * @return String for Hack-Italic font style
	 */
	public static String getHackItalic() {
		return hackItalic;
	}

	/**
	 * Gets the background screen path for the image to be in each of the screens
	 * 
	 * @return
	 */
	public static String getBackgroundScreenPath() {
		return backgroundScreenPath;
	}

	/**
	 * Constructor.
	 * 
	 * @param theStage JavaFX Stage instance.
	 * @param w        The first window to be displayed.
	 */

	public View(Controller c,Stage theStage) {
		this.c = c;
		this.theStage = theStage;
		// showSplashScreen();
		update(c.getCurrentWindow());
		this.theStage.show();
	}

	/**
	 * Updates the current Window object (if need be) and stores a reference to 
	 * the current window.<br><br>
	 * 
	 * Uses the current window to set the stage's scene and title.<br><br>
	 * 
	 * Protected so that the Windows package cannot invoke this directly, and so
	 * they have to go through the Model so the Model can handle what it needs
	 * to do.
	 * 
	 * @param w The current Window object that should be shown to the user. If 
	 * 			it is changed, then that new Window is stored.
	 */
	protected void update(Window w) {
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
