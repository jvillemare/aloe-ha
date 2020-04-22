package main.udel.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.udel.enums.Windows;
import main.udel.garden.Model;

/**
 * Preview the garden as it will appear in every season and 1, 2, and 3 years
 * down the line.
 * 
 * @author Team 0
 */
public class SeasonView extends Window {
	
	private Group root;
	private Scene scene;
	
	private BorderPane borderPane;
	private VBox vbox;
	private Text text;
	
	private Button back;
	private Button next;
	
	public SeasonView(Model m) {
		super(m, "Garden Previewer");
		
		borderPane = new BorderPane();
		vbox = new VBox();
		
		text = new Text("Select the season, year, and view you would like to see your Garden in!");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);
		
		
		back = new Button("Back to Plot Design");
		back.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Back to plot design clicked");
            	switchToWindow(Windows.PlotDesign);
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
		
		borderPane.setTop(vbox);
		borderPane.setLeft(back);
		borderPane.setRight(next);
		
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
