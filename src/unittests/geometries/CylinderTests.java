/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
/**
 * @author ester & tzipora 😎
 *
 */
class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		 // TC01: Test normal vector on side of cylinder
        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 5.0, 2.0);
        Vector expected = new Vector(0, 0, 1);
        assertEquals(expected, c.getNormal(new Point(1, 0, 5)), "Bad normal to cylinder side");

        c = new Cylinder(new Ray(new Point(1, 0, 1), new Vector(1, 0, 0)), 5.0, 2.0);
        // TC02: Test normal vector on top of cylinder
        expected = new Vector(0, 0, 1);
        assertEquals(expected, c.getNormal(new Point(3, 0, 0)), "Bad normal to cylinder top");

        // TC03: Test normal vector on bottom of cylinder
        expected = new Vector(0, 0, -1);
        assertEquals(expected, c.getNormal(new Point(1, 0, 2)), "Bad normal to cylinder bottom");

     // =============== Boundary Values Tests ==================
        // TC04: Test normal vector at the center of the top base
        Point p2 = new Point(0, 0, 10);
        Ray r2 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder c2 = new Cylinder(r2, 5.0, 10.0);
        assertEquals("Bad normal to cylinder", new Vector(0, 0, 1), c2.getNormal(p2));

        // TC05: Test normal vector at the center of the bottom base
        Point p3 = new Point(0, 0, 0);
        Ray r3 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder c3 = new Cylinder(r3, 5.0, 10.0);
        assertEquals("Bad normal to cylinder", new Vector(0, 0, -1), c3.getNormal(p3));
    }
}
