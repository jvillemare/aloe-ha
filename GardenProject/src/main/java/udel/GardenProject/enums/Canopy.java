package udel.GardenProject.enums;

/**
 * Vertical layers of the rain forest.
 * 
 * @version 1.0
 * @author Team 0
 * @see <a href="http://www.srl.caltech.edu/personnel/krubal/rainforest/Edit560s6/www/whlayers.html">
 * CalTech</a>
 */
public enum Canopy {
	
	FLOOR(15, "Floor", "Plants and Srubs", "Requires the least amount of sunlight"), 
	UNDERSTORY(55, "Understory", "Small Trees", "Requires less than average amount of sunlight"), 
	CANOPY(95, "Canopy", "Medium-size Trees", "Requires more than average amount of sunlight"), 
	EMERGENT(125, "Emergent", "Largest Trees", "Requires the most amount of sunlight");
	
	/**
	 * Maximum height of a canopy layer in Imperial feet
	 */
	private int maximumHeight;
	
	/**
	 * The name of the Canopy level.
	 */
	private String name;
	
	/**
	 * The description for the tool tip used in Plant selection
	 */
	private String description;
	
	/**
	 * String containing what plant is in each.
	 */
	private String contains;
	
	Canopy(int max, String name, String contains, String description) {
		this.maximumHeight = max;
		this.name = name;
		this.contains = contains;
		this.description = description;
	}
	
	/**
	 * Getter.
	 * @return The maximum height a canopy layer can be in Imperial feet.
	 */
	public int getMaximumHeight() {
		return this.maximumHeight;
	}
	
	/**
	 * Getter.
	 * @return The description of the canopy level.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Calculate the average height of a canopy by averaging the height of this
	 * canopy and the previous canopy.
	 * 
	 * @return Average height as a double.
	 */
	public double getAverageCanopyHeight() {
		if(this.ordinal() == 0)
			return (double)this.maximumHeight / 2.0;
		
		return (
				(double)this.maximumHeight + 
				Canopy.values()[this.ordinal() - 1].maximumHeight
				) / 2.0;
	}
	
	/**
	 * Using a height in feet, determine the canopy level that the height
	 * belongs to.
	 * 
	 * @param height	In feet.
	 * @return 	NULL if height is invalid. Otherwise, the corresponding Canopy
	 * 			level.
	 */
	public Canopy getCanopyFromHeight(double height) {
		return this.getCanopyFromHeight((int)height);
	}
	
	/**
	 * Using a height in feet, determine the canopy level that the height
	 * belongs to.
	 * 
	 * @param height	In feet.
	 * @return 	NULL if height is invalid. Otherwise, the corresponding Canopy
	 * 			level.
	 */
	public Canopy getCanopyFromHeight(int height) {
		if(height < 0)
			return null;
		
		Canopy[] c = Canopy.values();
		
		if(height > c[c.length - 1].maximumHeight)
			return null;
		
		for(int i = 0; i < c.length; i++)
			if(height <= c[i].maximumHeight)
				return c[i];
		
		return null;
	}
	
	/**
	 * Returns the name of the Canopy Level.
	 * @return String name of Canopy level
	 */
	public String getFriendlyName() {
		return name;
	}
	
	/**
	 * Getter.
	 * @return String of plants this canopy can contain
	 */
	public String getContains() {
		return contains;
	}

}
