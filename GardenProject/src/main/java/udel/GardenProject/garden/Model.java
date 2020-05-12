package udel.GardenProject.garden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plants.PlantLoader;
import udel.GardenProject.plants.PlantNameComparator;
import udel.GardenProject.windows.*;

/**
 * Updates the stage: Contains logic and data.
 * 
 * @author Team 0
 */
public class Model {

	/**
	 * Suggested width of stage for Window objects.
	 */
	private int width;

	/**
	 * Suggested height of stage for Window objects.
	 */
	private int height;

	/**
	 * Where on the user's OS can we save Application
	 */
	private String appDataDirectory;

	/**
	 * All the windows that can be displayed to the user.
	 */
	private Window[] windows;

	/**
	 * The last window that was displayed to the user.
	 */
	private Window lastWindow;

	/**
	 * The current window being displayed to the user.
	 */
	private Window currentWindow;

	/**
	 * All the plants.
	 */
	private ArrayList<Plant> plants;

	/**
	 * The plant to be shown in the PlantInfo window.
	 */
	private Plant plantInfoPlant;

	/**
	 * Where all the user data is collected and stored.
	 */
	private Session session;
	
	/**
	 * ArrayList<Plant> of native plants only
	 */
	private ArrayList<Plant> nativeOnly = new ArrayList<Plant>();
	
	/**
	 * Local reference to Controller for sending Window change updates.
	 */
	private Controller c;
	
	/**
	 * Constructor, initialize everything.
	 * 
	 * @param width
	 * @param height
	 */
	public Model(Controller c, int width, int height) {
		this.c = c;
		this.width = width;
		this.height = height;
		this.session = new Session();

		determineAppDataDirectory();

		try {
			this.plants = PlantLoader.getPlants();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("ERROR: Problem with reading Plant data JSON files...");
			System.exit(1);
		}
		Collections.sort(this.plants, new PlantNameComparator(true, false));

		setupWindows();
		printMemoryInfo();
		createNativeList();
	}

	/**
	 * Creates the list of all Native plants
	 */
	public void createNativeList() {
		for(Plant p : plants) {
			if(p.getDelawareNative()) {
				nativeOnly.add(p);
			}
		}
	}

