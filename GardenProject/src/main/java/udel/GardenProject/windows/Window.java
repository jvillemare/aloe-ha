package udel.GardenProject.windows;

import javafx.scene.Scene;

import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.Session;

/**
 * A window that can be displayed to the user.
 * 
 * @author Team 0
 */
public abstract class Window {

	/**
	 * Title of the window that appears for the stage.
	 */
	private String title;
	
	/**
	 * Local reference to Model for accessing plants and other helper methods.
	 */
	private Model model;

	/**
	 * Used for all the screens to set a standard width.
	 */
	private double windowWidth;
	
	/**
	 * Used for all the screens to set a standard width.
	 */
	private double windowHeight;

	public double getWindowWidth() {
		return windowWidth;
	}

	public double getWindowHeight() {
		return windowHeight;
	}
	
	/**
	 * This stores the enum for every Window to reference.
	 */
	private Windows enumWindow;

	/**
	 * All Windows must store a reference to the Model for Window switching and have
	 * a title to be displayed.
	 * 
	 * @param model Model reference.
	 * @param title Title of the Window on the Stage.
	 * @see main.udel.enums.Windows
	 */
	public Window(Model model, String title, Windows enumWindow) {
		this.model = model;
		this.title = title;
		this.enumWindow = enumWindow;
	}

	/**
	 * Helper method: Switch to a Window described by the Windows enum.
	 * 
	 * @param w Windows object.
	 * @see main.udel.enums.Windows
	 */
	public final void switchToWindow(Windows w) {
		this.model.setWindow(w);
	}

	/**
	 * Get the model.
	 * 
	 * @return Model reference.
	 */
	public final Model getModel() {
		return this.model;
	}

	/**
	 * Get the stage title of a Window.
	 * 
	 * @return title
	 */
	public final String getTitle() {
		return this.title;
	}

	/**
	 * Update the current title of the Window.
	 * 
	 * @param title New title of the Window object.
	 */
	public final void setTitle(String title) {
		// TODO: May not update title in View immediately because of its logic.
		// ...figure that out
		this.title = title;
	}

	/**
	 * Abstract. All Windows must specify how they're scene is returned, and what
	 * processing they do for it.
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
	public final Session getSession() {
		return this.model.getSession();
	}

	/**
	 * Trigger a Window to update any of it's attributes or call any of its methods
	 * to refresh.<br><br>
	 * 
	 * Invoked by the Model whenever a Window is being switched to.
	 */
	public void refresh() { }

	/**
	 * Code executed when the application is being stopped. Invoked by
	 * Controller.<br><br>
	 * 
	 * Default implementation is to do nothing. This may need to be implemented for
	 * each Window, depending.<br><br>
	 * 
	 * Call any save methods or tear down.
	 * 
	 * @see udel.GardenProject.garden.Controller
	 */
	public void stop() { }
	
	/**
	 * To obtain the Enum for each window.
	 * @return enum of the window
	 */
	public final Windows getEnum() { 
		return this.enumWindow;
	}

}
