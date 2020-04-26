package udel.GardenProject.garden;

import java.io.Serializable;
import java.util.ArrayList;

import udel.GardenProject.plants.plotObjects.PlotObject;

/**
 * Holds all the critical user-state information so that a
 * <code>.gardenproject</code> file can be easily loaded in and out without much
 * delay.
 * 
 * Isolated so that it can be saved and loaded easily.
 * 
 * All attributes should be adequately javadoc'd for future reference.
 * 
 * @author Team 0
 */
public class Session implements Serializable {

	/**
	 * Prevent newer version of GardenProject being loaded by older versions of
	 * save files to prevent errors.
	 * 
	 * Should only update this if absolutely necessary.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Does this current Session need to be saved? By default, if any setter is
	 * called, this is automatically set to true.
	 * 
	 * When Controller's <code>stop()</code> method is invoked, Model will check
	 * to see if it can save this Session to lastSavedFilepath. If not, it 
	 * simply continues.
	 */
	private boolean unsaved = false;
	
	/**
	 * Location of where this Session was last saved to. Makes it easier to load
	 * another Session by assuming the user wants to save this Session to where
	 * it was last saved.<br><br>
	 * 
	 * If the user saved a Session S to "C:/whatever.gardenproject", and the 
	 * user wants to open a new Session G, assume that the user wants to save S
	 * where it was saved at "C:/whatever.gardenproject", and start new Session
	 * G.
	 */
	private String lastSavedFilepath = "";
	
	/**
	 * Name of the user's plot so that they can customize them.
	 */
	private String plotName = "";
	
	/**
	 * Objects to be displayed on PlotDesign.
	 */
	private ArrayList<PlotObject> plot = new ArrayList<PlotObject>();

	public boolean isUnsaved() {
		return unsaved;
	}

	public void setUnsaved(boolean unsaved) {
		this.unsaved = unsaved;
	}

	public String getLastSavedFilepath() {
		return lastSavedFilepath;
	}

	public void setLastSavedFilepath(String lastSavedFilepath) {
		this.lastSavedFilepath = lastSavedFilepath;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		unsaved = true;
		this.plotName = plotName;
	}

	public ArrayList<PlotObject> getPlot() {
		unsaved = true;
		return plot;
	}

	public void setPlot(ArrayList<PlotObject> plot) {
		unsaved = true;
		this.plot = plot;
	}

}