	/**
	 * Get information about the current memory usage of this application in bytes
	 * from Runtime.
	 */
	public void printMemoryInfo() {
		System.out.println("\n" + "MaxMemory:   " + Runtime.getRuntime().maxMemory() + "\n" + "FreeMemory:  "
				+ Runtime.getRuntime().freeMemory() + "\n" + "TotalMemory: " + Runtime.getRuntime().totalMemory() + "\n"
				+ "UsedMemory:  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + "\n");
	}

	/**
	 * Gets the current Window that should be displayed to the user.
	 * 
	 * @return current Window.
	 */
	public Window getWindow() {
		return currentWindow;
	}

	/**
	 * Call the current Window's refresh() method.
	 */
	public void refreshCurrentWindow() {
		currentWindow.refresh();
	}

	/**
	 * Refreshes the new current Window, then sets it.
	 * 
	 * @param w A Window specified by the Windows enum.
	 */
	public void setWindow(Windows w) {
		System.gc();
		windows[w.ordinal()].refresh();
		lastWindow = currentWindow;
		currentWindow = windows[w.ordinal()];
		currentWindow.refresh();
		c.update(currentWindow);
	}

	/**
	 * Go back to the last window.
	 */
	public void goToLastWindow() {
		Window temp = currentWindow;
		lastWindow.refresh();
		currentWindow = lastWindow;
		lastWindow = temp;
		c.update(currentWindow);
	}

	/**
	 * TODO: Later..
	 * 
	 * @return
	 */
	public Window getLastWindow() {
		return this.lastWindow;
	}

	/**
	 * Get the suggested scene width for a Window object.
	 * 
	 * @return width as an integer.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get the suggested scene height for a Window object.
	 * 
	 * @return height as an integer.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Get the master ArrayList of Plants.
	 * 
	 * @return all plants.
	 */
	public ArrayList<Plant> getPlants() {
		return this.plants;
	}

	/**
	 * Get Session that holds all user state information.
	 * 
	 * @return Session instance.
	 */
	public Session getSession() {
		return this.session;
	}

	/**
	 * Search a given array of plants and return an array of all Plants that match the
	 * query. If the query matches the latin or any of the common names, that plant
	 * is returned.<br>
	 * <br>
	 * 
	 * Comparators can be used on the results.
	 * 
	 * @param query Simple, non-regex query where the query can appear anywhere in
	 *              the plant latin or common name.
	 *        plantList which are the plants to search thru.
	 * @return null if no matching results, a Plant array of all matching results.
	 */
	public HashMap<String, Plant> searchPlantsInArr(String query, ArrayList<Plant> plantList) {
		// preconditions for queries
		if (query.length() < 3)
			return null;

		// regular
		HashMap<String, Plant> results = new HashMap<String, Plant>();

		Iterator<Plant> pIterator = plantList.iterator();
		while (pIterator.hasNext()) {
			Plant p = pIterator.next();
			boolean addToResults = false;

			if (p.getLatinName().toLowerCase().contains(query.toLowerCase()))
				addToResults = true;

			if (p.getCommonNames() != null)
				for (String commonName : p.getCommonNames())
					if (commonName.toLowerCase().contains(query.toLowerCase()))
						addToResults = true;

			if (addToResults)
				results.put(p.getLatinName(), p);
		}

		return results;
	}
	
	/**
	 * Search the array of plants and return an array of all Plants that match the
	 * query. If the query matches the latin or any of the common names, that plant
	 * is returned.<br>
	 * <br>
	 * 
	 * @param query
	 * @return a HashMap of all plants that fit with query
	 */
	public HashMap<String, Plant> searchPlants(String query) {
		return searchPlantsInArr(query, plants);
	}

	/**
	 * Search the array of native plants and return an array of all Plants that 
	 * match the query. If the query matches the latin or any of the common names,
	 *  that plant is returned.<br>
	 * <br>
	 * 
	 * @param query
	 * @return a HashMap of all plants that fit with query
	 */
	public HashMap<String, Plant> searchNativePlants(String query) {
		return searchPlantsInArr(query, nativeOnly);
	}
	
	/**
	 * Safely <i>cache</i> a file to a user's computer. Hides all exceptions and
	 * potential errors.
	 * 
	 * @param filepath Path and filename where to save the file in the user's
	 *                 <code>appDataDirectory</code>.
	 * @return true if successful in caching the file, false if not.
	 * @see {@link #loadCacheFile(String) loadCacheFile}
	 */
	public boolean saveCacheFile(Object o, String filepath) {
		String realFilepath = calculateFilepath(filepath);
		try {
			System.out.println(realFilepath);
			File tmp = new File(realFilepath);
			tmp.createNewFile();
			FileOutputStream file = new FileOutputStream(tmp);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(o);

			out.close();
			file.close();
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Model: Failed to save cache file at " + realFilepath);
			return false;
		}
		return true;
	}

	/**
	 * Safely <i>load</i> a file to a user's computer. Hides all exceptions and
	 * potential errors.<br>
	 * <br>
	 * 
	 * This can also be used to check if a file exists in cache if this method
	 * returns false.
	 * 
	 * @param filepath Path and filename where to load the file in the user's
	 *                 <code>appDataDirectory</code>.
	 * @return file object
	 * @see {@link #saveCacheFile(String) saveCacheFile}
	 */
	public Object loadCacheFile(String filepath) {
		String realFilepath = calculateFilepath(filepath);
		// TODO: Change, how to return input stream but also close it before
		// returning it??
		try {
			FileInputStream file = new FileInputStream(realFilepath);
			ObjectInputStream in = new ObjectInputStream(file);

			this.session = (Session) in.readObject();

			in.close();
			file.close();

			System.out.println("Model: Loaded Session from " + realFilepath);
		} catch (IOException ex) {
			System.out.println("Model: IOException is caught, failed to load file from " + realFilepath);
			return false;
		} catch (ClassNotFoundException ex) {
			System.out.println("Model: ClassNotFoundException is caught, invalid file at " + realFilepath);
			return false;
		}
		return true;
	}

	/**
	 * Start a brand new Session, and save the old one at it's last saved location,
	 * if it has one.
	 * 
	 * @return False if there is no last saved file path, and the UI needs to ask
	 *         the user where to save it. True if successful in loading new session.
	 * @throws Exception if it failed to save the current session, ask the user
	 *                   again.
	 */
	public boolean startNewSession() throws Exception {
		if (session.getLastSavedFilepath().isEmpty())
			return false;
		if (saveSession(session.getLastSavedFilepath()) == false)
			throw new Exception(
					"Model: Failed to save current Session where it should go at " + session.getLastSavedFilepath());
		session = new Session();
		return true;
	}

	/**
	 * Start a new Session from one that was previously saved to disk.
	 * 
	 * @param filepath Relative filepath of the save.
	 * @return False if there is no last saved file path, and the UI needs to ask
	 *         the user where to save it. True if successful in loading new session.
	 * @throws Exception if it failed to save the current session, ask the user
	 *                   again.
	 */
	public boolean startNewSessionFromSavedSession(String filepath) throws Exception {
		if (session.getLastSavedFilepath().isEmpty())
			return false;
		if (saveSession(session.getLastSavedFilepath()) == false)
			throw new Exception("Model: Failed to save current Session");
		return loadSession(filepath);
	}

	/**
	 * Save the current session. Throws an exception if Model doesn't know where to
	 * save to.
	 * 
	 * @return True if successful (with disk) in saving. False if not.
	 * @throws Exception When there is no lastSavedFilepath specified in the
	 *                   Session, and the UI needs to prompt the user to ask where
	 *                   to save to.
	 * @see {@link #saveSession(String) saveSession}
	 */
	public boolean saveSession() throws Exception {
		if (session.getLastSavedFilepath().isEmpty())
			throw new Exception("Session is missing a savepath");
		return saveSession(session.getLastSavedFilepath());
	}

	/**
	 * Save the current Session to the specified filepath.<br>
	 * <br>
	 * 
	 * <b>NOTE</b>: This does not add the extension <code>.gardenproject</code> to
	 * the save file, you must specify that manually in the JavaFX FileChooser as
	 * the default extension.
	 * 
	 * @param filepath Absolute filepath and filename on disk.
	 */
	public boolean saveSession(String filepath) {
		try {
			FileOutputStream file = new FileOutputStream(filepath);
			ObjectOutputStream out = new ObjectOutputStream(file);

			session.setLastSavedFilepath(filepath);
			out.writeObject(this.session);

			out.close();
			file.close();

			System.out.println("Model: Saved Session at " + filepath);
		} catch (IOException ex) {
			System.out.println("Model: IOException is caught, failed to save file");
			return false;
		}
		return true;
	}

	/**
	 * Load a new Session from the specified filepath.<br>
	 * <br>
	 * 
	 * @param filepath Absolute filepath and filename on disk.
	 */
	public boolean loadSession(String filepath) {
		try {
			System.out.println(filepath);
			FileInputStream file = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(file);

			this.session = (Session) in.readObject();

			in.close();
			file.close();

			System.out.println("Model: Loaded Session from " + filepath);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Model: IOException is caught, failed to load file");
			return false;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Model: ClassNotFoundException is caught, invalid file");
			return false;
		}
		return true;
	}

	private String calculateFilepath(String filepath) {
		return this.appDataDirectory + filepath;
	}

	/**
	 * Determine where the Operating System's Application Data directory is
	 * located.<br>
	 * <br>
	 * 
	 * Borrow from <a href="https://stackoverflow.com/questions/9235734/">
	 * StackOverflow</a>.
	 */
	private void determineAppDataDirectory() {
		String OS = (System.getProperty("os.name")).toUpperCase();

		if (OS.contains("WIN")) { // windows
			appDataDirectory = System.getenv("AppData");
			if (!appDataDirectory.endsWith("\\")) {
				appDataDirectory = appDataDirectory + "\\";
			}
		} else { // Mac, Linux, other...
			appDataDirectory = System.getProperty("user.home");
			appDataDirectory += "/Library/Application Support";
		}
	}

	/**
	 * Setup all the Windows objects of the project.
	 * @throws Exception 
	 */
	private void setupWindows() {
		windows = new Window[Windows.values().length];

		windows[Windows.AllPlants.ordinal()] = new AllPlants(this);
		windows[Windows.Download.ordinal()] = new Download(this);
		windows[Windows.ExistingPlants.ordinal()] = new ExistingPlants(this);
		windows[Windows.PlantInfo.ordinal()] = new PlantInfo(this);
		windows[Windows.PlotDesign.ordinal()] = new PlotDesign(this);
		windows[Windows.Questionnaire.ordinal()] = new Questionnaire(this);
		windows[Windows.SeasonView.ordinal()] = new SeasonView(this);
		windows[Windows.Tutorial.ordinal()] = new Tutorial(this);
		windows[Windows.Welcome.ordinal()] = new Welcome(this);
		windows[Windows.PlantSelection.ordinal()] = new PlantSelection(this);

		this.currentWindow = windows[Windows.Welcome.ordinal()];
	}

	/**
	 * Code executed when the application is stopped. Invoked by Controller.
	 * 
	 * @see udel.GardenProject.garden.Controller
	 */
	public void stop() {
		if (windows.length > 0)
			for (Window w : windows) {
				w.stop();
			}

		if (session.isUnsaved()) {
			if (session.getLastSavedFilepath().isEmpty() == false) {
				try {
					saveSession();
				} catch (Exception e) {
					System.out.println("Model: Unable to save current Session"
							+ " as there's no location specified, continuing exit");
				}
			}
		}
	}

	public Plant getPlantInfoPlant() {
		return plantInfoPlant;
	}

	public void setPlantInfoPlant(Plant plantInfoPlant) {
		this.plantInfoPlant = plantInfoPlant;
	}

	/**
	 * Hack Bold font of size 20
	 */
	private Font hackBold20 = Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 20);

