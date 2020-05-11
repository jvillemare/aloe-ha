package udel.GardenProject.plotObjects;

import java.io.Serializable;

/**
 * An object that can appear in PlotDesign must implement this interface.
 * 
 * @author Team 0
 */
public abstract class PlotObject implements Serializable {
	
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
	
	/**
	 * Radius of obejct in feet.
	 */
	private double radius;
	
	/**
	 * Path of image for window view.
	 */
	private String windowImage;
	
	/**
	 * Path of image for plot design.
	 */
	private String plotImage;
  
	/**
	 * Constructor. Every object on the Plot in PlotDesign must have an X and Y
	 * position determined from a MouseRelease event, and a height for
	 * calculating plant transition, shade, etc.
	 * 
	 * @param x			Horizontal position in plot.
	 * @param y			Vertical position in plot.
	 * @param height	Height in feet of plot object.
   * @param radius  Radius in feet of plot object.
	 * @param imagePath	String of path to window view image.
	 * @param plotPath	String of path to plot design image.
	 */
	public PlotObject(double x, double y, double height, double radius, 
			String imagePath, String plotPath) {
		this.x = x;
		this.y = y;
		this.height = height;
    this.radius = radius;
		this.windowImage = imagePath;
		this.plotImage = plotPath;
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
	 * Getter.
	 * @return	Radius of this plot object in feet.
	 */
	public final double getRadius() {
		return this.radius;
  }
    
  /**
	 * Setter.
	 * @param x
	 */
	public final void setPlotX(double x) {
		this.x = x;
	}
	
	/**
	 * Setter.
	 * @param y
	 */
	public final void setPlotY(double y) {
		this.y = y;
	}

  /**
	 * Getter
	 * @return Path to image of plot object for window view.
	 */
	public final String getWindowImage() {
		return this.windowImage;
	}
	
	/**
	 * Getter
	 * @return Path to image of plot object for plot design.
	 */
	public final String getPlotImage() {
		return this.plotImage;
	}

}
