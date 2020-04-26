package udel.GardenProject.plants.plotObjects;

/**
 * An object that can appear in PlotDesign must implement this interface.
 * 
 * @author Team 0
 */
public interface PlotObject {
	
	/**
	 * Getter.
	 * 
	 * @return Get the horizontal position in PlotDesign.
	 */
	public double getPlotX();
	
	/**
	 * Getter.
	 * 
	 * @return Get the vertical position in PlotDesign.
	 */
	public double getPlotY();

}
