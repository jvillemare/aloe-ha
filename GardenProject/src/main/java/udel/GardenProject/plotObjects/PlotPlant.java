package udel.GardenProject.plotObjects;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.Session;
import udel.GardenProject.plants.Plant;

/**
 * Plant that can appear on the plot in PlotDesign.<br><br>
 * 
 * Special object that stays in the root of the 
 * <code>udel.GardenProject.plotObjects</code> package.
 * 
 * @author Team 0
 */
public class PlotPlant extends PlotObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * The plant this PlotPlant is representing.
	 */
	private Plant p;
	
	/**
	 * Plant that can appear on in the PlotDesign.
	 * 
	 * @param Session	T
	 * @param p			Plant that's appearing on the Plot.
	 * @param x 		Horizontal position in PlotDesign.
	 * @param y 		Vertical position in PlotDesign.
	 */
	public PlotPlant(Model model, Plant p, double x, double y) {
		// TODO: A plant is always about a foot wide? Let's say for simplicity.
		// Fix later. Definitely a helper method in plant that reads the 
		// description or calculates or something
		super(model, x, y, checkIfCanopy(p.getCanopy()), 5.0, chooseWindowImage(p), choosePlotImage(p));
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return	Plant object.
	 */
	public Plant getPlant() {
		return this.p;
	}
	
	/**
	 * Checks to see if the canopy is null.
	 * @param c Canopy of a plant.
	 * @return -1 if the canopy is null. Otherwise it will return the 
	 * getAverageCanopyHeight of the given canopy.
	 */
	public static double checkIfCanopy(Canopy c) {
		if (c == null) {
			return -1;
		}
		else {
			return c.getAverageCanopyHeight();
		}
	}
	
	/**
	 * <b>For Out the Window View:</b>
	 * Determines the image to be used for the window view.
	 * @param p 	The plant object.
	 * @return 		The correct image based on canopy level. If no canopy level 
	 * 				if provided, a sunflower is used.
	 */
	public static String chooseWindowImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
			case FLOOR:
				return "/viewImages/floor.png";
			case UNDERSTORY:
				return "/viewImages/understory.png";
			case CANOPY:
				return "/viewImages/canopy.png";
			case EMERGENT:
				return "/viewImages/emergent.png";
			default:
				return "/viewImages/sunflower.png";
		}
	}
	
	/**
	 * <b>For Plot Design:</b>
	 * Determines the image to be used for the plot design.
	 * @param p 	The plant object.
	 * @return 		The correct image based on canopy level. If no canopy level 
	 * 				if provided, a young tree is used.
	 */
	public static String choosePlotImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
			case FLOOR:
				return "/viewImages/plotFloor.png";
			case UNDERSTORY:
				return "/viewImages/plotUnderstory.png";
			case CANOPY:
				return "/viewImages/plotCanopy.png";
			case EMERGENT:
				return "/viewImages/plotEmergent.png";
			default:
				return "/viewImages/babyTree.png";
		}
	}
	
	public static Node render(Plant p) {
		// fall back plant image
		String plantImages = choosePlotImage(p);
				
		// choose plant image if available
		if(p.getImages() != null && p.getImages().length != 0) {
			plantImages = p.getImages()[0];
		}
				
		/*
		 * String[] plantImg = p.getImages();
			Image plantImage;
			// Get the actual image if it exists
			if (plantImg != null && plantImg.length > 0) {
				String path = p.getImages()[0];
				plantImage = new Image(path, imageSize, imageSize, true, true);
			} else {
				// get a default image
				plantImage = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), imageSize, imageSize,
						true, true);
			}
			ImageView imageView = new ImageView();
			imageView.setImage(plantImage);
		 */
				
		return null;
	}

	@Override
	public Node render() {
		return render(this.p);
	}

}
