/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author ester & tzipora 😁
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;

	/**
	 * Constructs a SpotLight object with the specified ambient intensity, position,
	 * and direction.
	 * 
	 * @param iA  The ambient intensity of the light.
	 * @param pos The position of the light source.
	 * @param dir The direction of the spotlight.
	 */
	public SpotLight(Color iA, Point pos, Vector dir) {
		super(iA, pos);
		direction = dir.normalize();
	}

	/**
	 * Calculates the intensity of the light at a given point, taking into account
	 * the spotlight's direction.
	 * 
	 * @param p The point in the scene.
	 * @return The intensity of the light at the specified point.
	 */
	@Override
	public Color getIntensity(Point p) {
		Vector l = super.getL(p);
		if (l == null)
			return super.getIntensity();

		double directionDotL = Util.alignZero(direction.dotProduct(l));
		if (directionDotL <= 0)
			return Color.BLACK;

		return super.getIntensity(p).scale(directionDotL); // the denominator from the super!!
	}
	
	@Override
	public double getDistance(Point p) {
		return super.getDistance(p);
	}

}
