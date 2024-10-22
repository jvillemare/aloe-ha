package udel.GardenProject.plotObjects;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import udel.GardenProject.garden.Model;

/**
 * An object that can appear in PlotDesign must implement this interface.
 * 
 * @version 1.0
 * @author Team 0
 */
public abstract class PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * Local reference to model to get the current session. The current session
	 * is used for getting the current width and height of the plot, and other 
	 * relevant factors for rendering Plot Objects.
	 */
	transient Model model;
	
	/**
	 * The name of the plot object
	 */
	private String name = "";
  
	/**
	 * Boolean value to determine if the plotobject use its own dragHandler
	 */
	private boolean useDefaultDragHandler;
  
	/**
	 * Constructor. Every object on the Plot in PlotDesign must have an X and Y
	 * position determined from a MouseRelease event, and a height for
	 * calculating plant transition, shade, etc.
	 * 
	 * @param 
	 * @param x			Horizontal position in plot.
	 * @param y			Vertical position in plot.
	 * @param height	Height in feet of plot object.
	 * @param radius  Radius in feet of plot object.
	 * @param imagePath	String of path to window view image.
	 * @param plotPath	String of path to plot design image.
	 */
	public PlotObject(Model model, double x, double y, 
			double height, double radius, String imagePath, String plotPath, String name) {
		this.model = model;
		this.x = x;
		this.y = y;
		this.height = height;
		this.radius = radius;
		this.windowImage = imagePath;
		this.plotImage = plotPath;
		this.name = name;
		this.useDefaultDragHandler = false;
	}
	
	/**
	 * Abstract。 Set the visible value for object related to adjustable polygon.
	 * @param vis
	 */
	public abstract void setVisible(boolean vis);
	
	/**
	 * Abstract. All Plot Objects must specify how they will appear on a plot.
	 * @return	Node object
	 */
	public abstract Node render();
	
	/**
	 * Abstract. All Plot Objects must specify how wide they will be in the UI.
	 * @return	Width of a plot object as it appears in a scene.
	 */
	public abstract double getRenderWidth();
	
	/**
	 * Abstract. All Plot Objects must specify how tall they will appear in the 
	 * UI.
	 * @return	Length of a plot object as it appears in a scene.
	 */
	public abstract double getRenderHeight();
	
	// TODO: Rename?
	public Node deleteFromInterface() {
		// TODO: Rearrange me
		// TODO: Make me abstract
		return null;
	}
	
	/**
	 * 
	 * Abstract. All Plot Objects must specify how they will be drawn on the
	 * season view canvas.
	 * 
	 * @param gc Graphics Context for drawing images/objects on canvas.
	 * @param gb Blur effect for shade beneath plants.
	 * @param minScale minimum scale amount for objects based on depth.
	 * @param maxDepth Depth of plot design window.
	 * @param maxWidth Width of plot design window.
	 * @param viewDepth Depth of gc's canvas.
	 * @param viewWidth Width of gc's Canvas.
	 * @param yearScale Scaling amount based on year.
	 * @param e Effect to be added to plants.
	 */
	public abstract void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth, double viewDepth, double viewWidth, double yearScale, Effect e);
	
	/**
	 * Helper method.
	 * @return	Width of user's plot.
	 */
	public final int getPlotWidth() {
		return this.model.getSession().getWidthOfUserPlot();
	}
	
	/**
	 * Helper method.
	 * @return	Length of user's plot.
	 */
	public final int getPlotLength() {
		return this.model.getSession().getLengthOfUserPlot();
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
	 * Getter.
	 * @return Model of current session.
	 */
	public final Model getModel() {
		return this.model;
	}
	
	/**
	 * Setter. Critical for a session that was reloaded from a save file.
	 * @param model	Updates the model reference.
	 */
	public final void setModel(Model model) {
		this.model = model;
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
	 * Getter.
	 * @return Path to image of plot object for window view.
	 */
	public final String getWindowImage() {
		return this.windowImage;
	}
	
	/**
	 * Getter.
	 * @return Path to image of plot object for plot design.
	 */
	public final String getPlotImage() {
		return this.plotImage;
	}
	
	/**
	 * All objects must be specified to what they are 
	 * @return	Name of object
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Getter.
	 * @return Boolean value of useDefaultDragHandler;
	 */
	public boolean getUseDefaultDragHandler() {
		return useDefaultDragHandler;
	}
	
	/**
	 * Setter
	 * @param useDefault
	 */
	public void setUseDefaultDragHandler(boolean useDefault) {
		useDefaultDragHandler = useDefault;
	}

}
