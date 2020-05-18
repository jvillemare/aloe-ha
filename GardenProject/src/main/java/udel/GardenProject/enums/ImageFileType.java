package udel.GardenProject.enums;

/**
 * The mulptiple ways a user can save an image of their plot in the Download
 * screen
 * 
 * @author Team 0
 */
public enum ImageFileType {

	/**
	 * Portable Network Graphic image format.
	 */
	PNG("*.png"), 
	
	/**
	 * Abbreviated form of the Joint Photographic Expert Group image format.
	 */
	JPG("*.jpg"), 
	
	/**
	 * Joint Photographic Expert Group image format.
	 */
	JPEG("*.jpeg"), 
	
	/**
	 * Bitmap image format.
	 */
	BMP("*.bmp"), 
	
	/**
	 * Graphics Interchange Format image format.
	 */
	GIF("*.gif"), 
	
	/**
	 * Tagged Image Format File image format.
	 */
	TIF("*.tif");

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
