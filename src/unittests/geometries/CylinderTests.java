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

        // TC02: Test normal vector on top of cylinder
        expected = new Vector(1, 0, 0);
        assertEquals(expected, c.getNormal(new Point(2, 1, 0)), "Bad normal to cylinder top");

        // TC03: Test normal vector on bottom of cylinder
        expected = new Vector(1, 0, 0);
        assertEquals(expected, c.getNormal(new Point(0, 1, 0)), "Bad normal to cylinder bottom");

     // =============== Boundary Values Tests ==================
        // TC04: Test normal vector at the center of the top base
        expected = new Vector(1, 0, 0);
        assertEquals(expected, c.getNormal(new Point(0, 0, 0)), "Bad normal to cylinder top center");

        // TC05: Test normal vector at the center of the bottom base
        expected = new Vector(1, 0, 0);
        assertEquals(expected, c.getNormal(new Point(2, 0, 0)), "Bad normal to cylinder bottom center");
    }
}
