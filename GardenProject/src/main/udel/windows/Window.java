package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.enums.Windows;
import main.udel.garden.Model;

/**
 * A window that will be displayed to the user.
 * 
 * @author Team 0
 */
public abstract class Window {
	
	private String title;
	private Model model;
	
	private Scene scene;
	
	public Window(Model model, String title) {
		this.model = model;
		this.title = title;
	}
	
	public void switchToWindow(Windows w) {
		this.model.setWindow(w);
	}
	
	/**
	 * TODO: Document later...
	 * @return ...
	 */
	public String getTitle() {
		return this.title;
	};
	
	/**
	 * TODO: Document later...
	 * @return ...
	 */
	public abstract Scene getScene();

}
