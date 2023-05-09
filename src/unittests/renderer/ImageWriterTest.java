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
class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {

		ImageWriter image = new ImageWriter("test1Image", 800, 500);

		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0)
					image.writePixel(i, j, new Color(java.awt.Color.BLUE));
				else
					image.writePixel(i, j, new Color(java.awt.Color.PINK));
			}
		}
		image.writeToImage();
	}

}
