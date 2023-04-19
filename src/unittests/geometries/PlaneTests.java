/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Ray;
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
	
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	public void testfindIntersections() {
		    Plane  plane = new Plane(new Point(0,5,0), new Point(-5,0,0), new Point(0,0,3));
		    // =============== Boundary Values Tests ==================
			
		    //Ray is parallel to the plane
			// TC01: the ray included in the plane
			Ray ray= new Ray(new Point(0,5,0), new Vector(-5,0,0));//the plane include this ray
			assertNull(plane.findIntersections(ray),"A included ray has zero intersection points");
			// TC02: the ray not included in the plane
			ray= new Ray(new Point(0,-5,0), new Vector(5,0,0));//the plane un included this ray
			assertNull(plane.findIntersections(ray),"An unincluded ray has zero intersection points" );
			
			//Ray is orthogonal to the plane
			// TC03: before the plane
			ray= new Ray(new Point(2,4,0), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertEquals(1, plane.findIntersections(ray).size(),"Ray is orthogonal to the plane and starts before the plane");
			// TC04: at the plane
			ray= new Ray(new Point(-5,0,0), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertNull( plane.findIntersections(ray),"Ray is orthogonal to the plane and starts at the plane");
			// TC05: after the plane
			ray= new Ray(new Point(-7,2,4), new Vector(-3,3,5));//the ray is orthogonal to the plane
			assertNull(plane.findIntersections(ray),"Ray is orthogonal to the plane and starts after the plane");
			
			//Ray is neither orthogonal nor parallel to and begins at the plane
			// TC06:
			ray= new Ray(new Point(-1,-1,0), new Vector(1,0,0));//the ray isn't orthogonal or parallel to the plane
			assertNull( plane.findIntersections(ray),"Ray is neither orthogonal nor parallel to and begins at reference point in the plane");
			
			//Ray is neither orthogonal nor parallel to the plane and begins in
			//the same point which appears as reference point in the plane
			// TC07:
			ray= new Ray(new Point(0,0,3), new Vector(-5,4,-3));//the ray isn't orthogonal or parallel to the plane but not intersects the plane
			assertNull( plane.findIntersections(ray),"Ray is neither orthogonal nor parallel to and begins at the plane");
			
			// ============ Equivalence Partitions Tests ================
			// TC08: The Ray must be neither orthogonal nor parallel to the plane
			//Ray does not intersect the plane
			ray= new Ray(new Point(1,2,0), new Vector(-3,-7,0));
			assertNull( plane.findIntersections(ray),"Ray is neither orthogonal nor parallel but doesnt intersects the plane");
			
			//&& TC09:
			// Ray intersects the plane
			// ray isn't orthogonal or parallel to the plane and intersects the plane
			ray= new Ray(new Point(4,3,0), new Vector(-5,3,0));
			assertEquals(1, plane.findIntersections(ray).size(),"Ray is neither orthogonal nor parallel and intersects the plane ");
	}

}
