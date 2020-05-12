package udel.GardenProject.plotObjects;

import java.io.Serializable;

/**
 * 
 * 
 * @author Team 0
 */
public class PlotTextLabel extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;

	public PlotTextLabel(double x, double y, String text) {
		super(x, y, 0.0, 0.0, "", "");
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

}
