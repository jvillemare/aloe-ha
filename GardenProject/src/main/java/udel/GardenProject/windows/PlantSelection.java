package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
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
import javafx.stage.Stage;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Colors;
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
 * @version 1.0
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
	private Button back, mainMenu, next, ease;

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
	 * Native Plants database from Model.
	 */
	private ArrayList<Plant> nativePlants = getModel().getNativePlants();
	
	/**
	 * True if there are no plants to choose from, false otherwise.
	 */
	private boolean plantSelEmpty = true;
	
	/**
	 * List holding each tilepane for each layer of the accordion.
	 */
	private List<TitledPane> accArr;
	
	/**
	 * Accordion holding all plant options in canopy order.
	 */
	private Accordion canopySelection;
	
	/**
	 * HashSet of all plants that fit from Questionnaire.
	 */
	private HashSet<Plant> filter = new HashSet<Plant>();
	
	/**
	 * All plants that fit from filter and the canopy level.
	 */
	private ArrayList<Plant> canopyPlant = new ArrayList<Plant>();
	
	/**
	 * Max number of plants per page
	 */
	private int maxPlantsPerPage = 15;
	
	/**
	 * Adjustments to size for margins, text, buttons, and scrollPane for the main.
	 */
	private int inset10 = 10;
	private int imgWidth = 350;
	private int imgHeight = 100;
	private int borderSideMargins = 80;
	private int gapBetweenButtons = 100;
	private int borderTopAndBottonMargin = 40;
	private int backgroundScreenWidthAndHeight = 100;
	private int prefScrollWidth = View.getCanvasWidth() / 3 + 30;
	private int prefScrollHeight = View.getCanvasHeight() / 5 * 4;
	private int selectedPlantBoxMinWidth = View.getCanvasWidth() / 2;
	private int scrollSelectedWidth = View.getCanvasWidth() / 2 + 30;
	private int scrollSelectedHeight = View.getCanvasHeight() / 5 * 4;
	private int selectedPlantBoxMinHeight = View.getCanvasHeight() / 5 * 4;
	
	/**
	 * Default Image with appropriate sizing
	 */
	private Image defaultImg = getModel().getDefaultImage(imgWidth, imgHeight);
	
	public PlantSelection(Model m) {
		super(m, "Plant Selection", Windows.PlantSelection);
		
		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text("Please select the plants you'd like to have in your Garden");
		text.setWrappingWidth(View.getCanvasWidth());

		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));
		vbox.getChildren().addAll(text);

		centerBox = new HBox();

		canopySelection = new Accordion();
		
		accArr = new ArrayList<TitledPane>();
		
		for(Canopy c : Canopy.values()) {
			TitledPane tile = new TitledPane();
			tile.setText(c.getContains());
			accArr.add(tile);
		}

		canopySelection.getPanes().clear();
		for (TitledPane t : accArr) {
			t.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
					View.getTextSizeForButtonsAndText()));
			canopySelection.getPanes().add(t);
		}

		selectedPlantsBox = new FlowPane();
		selectedPlantsBox.setHgap(inset10);
		selectedPlantsBox.setVgap(inset10);
		selectedPlantsBox.setMinWidth(selectedPlantBoxMinWidth);
		selectedPlantsBox.setMinHeight(selectedPlantBoxMinHeight);

		scrollSelected = new ScrollPane();
		scrollPaneFormat(scrollSelected);
		
		scrollCanopies = new ScrollPane();
		scrollPaneFormat(scrollCanopies);
		scrollCanopies.setPrefSize(prefScrollWidth, prefScrollHeight);

		scrollSelected.setPrefSize(scrollSelectedWidth, scrollSelectedHeight);

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setHgap(gapBetweenButtons);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(centerBox,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
				
	}
	
	/**
	 * Similar to PlantInfo, allows this method to be called in Refresh and thus
	 * renew the new desired traits for each plant in the left hand side.
	 * @throws Exception 
	 */
	public void displaySelection() {
		
		setFilter();

		for(TitledPane t : accArr) {
			populateTile(t, 0);
		}
		
		scrollCanopies.setContent(canopySelection);
		
		addSelected();
		
		scrollSelected.setContent(selectedPlantsBox);
		
		centerBox.getChildren().clear();
		centerBox.getChildren().addAll(scrollCanopies, scrollSelected);
		
		createButtons();
		tilePane.getChildren().clear();
		tilePane.getChildren().addAll(back, ease, mainMenu, next);
		
		borderPane.setPadding(new Insets(5));
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(centerBox);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
		
	}

	/**
	 * Creates an Alarm if there are not plants that suit the given answers.
	 */
	public void setAlarm() {
		ButtonType automatic = new ButtonType("Easy Plant");
		ButtonType cont = new ButtonType("Ok");
		Alert warning = new Alert(AlertType.WARNING, "No Plant Options",cont, automatic);
		warning.setContentText("Your specifications in Questionnaire do not match any of our current plants!"
				+ "\n\nPlease either:"
				+ "\n\t- Click Ok to continue anyways"
				+ "\n\t- Click Easy Plant Button to automatically ease \n\t   the inputs");
		
		if(plantSelEmpty) {
			warning.showAndWait().ifPresent(response -> {
			     if (response == automatic) {
			         easeSelection();
			     }
			 });
		}
	}
	
	/**
	 * Formats the ScrollPane to the desired width, height, and padding.
	 * 
	 * @param ScrollPane
	 */
	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setPadding(new Insets(inset10));
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
	public void populateTile(TitledPane t, int pageNum){
		System.out.println("I was called " + pageNum);
		createFlowPane(t, pageNum);
		
	}
	
	/**
	 * Filters the native plants to find those that fit.
	 */
	public void setFilter(){
		filter.clear();
		
		Moisture m = getSession().getMoistureOfPlot();
		SoilTypes s = getSession().getSoilTypeOfPlot();
		double l = getSession().getSunlightOfPlot();
		
		HashSet<Colors> selected = this.getModel().getSession().getColorsUserSelected();
		
		for (Plant p : nativePlants) {
			boolean fits = false;
			
			if(p.getMoisture() == m || p.getMoisture() == null || m == null) {
				if(p.getSoilType() == s || p.getSoilType() == SoilTypes.ANY || s == SoilTypes.ANY) {
					if(p.getLight() == l || 
							(p.getLight() < (l + 0.2) && p.getLight() >= l ) 
							|| p.getLight() == -1.0 || l == -1.0) {
						fits = true;
					}
				} 
			}
			
			if (getSession().getSelectedPlants().contains(p)) {
				fits  = false;
			}
			
			if(fits) {
				fits = checkSeason(p);
			}
			
			if (fits) {
				fits = checkColors(p, selected);
			}
			
			if(fits) {
				plantSelEmpty = false;
				filter.add(p);
			}
			
		}
	}

	/**
	 * Function creates a flow pane (with Scroll) for the type of canopy selected.
	 * Filters the plants to match those desired.
	 * 
	 * @param canopy --> Takes in a canopy
	 * @throws Exception 
	 */
	public void createFlowPane(TitledPane t, int page){
		VBox show = new VBox();
		HBox but = new HBox();
				
		but.setPadding(new Insets(25));
		
		Canopy canopy = Canopy.getCanopyByContains(t.getText());
		
		FlowPane canopyFlow = new FlowPane();
		
		if(plantSelEmpty) {
			t.setContent(canopyFlow);
		}else {
			int forward = page + 1;
			int backward = page - 1;
			Button backBut = new Button("Previous");
			backBut.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					populateTile(t, backward);
				}
			});
			Button nextBut = new Button("More Plants");
			nextBut.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					populateTile(t, forward);
				}
			});
		
			formatButton(backBut);
			formatButton(nextBut);
					
			canopyPlant.clear();
			for(Plant p : filter) {
				if(p.getCanopy() == canopy) {
					canopyPlant.add(p);
				}
			}
			
			if(canopyPlant.isEmpty()) {
				nextBut.setVisible(false);
			}else {
				int startpage = page * maxPlantsPerPage;
				for(int i = startpage; i < startpage + maxPlantsPerPage && i < canopyPlant.size(); i++) {
					canopyFlow.getChildren().add(createPlantBox(canopyPlant.get(i)));
					if(i == canopyPlant.size() - 1) {
						nextBut.setVisible(false);
					}else {
						nextBut.setVisible(true);
					}
				}
				
				if(page == 0) {
					backBut.setVisible(false);
				}else {
					backBut.setVisible(true);
				}
				
				but.getChildren().addAll(backBut, nextBut);
				show.getChildren().addAll(canopyFlow, but);
				t.setContent(show);
			}
		}
	}
	
	public void formatButton(Button b) {
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

	/**
	 * Checks if the seasons for a plant match what the user desires.
	 * 
	 * @param Plant
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean checkSeason(Plant p){
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
	 * Checks if a given plant contains any of the selected colors from the given ArrayList.
	 * 
	 * @param p 		Plant to compare colors.
	 * @param selected 	Colors to compare plant colors to.
	 * @return 			Boolean on if the plant contains any of the selected colors.
	 */
	public boolean checkColors(Plant p, HashSet<Colors> selected) {
		HashSet<Colors> colors = p.getColors();
		for (Colors color : colors) {
			if (selected.contains(Colors.ANYCOLOR) || selected.contains(color)) {
				return true;
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
			plantImage = new Image(path, imgWidth, imgHeight, true, true, true);
		} else {
			// get a default image
			plantImage = defaultImg;
		}
		
		ImageView imageView = new ImageView(plantImage);
		imageView.setCache(true);
		imageView.setCacheHint(CacheHint.SPEED);
		
		Button infoButton = new Button("Info");
		infoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getModel().setPlantInfoPlant(p);
				switchToWindow(Windows.PlantInfo);
			}
		});
		
		Button addPlant = new Button();
		if (getSession().getSelectedPlants().contains(p)){ 
			addPlant.setText("Remove");
		} else {
			addPlant.setText("Add Plant");
		}
				
		HBox buttonHolder = new HBox();
		buttonHolder.getChildren().addAll(infoButton, addPlant);

		VBox imgButtonHolder = new VBox();
		imgButtonHolder.getChildren().addAll(imageView, new Text(p.getLatinName()), buttonHolder);
		
		addPlant.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				if (addPlant.getText().equals("Add Plant")) {
					if (!selectedPlantsBox.getChildren().contains(imgButtonHolder)) {
						selectedPlantsBox.getChildren().add(imgButtonHolder);
					}
					imageView.setEffect(null);
					getSession().getSelectedPlants().add(p);
					addPlant.setText("Remove");
				} else {
					ColorAdjust grayscale = new ColorAdjust();
					grayscale.setSaturation(-1);
					imageView.setEffect(grayscale);
					getSession().getSelectedPlants().remove(p);
					addPlant.setText("Add Plant");
				}
			}
		});
		return imgButtonHolder;
	}
	
	/**
	 * This displays all plants that are in the HashMap that the user desired from Plant Selection
	 */
	public void addSelected() {
		for(Plant plant : getSession().getSelectedPlants()) {
			selectedPlantsBox.getChildren().add(createPlantBox(plant));
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

		mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});
		
		ease = new Button("Easy Plant") ;
		Tooltip easeToolTip = new Tooltip(
				"Ease the Questionnaire Inputs to Add More Plant Options.");
		ease.setTooltip(easeToolTip);
		ease.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				easeSelection();
			}
		});
		
		List<Button> buttons = new ArrayList<Button>();
		buttons.add(back);
		buttons.add(ease);
		buttons.add(mainMenu);
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
	 * Automatic addition to make plants appear in plant selection.
	 */
	public void easeSelection() {
		getSession().setSunlightOfPlot(-1.0);
		ArrayList<Seasons> updatedSeason = getSession().getSeasonsUserSelected();
		updatedSeason.add(Seasons.SUMMER);
		getSession().setSeasonsUserSelected(updatedSeason);
		HashSet<Colors> updatedColor = getSession().getColorsUserSelected();
		updatedColor.add(Colors.BLUE);
		getSession().setColorsUserWants(updatedColor);
		switchToWindow(Windows.PlotDesign);
		switchToWindow(Windows.PlantSelection);
	}
	
	public void refresh() {
		System.out.println(getModel().getLastWindow().getEnum().name());
		if(getModel().getLastWindow().getEnum() == Windows.Questionnaire || 
				getModel().getLastWindow().getEnum() == Windows.PlotDesign) {
			plantSelEmpty = true;
			try {
				displaySelection();
			}catch(Exception e) {
				e.getMessage();
			}
			selectedPlantsBox.getChildren().clear();
			addSelected();
			if(getModel().getLastWindow().getEnum() == Windows.Questionnaire) {
				setAlarm();
			}
		} else {
			System.out.println(getModel().getLastWindow().getEnum().name());
			System.out.println("I was NOT called");
		}
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
