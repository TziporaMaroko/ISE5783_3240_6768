/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author ester & tzipora 😎
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
		Point p= new Point (0,0,0);
		Ray r= new Ray(p,new Vector(1,0,0));
		Tube t=new Tube(r,5.0);
		assertEquals( "Bad normal to tube", new Vector(0, 0, 1), t.getNormal(new Point(1, 0, 5)));
        // =============== Boundary Values Tests ==================
		// TC02: When connecting the point to the top of the beam of the axis of the cylinder makes a right angle
		//with the axis - the point "is in front of the head of the ray"
		assertThrows(IllegalArgumentException.class, () ->t.getNormal(new Point(0, 1, 0)),"the point \"is in front of the head of the ray\""); 
	}
}
