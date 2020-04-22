package main.udel.windows;

import javafx.scene.Scene;
import main.udel.garden.Model;

/**
 * Basic questions about a user's plots that informs what plants are selected.
 * 
 * @author Team 0
 */
public class Questionnaire extends Window {
	
	private Scene scene;
	
	/**
	 * Create a Questionnaire window instance.
	 * 
	 * @param m Model
	 */
	public Questionnaire(Model m) {
		super(m, "Questions About Your Garden...");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
