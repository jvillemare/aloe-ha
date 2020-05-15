package udel.GardenProject.enums;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.plants.Plant;

/**
 * All objects that can appear in the PlotDesign and BluePrint windows. 
 * Specifies how the objects are rendered in an accordion or other UI container.
 * This does not contain the constant Plant, as this is more an enum of plot 
 * obstacles and any other features not plant.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum PlotObjects {

	// TODO: Alphabetically sort
	TextLabel("object", "/plotObjects/textLabel.png", "", true, true),
	Fence("lines", "/plotObjects/fence.png", "", true, false), 
	Path("lines", "/plotObjects/path.png", "", true, true), 
	Forest("polygons", "/plotObjects/forest.png", "", false, true), 
	Patio("polygons", "/plotObjects/patio.png", "", true, false), 
	Playground("polygons", "/plotObjects/playground.png", "", true, false), 
	Pool("polygons", "/plotObjects/pool.png", "", true, false), 
	Road("polygons", "/plotObjects/road.png", "", false, true), 
	Rock("polygons", "/plotObjects/rock.png", "", true, false), 
	Shed("polygons", "/plotObjects/shed.png", "", true, false), 
	BirdBath("special", "/plotObjects/birdBath.png", "Where birds come to rest", true, false), 
	Bench("special", "/plotObjects/bench.png", "Sitting utensil", true, false),
	Flamingo("special", "/plotObjects/flamingo.png", "Lawn ornament", true, false), 
	Gnome("special", "/plotObjects/gnome.png", "Wards off evil spirits", true, false),
	Other("special", "/plotObjects/other.png", "", true, false);
	
	/**
	 * What package is this PlotObject found in (for categorization).
	 */
	private String type;
	
	/**
	 * Path to the image that represents this PlotObjects in an accordion.
	 */
	private String imageFilePath;
	
	/**
	 * The text that appears in the tool tip for this PlotObject.
	 */
	private String description;
	
	/**
	 * Does this PlotObject commonly show up in a garden (true), or near it?
	 * (false).
	 */
	private boolean typicallyInGarden;
	
	/**
	 * Is this PlotObject supposed to show up in the BluePrint window?
	 */
	private boolean bluePrintSpecific;
	
	/**
	 * Internal Constructor.
	 * @param type					What package this PlotObject is in.
	 * @param imageFilePath			Where in the resources directory is the 
	 * 								image representing this PlotObject. 
	 * @param description			The text that appears in the tool tip for
	 * 								this PlotObject.
	 * @param typicallyInGarden		Does this PlotObject usually show up in a
	 * 								garden?
	 * @param bluePrintSpecific		Is this Plot Object supposed to show up in
	 * 								the BluePrint window?
	 */
	private PlotObjects(String type, String imageFilePath, String description,
			boolean typicallyInGarden, boolean bluePrintSpecific) {
		this.type = type;
		this.imageFilePath = imageFilePath;
		this.description = description;
		this.typicallyInGarden = typicallyInGarden;
		this.bluePrintSpecific = bluePrintSpecific;
	}
	
	/**
	 * Getter.
	 * @return	What package this PlotObject is in.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Getter.
	 * @return	The icon representing this plot object, the image path to it.
	 */
	public String getImageFilePath() {
		return this.imageFilePath;
	}
	
	/**
	 * Getter.
	 * @return	The text that should appear in a tool tip for this plot object.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter.
	 * @return	True if this PlotObject typically appears in a Garden, false if
	 * 			not.
	 */
	public boolean isTypicallyInGarden() {
		return this.typicallyInGarden;
	}

	/**
	 * Getter.
	 * @return	True if this PlotObject is supposed to show up in the BluePrint
	 * 			Window, false if not.
	 */
	public boolean isBluePrintSpecific() {
		return this.bluePrintSpecific;
	}

}
