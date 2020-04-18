package main.udel.windows;

import javafx.scene.Scene;

/**
 * A window that will be displayed to the user.
 * 
 * @author Team 0
 */
public abstract class Window {
	
	private String title;
	
	private Scene scene;
	
	public Window(String title) {
		this.title = title;
	}
	
	/**
	 * Document later...
	 * @return
	 */
	public String getTitle() {
		return this.title;
	};
	
	/**
	 * TODO: Document later...
	 * @return
	 */
	public Scene getScene() {
		return this.scene;
	}

}
