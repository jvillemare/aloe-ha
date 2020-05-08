package udel.GardenProject.enums;

import java.util.ArrayList;

/**
 * General season descriptions, and combinations thereof.
 * Used in Questionnaire and Plant Info
 * 
 * @author Team 0
 */
public enum Seasons {

	WINTER("Winter"), 
	SPRING("Spring"), 
	SUMMER("Summer"), 
	FALL("Fall"), 
	YEARROUND("Yearround");
	
	
	private String season = null;
	
	private Seasons(String s){
		season = s;
	}
	public String getSeason() {
		return season;
	}
	
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
		}
		
		return filter;
	}
	
}
