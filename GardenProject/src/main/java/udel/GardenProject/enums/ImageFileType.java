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

	private String fileType = null;

	private ImageFileType(String type) {
		this.fileType = type;
	}

	public String getImageFileType() {
		return fileType;
	}

}