	/**
	 * Gets the Input Stream of the HackBold font from the fonts resource package to
	 * be used in windows
	 * 
	 * @return The input stream of the HackBold font
	 */
	public Font getHackBold20() {
		return this.hackBold20;
	}

	/**
	 * Hack Bold Italic font of size 20
	 */
	private Font hackBoldItalic20 = Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-BoldItalic.ttf"), 20);

	/**
	 * Gets the Input Stream of the HackBold Italic font from the fonts resource
	 * package to be used in windows
	 * 
	 * @return The input stream of the HackBold font
	 */
	public Font getHackBoldItalic20() {
		return this.hackBoldItalic20;
	}

	/**
	 * Hack Bold font of size 12 for buttons
	 */
	private Font hackBold12 = Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 12);

	/**
	 * Gets the Input Stream of the HackBold font from the fonts resource package to
	 * be used in windows
	 * 
	 * @return The input stream of the HackBold font
	 */
	public Font getHackBold12() {
		return this.hackBold12;
	}

	/**
	 * Not hovering over the bottom buttons with keep it light green
	 */
	private String notHover = "-fx-base: #76C327;" + View.getBlackTextFill() + "-fx-focus-color: #3D6447;"
			+ "-fx-outer-border: #63A331;";

	/**
	 * Not hovering over buttons
	 * 
	 * @return String of attributes for when the user is not hovering over bottom
	 *         button
	 */
	public String getNotHover() {
		return this.notHover;
	}

	/**
	 * When the user is hovering over the bottom buttons
	 */
	private String hover = "-fx-base: white;" + View.getBlackTextFill() + "-fx-focus-color: #3D6447;"
			+ "-fx-outer-border: #63A331;";

	/**
	 * Hovering over buttons
	 * 
	 * @return String of attributes for when the user is hovering over bottom button
	 */
	public String getHover() {
		return this.hover;
	}
	
	/**
	 * Getter for getting an ArrayList<Plant> of only native plants.
	 * @return nativePlants
	 */
	public ArrayList<Plant> getNativePlants(){
		return nativeOnly;
	}

}
