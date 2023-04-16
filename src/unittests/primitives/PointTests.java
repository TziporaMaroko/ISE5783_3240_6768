/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * @author ester 
 *
 */
class PointTests {

	 Point p1 = new Point(1, 2, 3);
	 Point p2 = new Point(2,3,4);
	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		//regular subtract between two points
		assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)),"ERROR: subtract() wrong result");
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		// ============ Boundary values Tests ==============
		//regular add between point and vector
	    assertTrue((p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))),"ERROR: Point + Vector does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		//the squared distance between two points
		assertTrue(isZero(p1.distanceSquared(p2) - 3),"ERROR: distanceSquared() wrong value");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		// ============ Equivalence Partitions Tests ==============
		//the distance between two points
		assertTrue(isZero(p1.distance(p2) - 5),"ERROR: distance() wrong value");
	}

}
