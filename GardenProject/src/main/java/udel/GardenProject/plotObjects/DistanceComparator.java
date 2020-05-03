package udel.GardenProject.plotObjects;

import java.util.Comparator;

import udel.GardenProject.plants.Plant;

/**
 * 
 * 
 * @author Team 0
 */
public class DistanceComparator implements Comparator<PlotObject> {

	/**
	 * From where, horizontally, is the distance of the plot objects being 
	 * compared against?
	 */
	private double referenceX;
	
	/**
	 * From where, vertically, is the distance of the plot objects being 
	 * compared against?
	 */
	private double referenceY;
	
	/**
	 * Constructor. Take the reference x and y positions as straight doubles.
	 * 
	 * @param referenceX	Horizontal reference.
	 * @param referenceY	Verical reference.
	 */
	public DistanceComparator(double referenceX, double referenceY) {
		this.referenceX = referenceX;
		this.referenceY = referenceY;
	}
	
	/**
	 * Constructor. Get the reference x and y from a PlotObject itself.
	 * 
	 * @param reference		PlotObject to get the horizontal (x) and vertical
	 * 						(y) reference from.
	 */
	public DistanceComparator(PlotObject reference) {
		this.referenceX = reference.getPlotX();
		this.referenceY = reference.getPlotY();
	}
	
	@Override
	public int compare(PlotObject o1, PlotObject o2) {
		if(distanceToReference(o1) < distanceToReference(o2))
			return -1;
		else if(distanceToReference(o1) > distanceToReference(o2))
			return 1;
		else
			return 0;
	}
	
	/**
	 * Determine the distance a PlotObject is from the reference using the
	 * Pythagorean Theorem. 
	 * @param o		Plot Object to grab X and y from.
	 * @return	absolute distance from <code>o</code> to reference.
	 */
	private double distanceToReference(PlotObject o) {
		// TODO: Use just math.pow for the redundant statement?
		return Math.sqrt(
				(o.getPlotY() - referenceY) * (o.getPlotY() - referenceY) + 
				(o.getPlotX() - referenceX) * (o.getPlotX()- referenceX)
				);
	}

}
