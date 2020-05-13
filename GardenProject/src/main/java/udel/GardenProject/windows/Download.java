package udel.GardenProject.windows;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import udel.GardenProject.enums.ImageFileType;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import javafx.embed.swing.SwingFXUtils;

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

	/**
	 * Boxes for text at top and Buttons at the bottom
	 */
	private VBox vbox, bottomBoxes;
	private Text text;

	/**
	 * Used for saving options (PNG etc....)
	 */
	private HBox saveOptions;

	private static final String downloadSceneTitle = "Load or Save Your Plot";
	private String lastSaveFile;

	/**
	 * Buttons to go back, load, and download
	 */
	private Button back, mainMenu, downloadButton;

	/**
	 * Used to center button
	 */
	private TilePane tilePane;

	/**
	 * Used for grouping all the saving methods
	 */
	private ToggleGroup saveGroup;

	/**
	 * Toggle to allow user to save image of plot
	 */
	private ToggleButton saveImage;

	/**
	 * The option of saving the user has chosen
	 */
	private String saveOption;

	/**
	 * Converts a Buffered Image to A Writable Image to be converted to Image View
	 */
	private WritableImage writableImage;

	/**
	 * Holds the center download image
	 */
	private VBox imageHolder;

	/**
	 * The Center Image of the plot
	 */
	private ImageView imView;

	/**
	 * Adjustments of size for insets, texts, the center square, buttons, and
	 * background
	 */
	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int imageBoxWidth = 1260;
	private int imageBoxHeight = 570;
	private int gapBetweenButtons = 100;
	private int topTextWidthAdjustment = 20;
	private int backgroundScreenWidthAndHeight = 100;
	private String mouseEnterBottomButton = View.getWhiteBackgroundStyle() + View.getBlackTextFill();
	private String mouseExitBottomButton = View.getLightGreenBackgroundStyle() + View.getBlackTextFill();

	/**
	 * Assume the user has no last save file downloaded.
	 *
	 * @param m Model
	 */
	public Download(Model m) {
		super(m, downloadSceneTitle, Windows.Download);
		this.lastSaveFile = "";

		borderPane = new BorderPane();
		vbox = new VBox();
		saveOptions = new HBox();
		tilePane = new TilePane();

		saveOptions.setPadding(new Insets(inset10));

		text = new Text("Congrats! You've created your Garden! How would you like to save?");
		text.setWrappingWidth(View.getCanvasWidth() - topTextWidthAdjustment);
		text.setFont(getModel().getHackBold20());
		vbox.getChildren().addAll(text);
		vbox.setAlignment(Pos.CENTER);

		saveGroup = new ToggleGroup();
		saveImage = new ToggleButton("Save Image");
		saveImage.setToggleGroup(saveGroup);
		saveOptions.setAlignment(Pos.CENTER);
		saveOptions.getChildren().add(saveImage);

		createAndHandleButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(inset5));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(back, mainMenu, downloadButton);

		bottomBoxes = new VBox();
		bottomBoxes.getChildren().addAll(saveOptions, tilePane);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		borderPane.setPadding(new Insets(inset10, inset10, inset20, inset10));
		borderPane.setTop(vbox);
		borderPane.setBottom(bottomBoxes);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Opens a file chooser to allow user to save the image of their plot. 
	 * 
	 * @param option
	 */
	public void savingImage() {

		FileChooser imageSaver = new FileChooser();
		imageSaver.setTitle("Save 'Aloe-Ha' Image");
		imageSaver.setInitialFileName(getSession().getPlotName());

		/*
		 * Allows user to select different file types for saving images
		 */
		ImageFileType[] fileTypes = ImageFileType.values();
		for (ImageFileType type : fileTypes) {
			imageSaver.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter(type.toString(), type.getImageFileType()));
		}
		
		/*
		 * Shows file chooser and allows user to save screenshot of their plot 
		 */
		try {
			File f1 = imageSaver.showSaveDialog(null);
			if(f1 != null){
				imageSaver.setInitialDirectory(f1.getParentFile());
	            ImageIO.write(getSession().getScreenShot(), "png", f1);
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Creates and handles the downloading options and the buttons at the bottom of
	 * the screen
	 */
	public void createAndHandleButtons() {

		formatToggleButton(saveImage);
		saveImage.setOnAction((ActionEvent e) -> {
			savingImage();
		});

		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.SeasonView);
			}
		});

		mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		downloadButton = new Button("Download");
		downloadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getInput();
				/*
				 * Opens the file chooser and allows the user to save the file to their computer
				 */
				javafx.stage.Window scene2 = null;
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save 'Aloe-Ha' Garden Project");
				fileChooser.setInitialFileName(getSession().getPlotName());
				fileChooser.getExtensionFilters()
						.addAll(new FileChooser.ExtensionFilter("GARDENPROJECT", "*.gardenproject"));

				File file = fileChooser.showSaveDialog(scene2);
				if (file != null) {
					getModel().saveSession(file.getAbsolutePath());
				}
			}
		});

		/*
		 * Formatting how the buttons will look at the bottom of the screen
		 */
		List<Button> bottomButtons = new ArrayList<Button>();
		bottomButtons.add(back);
		bottomButtons.add(mainMenu);
		bottomButtons.add(downloadButton);

		for (Button b : bottomButtons) {
			b.setFont(getModel().getHackBold12());
			b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
			b.setPrefWidth(View.getButtonPrefWidth());

			DropShadow shadow = new DropShadow();
			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle(mouseEnterBottomButton);
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle(mouseExitBottomButton);
				}
			});
		}
	}

	/**
	 * Function that formats each of the toggle buttons for the options on how the
	 * user wants to save
	 * 
	 * @param b --> a toggle button
	 */
	public void formatToggleButton(ToggleButton b) {

		b.setPrefWidth(View.getButtonPrefWidth());
		b.setFont(getModel().getHackBold12());
		b.setStyle(getModel().getNotHover());

		DropShadow shadow = new DropShadow();

		b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setEffect(shadow);
				b.setStyle(getModel().getHover());
			}
		});

		b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setEffect(null);
				b.setStyle(getModel().getNotHover());
			}
		});

	}

	/**
	 * Create a new Download window and specify where the user's last save file is.
	 *
	 * @param m            Model
	 * @param lastSaveFile Path of the user's last save file.
	 */
	public Download(Model m, String lastSaveFile) {
		super(m, downloadSceneTitle, Windows.Download);
		this.lastSaveFile = lastSaveFile;
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

	/**
	 * Saves the a PNG of the plot to a file.
	 *
	 * @param filename Path where the file should be saved.
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
	 * @param filename Path where the file should be saved.
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
	 * @param filename Path where the file should be saved.
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

	/**
	 * Sends the type of file the user want to save to the session after user clicks
	 * download
	 */
	public void getInput() {

		getSession().setSaveOption(saveOption);

	}

	/**
	 * Refreshes the screen to the image of the plot corresponding to the users
	 * options in SeasonView
	 */
	public void refresh() {

		writableImage = new WritableImage(getSession().getScreenShot().getWidth(),
				getSession().getScreenShot().getHeight());
		Image i = SwingFXUtils.toFXImage(getSession().getScreenShot(), writableImage);
		imageHolder = new VBox();
		imageHolder.setPrefSize(imageBoxWidth, imageBoxHeight);
		imView = new ImageView(i);
		imView.setFitWidth(imageBoxWidth);
		imView.setFitHeight(imageBoxHeight);
		imageHolder.getChildren().add(imView);
		borderPane.setCenter(imageHolder);
	}

}
