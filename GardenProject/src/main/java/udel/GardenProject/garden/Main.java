package udel.GardenProject.garden;

/**
 * Main entry point for the GardenProject application to bypass JavaFX errors.
 * <br><br>
 * 
 * In order to have a standalone "fat" jar with all JavaFX dependencies built
 * with it, this <b>needs</b> to be the structure.<br><br>
 * 
 * No end user wants to have to install JRE 11, OpenJFX 11, unzip the OpenJFX 11
 * zip, invoke the Java runner and add the module path of OpenJFX 11 and the
 * modules of OpenJFX 11 and the program--all in a terminal.<br><br>
 * 
 * With JavaFX being no longer included in future JDKs/JREs from 10 onward, you
 * have to build a project jar with OpenJFX 11. <b>But</b> you cannot do this
 * if the main class (as specified by the manifest file of a jar) extends 
 * <code>Application</code>.<br><br>
 * 
 * Why is this rule? Why does it throw an exception? God knows.<br><br>
 * 
 * I wasted 20 hours on this. Do not touch it.
 * 
 * @author Team 0
 * @see <a href="https://stackoverflow.com/a/57691362/13158722">What made me
 * finally see the light</a>
 */
public class Main {
	
	public static void main(String[] args) {
		Controller.main(args);
	}

}
