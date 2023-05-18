/**

The Geometry interface represents a geometric object that has a normal vector at each point.
*/
package geometries;
import primitives.*;
import renderer.Camera;

public abstract class Geometry extends Intersectable{

	protected Color emission=Color.BLACK;
	/**
	 * Returns the normal vector to the geometry object at a given point.
	 * @param p  the point on the geometry object for which to calculate the normal vector.
	 * @return a Vector representing the normal to the geometry object at the specified point.
	 */
	public abstract Vector getNormal(Point p);
	public Color getEmission() {
		return this.emission;
	}
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
}
