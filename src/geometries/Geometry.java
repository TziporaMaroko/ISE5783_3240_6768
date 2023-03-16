/**

The Geometry interface represents a geometric object that has a normal vector at each point.
*/
package geometries;
import primitives.*;

public interface Geometry {

	/**
	 * Returns the normal vector to the geometry object at a given point.
	 * @param p - the point on the geometry object for which to calculate the normal vector.
	 * @return a Vector representing the normal to the geometry object at the specified point.
	 */
	public Vector getNormal(Point p);
}
