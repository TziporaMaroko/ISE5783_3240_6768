/**

The Geometry interface represents a geometric object that has a normal vector at each point.
*/
package geometries;

import primitives.*;
import renderer.Camera;

public abstract class Geometry extends Intersectable {

	protected Color emission = Color.BLACK;
	private Material material = new Material();

	/**
	 * Returns the normal vector to the geometry object at a given point.
	 * 
	 * @param p The point on the geometry object for which to calculate the normal
	 *          vector.
	 * @return A Vector representing the normal to the geometry object at the
	 *         specified point.
	 */
	public abstract Vector getNormal(Point p);

	/**
	 * Retrieves the emission color of the geometry object. The emission color
	 * represents the light emitted by the object.
	 * 
	 * @return The emission color of the geometry object.
	 */
	public Color getEmission() {
		return this.emission;
	}

	/**
	 * Sets the emission color of the geometry object. The emission color represents
	 * the light emitted by the object.
	 * 
	 * @param emission The emission color to set for the geometry object.
	 * @return The updated Geometry object.
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * Retrieves the material of the geometry object. The material defines the
	 * optical properties of the object, such as reflection and refraction
	 * coefficients.
	 * 
	 * @return The material of the geometry object.
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material of the geometry object. The material defines the optical
	 * properties of the object, such as reflection and refraction coefficients.
	 * 
	 * @param material The material to set for the geometry object.
	 * @return The updated Geometry object.
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
}