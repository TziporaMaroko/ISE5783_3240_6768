/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;

/**
 * @author ester
 *
 */
class SphereTest {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
			Sphere sp=new Sphere(new Point(0,0,0),1);//fdffsadsf3###########just this???
			assertEquals(new Vector(0, 0, 1), sp.getNormal(new Point(0, 0, 1)), "");
	}

}
