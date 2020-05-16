package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotPlant;

/**
 * All other miscellaneous plot objects descend from GenericSpecial.
 * 
 * @version 1.0
 * @author Team 0
 */
public class GenericSpecial extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public GenericSpecial(Model model, double x, double y, double height, double radius, String imagePath,
			String plotPath) {
		super(model, x, y, height, radius, imagePath, plotPath, "");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		Image genericSpecialImage;
		
		genericSpecialImage = new Image(
				getClass().getResourceAsStream(
						this.getPlotImage()), 40.0, 40.0,true, true);
		
		ImageView imageView = new ImageView();
		imageView.setImage(genericSpecialImage);
				
		return imageView;
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getName() {
		return "Special"; 
	}

}
