/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

/**
 * @author ester & tzipora 😎
 *
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
	      // TC01: There is a simple single test here
		  Point p1=new Point(0, 0, 1), p2=new Point(1, 0, 0), p3=new Point(0, 1, 0);
	      Triangle t= new Triangle(p1,p2,p3);
	      // ensure there are no exceptions
	      assertDoesNotThrow(() -> t.getNormal(p1), "");
	      // generate the test result
	      Vector result = t.getNormal(p1);
	      // ensure |result| = 1
	      assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
	      // ensure the result is orthogonal to all the edge
	      assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
	                    "Triangle's normal is not orthogonal to one of the edges");
	      assertTrue(isZero(result.dotProduct(p1.subtract(p3))),
                  "Triangle's normal is not orthogonal to one of the edges");
	      assertTrue(isZero(result.dotProduct(p2.subtract(p3))),
                  "Triangle's normal is not orthogonal to one of the edges");
	}

}
