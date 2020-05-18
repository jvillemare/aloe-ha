package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Compare plants by Canopy level, by default descending.
 * 
 * @version 1.0
 * @author Team 0
 */
public class CanopyComparator implements Comparator<Plant> {

	/**
	 * By default true.
	 */
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
		if(a==null && b==null) {
			return 0;
		}
		else if(a==null) {
			if(descending) {
				return b.getCanopy().ordinal();
			}
			else {
				return 0 - b.getCanopy().ordinal();
			}
		}
		else if(b==null) {
			if(descending) {
				return 0 - a.getCanopy().ordinal();
			}
			else {
				return a.getCanopy().ordinal();
			}
		}
		if (a.getCanopy().ordinal() == b.getCanopy().ordinal()) {
			return 0;
		}
		else if(descending) {
			return b.getCanopy().ordinal() - a.getCanopy().ordinal();
		}
		else {
			return a.getCanopy().ordinal() - b.getCanopy().ordinal();
		}
	}

}
