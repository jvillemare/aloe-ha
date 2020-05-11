package udel.GardenProject.windows;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.Session;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;
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
	 * Used for the overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Used to hold text, left and right panels
	 */
	private VBox vbox, autoRateVBox;

	private Text text;

	/**
	 * Used for labeling autorate bars
	 */
	private Text animalsFedTxt, contBloomTxt, matchTxt, transitionTxt;

	/**
	 * Text above editPlotButton
	 */
	private Text editPlotText;

	/**
	 * Used inside of leftDropdownVBox for the plants we will put in the plot
	 */
	private TilePane tilePane;

	/**
	 * Buttons in tilePane at the bottom of the screen
	 */
	private Button backButton, saveButton, mainMenu, nextButton;

	/**
	 * Used for when the user wants to edit the points of their plot
	 */
	private Button editPlotButton;

	/**
	 * Used to outline the center area
	 */
	private Rectangle box;

	/**
	 * The adjustable plot for the user to move around.
	 */
	private AdjustablePolygon poly;

	/**
	 * Used for placement of adjustable polygon and plants/obstacles etc
	 */
	private Group group;

	/**
	 * ScrollPane created for the canopy drop down choices
	 */
	private ScrollPane scrollSelections;
	
	

	/**
	 * Adjustments to buttons and panes
	 */
	private int autoRateBarWidth = 200;
	private int autoRateBarHeight = 10;
	private int autoRateBoxWidth = 255;
	private int autoRateBoxHeight = 550;
	private int gapBetweenButtons = 100;
	private int borderSideMargins = 200;
	
	/**
	 * Use in drag to determine create a new image or not
	 */
	private boolean create=true;
	/**
	 * Use in drag for control between different handler
	 */
	ImageView tmp;
	
	/**
	 * Use to save the session of the plotdesign
	 */
	private Session session;
	
	/**
	 * Accordion for existing, selected, and obstacles 
	 */
	private Accordion choicesAccordian;
	
	/**
	 * Dropdown for Accordion
	 */
	private TitledPane existing;
	private TitledPane selected;
	private TitledPane obstacles;
	
	private HashSet<Plant> existingPlants;
	
	private List<TitledPane> accArr;
	
	private HashMap<ImageView, PlotPlant> plotPlants = new HashMap<ImageView, PlotPlant>();

	private int borderTopAndBottonMargin = 90;
	private int backgroundWidthAndHeight = 100;
	private int rectWidth = View.getCanvasWidth() / 5 * 3;
	private int rectHeight = View.getCanvasHeight() / 7 * 6;
	private int scrollPrefWidth = View.getCanvasWidth() / 3 + 30;
	private int scrollPrefHeight = View.getCanvasHeight() / 5 * 4;

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

		box = new Rectangle(rectWidth, rectHeight);
		box.setStroke(Color.BLACK);
		box.setFill(Color.WHITE);
		group.getChildren().add(box);

		text = new Text(
				"Welcome to the Plot Design! Place all of your plants and objects on your plot to complete your garden!");
		text.setWrappingWidth(View.getCanvasWidth());
		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));

		vbox.setPadding(new Insets(0, 0, 20, 5));
		vbox.getChildren().addAll(text);

		editPlotText = new Text("\nAddPlot");
		editPlotText.setStyle("-fx-font-size: 20px;");
		editPlotButton = new Button("Edit Plot");
		editPlotButton.setPadding(new Insets(10, 5, 10, 5));
		editPlotButton.setStyle("-fx-font-size: 20px;");

		editPlotButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				poly = new AdjustablePolygon(Color.GREEN, Color.YELLOW, 40, 40);
				group.getChildren().add(poly.getPolygon());
				group.getChildren().addAll(poly.getAnchors());
				autoRateVBox.getChildren().addAll(poly.genButton(400, 100));

			}
		});

		
		
		

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
		autoRateVBox.setPadding(new Insets(5));

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
		plantDataButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.AllPlants);
			}
		});

		plantDataButton.setPadding(new Insets(10, 5, 10, 5));
		plantDataButton.setStyle("-fx-font-size: 20px;");

		autoRateVBox.getChildren().addAll(goToPlantData, plantDataButton);

		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(backButton, mainMenu, saveButton, nextButton);
		
		choicesAccordian = new Accordion();
		 accArr = new ArrayList<TitledPane>();
		try {
			populateTiles(accArr);
		} catch (Exception e) {
			System.out.println("WRONG");
		}
		
		
		for (TitledPane t : accArr) {
			t.setFont(getModel().getHackBold20());
			choicesAccordian.getPanes().add(t);
		}
		
		scrollSelections = new ScrollPane();
		scrollPaneFormat(scrollSelections);
		scrollSelections.setPrefSize(scrollPrefWidth, scrollPrefHeight);
		//scrollSelections.setContent(choicesAccordian);
		
		
		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundWidthAndHeight, backgroundWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(box,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vbox);
		borderPane.setRight(autoRateVBox);
		borderPane.setLeft(choicesAccordian);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(group);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Formats the scroll Pane with the necessary attributes to be consistent
	 * 
	 * @param scroll --> a scroll pane
	 */
	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setPadding(new Insets(10, 0, 10, 30));
		scroll.setMaxWidth(View.getCanvasWidth() / 4);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");

	}
	
