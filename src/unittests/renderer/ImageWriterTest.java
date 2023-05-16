/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author tzipora and ester:)
 *
 */
/**
 * 
 * This class contains unit tests for the ImageWriter class.
 * 
 * It tests the functionality of the writeToImage() method.
 */
class ImageWriterTest {

	/**
	 * 
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 * 
	 * This test writes pixels to an image and verifies the output.
	 */
	@Test
	void testWriteToImage() {

// Create an instance of ImageWriter with dimensions 800x500
		ImageWriter image = new ImageWriter("test1Image", 800, 500);

// Write pixels to the image
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0)
					image.writePixel(i, j, new Color(java.awt.Color.BLUE));
				else
					image.writePixel(i, j, new Color(java.awt.Color.PINK));
			}
		}

// Save the image to a file
		image.writeToImage();
	}

}