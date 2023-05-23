/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author ester & tzipora 🙃
 *
 */
public interface LightSource {
	/**
	 * Returns the intensity of the light at a given point.
	 * 
	 * @param p The point in the scene.
	 * @return The intensity of the light at the specified point.
	 */
	public Color getIntensity(Point p);

	/**
	 * Returns the direction from the light source to a given point.
	 * 
	 * @param p The point in the scene.
	 * @return The direction vector from the light source to the specified point.
	 */
	public Vector getL(Point p);
	double getDistance(Point point);
}
