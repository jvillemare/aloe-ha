package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.PlotObjects;
import udel.GardenProject.enums.PlotObjectsFactory;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotTextLabel;
import udel.GardenProject.plotObjects.polygons.GenericPolygon;
import udel.GardenProject.plotObjects.polygons.PlotForest;
import udel.GardenProject.plotObjects.polygons.PlotRoad;
import udel.GardenProject.plotObjects.special.PlotOther;

/**
 * Allow the user detail the background of their plot with labels, and specify
 * characteristics for some areas of their plot.
 * 
 * @version 1.0
 * @author Team 0
 */
public class BluePrint extends Window {
	
	private Group root;
	private Scene scene;
	
	/**
	 * Used for the overall layout.
	 */
	private BorderPane borderPane;
	
	/**
	 * Title text for the window.
	 */
	private Text text;
	private Group group;
	private ScrollPane scrollpane;
	private FlowPane bluePrintObjects;
	private ArrayList<PlotObjects> objects;
	/**
	 * Buttons in tilePane at the bottom of the screen.
	 */
	private Button backButton, mainMenu, nextButton;
	/**
	 * Holds the options for what the user wants to put in their plot.
	 */
	private Rectangle box;
	private TilePane tilePane;
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
	 * Used to hold text, for top panel.
	 */
	private VBox vbox;
	private int backgroundWidth = 50;
	private int backgroundHeight = 140;
	private int rectWidth = View.getCanvasWidth() / 5 * 3;
	private int rectHeight = View.getCanvasHeight() / 7 * 6;
	private int flowPaneWidthAdjustment = View.getCanvasWidth() / 9 + 20;
	private int gapBetweenButtons = 100;
	private int inset10 = 10;
	private int inset5 = 5;
	private int inset15 = 15;
	private int houseTextX = 360;
	private int houseTextY = 595;
	
	
	/**
	 * Constructor.
	 * 
	 * @param m		Model reference.
	 */
	public BluePrint(Model m) {
		super(m, "Blue Print Your Plot", Windows.BluePrint);
		// TODO Auto-generated constructor stub
		text=new Text("Drag and Drop the given garden objects from the left pane to build "
				+ "a blue print for your garden."
				+ " Click on the next to start design your gardan.");
		text.setWrappingWidth(View.getCanvasWidth());
		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));
		scrollpane = new ScrollPane();
		
		group = new Group();
		vbox = new VBox();
		tilePane = new TilePane();
		borderPane = new BorderPane();
		vbox.setPadding(new Insets(0, 0, 0, inset5));
		vbox.getChildren().addAll(text);
		createCenterBox();
		setPlotObjects();
		createButtons();
		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(inset15));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(backButton, mainMenu, nextButton);	
		borderPane.setTop(vbox);
		borderPane.setLeft(scrollpane);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(group);
		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundWidth, backgroundHeight);
		BorderPane.setMargin(scrollpane,
				new Insets(0, 0, 0, 60));
		borderPane.setBackground(View.getBackgroundScreen());
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Creates the flow pane of objects on the left
	 */
	public void setPlotObjects() {
		objects = new ArrayList<PlotObjects>();
		for(PlotObjects o:PlotObjects.values()) {
			if(o.isBluePrintSpecific()) {
				objects.add(o);
			}
		}
		createFlow(objects);
		
	}
	
	/**
	 * Objects in the box on the left to be placed in the plot
	 * @param obj Plot objects
	 */
	public void createFlow(ArrayList<PlotObjects> obj) {
		bluePrintObjects = new FlowPane();
		bluePrintObjects.setMaxWidth(flowPaneWidthAdjustment);
		bluePrintObjects.setPrefWidth(flowPaneWidthAdjustment);
		bluePrintObjects.setHgap(inset10);
		bluePrintObjects.setHgap(inset10);
		bluePrintObjects.setAlignment(Pos.BASELINE_CENTER);
		scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollpane.setContent(bluePrintObjects);
		PlotObjectsFactory pof = new PlotObjectsFactory();
		for(PlotObjects p:obj) {
			Node renderedPlotObject = pof.renderInAccordion(p);
			String name = p.name();
			Tooltip.install(renderedPlotObject, new Tooltip(name));
			
			VBox renderedPlotObjectVBox = new VBox(renderedPlotObject, new Text(name));
			renderedPlotObject.setOnMouseDragged(getHandlerForDrag());
			renderedPlotObject.setOnMouseReleased(getHandlerForRelease(p));
			bluePrintObjects.getChildren().add(renderedPlotObjectVBox);
		}
	}
	
	/**
	 * Creates the white box in the center of the screen.
	 */
	public void createCenterBox() {
		box = new Rectangle(rectWidth, rectHeight);
		box.setStroke(Color.BLACK);
		box.setFill(Color.WHITE);
		
		Text houseText = new Text("Your House is Here");
		houseText.setLayoutX(houseTextX);
		houseText.setLayoutY(houseTextY);
		group.getChildren().addAll(box, houseText);
		
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

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				takeSnapShot();
				switchToWindow(Windows.PlotDesign);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(backButton);
		buttons.add(mainMenu);
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
	 * <b>NOTE:</b> Does NOT add PlotObject to <code>plot</code> attribute in
	 * Session. Only adds a plot object to the interface. This is because if this
	 * method did add the passed PlotObject to the <code>plot</code>, then
	 * PlotDesign's <code>refresh()</code> would not work because it add already
	 * existing PlotObjects to the plot.<br>
	 * <br>
	 * 
	 * Take in a PlotObject, call its <code>render()</code> method, and set the
	 * returned Node's position and attach the necessary handlers to update its
	 * position.
	 * 
	 * @param po PlotObject being added to the plot.
	 * @param x  Horizontal coordinate for the image.
	 * @param y  Vertical coordinate for the image.
	 */
	public void addPlotObjectToInterface(PlotObject po, double x, double y) {
		po.setModel(getModel()); // for when reloading a save file, point to
			// new model instance
		po.setPlotX(x);
		po.setPlotY(y);
		Node plotObjectRepresentation = po.render();
		plotObjectRepresentation.setTranslateX(x);
		plotObjectRepresentation.setTranslateY(y);
		if (!po.getUseDefaultDragHandler()) {
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
			plotObjectRepresentation.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
			  	public void handle(MouseEvent event) {
					Tooltip.install(plotObjectRepresentation, new Tooltip(po.getName()));
				}
			});
			group.getChildren().add(plotObjectRepresentation);
		} else {
			group.getChildren().addAll(((Group)plotObjectRepresentation).getChildren());
			((GenericPolygon)po).setX(x);
			((GenericPolygon)po).setX(y);
		}
	}
	/**
	 * When user lets go of mouse on drag event, drop in a new plot object where
	 * they released their mouse.
	 * 
	 * @param event ...
	 * @param po    ...
	 */
	public void releaseTemporaryImage(MouseEvent event, PlotObjects po) {
		ImageView n = (ImageView) event.getSource();
		create = true;
		double newX = tmp.getLayoutX() - n.getParent().getLayoutBounds().getWidth();
		double newY = tmp.getLayoutY() - vbox.getLayoutBounds().getHeight();
		if (group.contains(newX, newY)) {
			PlotObject plotObjectToAdd;
			//plotObjectToAdd = new PlotOther(getModel(), newX, newY);
			switch (po) {
			case Forest:
				plotObjectToAdd = new PlotForest(getModel(), newX, newY);
				break;
			case Road:
				plotObjectToAdd = new PlotRoad(getModel(), newX, newY);
				break;
			case TextLabel:
				// TODO: Prompt a user with a textbox in a separate stage
				// window asking what they would like the text
				// label to say
				plotObjectToAdd = new PlotTextLabel(getModel(), newX, newY, "Empty Text Label");
				break;
			default:
				plotObjectToAdd = new PlotOther(getModel(), newX, newY);
				break;
			}

			getSession().getBluePrintPlot().add(plotObjectToAdd);
			addPlotObjectToInterface(plotObjectToAdd, newX, newY);
		}
		root.getChildren().remove(tmp);
	}
	/**
	 * TODO: ...?
	 * 
	 * @param po
	 * @return
	 */
	public EventHandler getHandlerForRelease(PlotObjects po) {
		return event -> releaseTemporaryImage((MouseEvent) event, po);
	}
	/**
	 * Create a temporary ImageView to follow around the mouse during drag.
	 * 
	 * @param event
	 */
	public void dragTemporaryImage(MouseEvent event) {

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
		return event -> dragTemporaryImage((MouseEvent) event);
	}
	
	/**
	 * Takes picture of plot
	 */
	public void takeSnapShot() {
		for (PlotObject po : getSession().getBluePrintPlot()) {
			if(po.getUseDefaultDragHandler()) {
				po.setVisible(false);
			}
		}
		WritableImage writableimage=group.snapshot(new SnapshotParameters(), null);
		ImageView image=new ImageView(writableimage);
		getModel().setImg(image);
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	@Override
	public void refresh() {
		group.getChildren().clear();
		createCenterBox();
		for (PlotObject po : getSession().getBluePrintPlot()) {
			addPlotObjectToInterface(po, po.getPlotX(), po.getPlotY());
			if(po.getUseDefaultDragHandler()) {
				po.setVisible(true);
			}
		}
		WritableImage writableimage=group.snapshot(new SnapshotParameters(), null);
		ImageView image=new ImageView(writableimage);
		getModel().setImg(image);
	}
	
}
