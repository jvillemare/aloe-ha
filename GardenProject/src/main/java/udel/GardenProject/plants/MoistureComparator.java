package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Sort plants by Moisture, by default descending.
 * 
 * @author Team 0
 */
public class MoistureComparator implements Comparator<Plant> {
	
	private boolean descending;

	/**
	 * By default, assume sorting a list of plants by moisture, descending.
	 */
	public MoistureComparator() {
		this.descending = true;
	}

	/**
	 * Sort a list of plants by moisture and specify whether it is ascending or
	 * descending.
	 * 
	 * @param descending true for descending, false for ascending.
	 */
	public MoistureComparator(boolean descending) {
		this.descending = descending;
	}

	public int compare(Plant a, Plant b) {
		// TODO: Implement
		return 0;
	}
	
}
