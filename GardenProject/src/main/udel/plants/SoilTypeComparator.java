package main.udel.plants;

import java.util.Comparator;

public class SoilTypeComparator implements Comparator<Plant>{

	private boolean phDescending;
	
	/**
	 * By default, assume sorting a list of plants by soil type, descending.
	 * Note: descending means descending alphabetic order
	 */
	public SoilTypeComparator() {
		this.phDescending = true;
	}
	
	/**
	 * Sort a list of plants by alphabetic order
	 * 
	 * @param descending	true for descending, false for ascending.
	 */
	public SoilTypeComparator(boolean descending) {
		this.phDescending = descending;
	}
	
	public int compare(Plant a, Plant b) {
		if (phDescending) {
			return a.soilType.getName().compareTo(b.soilType.getName());
		}else {
			return b.soilType.getName().compareTo(b.soilType.getName());
		}
	}
	
}
