package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Sort a list of plants by bloom time, assuming descending default.
 * 
 * @author Team 0
 */
public class BloomTimeComparator implements Comparator<Plant> {

	private boolean descending;
	
	/**
	 * By default, assume sorting a list of plants by bloom time, descending.
	 */
	public BloomTimeComparator() {
		this.descending = true;
	}
	
	/**
	 * Sort a list of plants by bloom time and specify whether it is ascending
	 * or descending.
	 * 
	 * @param descending	true for descending, false for ascending.
	 */
	public BloomTimeComparator(boolean descending) {
		this.descending = descending;
	}
	
	public int compare(Plant a, Plant b) {
		if (a.bloomTime == b.bloomTime) {
			return 0;
		}
		else if(this.descending) {
			if (a.bloomTime.ordinal() < b.bloomTime.ordinal()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			if(a.bloomTime.ordinal() < b.bloomTime.ordinal()) {
				return -1;	
			}
			else {
				return 1;
			}
		}
	}
	
}
