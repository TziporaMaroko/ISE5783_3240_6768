/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author tzipora and ester:)
 *
 */
class RayTests {

	 /**
	* Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	*/
	@Test
	public void testFindClosestPoint() {
	// =============== Boundary Values Tests ==================
	Ray ray = new Ray(new Point(0,0,1), new Vector(1,0,0));

	//An empty list
	List<Point>points = null;
	assertNull(ray.findClosestPoint(points),"empty list - must return null");
	
	Point p1 = new Point(1,0,0);
	Point p2 = new Point(2,0,0);
	Point p3 = new Point(3,0,0);

	//The first point is closest to the beginning of the foundation
	points = List.of(p1,p2,p3);
	assertEquals(p1, ray.findClosestPoint(points),"p1 must be returned");
	
	points = List.of(p2,p3,p1);
	//The last point is closest to the beginning of the foundation
	assertEquals(p1, ray.findClosestPoint(points),"p3 must be returned");

	
	// ============ Equivalence Partitions Tests ==============
	//A point in the middle of the list is closest to the beginning of the fund
	points = List.of(p2,p1,p3);
	assertEquals(p1, ray.findClosestPoint(points),"p2 must be returned");

	}

}
