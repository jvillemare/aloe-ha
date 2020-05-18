package udel.GardenProject.enums;

/**
 * The mulptiple ways a user can save an image of their plot in the Download
 * screen
 * 
 * @author Team 0
 *
 */
public enum ImageFileType {

	PNG("*.png"), JPG("*.jpg"), JPEG("*.jpeg"), BMP("*.bmp"), GIF("*.gif"), TIF("*.tif");

	/**
	 * File type regular expression as a string.
	 */
	private String fileType = null;
	
	/**
	 * Constructor.
	 * @param type	File type regular expression as a string.
	 */
	private ImageFileType(String type) {
		this.fileType = type;
	}

	/**
	 * Getter.
	 * @return	File type regular expression as a string.
	 */
	public String getImageFileType() {
		return fileType;
	}

}
