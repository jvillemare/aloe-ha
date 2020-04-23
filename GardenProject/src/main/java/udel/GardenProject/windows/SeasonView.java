package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
 * Preview the garden as it will appear in every season and 1, 2, and 3 years
 * down the line.
 *
 * @author Team 0
 */
public class SeasonView extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for main layout of seasonView
	 */
	private BorderPane borderPane;
	
	/**
	 * vbox --> info at the top
	 * imageVBox --> where the image will be shown to user (center) 
	 * layoutCenterVBox --> holds imageVBox and toggleOptionsTilePane
	 */
	private VBox vbox, imageVBox, layoutCenterVBox;
	
	/**
	 * text --> for the message at the top of the screen 
	 */
	private Text text;
	
	/**
	 * tilePane --> used for buttons at the bottom
	 * toggleOptionsTilePane --> used for toggle options 
	 */
	private TilePane tilePane, toggleOptionsTilePane;

	/**
	 * buttons to move between screens and save user input
	 */
	private Button back, save, next;
	
	/**
	 * Used for grouping different toggle selections
	 */
	private ToggleGroup seasonGroup, yearGroup, viewGroup;
	
	/**
	 * Different toggle options in each toggle group
	 */
	private ToggleButton spring, summer, winter, fall;
	private ToggleButton year0, year1, year2;
	private ToggleButton windowView, birdsEyeView;
	
	/**
	 * Used to hold the toggle groups 
	 */
	private HBox seasonHBox, yearHBox, viewHBox;
	
	/**
	 * Prospective area where the image of the garden plot should be
	 */
	private Rectangle square;
	
	/**
	 * Create a SeasonView window instance.
	 *
	 * @param m Model
	 */
	public SeasonView(Model m) {
		super(m, "Garden Previewer");

		borderPane = new BorderPane();
		vbox = new VBox();
		layoutCenterVBox = new VBox();
		imageVBox = new VBox();
		toggleOptionsTilePane = new TilePane();
		tilePane = new TilePane();
		
		text = new Text("Select the season, year, and view you would like to see your Garden in!");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);
		
		seasonGroup = new ToggleGroup();
		spring = new ToggleButton("Spring");
		summer = new ToggleButton("Summer");
		winter = new ToggleButton("Winter");
		fall = new ToggleButton("Fall");
		
		spring.setToggleGroup(seasonGroup);	
		summer.setToggleGroup(seasonGroup);
		winter.setToggleGroup(seasonGroup);
		fall.setToggleGroup(seasonGroup);
		
		yearGroup = new ToggleGroup();
		year0 = new ToggleButton("0 Years");
		year1 = new ToggleButton("1 Year");
		year2 = new ToggleButton("2 Years");
		
		year0.setToggleGroup(yearGroup);	
		year1.setToggleGroup(yearGroup);
		year2.setToggleGroup(yearGroup);
		
		viewGroup = new ToggleGroup();
		birdsEyeView = new ToggleButton("View from Top");
		windowView = new ToggleButton("View from Window");
		
		birdsEyeView.setToggleGroup(viewGroup);	
		windowView.setToggleGroup(viewGroup);

		back = new Button("Back to Plot Design");
		back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Back to plot design clicked");
            	switchToWindow(Windows.PlotDesign);
            }
        });
		
		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save: saving season/year/window options");
            	//add function to save the options the user chose 
            }
        });

		next = new Button("To Download");
		next.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("To Download clicked");
            	switchToWindow(Windows.Download);
            }
        });
		
		square = new Rectangle();
		square.setHeight(530);
        square.setWidth(1150);
        square.setStroke(Color.BLACK);
        square.setFill(null);
        imageVBox.getChildren().add(square);
		
		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(back, save, next);
		
		toggleOptionsTilePane.setAlignment(Pos.CENTER);
		toggleOptionsTilePane.setPadding(new Insets(5));
		toggleOptionsTilePane.setHgap(20);
		toggleOptionsTilePane.setVgap(20);
		
		seasonHBox = new HBox(summer, spring, fall, winter);
		yearHBox = new HBox(year0, year1, year2);
		viewHBox = new HBox(birdsEyeView, windowView);
		
		/**
		 * TODO: add listeners to the toggleButtons
		 */
		toggleOptionsTilePane.getChildren().addAll(seasonHBox, yearHBox, viewHBox);
		
		layoutCenterVBox.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null,null)));
		layoutCenterVBox.getChildren().addAll(imageVBox, toggleOptionsTilePane);
		
		borderPane.setPadding(new Insets(10));
		borderPane.setCenter(layoutCenterVBox);
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 1170, 650);
	}
	
	/**
	 * TODO: 
	 * Add a function that gets the user input 
	 */
	public void getInput() {
		
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
