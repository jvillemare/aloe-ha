package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantSelection extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Hold the buttons at the bottom of the screen
	 */
	private TilePane tilePane;

	/**
	 * Used to hold the text and the toggles at the top of the screen
	 */
	private VBox vbox;

	/**
	 * Information at the top of the screen
	 */
	private Text text;

	/**
	 * Navigation buttons at the bottom of the screen
	 */
	private Button back, next;

	/**
	 * ScrollPane for the FlowPane where user's selections of plants are placed w
	 */
	private ScrollPane scrollSelected;

	/**
	 * ScrollPane for the accordion selection
	 */
	private ScrollPane scrollCanopies;

	/**
	 * Flow Pane for the user to see what plants they have selected
	 */
	private FlowPane selectedPlantsBox;

	/**
	 * Box that holds the ScrollPane for both the accordion and the user selection
	 * of plants
	 */
	private HBox centerBox;

	/**
	 * Adjustments to size for margins, text, buttons, and scrollPane for the main.
	 */
	private int backgroundScreenWidthAndHeight = 100;
	private int borderTopAndBottonMargin = 40;
	private int borderSideMargins = 80;
	private int gapBetweenButtons = 100;
	private int prefScrollWidth = View.getCanvasWidth() / 3 + 30;
	private int prefScrollHeight = View.getCanvasHeight() / 5 * 4;
	private int selectedPlantBoxMinWidth = View.getCanvasWidth() / 2;
	private int selectedPlantBoxMinHeight = View.getCanvasHeight() / 5 * 4;
	private int scrollSelectedWidth = View.getCanvasWidth() / 2 + 30;
	private int scrollSelectedHeight = View.getCanvasHeight() / 5 * 4;
	private int imgWidth = 350;
	private int imgHeight = 100;
	

	public PlantSelection(Model m) {
		super(m, "Plant Selection", Windows.PlantSelection);
		
		try {
			displaySelection();
		}catch(Exception e) {
			System.out.println("Wrong size of a plants year Boolean Array");
		}
		

		
	}
	
	/**
	 * Similar to PlantInfo, allows this method to be called in Refresh and thus
	 * renew the new desired traits for each plant in the left hand side.
	 * @throws Exception 
	 */
	public void displaySelection() throws Exception {
		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text("Please select the plants you'd like to have in your Garden");
		text.setWrappingWidth(View.getCanvasWidth());

		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));
		vbox.getChildren().addAll(text);

		createButtons();

		centerBox = new HBox();

		Accordion canopySelection = new Accordion();
		
		List<TitledPane> accArr = new ArrayList<TitledPane>();
		
		populateTiles(accArr);

		for (TitledPane t : accArr) {
			t.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
					View.getTextSizeForButtonsAndText()));
			canopySelection.getPanes().add(t);
		}

		scrollCanopies = new ScrollPane();
		scrollPaneFormat(scrollCanopies);
		scrollCanopies.setPrefSize(prefScrollWidth, prefScrollHeight);
		scrollCanopies.setContent(canopySelection);

		selectedPlantsBox = new FlowPane();
		selectedPlantsBox.setMinWidth(selectedPlantBoxMinWidth);
		selectedPlantsBox.setMinHeight(selectedPlantBoxMinHeight);
		
		addSelected();

		scrollSelected = new ScrollPane();
		scrollPaneFormat(scrollSelected);

		scrollSelected.setPrefSize(scrollSelectedWidth, scrollSelectedHeight);
		scrollSelected.setContent(selectedPlantsBox);

		centerBox.getChildren().addAll(scrollCanopies, scrollSelected);

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(back, next);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(centerBox,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
		borderPane.setPadding(new Insets(5));
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(centerBox);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Formats the ScrollPane to the desired width, height, and padding.
	 * 
	 * @param ScrollPane
	 */
	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setPadding(new Insets(10));
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");

	}
	
	/**
	 * Populates each canopy level with plants that match from the users desires in Questionnaire.
	 * 
	 * @param List<TiledPane>
	 * @throws Exception 
	 */
	public void populateTiles(List<TitledPane> accArr) throws Exception {
		
		for(Canopy c : Canopy.values()) {
			TitledPane tile = new TitledPane(c.name().substring(0, 1) + c.name().substring(1).toLowerCase(), createFlowPane(c));
			accArr.add(tile);
		}
	}

	/**
	 * Function creates a flow pane (with Scroll) for the type of canopy selected.
	 * Filters the plants to match those desired.
	 * 
	 * @param canopy --> Takes in a canopy
	 * @throws Exception 
	 */
	public FlowPane createFlowPane(Canopy canopy) throws Exception {

		FlowPane flowCanopy = new FlowPane();
		
		Moisture m = getSession().getMoistureOfPlot();
		SoilTypes s = getSession().getSoilTypeOfPlot();
		double l = getSession().getSunlightOfPlot();
		
		ArrayList<Plant> plants = getModel().getPlants();
		
		Iterator<Plant> itr = plants.iterator();
		
		while (itr.hasNext()) {
			Plant p = itr.next();
		    
			boolean fits = false;
			
			if(p.getDelawareNative() == true) {
				if(p.getCanopy() == canopy) {
					if(p.getMoisture() == m || p.getMoisture() == null || m == null) {
						if(p.getSoilType() == s || p.getSoilType() == SoilTypes.ANY || s == SoilTypes.ANY) {
							if(p.getLight() == l || 
									(p.getLight() < (l + 0.2) && p.getLight() >= l ) 
									|| p.getLight() == -1.0 || l == -1.0) {
								fits = true;
							}
						} 
					}
				}
			}
			
			if(fits) {
				fits = checkSeason(p);
			}			
			
			if(fits) {
				flowCanopy.getChildren().add(createPlantBox(p));
				
			}
			
		}

		return flowCanopy;

	}

	/**
	 * Checks if the seasons for a plant match what the user desires.
	 * 
	 * @param Plant
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean checkSeason(Plant p) throws Exception {
		ArrayList<Seasons> seasonFilter = getSession().getSeasonsUserSelected();
		
		boolean[] year = p.getBloomTime();
		
		if(year == null) {
			return true;
		}else {
			ArrayList<Seasons> plantBloom = Seasons.getFilterSeason(year);
			for(Seasons desiredSeason : seasonFilter) {
				for(Seasons plantSeason : plantBloom) {
					if(desiredSeason == Seasons.YEARROUND) {
						return true;
					}else if(desiredSeason == plantSeason) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Creates a VBox of a plant including its image, button for info, and add button
	 * 
	 * @param Plant
	 * @return VBox
	 */
	public VBox createPlantBox(Plant p) {

		String[] plantImg = p.getImages();
		
		Image plantImage;

		//Get the actual image if it exists
		if (plantImg != null) {
			String path = p.getImages()[0];
			plantImage = new Image(path, imgWidth, imgHeight, true, true);
		} else {
			// get a default image
			plantImage = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), imgWidth,
					imgHeight, true, true);
		}
		ImageView imageView = new ImageView();
		imageView.setImage(plantImage);
		
		Button addPlant = new Button("Add Plant");

		Button infoButton = new Button("Info");
		infoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getModel().setPlantInfoPlant(p);
				switchToWindow(Windows.PlantInfo);
			}
		});

		HBox buttonHolder = new HBox();
		buttonHolder.getChildren().addAll(infoButton, addPlant);

		VBox imgButtonHolder = new VBox();
		imgButtonHolder.getChildren().addAll(imageView, buttonHolder);

		addPlant.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				selectedPlantsBox.getChildren().add(imgButtonHolder);
				getSession().addSelectedPlant(p);

			}
		});
		return imgButtonHolder;
	}
	
	/**
	 * This displays all plants that are in the HashMap that the user desired from Plant Selection
	 */
	public void addSelected() {
		for(Map.Entry<String, Plant> plant : getSession().getSelectedPlants().entrySet()) {
			Plant p = plant.getValue();
			selectedPlantsBox.getChildren().add(createPlantBox(p));
		}
	}
	
	/**
	 * Function that creates button at the bottom of the screen with handlers
	 */
	public void createButtons() {
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Questionnaire);
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(back);
		buttons.add(next);

		for (Button b : buttons) {
			b.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getButtonTextSize()));
			b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
			b.setPrefWidth(View.getButtonPrefWidth());

			DropShadow shadow = new DropShadow();
			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle(View.getWhiteBackgroundStyle() + View.getBlackTextFill());
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
				}
			});
		}
	}
	
	/**
	 * Refreshes the screen and to get the correct info from Model
	 */
	public void refresh() {	
		try {
			if(getModel().getLastWindow().getEnum() == Windows.Questionnaire) {
				displaySelection();
			}
		}catch(Exception e) {
			System.out.println("Wrong size of a plants year Boolean Array");
		}
		
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
