package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	private VBox vbox;
	private Text text;

	private static final String downloadSceneTitle = "Load or Save Your Plot";
	private String lastSaveFile;

	private Button back;

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

		text = new Text("Congrats! You've created your Garden! How would you like to save?");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);

		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Questionnaire);
            }
        });

		borderPane.setTop(vbox);
		borderPane.setLeft(back);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
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
