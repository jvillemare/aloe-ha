package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.enums.Windows;
import main.udel.garden.Model;

/**
 * A window that can be displayed to the user.
 * 
 * @author Team 0
 */
public abstract class Window {
	
	private String title;
	private Model model;
	
	private Scene scene;
	
	/**
	 * All Windows must store a reference to the Model for Window switching and
	 * have a title to be displayed.
	 * 
	 * @param model Model reference.
	 * @param title Title of the Window on the Stage.
	 * @see main.udel.enums.Windows
	 */
	public Window(Model model, String title) {
		this.model = model;
		this.title = title;
	}
	
	/**
	 * Helper function: Switch to a Window described by the Windows enum.
	 * 
	 * @param w Windows object.
	 * @see main.udel.enums.Windows
	 */
	public void switchToWindow(Windows w) {
		this.model.setWindow(w);
	}
	
	/**
	 * Get the stage title of a Window.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	};
	
	/**
	 * Abstract. All Windows must specify how they're scene is returned, and
	 * what processing they do for it.
	 * 
	 * @return A Window's specific scene.
	 */
	public abstract Scene getScene();

}
