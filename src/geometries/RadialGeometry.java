package geometries;

/**

RadialGeometry is an abstract base class for all geometries that have a radius
*/
public abstract class RadialGeometry extends Geometry {
	protected final double radius;

	/**
	Constructor
	@param rad the radius of the geometry
	 */
	public RadialGeometry(double rad) {
		radius = rad;
	}
}
