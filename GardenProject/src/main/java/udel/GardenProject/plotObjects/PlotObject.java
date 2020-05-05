package udel.GardenProject.plotObjects;

/**
 * An object that can appear in PlotDesign must implement this interface.
 * 
 * @author Team 0
 */
public abstract class PlotObject {
	
	/**
	 * Horizontal position of object in plot design. May have a different 
	 * definition in each subpackage.
	 */
	private double x;
	
	/**
	 * Vertical position of object in plot design. May have a different
	 * definition in each subpackage.
	 */
	private double y;
	
	/**
	 * Height of plot object in feet.
	 */
	private double height;
	
	private String imagePath;
	
	/**
	 * Constructor. Every object on the Plot in PlotDesign must have an X and Y
	 * position determined from a MouseRelease event, and a height for
	 * calculating plant transition, shade, etc.
	 * 
	 * @param x			Horizontal position in plot.
	 * @param y			Vertical position in plot.
	 * @param height	Height in feet of plot object.
	 */
	public PlotObject(double x, double y, double height, String path) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.imagePath = path;
	}
	
	/**
	 * Getter.
	 * @return	Get the horizontal position in PlotDesign.
	 */
	public final double getPlotX() {
		return this.x;
	}
	
	/**
	 * Getter.
	 * @return	Get the vertical position in PlotDesign.
	 */
	public final double getPlotY() {
		return this.y;
	}
	
	/**
	 * Getter.
	 * @return	How tall is this object in the real world?
	 */
	public final double getHeight() {
		return this.height;
	}
	
	/**
	 * Getter
	 * @return Path to image for plot object.
	 */
	public final String getImagePath() {
		return this.imagePath;
	}

}
