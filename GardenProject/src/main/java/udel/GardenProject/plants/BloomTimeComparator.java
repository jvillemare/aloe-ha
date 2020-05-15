package udel.GardenProject.plants;

import java.util.Comparator;

/**
 * Sort a list of plants by bloom time, assuming descending default.
 * 
 * @version 1.0
 * @author Team 0
 */
public class BloomTimeComparator implements Comparator<Plant> {

	private boolean descending;

	/**
	 * By default, assume sorting a list of plants by bloom time, descending(December->January).
	 */
	public BloomTimeComparator() {
		this.descending = true;
	}

	/**
	 * Sort a list of plants by bloom time and specify whether it is ascending or
	 * descending.
	 * 
	 * @param descending true for descending, false for ascending.
	 */
	public BloomTimeComparator(boolean descending) {
		this.descending = descending;
	}

	
	public int compare(Plant a, Plant b) {
		if (this.descending) {
			int aInd = -1;
			int bInd = -1;
			boolean[] aBloom = a.getBloomTime();
			boolean[] bBloom = b.getBloomTime();
			for (int i = 0; i < aBloom.length; i++) {
				if (aBloom[i] == true) {
					aInd = i;
					break;
				}
			}
			for (int i = 0; i < bBloom.length; i++) {
				if (bBloom[i] == true) {
					bInd = i;
					break;
				}
			}
			if (bInd == aInd) {
				return 0;
			}
			if (aInd == -1) {
				//By Default, Plants with not bloom data are ordered last;
				return 1;
			}
			else {
				if (bInd == -1) {
					//By Default, Plants with not bloom data are ordered last;
					return -1;
				}
				else {
					return aInd - bInd;
				}
			}
		}
		else {
			int aInd = -1;
			int bInd = -1;
			boolean[] aBloom = a.getBloomTime();
			boolean[] bBloom = b.getBloomTime();
			for (int i = 0; i < aBloom.length; i++) {
				if (aBloom[i] == true) {
					aInd = i;
					break;
				}
			}
			for (int i = 0; i < bBloom.length; i++) {
				if (bBloom[i] == true) {
					bInd = i;
					break;
				}
			}
			if (bInd == aInd) {
				return 0;
			}
			if (aInd == -1) {
				//By Default, Plants with not bloom data are ordered last;
				return 1;
			}
			else if (bInd == -1) {
				//By Default, Plants with not bloom data are ordered last;
				return -1;
			}
			else {
				return bInd - aInd;
			}
		}
	}
}
