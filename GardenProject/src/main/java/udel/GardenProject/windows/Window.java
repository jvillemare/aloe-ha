package udel.GardenProject.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.Session;

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
	}
	
	/**
	 * Update the current title of the Window.
	 * 
	 * @param title	New title of the Window object.
	 */
	public void setTitle(String title) {
		// TODO: May not update title in View immediately because of its logic.
		// ...figure that out
		this.title = title;
	}
	
	/**
	 * Abstract. All Windows must specify how they're scene is returned, and
	 * what processing they do for it.
	 * 
	 * @return A Window's specific scene.
	 */
	public abstract Scene getScene();
	
	/**
	 * Helper method, gets the Session object that stores all of the user state
	 * data.
	 * 
	 * @return Session instance.
	 */
	public Session getSession() {
		return this.model.getSession();
	}
	
	/**
	 * Code executed when the application is being stopped. Invoked by 
	 * Controller.<br><br>
	 * 
	 * Default implementation is to do nothing. This may need to be implemented
	 * for each Window, depending.<br><br>
	 * 
	 * Call any save methods or tear down.
	 * 
	 * @see udel.GardenProject.garden.Controller
	 */
	public void stop() { }

}
