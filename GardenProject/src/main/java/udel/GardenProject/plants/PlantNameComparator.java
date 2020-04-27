package udel.GardenProject.plants;

import java.util.Comparator;

public class PlantNameComparator implements Comparator<Plant> {

	private boolean az;
	private boolean common;

	/**
	 * By default, assume sorting a list of plants alphabetically by first common
	 * name, A to Z.
	 */
	public PlantNameComparator() {
		this.az = true;
		this.common = true;
	}

	/**
	 * Sort a list of plants alphabetically by first common name and specify whether
	 * it is A to Z(true) or Z to A(false)
	 * 
	 * @param descending true for A to Z, false for Z to A.
	 */
	public PlantNameComparator(boolean descending) {
		this.az = descending;
		this.common = true;
	}

	/**
	 * Sort a list of plants alphabetically
	 * <li>either from A to Z or Z to A depending on boolean descending
	 * <li>either by common or latin names depending on boolean common
	 * 
	 * @param descending true for A to Z, false for Z to A.
	 * @param common     true for common names, false for latin names
	 */
	public PlantNameComparator(boolean descending, boolean common) {
		this.az = descending;
		this.common = common;
	}

	@Override
	public int compare(Plant p1, Plant p2) {
		if (this.common) {
			String[] p1Com = p1.getCommonNames();
			String[] p2Com = p2.getCommonNames();
			if (p1Com == p2Com) {
				return 0;
			} else if (p1Com == null) {
				return 1;
			} else if (p2Com == null) {
				return -1;
			} else if (this.az) {
				return p1Com[0].compareTo(p2Com[0]);
			} else {
				return p2Com[0].compareTo(p1Com[0]);
			}
		} else {
			String lat1 = p1.getLatinName();
			String lat2 = p2.getLatinName();
			if (lat1 == lat2) {
				return 0;
			} else if (lat1 == null) {
				return 1;
			} else if (lat2 == null) {
				return -1;
			} else if (this.az) {
				return lat1.compareTo(lat2);
			} else {
				return lat2.compareTo(lat1);
			}
		}
	}

}
