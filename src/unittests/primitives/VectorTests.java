/**
 * 
 */
package unittests.primitives;
import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Vector;

/**
 * @author ester
 *
 */
class VectorTests {

	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// ============ Equivalence Partitions Tests ==============	
		assertTrue((v1.add(new Vector(1, -2, -3)).equals(new Vector(2, 0, 0))),"ERROR: addVector() does not work correctly");
		assertTrue((v1.add(v2).equals(new Vector(-1, -2, -3))),"ERROR: Point - Point does not work correctly");
		assertTrue((v1.subtract(v2).equals(new Vector(3,6,9))),"ERROR: Point - Point does not work correctly");
		
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1),
		"subtract() for same vector does not throw an exception");
				 
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		// ============ Equivalence Partitions Tests ==============
		assertTrue((v1.scale(3).equals(new Vector(3,6,9))),"ERROR: scaleVector() does not work correctly");
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalArgumentException.class,()->v1.scale(0),"scale vector by 0 equals to (0,0,0)");
	
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		assertTrue(isZero(v1.dotProduct(v2)+28), "ERROR: dotProduct() wrong value");
		// =============== Boundary Values Tests ==================
		assertTrue(isZero(v1.dotProduct(v1)), "ERROR: dotProduct() for orthogonal vectors is not zero");	 
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	 @Test
	 public void testCrossProduct() {
	 // ============ Equivalence Partitions Tests ==============
	 Vector vr = v1.crossProduct(v2);
	 // TC01: Test that length of cross-product is proper (orthogonal vectors taken
	 // for simplicity)
	 assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
	 // TC02: Test cross-product result orthogonality to its operands
	 assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
	 assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
	 // =============== Boundary Values Tests ==================
	 // TC11: test zero vector from cross-productof co-lined vectors
	 assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
	 "crossProduct() for parallel vectors does not throw an exception");
	 }


	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v= v1.normalize();
		assertTrue(isZero(v.length()-1), "ERROR: the normalized vector is not a unit vector");
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v),
				"ERROR: the normalized vector is not parallel to the original one");
		assertThrows(IllegalArgumentException.class, () -> v1.dotProduct(v),
				"ERROR: the normalized vector is opposite to the original one");
	}

}
