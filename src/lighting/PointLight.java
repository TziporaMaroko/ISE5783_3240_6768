/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * @author ester
 *
 */
public class PointLight extends Light implements LightSource {
	private Point position;
	private double kC = 1, kL = 0, kQ = 0;

	/**
	 * Constructs a PointLight object with the specified ambient intensity and
	 * position.
	 * 
	 * @param iA  The ambient intensity of the light.
	 * @param pos The position of the light source.
	 */
	public PointLight(Color iA, Point pos) {
		super(iA);
		position = pos;
	}

	/**
	 * Calculates the intensity of the light at a given point.
	 * 
	 * @param p The point in the scene.
	 * @return The intensity of the light at the specified point.
	 */
	@Override
	public Color getIntensity(Point p) {
		double dis = Util.alignZero(p.distance(position));
		if (Util.isZero(dis))
			return super.getIntensity();

		double denominator = Util.alignZero(kC + kL * dis + kQ * dis * dis);
		return super.getIntensity().reduce(denominator);
	}

	/**
	 * Calculates the direction from the light source to a given point.
	 * 
	 * @param p The point in the scene.
	 * @return The direction vector from the light source to the specified point.
	 */
	@Override
	public Vector getL(Point p) {
		if (p.equals(position))
			return null;
		return p.subtract(position).normalize();
	}

	/**
	 * Sets the constant attenuation factor of the light.
	 * 
	 * @param kC The constant attenuation factor.
	 * @return The updated PointLight object.
	 */
	public PointLight setKC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Sets the linear attenuation factor of the light.
	 * 
	 * @param kL The linear attenuation factor.
	 * @return The updated PointLight object.
	 */
	public PointLight setKL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Sets the quadratic attenuation factor of the light.
	 * 
	 * @param kQ The quadratic attenuation factor.
	 * @return The updated PointLight object.
	 */
	public PointLight setKQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

}
