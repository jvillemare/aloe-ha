package udel.GardenProject.plotObjects;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * A customizable text label that a user can put on their Plot
 * 
 * @author Team 0
 */
public class PlotTextLabel extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;

	public PlotTextLabel(Model model, double x, double y, String text) {
		super(model, x, y, 0.0, 0.0, "", "");
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		// return JavaFX Text where the text is this.text
		return null;
	}

}
