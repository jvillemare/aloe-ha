package udel.GardenProject.windows;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;


/**
 * Presently: Basic text description run down of all the features of the
 * program and how to use them.
 *
 * @author Team 0
 */
public class Tutorial extends Window {


	private Group root;
	private Scene scene;
	private ScrollPane scroll;
	private VBox vbox;
	private Text text;
	private BorderPane borderPane;
	private Button back;


	/**
	 * Create a Tutorial window instance.
	 *
	 * @param m Model
	 
	 * TODO: Get the screen to be a fixed size so you can actually scroll instead of the screen
	 * just getting bigger
	 */

	public Tutorial(Model m) {
		super(m, "Tutorial Window");

		borderPane = new BorderPane();
		vbox = new VBox();

		/*
		 * Text and images for the tutorial will be added here
		 */
		text = new Text("This is the tutorial frame\n\n\n\n\n\n\n\ns");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);

		scroll = new ScrollPane();
		scroll.setContent(vbox);
		scroll.setFitToWidth(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		borderPane.setRight(scroll);

		back = new Button("Back to Main");
		back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Start button clicked");
            	switchToWindow(Windows.Welcome);
            }
        });

		borderPane.setBottom(back);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
