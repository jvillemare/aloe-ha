package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Sunlight;

/**
 * For the BluePrint Window, allow the user to specify the characteristics in
 * specific areas of their plot.
 * 
 * @author Team 0
 */
public class BluePrintCharacteristics extends GenericPolygon implements 
	Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * TODO: The maximum canopy for this blue print characteristic area?
	 */
	private Canopy canopy;
	
	private Moisture moisture;
	
	private SoilTypes soilType;
	
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
	public BluePrintCharacteristics(double x, double y, AdjustablePolygon p, String windowPath,
			String plotPath) {
		super(x, y, 0.0, p, windowPath, plotPath);
		// TODO Auto-generated constructor stub
	}

}
