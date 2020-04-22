package main.udel.windows;

import javafx.scene.Scene;
import main.udel.garden.Model;

/**
 * Preview the garden as it will appear in every season and 1, 2, and 3 years
 * down the line.
 * 
 * @author Team 0
 */
public class SeasonView extends Window {
	
	private Scene scene;
	
	/**
	 * Create a SeasonView window instance.
	 * 
	 * @param m Model
	 */
	public SeasonView(Model m) {
		super(m, "Garden Previewer");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
