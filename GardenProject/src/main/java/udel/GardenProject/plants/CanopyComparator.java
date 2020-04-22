package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Compare plants by Canopy level, by default descending.
 * 
 * @author Team 0
 */
public class CanopyComparator implements Comparator<Plant> {

	private boolean descending;

	/**
	 * By default, assume sorting a list of plants by canopy, descending.
	 */
	public CanopyComparator() {
		this.descending = true;
	}

	/**
	 * Sort a list of plants by canopy and specify whether it is ascending or
	 * descending.
	 * 
	 * @param descending true for descending, false for ascending.
	 */
	public CanopyComparator(boolean descending) {
		this.descending = descending;
	}

	public int compare(Plant a, Plant b) {
		// TODO: Implement
		return 0;
	}

}
