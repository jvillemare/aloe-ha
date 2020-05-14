package udel.GardenProject.enums;

/**
 * All objects that can appear in the PlotDesign.
 * 
 * @author Team 0
 */
public enum PlotObjects {

	// TODO: Alphabetically sort
	Plant("object", true),
	TextLabel("object", true),
	Fence("lines", true), 
	Path("lines", true), 
	Forest("polygons", false), 
	Patio("polygons", true), 
	Playground("polygons", true), 
	Pool("polygons", true), 
	Road("polygons", false), 
	Rock("polygons", true), 
	Shed("polygons", true), 
	BirdBath("special", true), 
	Bench("special", true),
	Flamingo("special", true), 
	Gnome("special", true),
	Other("special", true);
	
	/**
	 * What package is this PlotObject found in (for categorization).
	 */
	private String type;
	
	/**
	 * Does this PlotObject commonly show up in a garden (true), or near it?
	 * (false).
	 */
	private boolean typicallyInGarden;
	
	/**
	 * Internal Constructor.
	 * @param type					What package this PlotObject is in.
	 * @param typicallyInGarden		Does this PlotObject usually show up in a
	 * 								garden?
	 */
	private PlotObjects(String type, boolean typicallyInGarden) {
		this.type = type;
		this.typicallyInGarden = typicallyInGarden;
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
	 * @return	True if this PlotObject typically appears in a Garden, false if
	 * 			not.
	 */
	public boolean isTypicallyInGarden() {
		return this.typicallyInGarden;
	}
	
	public PlotObject create(Model m, double x, double y, Object... objects) {
		
	}

}
