package main.udel.plants;

import java.util.Comparator;

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
		if (descending) {
			return a.moisture.getIntensity() - b.moisture.getIntensity();
		}else {
			return b.moisture.getIntensity() - a.moisture.getIntensity();
		}
	}
}

