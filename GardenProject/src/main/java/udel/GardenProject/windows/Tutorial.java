package udel.GardenProject.windows;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	private Text welcomeText;
	private BorderPane borderPane;
	private Button back;
	private AnchorPane welcomePane;


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
		borderPane.setStyle("-fx-background-color: DAE6F3;");
		
		vbox = new VBox();
		
		Text welcometxt = new Text("Welcome to the Tutorial!");
		welcometxt.setFont(new Font(25));
		welcometxt.setFill(Color.DARKGREEN);
		
		StackPane welcomePane = new StackPane();
		welcomePane.getChildren().add(welcometxt);
		welcomePane.setStyle("-fx-background-color: DAE6F3;");
		
		borderPane.setTop(welcomePane);
		
		back = new Button("Back to Main");
		back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Start button clicked");
            	switchToWindow(Windows.Welcome);
            }
        });
		
		scroll = new ScrollPane();
		scroll.setContent(vbox);
		scroll.setFitToWidth(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		borderPane.setCenter(scroll);
		
		Text tutorial = new Text("1. Select \"Start New Plot\" Button\n\n"
				+ "2. Select Plants that are Within your Garden\n\n"
				+ "3. Answer Some Questions about your Garden\n\n"
				+ "4. Select the Plants you wish to have according to your Selections\n\n"
				+ "5. Drag and Drop the plants to Where you Want Them in your Garden\n\n");
		
		tutorial.setFont(new Font(20));
		tutorial.setFill(Color.DARKGREEN);
		
		scroll.setContent(tutorial);
		
		
		StackPane backPane = new StackPane();
		backPane.getChildren().add(back);
		backPane.setStyle("-fx-background-color: DAE6F3;");

		borderPane.setBottom(backPane);

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
