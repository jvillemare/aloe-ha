package udel.GardenProject.windows;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotPlant;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

/**
 * Heart of the application: Where the user can drag plants, obstacles, shade,
 * text boxes, and interact with their virtual, top-down plot.
 *
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
	private VBox vbox, leftDropdownVBox, autoRateVBox;

	private Text text;

	/**
	 * Used for labeling autorate bars.
	 */
	private Text animalsFedTxt, contBloomTxt, matchTxt, transitionTxt;

	/**
	 * Text above editPlotButton.
	 */
	private Text editPlotText;

	/**
	 * Used inside of leftDropdownVBox for the plants we will put in the plot.
	 */
	private TilePane tilePane;

	private int statistics[];

	/**
	 * Buttons in tilePane at the bottom of the screen.
	 */
	private Button backButton, saveButton, loadButton, nextButton;

	/**
	 * Used for when the user wants to edit the points of their plot.
	 */
	private Button editPlotButton;

	/**
	 * Holds the options for what the user wants to put in their plot.
	 */
	private HBox optionHBox;

	/**
	 * Used for toggling options to display.
	 */
	private ToggleGroup itemGroup;
	private ToggleButton existingPlants, selectedPlants, obstacles;

	private FlowPane flow;

	/**
	 * Used for scrolling in the flowPane for all the options the user can all to
	 * their plot.
	 */
	private ScrollPane scroll;

	/**
	 * Bars showing status of each autorate.
	 */
	private Rectangle animalsFedBar, contBloomBar, matchBar, transitionBar;

	/**
	 * Used to outline the center area
	 */
	private Rectangle box;

	/**
	 * The adjustable plot for the user to move around.
	 */
	private AdjustablePolygon poly;

	/**
	 * Used for placement of adjustable polygon and plants/obstacles etc.
	 */
	private Group group;
	double xbound;

	/**
	 * Create a new PlotDesign window instance.
	 *
	 * @param m Model
	 */
	public PlotDesign(Model m) {
		super(m, "Plot Designer");

		borderPane = new BorderPane();
		vbox = new VBox();
		leftDropdownVBox = new VBox();
		leftDropdownVBox.setBackground(new Background(new BackgroundFill(Color.SEASHELL, null, null)));
		tilePane = new TilePane();
		group = new Group();

		box = new Rectangle(620, 550);
		box.setStroke(Color.BLACK);
		box.setFill(Color.WHITE);
		group.getChildren().add(box);

		text = new Text(
				"Welcome to the Plot Design! Place all of your plants and objects on your plot to complete your garden!");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.setMaxWidth(1100);
		vbox.getChildren().addAll(text);

		itemGroup = new ToggleGroup();
		existingPlants = new ToggleButton("Existing Plants");
		selectedPlants = new ToggleButton("Selected Plants");
		obstacles = new ToggleButton("Obstacles");

		existingPlants.setToggleGroup(itemGroup);
		selectedPlants.setToggleGroup(itemGroup);
		obstacles.setToggleGroup(itemGroup);

		optionHBox = new HBox(existingPlants, selectedPlants, obstacles);

		existingPlants.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("ExistingPlants: selected");
				/**
				 * TODO: add a function here to add the correct tiles
				 */
			}
		});

		selectedPlants.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("SelectedPlants: selected");
				/**
				 * TODO: add a function here to add the correct tiles
				 */
			}
		});

		obstacles.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Obstacles: selected");
				/**
				 * TODO: add a function here to add the correct tiles
				 */
			}
		});

		flow = new FlowPane();
		flow.setPadding(new Insets(5, 5, 5, 5));
		flow.setVgap(10);
		flow.setHgap(10);
		flow.setPrefWrapLength(270);
		flow.setStyle("-fx-background-color: DAE6F3;");

		// This is used for testing purposes

		Image pages[] = new Image[40];
		for (int i = 0; i < 40; i++) {
			pages[i] = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 350, 100, true, true);
			ImageView imageView = new ImageView(pages[i]);
			flow.getChildren().add(imageView);
						
			imageView.setOnMouseDragged(getHandlerForDrag());	
			imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("image released");

					Node n = (Node) event.getSource();

					if (n.getTranslateX() >= 150) {

						n.setTranslateX(imageView.getX());
						n.setTranslateY(imageView.getY());
						ImageView iv = new ImageView();
						Image im = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 350, 100, true,
								true);
						iv.setImage(im);
						iv.setPreserveRatio(true);
						iv.setFitHeight(100);
						iv.setTranslateX(iv.getX());
						iv.setTranslateY(iv.getY());
						flow.getChildren().add(iv);

					} else if (n.getTranslateX() < 150) {
						n.setVisible(false);
					}
				}
			});

		}

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVmax(440);
		scroll.setPrefSize(850, 600);

		scroll.setContent(flow);

		animalsFedTxt = new Text("Animals Fed");
		contBloomTxt = new Text("Continuous Bloom");
		matchTxt = new Text("Matches Garden");
		transitionTxt = new Text("Transition");

		animalsFedTxt.setStyle("-fx-font-size: 20px;");
		contBloomTxt.setStyle("-fx-font-size: 20px;");
		matchTxt.setStyle("-fx-font-size: 20px;");
		transitionTxt.setStyle("-fx-font-size: 20px;");

		animalsFedBar = new Rectangle(200, 10);
		contBloomBar = new Rectangle(200, 10);
		matchBar = new Rectangle(200, 10);
		transitionBar = new Rectangle(200, 10);

		animalsFedBar.setStroke(Color.BLACK);
		contBloomBar.setStroke(Color.BLACK);
		matchBar.setStroke(Color.BLACK);
		transitionBar.setStroke(Color.BLACK);

		animalsFedBar.setFill(Color.WHITE);
		contBloomBar.setFill(Color.WHITE);
		matchBar.setFill(Color.WHITE);
		transitionBar.setFill(Color.WHITE);

		autoRateVBox = new VBox();
		autoRateVBox.setPrefWidth(255);
		autoRateVBox.setPrefHeight(550);
		autoRateVBox.setPadding(new Insets(5, 5, 5, 5));

		editPlotText = new Text("\nAddPlot");
		editPlotText.setStyle("-fx-font-size: 20px;");
		editPlotButton = new Button("Edit Plot");
		editPlotButton.setPadding(new Insets(10, 5, 10, 5));
		editPlotButton.setStyle("-fx-font-size: 20px;");

		editPlotButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Edit Plot Button");

				poly = new AdjustablePolygon(Color.GREEN, Color.YELLOW, 40, 40);
				group.getChildren().add(poly.getPolygon());
				group.getChildren().addAll(poly.getAnchors());
				autoRateVBox.getChildren().addAll(poly.genButton(400, 100));

			}
		});

		Text goToPlantData = new Text("\nGet More Plants");
		goToPlantData.setStyle("-fx-font-size: 20px;");
		Button plantDataButton = new Button("Plant Database");
		plantDataButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.AllPlants);
			}
		});

		plantDataButton.setPadding(new Insets(10, 5, 10, 5));
		plantDataButton.setStyle("-fx-font-size: 20px;");

		autoRateVBox.getChildren().addAll(animalsFedTxt, animalsFedBar, contBloomTxt, contBloomBar, matchTxt, matchBar,
				transitionTxt, transitionBar, editPlotText, editPlotButton,
				/* poly.genButton(400, 100), */ goToPlantData, plantDataButton);

		leftDropdownVBox.setPrefWidth(255);
		leftDropdownVBox.setPrefHeight(550);
		leftDropdownVBox.getChildren().addAll(optionHBox, scroll);

		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backButton, saveButton, loadButton, nextButton);

		borderPane.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, null, null)));
		borderPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setTop(vbox);
		borderPane.setRight(autoRateVBox);
		borderPane.setLeft(leftDropdownVBox);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(group);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 1100, 650);
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
				System.out.println("Go Back: going to PlantSelection window");
				switchToWindow(Windows.PlantSelection);
			}
		});

		saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Save: saving work");
				// add saving function
			}
		});

		loadButton = new Button("Load");
		loadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Load: loading garden");
				// add loading function
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Next: going to seasonView window");
				switchToWindow(Windows.SeasonView);
			}
		});
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
			if(p.getPlant().getDescription().contains("Berry/Nut/Seed Product: Yes"))
				animalsFed++;
		}
		
		// garbage collector cleanup
		thePlot = null;
		p = null;
		
		return (double)animalsFed / (double)plantCount;
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
	 * TODO: What does this do?...
	 */
	public void getObstacle() {

	}

	/**
	 * TODO: What does this do?...
	 * 
	 * @param obstacle	...
	 * @return	...
	 */
	public Object setObstacle(Object obstacle) {
		return null;
	}

	/**
	 * What does this do?...
	 * 
	 * @param p	...
	 * @return	...
	 */
	public Plant setPlant(Plant p) {
		return p;
	}

	/**
	 * TODO: What does this do?...
	 */
	public void getPlant() {

	}
	
	/**
	 * TODO: What does this do?....
	 * @param img	...
	 */
	public void addimage(ImageView img) {
		group.getChildren().add(img);
		System.out.println("added");
	}
	
	/**
	 * TODO: What does this do? ...
	 * @param event	...
	 */
	public void drag(MouseEvent event) {
		System.out.println("dragging image");
		ImageView n = (ImageView)event.getSource();
		ImageView tmp=new ImageView(n.getImage());
		System.out.println(n.getX());
		xbound=n.getParent().getLayoutBounds().getMaxX();
		System.out.println(n.getScene().getWidth());
		if(!n.getParent().getLayoutBounds().contains(new Point2D(n.getX()+event.getX(),n.getY()+event.getY()))) {
			addimage(tmp);
		}
		
	}

	/**
	 * TODO: What does this do? ...
	 * @return	...
	 */
	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
}