public void populateTiles(List<TitledPane> accArr) throws Exception {
		
	System.out.println(getSession().getExistingPlants().size() + "size of existing plants : PLotdesign  \n");
	TitledPane existing = new TitledPane("Existing Plants", createPlantFlow(getSession().getExistingPlants()));
	accArr.add(existing);
	
	System.out.println(getSession().getSelectedPlants().size() + "size of selected plants : PLotdesign  \n");
	TitledPane selected = new TitledPane("Selected Plants", createPlantFlow(getSession().getSelectedPlants()));
	accArr.add(selected);
	
		
	}
	
	public void createTitledPane(String s, FlowPane flow) {
		TitledPane titledPane = new TitledPane(s, flow);
		titledPane.setFont(getModel().getHackBold20());
		choicesAccordian.getPanes().add(titledPane);
		
	}
	
	

	/**
	 * Creates the flow panes for each of the drop downs 
	 */
	public FlowPane createPlantFlow(HashSet<Plant> plants) {
		System.out.println("starting ");
		FlowPane flow = new FlowPane();
		flow.setMaxWidth(100);
		
		//System.out.println(plants.size() + "size of existing plants : PLotdesign  ");
		Iterator<Plant> plantIter = plants.iterator();
		System.out.println("after creating iterator  ");
		while(plantIter.hasNext()) {
			
			
			Plant p = plantIter.next();
			
			
			/*
			plotPlants.put(imageView, new PlotPlant(p,0,0));
			imageView.setOnMouseDragged(getHandlerForDrag());
			imageView.setOnMouseReleased(getHandlerForRelease());
			*/
			flow.getChildren().add(createPlantBox(p));
			System.out.println("added imageview to flow ");
		}
		System.out.println("returning flow ");
		return flow;
	}
	
	
	public VBox createPlantBox(Plant p) {

		String[] plantImg = p.getImages();
		
		Image plantImage;

		//Get the actual image if it exists
		if (plantImg != null) {
			String path = p.getImages()[0];
			plantImage = new Image(path, 20, 20, true, true);
		} else {
			// get a default image
			plantImage = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 20,
					20, true, true);
		}
		ImageView imageView = new ImageView();
		imageView.setImage(plantImage);
		
		//String name = p.getLatinName();
		//System.out.println(name + "---------------");
		
		//Tooltip.install(imageView, new Tooltip(name));
		//System.out.println("created tool tip ");
		
		VBox imgButtonHolder = new VBox();
		imgButtonHolder.getChildren().addAll(imageView, new Text(p.getLatinName()));
		
		
		return imgButtonHolder;
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
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * TODO: FIX DRAGGING
	 * 
	 * @param img the image need to be copy
	 * @param x the X coordinate for the image
	 * @param y the Y coordinate for the image
	 */
	/*
	public void addImage(ImageView img, double x, double y) {
		PlotPlant plotplant= plotPlants.get(img);
		plotplant.setPlotX(x);
		plotplant.setPlotY(y);
		ImageView temp=new ImageView(img.getImage());
		System.out.println(plotPlants.get(img));
		session.getPlot().add(plotplant);
		group.getChildren().add(temp);
		temp.setTranslateX(x);
		temp.setTranslateY(y);
		temp.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	ImageView n = (ImageView) event.getSource();
                double newX=n.getTranslateX()+event.getX();
                double newY=n.getTranslateY()+event.getY();
                if(newX>0&&newX<group.getLayoutBounds().getWidth()-n.getImage().getRequestedWidth()) {
                	n.setTranslateX(newX);//n.getTranslateX()+event.getX());
                	plotplant.setPlotX(newX);
                }
                if(newY>0&&newY<group.getLayoutBounds().getHeight()-n.getImage().getRequestedHeight()) {
                	n.setTranslateY(newY);//n.getTranslateY()+event.getY());
                	plotplant.setPlotY(newY);
                }
            }
        });
	}
	
	public void drag(MouseEvent event) {
		ImageView n = (ImageView) event.getSource();
		if(create) {
			tmp = new ImageView(n.getImage());
			root.getChildren().add(tmp);
			create=false;
		}
		tmp.setLayoutX(n.getLayoutX()+event.getX());
		tmp.setLayoutY(n.getLayoutY()+event.getY());

	}

	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}

	public void release(MouseEvent event) {
		ImageView n = (ImageView) event.getSource();
		create=true;
		if(group.contains(tmp.getLayoutX()-n.getParent().getLayoutBounds().getWidth(),tmp.getLayoutY()-vbox.getLayoutBounds().getHeight())) {
			addImage(n,tmp.getLayoutX()-n.getParent().getLayoutBounds().getWidth(),tmp.getLayoutY()-vbox.getLayoutBounds().getHeight());
		}
		root.getChildren().remove(tmp);
		
	}
  
	public EventHandler getHandlerForRelease() {
		return event -> release((MouseEvent) event);
	}
  */
	/**
	 * Remove everything from the flow panes, the center box, and the autorate boxes
	 * and add info back in again for the correct session
	 */
	@Override
	public void refresh() {
		
		
		/*
		 * It is printing the all the plants I've selected to the screen, so why are they not showing up when I call it in create plant flow?
		 */
		Iterator<Plant> plants = getSession().getExistingPlants().iterator();
		while (plants.hasNext()) {
			String p = plants.next().getLatinName();
			System.out.println(p);
		}
		
		/**
		 * This doesn't do anything 
		 */
		TitledPane existing = new TitledPane("Existing Plants", createPlantFlow(getSession().getExistingPlants()));
		accArr.add(existing);
		
		//System.out.println(getSession().getSelectedPlants().size() + "size of selected plants : PLotdesign  \n");
		TitledPane selected = new TitledPane("Selected Plants", createPlantFlow(getSession().getSelectedPlants()));
		accArr.add(selected);
		
		/**
		 * TODO: remove everything from flowpanes and add back in
		 */
		/**
		 * TODO: remove everything from center box and add back in
		 */
		/**
		 * TODO: remove everything from autorate boxes and add back in
		 */
	}
	
}
