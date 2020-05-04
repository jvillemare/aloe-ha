package udel.GardenProject.garden;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import udel.GardenProject.enums.PlotObjects;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Holds all the critical user-state information so that a
 * <code>.gardenproject</code> file can be easily loaded in and out without much
 * delay.<br><br>
 * 
 * Isolated so that it can be saved and loaded easily.<br><br>
 * 
 * All attributes should be adequately javadoc'd for future reference.<br><br>
 * 
 * NOTE: All attributes should be initialized to a default value.
 * 
 * @author Team 0
 */
public class Session implements Serializable {

	/**
	 * Prevent newer version of GardenProject being loaded by older versions of save
	 * files to prevent errors.
	 * 
	 * Should only update this if absolutely necessary.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Does this current Session need to be saved? By default, if any setter is
	 * called, this is automatically set to true.
	 * 
	 * When Controller's <code>stop()</code> method is invoked, Model will check to
	 * see if it can save this Session to lastSavedFilepath. If not, it simply
	 * continues.
	 */
	private boolean unsaved = false;

	/**
	 * Location of where this Session was last saved to. Makes it easier to load
	 * another Session by assuming the user wants to save this Session to where it
	 * was last saved.<br>
	 * <br>
	 * 
	 * If the user saved a Session S to "C:/whatever.gardenproject", and the user
	 * wants to open a new Session G, assume that the user wants to save S where it
	 * was saved at "C:/whatever.gardenproject", and start new Session G.
	 */
	private String lastSavedFilepath = "";

	/**
	 * Name of the user's plot so that they can customize them.
	 */
	private String plotName = "";

	/**
	 * Width and Length of the users plot in feet
	 */
	private int widthOfUserPlot = 0;
	private int lengthOfUserPlot = 0;

	/**
	 * User's moisture level of their plot
	 */
	private String moistureOfPlot = "";

	/**
	 * User's soil type of their plot
	 */
	private String soilTypeOfPlot = "";

	/**
	 * Users sunlight of their plot
	 */
	private String sunlightOfPlot = "";
	
	/**
	 * Existing plants already in users plot.<br><br>
	 * 
	 * <b>NOTE</b>: Key should plant latin name, value the plant itself.
	 */
	private HashMap<String, Plant> existingPlants = new HashMap<String, Plant>();
	
	/**
	 * User's selected seasons for blooms
	 */
	private ArrayList<Seasons> seasonsUserSelected = new ArrayList<Seasons>();

	/**
	 * User's selected colors for flowers
	 */
	private ArrayList<Color> colorsUserSelected = new ArrayList<Color>();

	/**
	 * Objects actually in the plot, to be displayed in PlotDesign.
	 */
	private ArrayList<PlotObject> plot = new ArrayList<PlotObject>();

	/**
	 * Season selected by user from SeasonView
	 */
	private Seasons seasonInput = Seasons.SPRING;
	// TODO: Does spring make sense as a default?

	/**
	 * Year selected by user from SeasonView
	 */
	private int yearInput = 0;

	/**
	 * Type of view selected by user from SeasonView
	 */
	private String viewInput = "";
	// TODO: Replace with enum

	/**
	 * The user's selection to how they want to save on the download screen
	 */
	private String saveOption;
	
	/**
	 * Plot Objects the user wants to appear in the PlotDesign left column.
	 */
	private ArrayList<PlotObjects> selectedPlotObjects = 
			 new ArrayList<PlotObjects>();
	
	public ArrayList<PlotObjects> getSelectedPlotObjects() {
		return this.selectedPlotObjects;
	}

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

	public void setWidthOfUserPlot(int w) {
		this.widthOfUserPlot = w;
	}

	public int getWidthOfUserPlot() {
		return widthOfUserPlot;
	}

	public void setLengthOfUserPlot(int w) {
		this.lengthOfUserPlot = w;
	}

	public int getLengthOfUserPlot() {
		return lengthOfUserPlot;
	}

	public String getMoistureOfPlot() {
		return moistureOfPlot;
	}

	public void setMoistureOfPlot(String m) {
		this.moistureOfPlot = m;
	}

	public String getSoilTypeOfPlot() {
		return soilTypeOfPlot;
	}

	public void setSoilTypeOfPlot(String st) {
		this.soilTypeOfPlot = st;
	}

	public String getSunlightOfPlot() {
		return sunlightOfPlot;
	}

	public void setSunlightOfPlot(String sun) {
		this.sunlightOfPlot = sun;
	}

	public void setSeasonsUserSelected(ArrayList<Seasons> season) {
		this.seasonsUserSelected = season;
	}

	public ArrayList<Seasons> getSeasonsUserSelected() {
		return seasonsUserSelected;
	}

	public void setColorsUserWants(ArrayList<Color> color) {
		this.colorsUserSelected = color;
	}

	public ArrayList<Color> getColorsUserSelected() {
		return colorsUserSelected;
	}

	public Seasons getSeasonInput() {
		return seasonInput;
	}

	public void setSeasonInput(Seasons chooseSeason) {
		this.seasonInput = chooseSeason;
	}

	public int getYearInput() {
		return yearInput;
	}

	public void setYearInput(int y) {
		this.yearInput = y;
	}

	public String getViewInput() {
		return viewInput;
	}

	public void setViewInput(String v) {
		this.viewInput = v;
	}

	public String getSaveOption() {
		return saveOption;
	}

	public void setSaveOption(String so) {
		this.saveOption = so;
	}

	public HashMap<String, Plant> getExistingPlants() {
		return existingPlants;
	}

	public void setExistingPlants(HashMap<String, Plant> existingPlants) {
		this.existingPlants = existingPlants;
	}

}
