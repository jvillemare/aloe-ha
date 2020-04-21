package main.udel.windows;

import javafx.scene.control.Button;

/**
 * Where the user can export their plot as a PNG/JPEG, save it as a
 * <code>.gardenproject</code> file, load a saved project, or exit.
 * 
 * @author Team 0
 */
public class Download extends Window {
	
	/**
	 * button that brings to previous window
	 */
	private Button backButton;

	public Download() {
		super("Load or Save Your Plot");
	}
	
	/**
	 * saves the PNG plant 
	 * @return true if it saved and false if it did not
	 */
	public boolean savePNG(String filename) {
		return false;
	}
	
	/**
	 * saves the plant
	 * @return true if saved and false if it did not
	 */
	public boolean save(String filename) {
		return false;
	}
	
	/**
	 * loads the plotdesign of a window
	 * @return the plotdesign desired
	 */
	public PlotDesign load() {
		return null;
	}
	
	/**
	 * exits
	 */
	public void exit() {
		
	}
	
}
