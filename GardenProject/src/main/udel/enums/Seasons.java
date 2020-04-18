package main.udel.enums;

/**
 * General season descriptions, and combinations thereof.
 * 
 * @author Team 0
 */
public enum Seasons {

	WINTER("Winter"), 
	SPRING("Spring"), 
	SUMMER("Summer"), 
	FALL("Fall"), 
	WINTER_SPRING("Winter-Spring"), 
	SPRING_SUMMER("Spring-Summer"), 
	SUMMER_FALL("Summer-Fall"), 
	YEARROUND("Yearround");
	
	
	private String season = null;
	
	private Seasons(String s){
		season = s;
	}
	public String getSeason() {
		return season;
	}
	
}
