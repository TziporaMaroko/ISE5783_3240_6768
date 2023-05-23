/**

The DirectionalLight class represents a directional light source in a lighting system.
It extends the Light class and implements the LightSource interface.
A directional light emits light uniformly in a specific direction and has no specific position.
@author ester and tzipora
*/
package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {
	private Vector direction;

	/**
	 * Constructs a DirectionalLight object with the specified ambient intensity and
	 * direction.
	 * 
	 * @param iA  The ambient intensity of the directional light.
	 * @param dir The direction of the light represented by a vector.
	 */
	public DirectionalLight(Color iA, Vector dir) {
		super(iA);
		direction = dir;
	}

	/**
	 * Retrieves the intensity of the directional light at a given point. Since a
	 * directional light has uniform intensity, the intensity returned is the same
	 * as the ambient intensity.
	 * 
	 * @param p The point at which to calculate the intensity.
	 * @return The intensity of the directional light at the specified point.
	 */
	@Override
	public Color getIntensity(Point p) {
		return getIntensity();
	}

	/**
	 * Retrieves the direction from the light source to a given point. Since a
	 * directional light has a fixed direction, the direction returned is the
	 * normalized direction vector.
	 * 
	 * @param p The point for which to calculate the direction.
	 * @return The direction from the light source to the specified point.
	 */
	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}
}
