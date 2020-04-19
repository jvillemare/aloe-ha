package main.udel.enums;

/**
 * General season descriptions, and combinations thereof.
 * Used in Questionnaire and Plant Info
 * 
 * @author Team 0
 */

/*
 * 
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
	
}
