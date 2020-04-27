package udel.GardenProject.plants;

import java.util.Comparator;

public class PlantLatinNameComparator implements Comparator<Plant>{
	
	private boolean az;
	
	
	/**
	 * By default, assume sorting a list of plants alphabetically by latin name, A to Z.
	 */
	public PlantLatinNameComparator() {
		this.az = true;
	}
	
	/**
	 * Sort a list of plants alphabetically and specify whether it is A to Z(true) or Z to A(false)
	 * 
	 * @param descending true for descending, false for ascending.
	 */
	public PlantLatinNameComparator(boolean descending) {
		this.az = descending;
	}	
	

	@Override
	public int compare(Plant p1, Plant p2) {
		if (p1.getLatinName().equals(p2.getLatinName())) {
			return 0;
		}
		else if(this.az) {
			return p1.getLatinName().compareTo(p2.getLatinName());
		}
		else {
			return p2.getLatinName().compareTo(p1.getLatinName());
		}
	}

}
