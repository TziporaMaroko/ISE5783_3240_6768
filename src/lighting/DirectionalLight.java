/**
 * 
 */
package lighting;

import primitives.*;


/**
 * @author ester
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction;

	public DirectionalLight(Color iA, Vector dir) {
		super(iA);
		direction = dir;
	}

	@Override
	public Color getIntensity(Point p) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}

}
