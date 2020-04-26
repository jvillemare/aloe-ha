package udel.GardenProject.garden;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import udel.GardenProject.enums.Windows;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plants.PlantLoader;
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
	 * Constructor, initialize everything.
	 * 
	 * @param width
	 * @param height
	 */
	public Model(int width, int height) {
		this.width = width;
		this.height = height;
		this.session = new Session();
		
		determineAppDataDirectory();
		setupWindows();
		
		try {
			this.plants = PlantLoader.getPlants();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("ERROR: Problem with reading Plant data JSON files...");
			System.exit(1);
		}
	}
	
	/**
	 * If the user is on the PlotDesign Window, it repeatedly checks the plot
	 * for collision errors, invasive plants, and evaluates it.
	 */
	public void update() {
		// TODO: Implement for PlotDesign...
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
	 * Change the current Window.
	 * @param w A Window specified by the Windows enum.
	 */
	public void setWindow(Windows w) {
		lastWindow = currentWindow;
		currentWindow = windows[w.ordinal()];
		currentWindow.refresh();
	}
	
	/**
	 * Go back to the last window.
	 */
	public void goToLastWindow() {
		Window temp = currentWindow;
		currentWindow = lastWindow;
		lastWindow = temp;
		currentWindow.refresh();
	}
	
	/**
	 * TODO: Later..
	 * @return
	 */
	public Window getLastWindow() {
		return this.lastWindow;
	}
	
	/**
	 * Get the suggested scene width for a Window object.
	 * @return width as an integer.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the suggested scene height for a Window object.
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
	 * Search the array of plants and return an array of all Plants that match
	 * the query. If the query matches the latin or any of the common names, 
	 * that plant is returned.<br><br>
	 * 
	 * Comparators can be used on the results.
	 * 
	 * @param	query	Simple, non-regex query where the query can appear
	 * 					anywhere in the plant latin or common name.
	 * @return	null if no matching results, a Plant array of all matching 
	 * 			results.
	 */
	public ArrayList<Plant> searchPlants(String query) {
		ArrayList<Plant> results = new ArrayList<Plant>();
		
		Iterator<Plant> pIterator = plants.iterator();
		while(pIterator.hasNext()) {
			Plant p = pIterator.next();
			boolean addToResults = false;
			
			if(p.getLatinName().contains(query))
				addToResults = true;
			
			for(String commonName : p.getCommonNames())
				if(commonName.contains(query))
					addToResults = true;
			
			if(addToResults)
				results.add(p);
		}
		
		return results;
	}
	
	/**
	 * Safely <i>cache</i> a file to a user's computer. Hides all exceptions and
	 * potential errors.
	 * 
	 * @param filepath	Path and filename where to save the file in the user's
	 * 					<code>appDataDirectory</code>.
	 * @return true if successful in caching the file, false if not.
	 * @see {@link #loadCacheFile(String) loadCacheFile}
	 */
	public boolean saveCacheFile(Object o, String filepath) {
		String realFilepath = calculateFilepath(filepath);
		try {
			FileOutputStream file = new FileOutputStream(appDataDirectory + realFilepath); 
	        ObjectOutputStream out = new ObjectOutputStream(file); 
	          
	        out.writeObject(o); 
	          
	        out.close(); 
	        file.close(); 
		} catch(IOException e) {
			System.out.println("Model: Failed to save cache file at " + realFilepath);
			return false;
		}
		return true;
	}
	
	/**
	 * Safely <i>load</i> a file to a user's computer. Hides all exceptions and
	 * potential errors.<br><br>
	 * 
	 * This can also be used to check if a file exists in cache if this method
	 * returns false.
	 * 
	 * @param filepath	Path and filename where to load the file in the user's
	 * 					<code>appDataDirectory</code>.
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
              
            this.session = (Session)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Model: Loaded Session from " + realFilepath); 
        } catch(IOException ex) { 
            System.out.println("Model: IOException is caught, failed to load file from " 
            		+ realFilepath);
            return false;
        } catch(ClassNotFoundException ex)  { 
            System.out.println("Model: ClassNotFoundException is caught, invalid file at "
            		+ realFilepath);
            return false;
        } 
        return true;
	}
	
	/**
	 * Start a brand new Session, and save the old one at it's last saved
	 * location, if it has one.
	 * 
	 * @return False if there is no last saved file path, and the UI needs to 
	 * 			ask the user where to save it. True if successful in loading new
	 * 			session.
	 * @throws Exception if it failed to save the current session, ask the user
	 * 			again. 
	 */
	public boolean startNewSession() throws Exception {
		if(session.getLastSavedFilepath().isEmpty())
			return false;
		if(saveSession(session.getLastSavedFilepath()) == false)
			throw new Exception("Model: Failed to save current Session where it should go at "
					+ session.getLastSavedFilepath());
		session = new Session();
		return true;
	}
	
	/**
	 * Start a new Session from one that was previously saved to disk.
	 * 
	 * @param filepath Relative filepath of the save.
	 * @return False if there is no last saved file path, and the UI needs to
	 *			ask the user where to save it. True if successful in loading new
	 *			session.
	 * @throws Exception if it failed to save the current session, ask the user
	 * 			again. 
	 */
	public boolean startNewSessionFromSavedSession(String filepath) throws Exception {
		if(session.getLastSavedFilepath().isEmpty())
			return false;
		if(saveSession(session.getLastSavedFilepath()) == false)
			throw new Exception("Model: Failed to save current Session");
		return loadSession(filepath);
	}
	
	/**
	 * Save the current session. Throws an exception if Model doesn't know where
	 * to save to.
	 * 
	 * @return True if successful (with disk) in saving. False if not.
	 * @throws Exception When there is no lastSavedFilepath specified in the
	 * 			Session, and the UI needs to prompt the user to ask where to
	 * 			save to.
	 * @see {@link #saveSession(String) saveSession}
	 */
	public boolean saveSession() throws Exception {
		if(session.getLastSavedFilepath().isEmpty())
			throw new Exception("Session is missing a savepath");
		return saveSession(session.getLastSavedFilepath());
	}
	
	/**
	 * Save the current Session to the specified filepath.<br><br>
	 * 
	 * <b>NOTE</b>: This does not add the extension <code>.gardenproject</code>
	 * to the save file, you must specify that manually in the JavaFX 
	 * FileChooser as the default extension.
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
        } catch(IOException ex) { 
            System.out.println("Model: IOException is caught, failed to save file");
            return false;
        } 
        return true;
	}
	
	/**
	 * Load a new Session from the specified filepath.<br><br>
	 * 
	 * @param filepath Absolute filepath and filename on disk.
	 */
	public boolean loadSession(String filepath) {
        try {    
            FileInputStream file = new FileInputStream(filepath); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            this.session = (Session)in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Model: Loaded Session from " + filepath); 
        } catch(IOException ex) { 
            System.out.println("Model: IOException is caught, failed to load file");
            return false;
        } catch(ClassNotFoundException ex)  { 
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
	 * located.<br><br>
	 * 
	 * Borrow from <a href="https://stackoverflow.com/questions/9235734/">
	 * StackOverflow</a>.
	 */
	private void determineAppDataDirectory() {
		String OS = (System.getProperty("os.name")).toUpperCase();

		if (OS.contains("WIN")) { // windows
			appDataDirectory = System.getenv("AppData");
		} else { // Mac, Linux, other...
			appDataDirectory = System.getProperty("user.home");
			appDataDirectory += "/Library/Application Support";
		}
	}
	
	/**
	 * Setup all the Windows objects of the project.
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
		for(Window w : windows) {
			w.stop();
		}
		
		if(session.isUnsaved()) {
			if(session.getLastSavedFilepath().isEmpty() == false) {
				try {
					saveSession();
				} catch (Exception e) {
					System.out.println("Model: Unable to save current Session" +
							" as there's no location specified, continuing exit");
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

}
