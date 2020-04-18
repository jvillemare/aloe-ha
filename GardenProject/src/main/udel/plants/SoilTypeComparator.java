package main.udel.plants;

import java.util.Comparator;

public class SoilTypeComparator implements Comparator<Plant> {

	private boolean descending;

	/**
	 * By default, assume sorting a list of plants by soil type, descending.
	 */
	public SoilTypeComparator() {
		this.descending = true;
	}

	/**
	 * Sort a list of plants by soil type and specify whether it is ascending or
	 * descending.
	 * 
	 * @param descending true for descending, false for ascending.
	 */
	public SoilTypeComparator(boolean descending) {
		this.descending = descending;
	}

	public int compare(Plant a, Plant b) {
		// TODO: Implement
		return 0;
	}


}
