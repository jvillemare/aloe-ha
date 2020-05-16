package udel.GardenProject.windows;

import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import udel.GardenProject.enums.PlotObjects;
import udel.GardenProject.enums.PlotObjectsFactory;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotPlant;
import udel.GardenProject.plotObjects.PlotTextLabel;
import udel.GardenProject.plotObjects.lines.PlotFence;
import udel.GardenProject.plotObjects.lines.PlotPath;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;
import udel.GardenProject.plotObjects.polygons.PlotForest;
import udel.GardenProject.plotObjects.polygons.PlotPatio;
import udel.GardenProject.plotObjects.polygons.PlotPlayground;
import udel.GardenProject.plotObjects.polygons.PlotPool;
import udel.GardenProject.plotObjects.polygons.PlotRoad;
import udel.GardenProject.plotObjects.polygons.PlotRock;
import udel.GardenProject.plotObjects.polygons.PlotShed;
import udel.GardenProject.plotObjects.special.PlotBench;
import udel.GardenProject.plotObjects.special.PlotBirdBath;
import udel.GardenProject.plotObjects.special.PlotFlamingo;
import udel.GardenProject.plotObjects.special.PlotGnome;
import udel.GardenProject.plotObjects.special.PlotOther;

/**
 * Heart of the application: Where the user can drag plants, obstacles, shade,
 * text boxes, and interact with their virtual, top-down plot.
 *
 * @version 1.0
 * @author Team 0
 */
