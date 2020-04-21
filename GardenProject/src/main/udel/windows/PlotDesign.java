package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.plants.Plant;

/**
 * Heart of the application: Where the user can drag plants, obstacles, shade,
 * text boxes, and interact with their virtual, top-down plot.
 * 
 * @author Team 0
 */
public class PlotDesign extends Window {
	
	private Scene scene;

	private int statistics[];
	private Button saveButton;
	private Button backButton;
	private Button loadButton;
	private Button nextButton;
	
	public PlotDesign() {
		super("Plot Designer");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	public void getObstacle() {
		
	}
	
	public Object setObstacle(Object obstacle) {
		return null;
	}
	
	public Plant setPlant(Plant p) {
		return p;
	}
	
	public void getPlant() {
		
	}
	
}
