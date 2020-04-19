package main.udel.plants;

import java.util.Comparator;

public class SoilTypeComparator implements Comparator<Plant>{

	
	private boolean phDescending;
	
	/**
	 * By default, assume sorting a list of plants by soil type, descending.
	 * Note: descending means most alkaline first to most acidic while ascending is opposite.
	 */
	public SoilTypeComparator() {
		this.phDescending = true;
	}
	
	/**
	 * Sort a list of plants by bloom time and specify whether it is ascending
	 * or descending.
	 * 
	 * @param descending	true for descending, false for ascending.
	 */
	public SoilTypeComparator(boolean descending) {
		this.phDescending = descending;
	}
	
	public int compare(Plant a, Plant b) {
		if (phDescending) {
			return a.soilType.getPHType().compareTo(b.soilType.getPHType());
		}else {
			return b.soilType.getPHType().compareTo(b.soilType.getPHType());
		}
	}
	
}