public class PlotDesign extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for the overall layout.
	 */
	private BorderPane borderPane;

	/**
	 * Used to hold text, left and right panels.
	 */
	private VBox vbox, autoRateVBox;

	/**
	 * TODO: What is this? Change the variable name to something other than
	 * text since it's not that helpful.
	 */
	private Text text;

	/**
	 * Used for labeling auto rate bars.
	 */
	private Text animalsFedTxt, contBloomTxt, matchTxt, transitionTxt;

	/**
	 * Used inside of leftDropdownVBox for the plants we will put in the plot.
	 */
	private TilePane tilePane;

	/**
	 * Buttons in tilePane at the bottom of the screen.
	 */
	private Button backButton, saveButton, mainMenu, nextButton;

	/**
	 * Holds the options for what the user wants to put in their plot.
	 */
	private Rectangle box;

	/**
	 * Used for placement of adjustable polygon and plants/obstacles etc
	 */
	private Group group;

	/**
	 * ScrollPane created for the canopy drop down choices
	 */
	private ScrollPane scrollSelections;

	/**
	 * Used for scrolling in the flowPane for all the options the user can all to
	 * their plot.
	 */
	private boolean create = true;
	
	/**
	 * Use in drag for control between different handler.
	 */
	private ImageView tmp;

	/**
	 * Accordion for existing, selected, and obstacles
	 */
	private Accordion choicesAccordian;

	/**
	 * Used to add in Titled Panes to accordion.
	 */
	private List<TitledPane> accArr;

	/**
	 * Adjustments to buttons and panes.
	 */
	private int autoRateBarWidth = 200;
	private int autoRateBarHeight = 10;
	private int autoRateBoxWidth = 255;
	private int autoRateBoxHeight = 550;
	private int gapBetweenButtons = 100;
	private int borderSideMargins = 200;
	private int borderTopAndBottonMargin = 90;
	private int backgroundWidthAndHeight = 100;
	private int rectWidth = View.getCanvasWidth() / 5 * 3;
	private int rectHeight = View.getCanvasHeight() / 7 * 6;
	private int scrollPrefWidth = View.getCanvasWidth() / 5 + 30;
	private int scrollPrefHeight = View.getCanvasHeight() / 5 * 4;
	private int flowPaneWidthAdjustment = View.getCanvasWidth() / 9;
	private int allPlantsButtonFontSize = 17;
	private int tilePaneWidthAdjustment = 20;
	private int allPlantsButtonWidth = 170;
	private int imageSize = 100;
	private int inset10 = 10;
	private int inset20 = 20;
	private int inset5 = 5;

	/**
	 * Create a new PlotDesign window instance.
	 *
	 * @param m Model
	 */
	public PlotDesign(Model m) {
		super(m, "Plot Designer", Windows.PlotDesign);

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();
		group = new Group();

		createCenterBox();

		text = new Text(
				"Welcome to the Plot Design! Place all of your plants and objects on your plot to complete your garden!");
		text.setWrappingWidth(View.getCanvasWidth());
		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));

		vbox.setPadding(new Insets(0, 0, inset20, inset5));
		vbox.getChildren().addAll(text);

		animalsFedTxt = new Text("Animals Fed");
		contBloomTxt = new Text("Continuous Bloom");
		matchTxt = new Text("Matches Garden");
		transitionTxt = new Text("Transition");

		ArrayList<Text> autoRatings = new ArrayList<Text>();
		autoRatings.add(animalsFedTxt);
		autoRatings.add(contBloomTxt);
		autoRatings.add(matchTxt);
		autoRatings.add(transitionTxt);

		autoRateVBox = new VBox();
		autoRateVBox.setPrefWidth(autoRateBoxWidth);
		autoRateVBox.setPrefHeight(autoRateBoxHeight);
		autoRateVBox.setPadding(new Insets(inset5));

		for (Text t : autoRatings) {
			t.setStyle("-fx-font-size: 20px;");
			Rectangle r = new Rectangle(autoRateBarWidth, autoRateBarHeight);
			r.setStroke(Color.BLACK);
			r.setFill(Color.WHITE);
			autoRateVBox.getChildren().addAll(t, r);
		}

		Text goToPlantData = new Text("\nGet More Plants");
		goToPlantData.setStyle("-fx-font-size: 20px;");
		Button plantDataButton = new Button("Plant Database");

		plantDataButton
				.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), allPlantsButtonFontSize));
		plantDataButton.setStyle(View.getPinkBackgroundStyle() + View.getBlackTextFill() + "-fx-border-width: 1;"
				+ "-fx-border-color: #000000;");
		plantDataButton.setPrefWidth(allPlantsButtonWidth);
		plantDataButton.setPadding(new Insets(inset10, inset5, inset10, inset5));

		DropShadow shadow = new DropShadow();
		plantDataButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				plantDataButton.setEffect(shadow);
				plantDataButton.setStyle(View.getWhiteBackgroundStyle() + View.getBlackTextFill());
			}
		});
		plantDataButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				plantDataButton.setEffect(null);
				plantDataButton.setStyle(View.getPinkBackgroundStyle() + View.getBlackTextFill()
						+ "-fx-border-width: 1;" + "-fx-border-color: #000000;");
			}
		});
		plantDataButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.AllPlants);
			}
		});

		autoRateVBox.getChildren().addAll(goToPlantData, plantDataButton);

		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(inset5));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(backButton, mainMenu, saveButton, nextButton);

		choicesAccordian = new Accordion();
		accArr = new ArrayList<TitledPane>();
		populateTiles(accArr);

		for (TitledPane t : accArr) {
			t.setFont(getModel().getHackBold20());
			choicesAccordian.getPanes().add(t);
		}

		scrollSelections = new ScrollPane();
		scrollPaneFormat(scrollSelections);
		scrollSelections.setPrefSize(scrollPrefWidth, scrollPrefHeight);
		scrollSelections.setContent(choicesAccordian);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundWidthAndHeight, backgroundWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(box,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
		borderPane.setPadding(new Insets(inset10));
		borderPane.setTop(vbox);
		borderPane.setRight(autoRateVBox);
		borderPane.setLeft(scrollSelections);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(group);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Formats the scroll Pane with the necessary attributes to be consistent.
	 * 
	 * @param scroll --> a scroll pane.
	 */
	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setMaxWidth(scrollPrefWidth);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");
	}

	/**
	 * Creates the white box in the center of the screen.
	 */
	public void createCenterBox() {
		box = new Rectangle(rectWidth, rectHeight);
		box.setStroke(Color.BLACK);
		box.setFill(Color.WHITE);
		group.getChildren().add(box);
	}

	/**
	 * Creates the Titled panes that need to be added in the accordion.
	 * @param accArr ArrayList of TitledPanes.
	 * @throws Exception.
	 */
	public void populateTiles(List<TitledPane> accArr) {
		System.out.println("POPULATE TILES CALLED");
		FlowPane existingFlow = createPlantFlow(getSession().getExistingPlants());
		TitledPane existing = new TitledPane("Existing Plants  ", existingFlow);
		accArr.add(existing);

		FlowPane selectedFlow = createPlantFlow(getSession().getSelectedPlants());
		TitledPane selected = new TitledPane("Selected Plants", selectedFlow);
		accArr.add(selected);

		FlowPane obstaclesFlow = createObstacleFlow(getSession().getSelectedPlotObjects());
		TitledPane obstacles = new TitledPane("Garden Objects", obstaclesFlow);
		accArr.add(obstacles);
	}

	/**
	 * Sets the specification for the titled pane and sets it into the 
	 * accordion.
	 * @param s    The title of the drop down menu.
	 * @param flow The Flow Pane of plant or objects.
	 */
	public void createTitledPane(String s, FlowPane flow) {
		TitledPane titledPane = new TitledPane(s, flow);
		titledPane.setMaxWidth(scrollPrefWidth - tilePaneWidthAdjustment);
		titledPane.setFont(getModel().getHackBold20());
		choicesAccordian.getPanes().add(titledPane);
	}

	/**
	 * Creates the flow pane for the PlotObjects (non-PlotPlants) the user 
	 * chose.
	 * 
	 * @param obj ArrayList of Plot Objects.
	 * @return Flow Pane.
	 */
	public FlowPane createObstacleFlow(ArrayList<PlotObjects> obj) {
		FlowPane flow = new FlowPane();
		flow.setMaxWidth(flowPaneWidthAdjustment);
		flow.setPrefWidth(flowPaneWidthAdjustment);
		flow.setHgap(inset10);
		flow.setHgap(inset10);
		
		PlotObjectsFactory pof = new PlotObjectsFactory();

		for (PlotObjects p : obj) {
			Node renderedPlotObject = pof.renderInAccordion(p);

			renderedPlotObject.setOnMouseDragged(getHandlerForDrag());
			renderedPlotObject.setOnMouseReleased(getHandlerForRelease(p));

			flow.getChildren().add(renderedPlotObject);
		}
		return flow;
	}

	/**
	 * Creates Flow Pane for a HashSet of Plants.
	 * 
	 * @param plants HashSet of Plants.
	 * @return FlowPane
	 */
	public FlowPane createPlantFlow(HashSet<Plant> plants) {
		Thread.currentThread().getStackTrace();
		System.out.println("starting with plants.size=" + plants.size());
		FlowPane flow = new FlowPane();
		flow.setMaxWidth(flowPaneWidthAdjustment);
		flow.setPrefWidth(flowPaneWidthAdjustment);
		flow.setHgap(inset10);
		flow.setHgap(inset10);

		Iterator<Plant> plantIter = plants.iterator();
		System.out.println("after creating iterator");
		while (plantIter.hasNext()) {
			Plant p = plantIter.next();
			System.out.println("PlotDesign.createPlantFlow: adding " + p.getLatinName());
			Node plantRepresentation = p.renderInAccordion(
					getSession().getWidthOfUserPlot(), 
					getSession().getLengthOfUserPlot());

			String name = p.getLatinName();
			Tooltip.install(plantRepresentation, new Tooltip(name));

			plantRepresentation.setOnMouseDragged(getHandlerForDrag(p));
			plantRepresentation.setOnMouseReleased(getHandlerForRelease(null));

			VBox imageAndNameHolder = new VBox(plantRepresentation, new Text(name));
			flow.getChildren().add(imageAndNameHolder);
		}
		//if(plants.size() == 0)
			//
		return flow;
	}

	/**
	 * Creates the buttons that will be added to the tilePane at the bottom of the
	 * screen
	 */
	public void createButtons() {
		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlantSelection);
			}
		});

		mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				javafx.stage.Window scene2 = null;
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save 'Aloe-Ha' Garden Project");
				fileChooser.setInitialFileName(getSession().getPlotName());
				fileChooser.getExtensionFilters()
						.addAll(new FileChooser.ExtensionFilter("GARDENPROJECT", "*.gardenproject"));

				File file = fileChooser.showSaveDialog(scene2);
				if (file != null) {

					System.out.println(getModel().saveSession(file.getAbsolutePath()));
					getModel().saveSession(file.getAbsolutePath());
				}
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.SeasonView);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(backButton);
		buttons.add(mainMenu);
		buttons.add(saveButton);
		buttons.add(nextButton);

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
	 * Determine what percentage of plants have food sources for any animal. 
	 * Looks at the plant descriptions to see if the plant has seeds, nuts, or
	 * fruits that animals could feed on.
	 * 
	 * @return	Percentage expressed as a decimal from 0.0 (0%) to 1.0 (100%).
	 * 			0.0 means no plants feed animals, and 1.0 means all plants in
	 * 			the plot feeds animals.
	 */
	protected double evaluateAnimalsFed() {
		ArrayList<PlotObject> thePlot = getModel().getSession().getPlot();
		PlotPlant p;
		
		int plantCount = 0;
		int animalsFed = 0;
		
		for(PlotObject pObject : thePlot) {
			// This is a little bit hacky, but you tell me what's a better
			// solution. I thought about it for a few hours but couldn't come up
			// with anything better.
			try {
				p = (PlotPlant)pObject;
			} catch(ClassCastException e) {
				continue;
			}
			
			plantCount++;
			
			// TODO: What if it does not even have "Berry...Product" in description?
			if(isAnimalFed(p.getPlant()))
				animalsFed++;
		}
		
		// garbage collector cleanup
		thePlot = null;
		p = null;
		
		return (double)animalsFed / (double)plantCount;
	}
	
	/**
	 * Abstracted out animal fed characteristic of a plot so plant PlotObjects
	 * that don't feed animals can be highlighted specifically.
	 * @param p	Plant object to check.
	 * @return	True if a plant feeds an animal, False if not.
	 */
	protected boolean isAnimalFed(Plant p) {
		return p.getDescription().contains("Berry/Nut/Seed Product: Yes");
	}
	
	/**
	 * Determine what percentage of the year has plants in bloom. Plants have 
	 * bloom time stored as a 12 element boolean array, where every month
	 * corresponds to a month of the year.<br><br>
	 * 
	 * This method ORs all the booleans in the Plant's bloom time into a master
	 * boolean array, and this method determines what percentage of the latter
	 * boolean array is true.
	 * 
	 * @return	Percentage expressed as a decimal from 0.0 (0%) to 1.0 (100%).
	 * 			0.0 means there's no bloom in the plot, and 1.0 every month of
	 * 			the year has a plant in bloom.
	 */
	protected double evaluateContinousBloom() {
		ArrayList<PlotObject> thePlot = getModel().getSession().getPlot();
		
		boolean[] bloom = new boolean[12];
		PlotPlant p;
		boolean[] plantsBloom;
		
		for(PlotObject pObject : thePlot) {
			// Hacky solution that works. Check out explanation in
			//		evaluatedAnimalsFed()
			try {
				p = (PlotPlant)pObject;
			} catch(ClassCastException e) {
				continue;
			}
			
			plantsBloom = p.getPlant().getBloomTime();
			
			for(int i = 0; i < plantsBloom.length; i++) {
				bloom[i] = bloom[i] || plantsBloom[i];
			}
		}
		
		double monthsPlantsInBloom = 0.0;
		
		for(boolean monthHasPlantInBloom : bloom)
			if(monthHasPlantInBloom)
				monthsPlantsInBloom += 1.0;
		
		// garbage collector cleanup
		thePlot = null;
		bloom = null;
		p = null;
		plantsBloom = null;
		
		return monthsPlantsInBloom / 12.0;
	}
	
	/**
	 * Determine the percentage of plants and the percentage of each plant that
	 * matches the chosen plot characteristics specified by the user in the
	 * Questionnaire. Meaning, for every single Plant, what percentage of that
	 * plant matches what the user is looking for?<br><br>
	 * 
	 * If a user wants a yellow plant that blooms all year round, and there is a
	 * plant matching that description, that plant has a score of 
	 * <code>1.0</code>. If its a yellow plant that blooms only part of the
	 * year, it has a score of <code>0.5</code>. The average of these plant
	 * scores is calculated for every plant.
	 * 
	 * @return	Percentage expressed as a decimal from 0.0 (0%) to 1.0 (100%).
	 */
	protected double evaluateMatchesGarden() {
		return 0.0;
	}
	
	/**
	 * <b>NOTE:</b> This method should only be run every so often because of its 
	 * complexity and cost.<br><br>
	 *
	 * Checks every plant in the plot, and determines all the PlotObjects that 
	 * are closest and furthest away to that plant. Assume that an 
	 * <i>average</i> plant requires about 4 hours of sun light a day.
	 * 
	 *  ...Based on the angle of the sun
	 * and the surrounding plan ... if any PlotObject is greater than the Plants
	 * height * 2 then say its transition isn't good.
	 * 
	 * @return	Percentage expressed as a decimal from 0.0 (0%) to 1.0 (100%).
	 */
	protected double evaluateTransition() {
		ArrayList<PlotObject> thePlot = getModel().getSession().getPlot();
		
		double degreesToNorth = 0.0; // where is true north?
		double sunlightWindow = 15.0; // how much does the sun-rise/set amplitude vary?
		double windowResolution = 5.0; // increment of vectors to take for window intersection
		
		int plantCount = 0;
		double averageLight = 0.0;
		
		PlotPlant p;
		
		for(PlotObject pObject : thePlot) {
			// Hacky solution that works. Check out explanation in
			//		evaluatedAnimalsFed()
			try {
				p = (PlotPlant)pObject;
			} catch(ClassCastException e) {
				continue;
			}
			
			// how much light do the plot objects block
			double totalShadeEffect = 0.0;
			
			for(PlotObject obstacle : thePlot) {
				if(obstacle.equals(p))
					continue; // don't compare P against itself
				double obstacleDistance = distanceIn2d(p.getPlotX(), 
						p.getPlotY(), obstacle.getPlotX(), obstacle.getPlotY());
				if(obstacleDistance > obstacle.getHeight())
					continue; // is the obstacle within shadow range?
				
				double currentVector = windowResolution;
				boolean isIntersecting = false;
				
				while(currentVector > 0.0) {
					if(lineIntersectsCircle(p.getPlotX(), p.getPlotY(),
							// TODO: line below has to change according to currentVector
							obstacle.getPlotX(), obstacle.getPlotY(), 
							obstacle.getPlotX(), obstacle.getPlotY(), 
							obstacle.getRadius())) {
						isIntersecting = true;
						break;
					}
					currentVector -= windowResolution;
				}
				
				if(isIntersecting) {
					// as obstacle height increases, so does its shadow
					double obstacleShadeEffect = 
							(1 - (p.getHeight() / obstacle.getHeight()));
					// the closer the obstacle, the more effect its shadow has
					obstacleShadeEffect *= Math.cos(
							((obstacleDistance/obstacle.getHeight()) * Math.PI)/2.0
							);
					// what percentage of sunlight view does this object block?
					obstacleShadeEffect *= (180 * obstacle.getRadius()) / 
							(Math.PI * obstacleDistance);
					// plot object can only block sunlight from east/west, not both
					obstacleShadeEffect *= 0.5;
					totalShadeEffect += obstacleShadeEffect;
				}
			}
			
			if(1.0 - totalShadeEffect < p.getPlant().getLight()) {
				// plant is getting less light than it needs
				// calculate what percentage of light it is getting
				averageLight += (1.0 - totalShadeEffect) / p.getPlant().getLight();
			} else {
				// plant is getting enough light
				averageLight += 1.0;
			}
			plantCount++;
		}
		
		// garbage collector cleanup
		thePlot = null;
		p = null;
		
		if(averageLight != 0.0)
			return averageLight / (double)plantCount;
		else
			return 0.0; // no plants to check
	}
	
	/**
	 * Determine if a line intersects a circle.
	 * @param x1	X point of the first line.
	 * @param y1	Y point of the first line.
	 * @param x2	X point of the second line.
	 * @param y2	Y point of the second line.
	 * @param circleX
	 * @param circleY
	 * @param circleR
	 * @return True if the line intersects a circle, False if not.
	 * @see <code>windows.PlotDesign.evaluateTransition()</code>
	 */
	private boolean lineIntersectsCircle(double x1, double y1, double x2, 
			double y2, double circleX, double circleY, double circleR) {
		return (Math.abs((x2 - x1)*circleY +  circleX*(y1 -     
			       y2) + (x1 - x2)*y1 +
			       (y1 - y2)*x1)/ Math.sqrt(Math.pow((x2 - x1), 2) +
			       Math.pow((y1 - y2), 2)) <= circleR);
	}
	
	/**
	 * Determine distance between two points.
	 * @param x1	X of first point.
	 * @param y1	Y of first point.
	 * @param x2	X of second point.
	 * @param y2	Y of second point.
	 * @return Distance between two points as a double.
	 * @see <code>windows.PlotDesign.evaluateTransition()</code>
	 */
	public double distanceIn2d(double x1, double y1,
			double x2, double y2) {       
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

	/**
	 * <b>NOTE:</b> Does NOT add PlotObject to <code>plot</code> attribute in
	 * Session. Only adds a plot object to the interface. This is because if
	 * this method did add the passed PlotObject to the <code>plot</code>, then
	 * PlotDesign's <code>refresh()</code> would not work because it add already
	 * existing PlotObjects to the plot.<br><br>
	 * 
	 * Take in a PlotObject, call its <code>render()</code> method, and set the
	 * returned Node's position and attach the necessary handlers to update its
	 * position.
	 * 
	 * @param po  PlotObject being added to the plot.
	 * @param x   Horizontal coordinate for the image.
	 * @param y   Vertical coordinate for the image.
	 */
	public void addPlotObjectToInterface(PlotObject po, double x, double y) {
		po.setPlotX(x);
		po.setPlotY(y);
		Node plotObjectRepresentation = po.render();
		plotObjectRepresentation.setTranslateX(x);
		plotObjectRepresentation.setTranslateY(y);
		if(!po.getUseDefaultDragHandler()){
			plotObjectRepresentation.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					double newX = plotObjectRepresentation.getTranslateX() + event.getX();
					double newY = plotObjectRepresentation.getTranslateY() + event.getY();
					if (newX > 0 && newX < group.getLayoutBounds().getWidth() - po.getRenderWidth()) {
						plotObjectRepresentation.setTranslateX(newX);
						po.setPlotX(newX);
					}
					if (newY > 0 && newY < group.getLayoutBounds().getHeight() - po.getRenderHeight()) {
						plotObjectRepresentation.setTranslateY(newY);
						po.setPlotY(newY);
					}
				}
			});
			group.getChildren().add(plotObjectRepresentation);
		}
		else {
			group.getChildren().addAll(((Group)plotObjectRepresentation).getChildren());
		}
	}

	/**
	 * Create a temporary ImageView to follow around the mouse during drag.
	 * @param event
	 */
	public void dragTemporaryImage(MouseEvent event, Plant p) {
		if(p != null && getModel().getPlotDesignDraggingPlant() == null)
			getModel().setPlotDesignDraggingPlant(p);
		
		ImageView n = (ImageView) event.getSource();
		if (create) {
			tmp = new ImageView(n.getImage());
			root.getChildren().add(tmp);
			create = false;
		}
		tmp.setLayoutX(n.getLayoutX() + event.getX());
		tmp.setLayoutY(n.getLayoutY() + event.getY());
	}

	public EventHandler getHandlerForDrag() {
		return event -> dragTemporaryImage((MouseEvent) event, null);
	}
	
	public EventHandler getHandlerForDrag(Plant p) {
		return event -> dragTemporaryImage((MouseEvent) event, p);
	}

	/**
	 * When user lets go of mouse on drag event, drop in a new plot object
	 * where they released their mouse.
	 * @param event		...
	 * @param po		...
	 */
	public void releaseTemporaryImage(MouseEvent event, PlotObjects po) {
		ImageView n = (ImageView) event.getSource();
		create = true;
		double newX = tmp.getLayoutX() - n.getParent().getLayoutBounds().getWidth();
		double newY = tmp.getLayoutY() - vbox.getLayoutBounds().getHeight();
		if (group.contains(newX, newY)) {
			PlotObject plotObjectToAdd;
			if(po == null) {
				plotObjectToAdd = new PlotPlant(getModel(), getModel().getPlotDesignDraggingPlant(), newX, newY);
				getModel().setPlotDesignDraggingPlant(null);
			} else {
				switch(po) {
					case Bench:
						plotObjectToAdd = new PlotBench(getModel(), newX, newY);
						break;
					case BirdBath:
						plotObjectToAdd = new PlotBirdBath(getModel(), newX, newY);
						break;
					case Fence: // assume 6.0 foot high fence
						plotObjectToAdd = new PlotFence(getModel(), newX, newY, 6.0);
						break;
					case Flamingo:
						plotObjectToAdd = new PlotFlamingo(getModel(), newX, newY);
						break;
					case Forest:
						plotObjectToAdd = new PlotForest(getModel(), newX, newY);
						break;
					case Gnome:
						plotObjectToAdd = new PlotGnome(getModel(), newX, newY);
						break;
					case Other:
						plotObjectToAdd = new PlotOther(getModel(), newX, newY);
						break;
					case Path:
						plotObjectToAdd = new PlotPath(getModel(), newX, newY);
						break;
					case Patio:
						plotObjectToAdd = new PlotPatio(getModel(), newX, newY);
						break;
					case Playground:
						plotObjectToAdd = new PlotPlayground(getModel(), newX, newY);
						break;
					case Pool:
						plotObjectToAdd = new PlotPool(getModel(), newX, newY);
						break;
					case Road:
						plotObjectToAdd = new PlotRoad(getModel(), newX, newY);
						break;
					case Rock:
						plotObjectToAdd = new PlotRock(getModel(), newX, newY);
						break;
					case Shed:
						plotObjectToAdd = new PlotShed(getModel(), newX, newY);
						break;
					case TextLabel:
						// TODO: Prompt a user with a textbox in a separate stage
						//			window asking what they would like the text
						//			label to say
						plotObjectToAdd = new PlotTextLabel(getModel(), newX, newY, "FIX ME");
						break;
					default:
						plotObjectToAdd = new PlotOther(getModel(), newX, newY);
						break;
				}
				
			}
			getSession().getPlot().add(plotObjectToAdd);
			addPlotObjectToInterface(plotObjectToAdd, newX, newY);
		}
		root.getChildren().remove(tmp);
	}

	/**
	 * TODO: ...?
	 * @param po
	 * @param principal
	 * @return
	 */
	public EventHandler getHandlerForRelease(PlotObjects po) {
		return event -> releaseTemporaryImage((MouseEvent) event, po);
	}

	/**
	 * Remove everything from the flow panes, the center box, and the auto rate 
	 * boxes and add info back in again for the correct session
	 */
	@Override
	public void refresh() {
		accArr.clear();
		choicesAccordian.getPanes().clear();
		populateTiles(accArr);
		
		for (TitledPane t : accArr) {
			t.setFont(getModel().getHackBold20());
			choicesAccordian.getPanes().add(t);
		}

		group.getChildren().clear();
		createCenterBox();

		for (PlotObject po : getSession().getPlot()) {
			addPlotObjectToInterface(po, po.getPlotX(), po.getPlotY());
		}
		
		// TODO: Remove stuff from auto rate box and add back in
	}

}
