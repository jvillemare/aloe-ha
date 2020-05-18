package udel.GardenProject.enums;

import java.util.ArrayList;

/**
 * General season descriptions, and combinations thereof. Used in Questionnaire 
 * and Plant Info.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum Seasons {

	/**
	 * December to February.
	 */
	WINTER("Winter"), 
	
	/**
	 * March to May.
	 */
	SPRING("Spring"), 
	
	/**
	 * June to August.
	 */
	SUMMER("Summer"), 
	
	/**
	 * September to November.
	 */
	FALL("Fall"), 
	
	/**
	 * All the months of the year.
	 */
	YEARROUND("Year Round");
	
	/**
	 * Friendly name of the enum for the UI.
	 */
	private String season = null;
	
	/**
	 * Constructor.
	 * @param s	Friendly name of the enum for the UI.
	 */
	private Seasons(String s){
		season = s;
	}
	
	/**
	 * Getter.
	 * @return	Friendly name of the enum for the UI.
	 */
	public String getSeason() {
		return season;
	}
	
	/**
	 * Creates an ArrayList<Seasons> that contains all the seasons the given
	 * boolean year would bloom at.
	 * 
	 * @param year
	 * @return ArrayList<Seasons>
	 * @throws Exception 
	 */
	public static ArrayList<Seasons> getFilterSeason(boolean[] year){
		ArrayList<Seasons> filter = new ArrayList<Seasons>();
		int size = year.length;
		
		if(size == 12) {
			if(year[0] || year[1] || year[11]) {
				filter.add(WINTER);
			}
			
			if(year[2] || year[3] || year[4]) {
				filter.add(SPRING);
			}
			
			if(year[5] || year[6] || year[7]) {
				filter.add(SUMMER);
			}
			
			if(year[8] || year[9] || year[10]) {
				filter.add(FALL);
			}
		}else {
			filter.add(YEARROUND);
			
		}
		
		return filter;
	}
	
}

