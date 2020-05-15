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

	WINTER("Winter"), 
	SPRING("Spring"), 
	SUMMER("Summer"), 
	FALL("Fall"), 
	YEARROUND("Year Round");
	
	
	private String season = null;
	
	private Seasons(String s){
		season = s;
	}
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
	public static ArrayList<Seasons> getFilterSeason(boolean[] year) throws Exception{
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
			throw new Exception("Wrong year size in Filtering Seasons");
		}
		
		return filter;
	}
	
}

