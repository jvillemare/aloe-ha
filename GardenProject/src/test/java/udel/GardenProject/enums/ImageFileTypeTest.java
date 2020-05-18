package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageFileTypeTest {

	@Test
	public void test() {
		ImageFileType i = ImageFileType.BMP;
		assertTrue(i.getImageFileType().equals("*.bmp"));
	}

}
