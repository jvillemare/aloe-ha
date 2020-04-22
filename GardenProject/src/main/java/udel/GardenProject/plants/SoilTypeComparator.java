package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Sort plants by SoilType, by default pH descending.
 * 
 * @author Team 0
 */
public class SoilTypeComparator implements Comparator<Plant>{

	private boolean descending;
	
	/**
	 * By default, assume sorting a list of plants by soil type, descending.
	 * Note: descending means descending alphabetic order
	 */
	public SoilTypeComparator() {
		this.descending = true;
	}
	
	/**
	 * Sort a list of plants by alphabetic order
	 * 
	 * @param descending	true for descending, false for ascending.
	 */
	public SoilTypeComparator(boolean descending) {
		this.descending = descending;
	}
	
	public int compare(Plant a, Plant b) {
		if (descending) {
			return a.soilType.getName().compareTo(b.soilType.getName());
		}else {
			return b.soilType.getName().compareTo(b.soilType.getName());
		}
	}
	
}
