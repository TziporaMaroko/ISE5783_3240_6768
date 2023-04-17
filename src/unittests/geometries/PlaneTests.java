/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Vector;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Triangle;

/**
 * @author ester & tzipora 😎
 *
 */
class PlaneTests {
	@Test
	void testConstructor() {
        // =============== Boundary Values Tests ==================
		//test two identical points
		assertThrows(IllegalArgumentException.class,()->new Plane(new Point(0,0,0),new Point(0,0,0),new Point(0,1,0)));
		//test three points in the same line
		assertThrows(IllegalArgumentException.class, () ->new Plane(new Point(1,0,0),new Point(2,0,0),new Point(3,0,0))); 
	}
	

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
	      // TC01: There is a simple single test here
		  Point p1=new Point(0, 0, 1), p2=new Point(1, 0, 0), p3=new Point(0, 1, 0);
	      Plane p= new Plane(p1,p2,p3);
	      // ensure there are no exceptions
	      assertDoesNotThrow(() -> p.getNormal(p1), "");
	      // generate the test result
	      Vector result = p.getNormal(p1);
	      // ensure |result| = 1
	      assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
	      // ensure the result is orthogonal to all the edge
	      assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
	                    "Plane's normal is not orthogonal to one of the edges");
	      assertTrue(isZero(result.dotProduct(p1.subtract(p3))),
                "Plane's normal is not orthogonal to one of the edges");
	      assertTrue(isZero(result.dotProduct(p2.subtract(p3))),
                "Plane's normal is not orthogonal to one of the edges");
		
	}

}
