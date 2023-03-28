package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * 
 * A plane is a flat surface that extends infinitely in all directions.
 */
public class Plane implements Geometry {
	private final Point q0; // A point on the plane
	private final Vector normal; // The plane's normal vector

	/**
	 * 
	 * Constructs a plane using three points on the plane.
	 * 
	 * @param p0 the first point on the plane
	 * @param p1 the second point on the plane
	 * @param p2 the third point on the plane
	 */
	public Plane(Point p0, Point p1, Point p2) {
		q0 = p0;
		normal = null; // Not yet initialized
	}

	/**
	 * 
	 * Constructs a plane using a point on the plane and a normal vector.
	 * 
	 * @param p0 the point on the plane
	 * @param v  the normal vector of the plane
	 */
	public Plane(Point p0, Vector v) {
		q0 = p0;
		normal = v.normalize(); // Normalizes the vector to make it a unit vector
	}

	/**
	 * 
	 * Gets the normal vector of the plane.
	 * 
	 * @return the normal vector of the plane
	 */
	public Vector getNormal() {
		return null; //Not yet implemented
	}

	/**
	 * 
	 * Gets the normal vector of the plane at the specified point.
	 * 
	 * @param p the point to get the normal vector at
	 * @return the normal vector of the plane
	 */
	@Override
	public Vector getNormal(Point p) {
// The normal of a point on a plane is the same as the normal of the plane itself
		return getNormal();
	}
}
