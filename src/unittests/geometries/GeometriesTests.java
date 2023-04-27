/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author ester & tzipora 😎
 *
 */
class GeometriesTests extends Geometries {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		
		// ============ Equivalence Partitions Tests ==============
		Geometries collection= new Geometries();
		Sphere sphere = new Sphere(new Point(1, 0, 0), 1); 
		Triangle triangle = new Triangle(new Point(-4,0,0), new Point(0, 0, 5), new Point(0, -5, 0));
		Plane plane = new Plane (new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));
		collection.add(sphere, triangle, plane);
		//=====Some (but not all) shapes are cut=====//
		//triangle and plane cut
	    assertEquals( 2, collection.findIntersections(new Ray(new Point(-4, -3, 2), new Vector(9,5,-1))).size(),"wrong number of intersactions");
	    
		
	 
		
	    // =============== Boundary Values Tests ==================
	    //=====Empty body collection=====//
	    Geometries collectionEmpty= new Geometries();
		assertNull(collectionEmpty.findIntersections(new Ray(new Point(-4, -3, 2), new Vector(9,5,-1))),"An empty body collection must return null");
	    
	    
	    //=====No cut shape =====//
		assertNull(collection.findIntersections(new Ray(new Point(0, -8, 0), new Vector(-10,-1,0))),"No cut shape must return 0");
		
		
		//=====Only one shape is cut=====//
		//the plane cut
		assertEquals( 1, collection.findIntersections(new Ray(new Point(-0.8, -3, 1), new Vector(3.4,3,1.57))).size(),"wrong number of intersactions");

		
		//=====All shapes are cut=====//
		assertEquals(4, collection.findIntersections(new Ray(new Point(-4, -3, 0), new Vector(6,3,0.5))).size(),"wrong number of intersactions" );

	}

}
