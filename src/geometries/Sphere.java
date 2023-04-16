package geometries;

import primitives.*;

/**
 * A sphere is a three-dimensional shape that is perfectly round.
 */
public class Sphere extends RadialGeometry {
	private final Point center;

	/**
	 * 
	 * Constructor
	 * 
	 * @param p the center point of the sphere
	 * @param r the radius of the sphere
	 */
	public Sphere(Point p, double r) {
		super(r);
		center = p;
	}

	/**
	 * 
	 * Getter for center
	 * 
	 * @return the center point of the sphere
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * 
	 * Overrides the getNormal method from the RadialGeometry class
	 * 
	 * @param p the point on the surface of the sphere
	 * @return the normal vector of the sphere at point p
	 */
	@Override
	public Vector getNormal(Point p) {
		// The normal of a point on the surface of a sphere is a vector from the center of the sphere to that point
		return (p.subtract(center)).normalize();	
		}
}