package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;

/**
 * Where the user can export their plot as a PNG/JPEG, save it as a
 * <code>.gardenproject</code> file, load a saved project, or exit.
 *
 * @author Team 0
 */
public class Download extends Window {

	private Group root;
	private Scene scene;

	private BorderPane borderPane;
	private VBox vbox, saveOptions;
	private Text text;

	private static final String downloadSceneTitle = "Load or Save Your Plot";
	private String lastSaveFile;

	/**
	 * Buttons to go back, load, and download
	 */
	private Button back, load, downloadButton;
	
	/**
	 * Used to center button
	 */
	private TilePane tilePane;
	
	/**
	 * Used for grouping all the saving methods 
	 */
	private ToggleGroup saveGroup;
	
	/**
	 * Toggles between how the user wants to save. 
	 * User will be able to save one type of file at a time 
	 */
	private ToggleButton pngSave;
	
	/**
	 * Used to show which image they will be saving
	 */
	private Rectangle square;

	/**
	 * Assume the user has no last save file downloaded.
	 *
	 * @param m	Model
	 */
	public Download(Model m) {
		super(m, downloadSceneTitle);
		this.lastSaveFile = "";

		borderPane = new BorderPane();
		vbox = new VBox();
		saveOptions = new VBox();
		tilePane = new TilePane();

		saveOptions.setPadding(new Insets(10, 10, 10, 10));
		
		text = new Text("Congrats! You've created your Garden! How would you like to save?");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);
		
		saveGroup = new ToggleGroup();
		pngSave = new ToggleButton("PNG");
		pngSave.setToggleGroup(saveGroup);	
		saveOptions.getChildren().add(pngSave);
		
		/**
		 * TODO: add a listener for the save toggles to get which option the user chose
		 */

		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Back: Going back to seasonView");
            	switchToWindow(Windows.SeasonView); 
            }
        });
		
		load = new Button("Load");
		load.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Load: Loading garden to load menu");
            	/**
            	 * TODO: implement function to load plot in the load option at the welcome menu
            	 * loadPlot(current plot); ??? 
            	 */
            }
        });
		
		downloadButton = new Button("Download");
		downloadButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Download: downloading png");
            	
            }
        });
		
		square = new Rectangle();
		square.setHeight(530);
        square.setWidth(830);
        square.setStroke(Color.BLACK);
        square.setFill(null);

        borderPane.setCenter(square);
		borderPane.setTop(vbox);
		borderPane.setRight(saveOptions);

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(back, downloadButton);
		
		borderPane.setBackground(new Background(new BackgroundFill(Color.SEASHELL, null,null)));
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 900, 610);
	}

	/**
	 * Create a new Download window and specify where the user's last save file
	 * is.
	 *
	 * @param m				Model
	 * @param lastSaveFile	Path of the user's last save file.
	 */
	public Download(Model m, String lastSaveFile) {
		super(m, downloadSceneTitle);
		this.lastSaveFile = lastSaveFile;



	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * Saves the a PNG of the plot to a file.
	 *
	 * @param	filename	Path where the file should be saved.
	 *
	 * @return true if it saved and false if it did not
	 * @see javafx.stage.FileChooser
	 */
	public boolean savePNG(String filename) {
		return false;
	}

	/**
	 * Saves the plot as a <code>.gardenproject</code> file.
	 *
	 * @param	filename	Path where the file should be saved.
	 *
	 * @return true if saved and false if it did not
	 * @see javafx.stage.FileChooser
	 */
	public boolean save(String filename) {
		return false;
	}

	/**
	 * Loads a plot from a <code>.gardenproject</code> file as a PlotDesign
	 * instance.
	 *
	 * @param	filename	Path where the file should be saved.
	 *
	 * @return the PlotDesign desired
	 * @see javafx.stage.FileChooser
	 */
	public PlotDesign load(String filename) {
		return null;
	}

	/**
	 * Exit the GardenProject program.
	 */
	public void exit() {
		// TODO: Implement
	}

}
