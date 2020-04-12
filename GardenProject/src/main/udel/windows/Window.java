package windows;

/**
 * A window that will be displayed to the user.
 * 
 * @author Team 0
 */
public abstract class Window {
	
	private String title;
	
	public Window(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title + "spoiler";
	};

}
