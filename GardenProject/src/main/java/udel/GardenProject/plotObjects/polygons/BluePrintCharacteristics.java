package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Sunlight;
import udel.GardenProject.garden.Model;

/**
 * For the BluePrint Window, allow the user to specify the characteristics in
 * specific areas of their plot.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class BluePrintCharacteristics extends GenericPolygon implements 
	Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * TODO: The maximum canopy for this blue print characteristic area?
	 */
	private Canopy canopy;
	
	/**
	 * Specific moisture level of this characteristic area.
	 */
	private Moisture moisture;
	
	/**
	 * Specific soil type of this characteristic area.
	 */
	private SoilTypes soilType;
	
	/**
	 * Specific sunlight of this characteristic area.
	 */
	private Sunlight sunlight;

	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position of the top left point of this polygon.
	 * @param y	Vertical position of the top left point of this polygon.
	 * @param p
	 * @param windowPath
	 * @param plotPath
	 */
	public BluePrintCharacteristics(Model model, double x, double y, 
			AdjustablePolygon p, String windowPath,
			String plotPath) {
		super(model, x, y, 0.0, p, windowPath, plotPath);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Setter. Set all of the 
	 * @param canopy
	 * @param moisture
	 * @param soilType
	 * @param sunlight
	 */
	public void setAll(Canopy canopy, Moisture moisture, SoilTypes soilType, 
			Sunlight sunlight) {
		this.canopy = canopy;
		this.moisture = moisture;
		this.soilType = soilType;
		this.sunlight = sunlight;
	}

	/**
	 * @return the canopy
	 */
	public Canopy getCanopy() {
		return canopy;
	}

	/**
	 * @param canopy the canopy to set
	 */
	public void setCanopy(Canopy canopy) {
		this.canopy = canopy;
	}

	/**
	 * @return the moisture
	 */
	public Moisture getMoisture() {
		return moisture;
	}

	/**
	 * @param moisture the moisture to set
	 */
	public void setMoisture(Moisture moisture) {
		this.moisture = moisture;
	}

	/**
	 * @return the soilType
	 */
	public SoilTypes getSoilType() {
		return soilType;
	}

	/**
	 * @param soilType the soilType to set
	 */
	public void setSoilType(SoilTypes soilType) {
		this.soilType = soilType;
	}

	/**
	 * @return the sunlight
	 */
	public Sunlight getSunlight() {
		return sunlight;
	}

	/**
	 * @param sunlight the sunlight to set
	 */
	public void setSunlight(Sunlight sunlight) {
		this.sunlight = sunlight;
	}

	@Override
	public Node render() {
		return null;
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 40.0;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 40.0;
	}
	
	@Override
	public String getName() {
		return ""; 
	}

}
